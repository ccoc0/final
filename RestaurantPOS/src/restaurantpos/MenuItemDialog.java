package restaurantpos;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * MenuItemDialog - Dialog for adding and editing menu items
 *
 * @author JollibeePOS
 */
public class MenuItemDialog extends javax.swing.JDialog {

    // Jollibee Brand Colors
    private static final Color JOLLIBEE_RED = new Color(204, 0, 0);
    private static final Color JOLLIBEE_YELLOW = new Color(255, 204, 0);
    private static final Color JOLLIBEE_DARK_RED = new Color(153, 0, 0);
    private static final Color JOLLIBEE_LIGHT_YELLOW = new Color(255, 238, 153);

    private MenuItem menuItem;
    private boolean saved = false;
    private String selectedImagePath;
    private String[] categories = {"Chicken", "Burgers", "Pasta", "Rice Meals", "Drinks", "Desserts", "Sides"};

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        headerPanel = new javax.swing.JPanel();
        headerLabel = new javax.swing.JLabel();
        formPanel = new javax.swing.JPanel();
        nameLabel = new javax.swing.JLabel();
        nameField = new javax.swing.JTextField();
        categoryLabel = new javax.swing.JLabel();
        categoryCombo = new javax.swing.JComboBox<>();
        priceLabel = new javax.swing.JLabel();
        priceField = new javax.swing.JTextField();
        descriptionLabel = new javax.swing.JLabel();
        descriptionScrollPane = new javax.swing.JScrollPane();
        descriptionArea = new javax.swing.JTextArea();
        imageLabel = new javax.swing.JLabel();
        imagePathField = new javax.swing.JTextField();
        browseImageButton = new javax.swing.JButton();
        imagePreviewLabel = new javax.swing.JLabel();
        buttonsPanel = new javax.swing.JPanel();
        saveButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setModal(true);
        setResizable(false);
        setMinimumSize(new java.awt.Dimension(500, 480));

        mainPanel.setLayout(new java.awt.BorderLayout());
        mainPanel.setBackground(JOLLIBEE_RED);

        // Header - Jollibee Red
        headerPanel.setBackground(JOLLIBEE_RED);
        headerPanel.setLayout(new java.awt.BorderLayout());
        headerPanel.setPreferredSize(new java.awt.Dimension(480, 60));

        String title = (menuItem == null) ? "Add New Menu Item" : "Edit Menu Item";
        headerLabel.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 20));
        headerLabel.setForeground(JOLLIBEE_YELLOW);
        headerLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        headerLabel.setText(title);
        headerLabel.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));
        headerPanel.add(headerLabel, java.awt.BorderLayout.CENTER);

        mainPanel.add(headerPanel, java.awt.BorderLayout.NORTH);

        // Form Panel - White
        formPanel.setLayout(new java.awt.GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createEmptyBorder(15, 30, 15, 30));

        java.awt.GridBagConstraints gbc = new java.awt.GridBagConstraints();
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gbc.insets = new java.awt.Insets(8, 8, 8, 8);

        nameLabel.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 13));
        nameLabel.setForeground(JOLLIBEE_DARK_RED);
        nameLabel.setText("Item Name:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        formPanel.add(nameLabel, gbc);

        nameField.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 13));
        nameField.setBorder(BorderFactory.createLineBorder(JOLLIBEE_YELLOW, 2));
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1;
        formPanel.add(nameField, gbc);

        categoryLabel.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 13));
        categoryLabel.setForeground(JOLLIBEE_DARK_RED);
        categoryLabel.setText("Category:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        formPanel.add(categoryLabel, gbc);

        categoryCombo.setModel(new javax.swing.DefaultComboBoxModel<>(categories));
        categoryCombo.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 13));
        categoryCombo.setBackground(Color.WHITE);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1;
        formPanel.add(categoryCombo, gbc);

        priceLabel.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 13));
        priceLabel.setForeground(JOLLIBEE_DARK_RED);
        priceLabel.setText("Price (P):");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0;
        formPanel.add(priceLabel, gbc);

        priceField.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 13));
        priceField.setBorder(BorderFactory.createLineBorder(JOLLIBEE_YELLOW, 2));
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 1;
        formPanel.add(priceField, gbc);

        descriptionLabel.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 13));
        descriptionLabel.setForeground(JOLLIBEE_DARK_RED);
        descriptionLabel.setText("Description:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0;
        gbc.anchor = java.awt.GridBagConstraints.NORTHWEST;
        formPanel.add(descriptionLabel, gbc);

        descriptionArea.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 13));
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setBorder(BorderFactory.createLineBorder(JOLLIBEE_YELLOW, 2));
        descriptionScrollPane.setViewportView(descriptionArea);
        descriptionScrollPane.setPreferredSize(new java.awt.Dimension(280, 70));
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.weightx = 1;
        gbc.anchor = java.awt.GridBagConstraints.CENTER;
        formPanel.add(descriptionScrollPane, gbc);

        imageLabel.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 13));
        imageLabel.setForeground(JOLLIBEE_DARK_RED);
        imageLabel.setText("Image:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weightx = 0;
        formPanel.add(imageLabel, gbc);

        imagePathField.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 12));
        imagePathField.setEditable(false);
        imagePathField.setBackground(JOLLIBEE_LIGHT_YELLOW);
        imagePathField.setBorder(BorderFactory.createLineBorder(JOLLIBEE_YELLOW, 2));
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.weightx = 1;
        formPanel.add(imagePathField, gbc);

        browseImageButton.setText("Browse");
        browseImageButton.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 11));
        browseImageButton.setBackground(JOLLIBEE_YELLOW);
        browseImageButton.setForeground(JOLLIBEE_DARK_RED);
        browseImageButton.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        browseImageButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        browseImageButton.setFocusPainted(false);
        browseImageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browseImageButtonActionPerformed(evt);
            }
        });
        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.weightx = 0;
        formPanel.add(browseImageButton, gbc);

        imagePreviewLabel.setFont(new java.awt.Font("Segoe UI Emoji", java.awt.Font.PLAIN, 50));
        imagePreviewLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imagePreviewLabel.setText("🖼️");
        imagePreviewLabel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(JOLLIBEE_YELLOW, 2),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        imagePreviewLabel.setPreferredSize(new java.awt.Dimension(100, 100));
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 3;
        gbc.insets = new java.awt.Insets(15, 8, 8, 8);
        formPanel.add(imagePreviewLabel, gbc);

        mainPanel.add(formPanel, java.awt.BorderLayout.CENTER);

        // Buttons Panel - White
        buttonsPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 20, 15));
        buttonsPanel.setBackground(Color.WHITE);

        saveButton.setText("Save");
        saveButton.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 14));
        saveButton.setBackground(JOLLIBEE_YELLOW);
        saveButton.setForeground(JOLLIBEE_DARK_RED);
        saveButton.setBorder(BorderFactory.createEmptyBorder(12, 40, 12, 40));
        saveButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        saveButton.setFocusPainted(false);
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });
        buttonsPanel.add(saveButton);

        cancelButton.setText("Cancel");
        cancelButton.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 14));
        cancelButton.setBackground(Color.GRAY);
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setBorder(BorderFactory.createEmptyBorder(12, 40, 12, 40));
        cancelButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cancelButton.setFocusPainted(false);
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
    }// </editor-fold>//GEN-END:initComponents

    private void browseImageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browseImageButtonActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select Menu Item Image");
        fileChooser.setFileFilter(new FileNameExtensionFilter(
                "Image Files (*.jpg, *.jpeg, *.png, *.gif)", "jpg", "jpeg", "png", "gif"));

        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();

            try {
                // Create images folder if it doesn't exist
                File imagesFolder = new File("RestaurantPOS/src/restaurantpos/images");
                if (!imagesFolder.exists()) {
                    imagesFolder.mkdirs();
                }

                // Generate unique filename
                String fileName = selectedFile.getName();
                // Remove any path info, just keep filename
                if (fileName.contains("\\")) {
                    fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
                }
                if (fileName.contains("/")) {
                    fileName = fileName.substring(fileName.lastIndexOf("/") + 1);
                }

                // Destination file in images folder
                File destFile = new File(imagesFolder, fileName);

                // Copy the file to images folder
                java.nio.file.Files.copy(
                        selectedFile.toPath(),
                        destFile.toPath(),
                        java.nio.file.StandardCopyOption.REPLACE_EXISTING
                );

                // Save only the relative path
                selectedImagePath = "images/" + fileName;
                imagePathField.setText(fileName);

                // Show preview
                ImageIcon icon = new ImageIcon(destFile.getAbsolutePath());
                Image img = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                imagePreviewLabel.setText("");
                imagePreviewLabel.setIcon(new ImageIcon(img));

                JOptionPane.showMessageDialog(this,
                        "Image copied to project folder!\nPath saved: images/" + fileName,
                        "Image Added",
                        JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this,
                        "Error copying image: " + e.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                // Fallback to absolute path
                selectedImagePath = selectedFile.getAbsolutePath();
                imagePathField.setText(selectedFile.getName());
            }
        }
    }//GEN-LAST:event_browseImageButtonActionPerformed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        String name = nameField.getText().trim();
        String category = (String) categoryCombo.getSelectedItem();
        String priceText = priceField.getText().trim();
        String description = descriptionArea.getText().trim();

        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please enter the item name!",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE);
            nameField.requestFocus();
            return;
        }

        if (priceText.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please enter the price!",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE);
            priceField.requestFocus();
            return;
        }

        double price;
        try {
            price = Double.parseDouble(priceText);
            if (price <= 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Please enter a valid price!",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE);
            priceField.requestFocus();
            return;
        }

        if (menuItem == null) {
            menuItem = new MenuItem();
        }

        menuItem.setName(name);
        menuItem.setCategory(category);
        menuItem.setPrice(price);
        menuItem.setDescription(description);
        menuItem.setImagePath(selectedImagePath);

        DatabaseHandler dbHandler = DatabaseHandler.getInstance();
        boolean success;

        if (menuItem.getId() == 0) {
            success = dbHandler.addMenuItem(menuItem);
        } else {
            success = dbHandler.updateMenuItem(menuItem);
        }

        if (success) {
            saved = true;
            JOptionPane.showMessageDialog(this,
                    "Menu item saved successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this,
                    "Failed to save menu item!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_saveButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        saved = false;
        dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel mainPanel;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JLabel headerLabel;
    private javax.swing.JPanel formPanel;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JTextField nameField;
    private javax.swing.JLabel categoryLabel;
    private javax.swing.JComboBox<String> categoryCombo;
    private javax.swing.JLabel priceLabel;
    private javax.swing.JTextField priceField;
    private javax.swing.JLabel descriptionLabel;
    private javax.swing.JScrollPane descriptionScrollPane;
    private javax.swing.JTextArea descriptionArea;
    private javax.swing.JLabel imageLabel;
    private javax.swing.JTextField imagePathField;
    private javax.swing.JButton browseImageButton;
    private javax.swing.JLabel imagePreviewLabel;
    private javax.swing.JPanel buttonsPanel;
    private javax.swing.JButton saveButton;
    private javax.swing.JButton cancelButton;
    // End of variables declaration//GEN-END:variables

    public MenuItemDialog(java.awt.Frame parent, boolean modal, MenuItem item) {
        super(parent, modal);
        this.menuItem = item;
        this.selectedImagePath = item != null ? item.getImagePath() : null;

        initComponents();

        if (item != null) {
            nameField.setText(item.getName());
            categoryCombo.setSelectedItem(item.getCategory());
            priceField.setText(String.format("%.2f", item.getPrice()));
            descriptionArea.setText(item.getDescription());
            if (item.getImagePath() != null && !item.getImagePath().isEmpty()) {
                imagePathField.setText(new File(item.getImagePath()).getName());
                try {
                    ImageIcon icon = new ImageIcon(item.getImagePath());
                    Image img = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                    imagePreviewLabel.setText("");
                    imagePreviewLabel.setIcon(new ImageIcon(img));
                } catch (Exception e) {
                    imagePreviewLabel.setText("🖼️");
                }
            }
        }
    }

    public boolean isSaved() {
        return saved;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }
}