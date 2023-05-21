package bean_photo;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class UploadPhoto extends JPanel {
    private JButton uploadButton;
    private byte[] selectedFileBytes;
    private ActionListener actionListener;

    public UploadPhoto() {
        setLayout(new BorderLayout());
        
        uploadButton = new JButton("Seleccionar foto");
        uploadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Arxius de Imatge", "png", "jpg");
                fileChooser.setFileFilter(filter);

                int result = fileChooser.showOpenDialog(UploadPhoto.this);

                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    selectedFileBytes = readFileBytes(selectedFile);
                    
                    if (actionListener != null) {
                        actionListener.actionPerformed(e);
                    }
                }
            }
        });

        add(uploadButton);
    }

    public byte[] getFileBytes() {
        return selectedFileBytes;
    }

    public void addActionListener(ActionListener listener) {
        actionListener = listener;
    }

    private byte[] readFileBytes(File file) {
        try {
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                bos.write(buffer, 0, bytesRead);
            }

            fis.close();
            bos.close();

            return bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}