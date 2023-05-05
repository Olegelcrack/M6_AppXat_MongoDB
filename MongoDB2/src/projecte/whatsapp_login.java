/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projecte;

/**
 *
 * @author DAM
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import org.bson.Document;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import java.text.SimpleDateFormat;
import static com.mongodb.client.model.Filters.*;
import static mongodb2.MongoDB2.collection;
import static mongodb2.MongoDB2.database;
import static mongodb2.MongoDB2.mongoClient;

public class whatsapp_login extends JFrame {
    
    public whatsapp_login() {
    // Configurar la interfaz gráfica
    this.setLayout(new GridLayout(3, 2));
    this.setSize(300, 150);
    this.setTitle("WhatsApp Login");

    // Agregar los campos de usuario y contraseña
    JTextField userField = new JTextField();
    JPasswordField passwordField = new JPasswordField();

    this.add(new JLabel("Usuari:"));
    this.add(userField);
    this.add(new JLabel("Contrassenya:"));
    this.add(passwordField);

    // Agregar los botones de login y cancelar
    JButton loginButton = new JButton("Login");
    JButton cancelButton = new JButton("Cancel");
    JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
    buttonPanel.add(loginButton);
    buttonPanel.add(cancelButton);
    this.add(buttonPanel);

    // Configurar el botón de login
    loginButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            String user = userField.getText();
            char[] password = passwordField.getPassword();

            // Verificar si el usuario y la contraseña existen en la colección "usuarios"
            System.out.println(collection);
            System.out.println(collection);
            Document userDoc = collection.find(Filters.and(Filters.eq("usuari", user), Filters.eq("contrassenya", password))).first();
            System.out.println(userDoc);
            if (userDoc != null) {
                // Si el usuario y la contraseña son correctos, abrir el chat
                new whatsapp();
                dispose(); // Cerrar la pantalla de login
            } else {
                // Si el usuario y la contraseña no son correctos, mostrar un mensaje de error
                JOptionPane.showMessageDialog(null, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    });

    // Configurar el botón de cancelar
    cancelButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            dispose(); // Cerrar la pantalla de login
        }
    });

    // Configurar la ventana
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);
}
    
public static void main(String[] args) {
    // Conectar con MongoDB
        mongoClient = new MongoClient("localhost", 27017);
        database = mongoClient.getDatabase("whatsapp");
        collection = database.getCollection("usuaris");

    // Abrir la pantalla de login
    new whatsapp_login();
}
}
