/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package login;

/**
 *
 * @author Lam
 */
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import client.MainFrame;
import javax.swing.JFrame;
import tags.Encode;
import tags.Tags;

public class LoginFrame extends javax.swing.JFrame implements ActionListener {

    private static String NAME_FAILED = "THIS NAME CONTAINS INVALID CHARACTER. PLEASE TRY AGAIN";
    private static String NAME_EXSIST = "THIS NAME IS ALREADY USED. PLEASE TRY AGAIN";
    private static String SERVER_NOT_START = "TURN ON SERVER BEFORE START";

    private Pattern checkName = Pattern.compile("[_a-zA-Z][_a-zA-Z0-9]*");
    private JPanel contentPane;
    private JTextField txtIP;
    private JTextField txtPort;
    private JTextField txtUserName;
    int port;
    String IP, userName;
    JButton btnConnectServer;
    String file = System.getProperty("user.dir") + "\\Server.txt";
    List<String> listServer = new ArrayList<>();

    /**
     * Creates new form LoginFrame
     */
    public LoginFrame() {
        initComponents();
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 680, 504);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Login Form");
        lblNewLabel.setForeground(Color.BLACK);
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 32));
        lblNewLabel.setBounds(237, 0, 352, 49);
        contentPane.add(lblNewLabel);

        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(new CompoundBorder(null, UIManager.getBorder("List.noFocusBorder")),
                "Setting ", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
        panel.setBackground(Color.WHITE);
        panel.setBounds(53, 91, 581, 280);
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel lblNewLabel_1 = new JLabel("IP Address Server:");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel_1.setBounds(26, 32, 136, 37);
        panel.add(lblNewLabel_1);

        txtIP = new JTextField();
        txtIP.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtIP.setBounds(237, 41, 277, 37);
        panel.add(txtIP);
        txtIP.setColumns(10);
        try {
            txtIP.setText(Inet4Address.getLocalHost().getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        JLabel lblNewLabel_2 = new JLabel("Port:");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel_2.setBounds(26, 114, 45, 13);
        panel.add(lblNewLabel_2);

        txtPort = new JTextField();
        txtPort.setText("8080");
        txtPort.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtPort.setBounds(237, 102, 277, 37);
        panel.add(txtPort);
        txtPort.setColumns(10);

        JComboBox comboBox = new JComboBox();
        comboBox.setForeground(Color.WHITE);
        // Read Data
        String[] data = null;
        try {
            data = readFileServer();
        } catch (FileNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        if (data == null) {
            comboBox.setVisible(false);
        } else {
            comboBox.setModel(new DefaultComboBoxModel(data));
            // Handle event
            comboBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JComboBox cb = (JComboBox) e.getSource();
                    String ss = (String) cb.getSelectedItem();
                    String[] s = ss.split(" ");
                    updateServer(s[0], s[1]);
                }
            });
        }
        comboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
        comboBox.setBounds(237, 150, 277, 37);
        panel.add(comboBox);

        JLabel lblNewLabel_3 = new JLabel("Your Name");
        lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel_3.setBounds(26, 213, 84, 37);
        panel.add(lblNewLabel_3);

        txtUserName = new JTextField();
        txtUserName.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtUserName.setBounds(237, 213, 277, 37);
        panel.add(txtUserName);
        txtUserName.setColumns(10);

        btnConnectServer = new JButton("Connect Server");
        btnConnectServer.setBounds(237, 402, 174, 38);
        btnConnectServer.addActionListener(this);
        contentPane.add(btnConnectServer);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LoginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    LoginFrame frame = new LoginFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    void updateServer(String IP, String port) {
        txtIP.setText(IP);
        txtPort.setText(port);
    }

    String[] readFileServer() throws FileNotFoundException {
        // Read file
        System.out.println(file);
        Scanner scanner = new Scanner(new File(file));
        while (scanner.hasNext()) {
            String server = scanner.nextLine();
            System.out.println(server + "-" + port);
            listServer.add(server);
        }
        scanner.close();
        String[] array = listServer.toArray(new String[0]);
        return array;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

        if (e.getSource() == btnConnectServer) {
            userName = txtUserName.getText();
            IP = txtIP.getText();

            // must edit here
            if (checkName.matcher(userName).matches() && !IP.equals("")) {
                try {
                    // Login
                    Random rd = new Random();
                    int portPeer = 10000 + rd.nextInt() % 1000;
                    InetAddress ipServer = InetAddress.getByName(IP);
                    int portServer = Integer.parseInt(txtPort.getText());
                    Socket socketClient = new Socket(ipServer, portServer);

                    String msg = Encode.getCreateAccount(userName, Integer.toString(portPeer));
                    ObjectOutputStream serverOutputStream = new ObjectOutputStream(socketClient.getOutputStream());
                    serverOutputStream.writeObject(msg);
                    serverOutputStream.flush();
                    ObjectInputStream serverInputStream = new ObjectInputStream(socketClient.getInputStream());
                    msg = (String) serverInputStream.readObject();

                    socketClient.close();
                    if (msg.equals(Tags.SESSION_DENY_TAG)) {
                        JOptionPane.showMessageDialog(this, NAME_EXSIST, "Login Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    System.out.println("Port Server Login: " + portServer);
                    new MainFrame(IP, portPeer, userName, msg, portServer);
                    this.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, SERVER_NOT_START, "Login Error", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(this, NAME_FAILED, "Login Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
