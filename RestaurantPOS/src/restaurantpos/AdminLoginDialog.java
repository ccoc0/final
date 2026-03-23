package restaurantpos;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * AdminLoginDialog - Login dialog for admin authentication
 * Username: admin
 * Password: admin
 * 
 * @author JollibeePOS
 */
public class AdminLoginDialog extends javax.swing.JDialog {

    // Jollibee Brand Colors
    private static final Color JOLLIBEE_RED = new Color(204, 0, 0);
    private static final Color JOLLIBEE_YELLOW = new Color(255, 204, 0);
    private static final Color JOLLIBEE_DARK_RED = new Color(153, 0, 0);

    private boolean authenticated = false;
    private DatabaseHandler dbHandler;

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        
        mainPanel = new javax.swing.JPanel();
        headerPanel = new javax.swing.JPanel();
        headerLabel = new javax.swing.JLabel();
        formPanel = new javax.swing.JPanel();
        usernameLabel = new javax.swing.JLabel();
        usernameField = new javax.swing.JTextField();
        passwordLabel = new javax.swing.JLabel();
        passwordField = new javax.swing.JPasswordField();
        buttonsPanel = new javax.swing.JPanel();
        loginButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Jollibee Admin Login");
        setModal(true);
        setResizable(false);
        setMinimumSize(new java.awt.Dimension(400, 320));

        mainPanel.setLayout(new java.awt.BorderLayout());
        mainPanel.setBackground(JOLLIBEE_RED);

        headerPanel.setBackground(JOLLIBEE_RED);
        headerPanel.setLayout(new java.awt.BorderLayout());
        headerPanel.setPreferredSize(new java.awt.Dimension(400, 80));

        headerLabel.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 24));
        headerLabel.setForeground(JOLLIBEE_YELLOW);
        headerLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        headerLabel.setText("JOLLIBEE ADMIN");
        headerLabel.setBorder(BorderFactory.createEmptyBorder(15, 10, 5, 10));
        headerPanel.add(headerLabel, java.awt.BorderLayout.NORTH);

        JLabel subLabel = new JLabel("Enter your credentials");
        subLabel.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 12));
        subLabel.setForeground(Color.WHITE);
        subLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        subLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 15, 10));
        headerPanel.add(subLabel, java.awt.BorderLayout.CENTER);

        mainPanel.add(headerPanel, java.awt.BorderLayout.NORTH);

        formPanel.setLayout(new java.awt.GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        java.awt.GridBagConstraints gbc = new java.awt.GridBagConstraints();
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gbc.insets = new java.awt.Insets(10, 10, 10, 10);

        usernameLabel.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 14));
        usernameLabel.setForeground(JOLLIBEE_DARK_RED);
        usernameLabel.setText("Username:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(usernameLabel, gbc);

        usernameField.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 14));
        usernameField.setBorder(BorderFactory.createLineBorder(JOLLIBEE_YELLOW, 2));
        usernameField.setPreferredSize(new java.awt.Dimension(200, 35));
        gbc.gridx = 1;
        gbc.gridy = 0;
        formPanel.add(usernameField, gbc);

        passwordLabel.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 14));
        passwordLabel.setForeground(JOLLIBEE_DARK_RED);
        passwordLabel.setText("Password:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(passwordLabel, gbc);

        passwordField.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 14));
        passwordField.setBorder(BorderFactory.createLineBorder(JOLLIBEE_YELLOW, 2));
        passwordField.setPreferredSize(new java.awt.Dimension(200, 35));
        gbc.gridx = 1;
        gbc.gridy = 1;
        formPanel.add(passwordField, gbc);

        mainPanel.add(formPanel, java.awt.BorderLayout.CENTER);

        buttonsPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 20, 15));
        buttonsPanel.setBackground(Color.WHITE);

        loginButton.setText("Login");
        loginButton.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 14));
        loginButton.setBackground(JOLLIBEE_YELLOW);
        loginButton.setForeground(JOLLIBEE_DARK_RED);
        loginButton.setPreferredSize(new java.awt.Dimension(100, 35));
        loginButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        loginButton.setFocusPainted(false);
        loginButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });
        buttonsPanel.add(loginButton);

        cancelButton.setText("Cancel");
        cancelButton.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 14));
        cancelButton.setBackground(Color.GRAY);
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setPreferredSize(new java.awt.Dimension(100, 35));
        cancelButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        cancelButton.setFocusPainted(false);
        cancelButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });
        buttonsPanel.add(cancelButton);

        mainPanel.add(buttonsPanel, java.awt.BorderLayout.SOUTH);

        add(mainPanel);

        pack();
        setLocationRelativeTo(getParent());
        getRootPane().setDefaultButton(loginButton);
    }// </editor-fold>//GEN-END:initComponents

    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonActionPerformed
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());
        
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Please enter both username and password!",
                "Login Error",
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if (dbHandler.authenticateAdmin(username, password)) {
            authenticated = true;
            dispose();
        } else {
            JOptionPane.showMessageDialog(this,
                "Invalid username or password!\n\nHint: admin / admin",
                "Login Failed",
                JOptionPane.ERROR_MESSAGE);
            passwordField.setText("");
            passwordField.requestFocus();
        }
    }//GEN-LAST:event_loginButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        authenticated = false;
        dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel mainPanel;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JLabel headerLabel;
    private javax.swing.JPanel formPanel;
    private javax.swing.JLabel usernameLabel;
    private javax.swing.JTextField usernameField;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JPanel buttonsPanel;
    private javax.swing.JButton loginButton;
    private javax.swing.JButton cancelButton;
    // End of variables declaration//GEN-END:variables

    public AdminLoginDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        dbHandler = DatabaseHandler.getInstance();
        initComponents();
    }
    
    public boolean isAuthenticated() {
        return authenticated;
    }
}
