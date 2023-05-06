/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projecte;


import javax.swing.*;
import javax.swing.Timer;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import org.bson.Document;
import com.mongodb.MongoClient;
import com.toedter.calendar.JCalendar;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.*;
import java.text.SimpleDateFormat;
import static mongodb2.MongoDB2.collection;

public class whatsapp extends JFrame {
    private JButton sendButton;
    private JTextField messageField;
    private JTextField userField;
    private JList<String> messageList;
    private DefaultListModel<String> messageListModel;
    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> collection;
    private MongoCollection<Document> collection2;
    private Timer timer;
    private SimpleDateFormat dateFormat;
    private boolean loggedIn = false;
    
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JFrame loginFrame;
    public whatsapp() {
        // Configurar la interfaz gráfica
        //showLoginWindow();
        this.setLayout(new BorderLayout());
        this.setTitle("WhatsApp Chat");
        this.setVisible(true);
        WhatsappView wv = new WhatsappView();
        // Panel contenedor con ancho máximo
        JPanel container = new JPanel();
        container.setLayout(new BorderLayout());
        container.setMaximumSize(new Dimension(800, 400));
        container.setPreferredSize(new Dimension(800, 400));
        // Panel para el chat
        JPanel chatPanel = new JPanel(new BorderLayout());
        messageListModel = new DefaultListModel<String>();
        messageList = new JList<String>(messageListModel);
        chatPanel.add(new JScrollPane(messageList), BorderLayout.CENTER);
        chatPanel.setMaximumSize(new Dimension(400, 400));
        chatPanel.setPreferredSize(new Dimension(400, 400));
        chatPanel.setBorder(BorderFactory.createEmptyBorder(0, 50, 50, 50));
        
        // Panel para el calendario
        JPanel calendarPanel = new JPanel(new BorderLayout());
        JCalendar calendar = new JCalendar();
        calendarPanel.add(calendar, BorderLayout.CENTER);
        calendarPanel.setMaximumSize(new Dimension(200, 200));
        calendarPanel.setPreferredSize(new Dimension(200, 200));
        calendarPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 100, 50));
        
        // Agregar paneles al contenedor
        container.add(chatPanel, BorderLayout.CENTER);
        container.add(calendarPanel, BorderLayout.EAST);

        // Panel para el campo de entrada y botón de enviar
        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.setPreferredSize(new Dimension(200, 50));
        messageField = new JTextField(20);
        sendButton = new JButton("Enviar");
        inputPanel.add(new JLabel("Mensaje:"));
        inputPanel.add(messageField);
        inputPanel.add(sendButton);
        inputPanel.setMaximumSize(new Dimension(200, 50));
        inputPanel.setPreferredSize(new Dimension(200, 50));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(0,50, 0, 300));

        // Agregar panel contenedor y panel de entrada al frame
        this.add(container, BorderLayout.CENTER);
        this.add(inputPanel, BorderLayout.SOUTH);
        this.pack();
        
                // Conectar con MongoDB
        mongoClient = new MongoClient("localhost", 27017);
        database = mongoClient.getDatabase("whatsapp");
        collection = database.getCollection("missatges");
        collection2 = database.getCollection("usuaris");
        
        // Configurar el botón de enviar
        sendButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String user = usernameField.getText();
                String message = messageField.getText();
                Document doc = new Document("user", user)
                                .append("missatge", message)
                                .append("hora", new Date());
                collection.insertOne(doc);
                messageListModel.addElement(user + ": " + message);
                messageField.setText("");
            }
        });

        // Recuperar los mensajes existentes de MongoDB
        FindIterable<Document> messages = collection.find();
        dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        for (Document message : messages) {
            String user = message.getString("user");
            String text = message.getString("missatge");
            String dateStr = dateFormat.format(message.getDate("hora"));
            messageListModel.addElement(user + ": " + text + " (" + dateStr + ")");
        }

        // Configurar el timer para recargar la información del chat cada segundo
        timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                messageListModel.clear();
                FindIterable<Document> messages = collection.find();
                for (Document message : messages) {
                    String user = message.getString("user");
                    String text = message.getString("missatge");
                    String dateStr = dateFormat.format(message.getDate("hora"));
                    messageListModel.addElement(user + ": " + text + " (" + dateStr + ")");
                }
            }
        });
        timer.start();

        // Configurar la ventana
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    private void showLoginWindow() {
        loginFrame = new JFrame("Login");
        loginFrame.setLayout(new BorderLayout());
        loginFrame.setSize(300, 150);
        
        JPanel inputPanel = new JPanel(new GridLayout(2, 2));
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        loginButton = new JButton("Login");
        inputPanel.add(new JLabel("Username:"));
        inputPanel.add(usernameField);
        inputPanel.add(new JLabel("Password:"));
        inputPanel.add(passwordField);
        
        loginFrame.add(inputPanel, BorderLayout.CENTER);
        loginFrame.add(loginButton, BorderLayout.SOUTH);
        
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String user = usernameField.getText();
                char[] password = passwordField.getPassword();
                Document filtro = new Document("usuari", user).append("contrassenya", new String(password));
                Document userDoc = collection2.find(filtro).first();
                
                if (userDoc != null) {
                    // Si el usuario y la contraseña son correctos, abrir el chat
                    loginFrame.dispose();
                    setVisible(true); // Mostrar la ventana principal "whatsapp"
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setVisible(true);
    }

    public static void main(String[] args) {
        new  whatsapp();
    }
}