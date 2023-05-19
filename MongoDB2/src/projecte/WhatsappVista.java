/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projecte;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.bson.Document;

public class WhatsappVista extends JFrame {

    private DefaultListModel<String> messageListModel;
    private JList<String> messageList;
    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> collection;
    public MongoCollection<Document> collection2;
    private SimpleDateFormat dateFormat;
    private SimpleDateFormat horaFormat;
    private Timer timer;
    private BasicDBObject query = new BasicDBObject();
    private String usuariEl;
    private String missatgeEl;
    private String horaEl;
    private String avui;
    private String usuari;
    private String xat;
    private Date selectedDate;
    private long missatges_antics;
    private boolean isAdmin;
    private ArrayList<String> xats;
    private Document filtrouser;
    public WhatsappVista(String usuari, String xat, boolean isAdmin, Document filtro) {
        initComponents();
        setTitle("Xat");
        this.usuari = usuari;
        this.isAdmin = isAdmin;
        this.xat = xat;
        this.xats = xats;
        this.filtrouser = filtro;
        
        mongoClient = MongoClients.create("mongodb://localhost/27017");
        database = mongoClient.getDatabase("whatsapp");
        collection = database.getCollection("missatges");
        collection2 = database.getCollection("usuaris");

        messageListModel = new DefaultListModel<String>();
        messageList = new JList<String>(messageListModel);
        jScrollPanel.setViewportView(messageList);
        
        horaFormat = new SimpleDateFormat("HH:mm:ss");
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date startOfDay = calendar.getTime();
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        Date endOfDay = calendar.getTime();


        // Añadir el filtro a la consulta existente
        query.put("hora", new BasicDBObject("$gte", startOfDay).append("$lte", endOfDay));
        query.put("xat", new Document("$eq", xat));
        FindIterable<Document> messages = collection.find(query);
        for (Document message : messages) {
            String user = message.getString("user");
            String text = message.getString("missatge");
            String hora = horaFormat.format(message.getDate("hora"));
            avui = dateFormat.format(message.getDate("hora"));
            messageListModel.addElement(user + ": " + text + " (" + hora + ")");
            
        }
        
        String formattedDate = dateFormat.format(new Date());
        dia.setText(formattedDate);
        nomxat.setText(xat);
        
        // Configurar el timer para recargar la información del chat cada segundo
        
        timer = new Timer(100, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(missatge.getText().isEmpty()){
                    sendButton.setEnabled(false);
                }else{
                    sendButton.setEnabled(true);
                }
                if(selectedDate == null){
                    selectedDate = new Date();
                }
                
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(selectedDate);
                calendar.set(Calendar.HOUR_OF_DAY, 0);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                Date startOfDay = calendar.getTime();
                calendar.set(Calendar.HOUR_OF_DAY, 23);
                calendar.set(Calendar.MINUTE, 59);
                calendar.set(Calendar.SECOND, 59);
                Date endOfDay = calendar.getTime();
                // Añadir el filtro a la consulta existente
                query.put("hora", new BasicDBObject("$gte", startOfDay).append("$lte", endOfDay));
                query.put("xat", new Document("$eq", xat));
                FindIterable<Document> messages = collection.find(query);
                long count = collection.countDocuments(query);
                if (count!=missatges_antics){
                    messageListModel.clear();
                    for (Document message : messages) {
                        String user = message.getString("user");
                        String text = message.getString("missatge");
                        String dateStr = horaFormat.format(message.getDate("hora"));
                        avui = dateFormat.format(message.getDate("hora"));
                        messageListModel.addElement(user + ": " + text + " (" + dateStr + ")");
                    }
                }
                missatges_antics = count;
            }
        });
        timer.start();
        
        messageList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int index = messageList.getSelectedIndex();
                    if (index != -1) {
                        String selectedMessage = messageList.getModel().getElementAt(index);
                        String[] parts = selectedMessage.split(":");

                        if (parts.length > 1) {
                            usuariEl = parts[0];
                            missatgeEl = parts[1].substring(0, parts[1].indexOf("(")).trim();
                            horaEl = selectedMessage.substring(selectedMessage.lastIndexOf("(") + 1, selectedMessage.lastIndexOf(")"));
                        }
                    }
                }
            }
        });
        
        
        missatge.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER && !missatge.getText().isEmpty()) {
                    String user = usuari;
                    String message = missatge.getText();
                    String dateStr = horaFormat.format(new Date());
                    Document doc = new Document("user", user)
                                    .append("missatge", message)
                                    .append("hora", new Date())
                                    .append("xat", nomxat.getText());
                    collection.insertOne(doc);
                    if(selectedDate != new Date()){
                        
                    }else{
                        messageListModel.addElement(user + ": " + message + " (" + dateStr + ")");
                    }
                    missatge.setText("");
                }
            }
        });
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sendButton = new javax.swing.JButton();
        missatge = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jCalendar1 = new com.toedter.calendar.JCalendar();
        chatPanel = new javax.swing.JPanel();
        jScrollPanel = new javax.swing.JScrollPane();
        titol = new javax.swing.JLabel();
        dia = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        sortirConv = new javax.swing.JButton();
        logOut = new javax.swing.JButton();
        nomxat = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        sendButton.setText("Enviar");
        sendButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendButtonActionPerformed(evt);
            }
        });

        missatge.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                missatgeActionPerformed(evt);
            }
        });

        jLabel2.setText("Missatge: ");

        jCalendar1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jCalendar1PropertyChange(evt);
            }
        });

        javax.swing.GroupLayout chatPanelLayout = new javax.swing.GroupLayout(chatPanel);
        chatPanel.setLayout(chatPanelLayout);
        chatPanelLayout.setHorizontalGroup(
            chatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 487, Short.MAX_VALUE)
        );
        chatPanelLayout.setVerticalGroup(
            chatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(chatPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        titol.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        titol.setText("XAT");

        dia.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N

        jButton1.setForeground(new java.awt.Color(255, 0, 0));
        jButton1.setText("Esborrar Missatge");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        sortirConv.setText("Sortir de la Conversació");
        sortirConv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sortirConvActionPerformed(evt);
            }
        });

        logOut.setForeground(new java.awt.Color(255, 51, 0));
        logOut.setText("Log Out");
        logOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logOutActionPerformed(evt);
            }
        });

        nomxat.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(titol, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nomxat, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dia, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(24, 24, 24)
                                .addComponent(missatge, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(sendButton))
                            .addComponent(chatPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jCalendar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(sortirConv, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(logOut, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(28, 28, 28))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(titol, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(dia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nomxat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jCalendar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(sortirConv)
                        .addGap(18, 18, 18)
                        .addComponent(logOut))
                    .addComponent(chatPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sendButton)
                    .addComponent(missatge, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void missatgeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_missatgeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_missatgeActionPerformed

    private void sendButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendButtonActionPerformed
        if(missatge.getText().isEmpty())
            return;
        
        String user = usuari;
        String message = missatge.getText();
        String dateStr = horaFormat.format(new Date());
        Document doc = new Document("user", user)
                        .append("missatge", message)
                        .append("hora", new Date())
                        .append("xat", nomxat.getText());
        collection.insertOne(doc);
        if(selectedDate != new Date()){

        }else{
            messageListModel.addElement(user + ": " + message + " (" + dateStr + ")");
        }
        missatge.setText("");
        
    }//GEN-LAST:event_sendButtonActionPerformed

    private void jCalendar1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jCalendar1PropertyChange
       
        if ("calendar".equals(evt.getPropertyName())) {
            selectedDate = jCalendar1.getCalendar().getTime();
            // Usa la fecha seleccionada en lo que necesites hacer a continuación
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            
            String dateStr = dateFormat.format(selectedDate);
            dia.setText(dateStr);
        
        
            // Crear una consulta para buscar los mensajes con la fecha seleccionada
            
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(selectedDate);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            Date startOfDay = calendar.getTime();
            calendar.set(Calendar.HOUR_OF_DAY, 23);
            calendar.set(Calendar.MINUTE, 59);
            calendar.set(Calendar.SECOND, 59);
            Date endOfDay = calendar.getTime();
            

            // Añadir el filtro a la consulta existente
            query.put("hora", new BasicDBObject("$gte", startOfDay).append("$lte", endOfDay));
            query.put("xat", new Document("$eq", xat));


            // Obtener los mensajes que coinciden con la consulta
            FindIterable<Document> messages = collection.find(query);

            // Añadir los mensajes al modelo de la lista
            messageListModel.clear();
            for (Document message : messages) {
                String user = message.getString("user");
                String text = message.getString("missatge");
                String messageDateStr = horaFormat.format(message.getDate("hora"));
                messageListModel.addElement(user + ": " + text + " (" + messageDateStr + ")");
            }
        }
    }//GEN-LAST:event_jCalendar1PropertyChange

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        if(usuariEl == null || usuariEl == "")
            JOptionPane.showMessageDialog(null, "No has seleccionat cap missatge", "Error", JOptionPane.ERROR_MESSAGE);
        else{
            if(usuariEl.equals(usuari)){
                String diaMissatgeString = avui +" "+ horaEl;
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                try {
                    Date diaMissatge = sdf.parse(diaMissatgeString);
                    BasicDBObject query = new BasicDBObject();
                    query.put("user", usuariEl);
                    query.put("missatge", missatgeEl);
                    query.put("hora", new BasicDBObject("$gte", diaMissatge)); // comparar la horaMongoDB con una consulta $lte (menor o igual)
                    collection.deleteOne(query);

                    JOptionPane.showMessageDialog(null, "Missatge eliminat amb èxit", "Informació", JOptionPane.INFORMATION_MESSAGE);
                    usuariEl = "";
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }else{
                JOptionPane.showMessageDialog(null, "No Pots Borrar Missatges de Altres Persones", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        
    }//GEN-LAST:event_jButton1MouseClicked

    private void sortirConvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sortirConvActionPerformed
        
        timer.stop();
        dispose();
        new chats(usuari, isAdmin,filtrouser).setVisible(true);
        
    }//GEN-LAST:event_sortirConvActionPerformed

    private void logOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logOutActionPerformed
        setVisible(false);
        new login().setVisible(true);
    }//GEN-LAST:event_logOutActionPerformed
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         fd* For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(WhatsappVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(WhatsappVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(WhatsappVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(WhatsappVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new login().setVisible(true);
            }
        });
    }
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel chatPanel;
    private javax.swing.JLabel dia;
    private javax.swing.JButton jButton1;
    private com.toedter.calendar.JCalendar jCalendar1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPanel;
    private javax.swing.JButton logOut;
    private javax.swing.JTextField missatge;
    private javax.swing.JLabel nomxat;
    private javax.swing.JButton sendButton;
    private javax.swing.JButton sortirConv;
    private javax.swing.JLabel titol;
    // End of variables declaration//GEN-END:variables
}
