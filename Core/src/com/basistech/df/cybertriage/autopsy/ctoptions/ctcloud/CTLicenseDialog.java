/*
 * Autopsy Forensic Browser
 *
 * Copyright 2023 Basis Technology Corp.
 * Contact: carrier <at> sleuthkit <dot> org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.basistech.df.cybertriage.autopsy.ctoptions.ctcloud;

import java.awt.Color;
import java.util.regex.Pattern;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.apache.commons.lang3.StringUtils;
import org.openide.util.NbBundle.Messages;
import org.sleuthkit.autopsy.corecomponents.TextPrompt;

/**
 * License dialog
 */
class CTLicenseDialog extends javax.swing.JDialog {

    private static final Pattern LICENSE_PATTERN = Pattern.compile("^\\s*[a-zA-Z0-9\\-]+?\\s*$");
    private String licenseString = null;

    /**
     * Creates new form CTLicenseDialog
     */
    public CTLicenseDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        configureHintText();
        this.licenseNumberTextField.getDocument().putProperty("filterNewlines", Boolean.TRUE);
        this.licenseNumberTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                verifyInput();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                verifyInput();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                verifyInput();
            }
        });
    }
    
    private void configureHintText() {
        TextPrompt textPrompt = new TextPrompt(
                StringUtils.defaultString(this.licenseNumberTextField.getToolTipText()),
                this.licenseNumberTextField);
        
        textPrompt.setForeground(Color.LIGHT_GRAY);
        float alpha = 0.9f; // Mostly opaque
        textPrompt.changeAlpha(alpha);
    }
    
    String getValue() {
        return licenseString;
    }

    @Messages({
        "CTLicenseDialog_verifyInput_licenseNumberError=<html>Please enter a license number</html>"
    })
    private void verifyInput() {
        String licenseInput = StringUtils.defaultString(this.licenseNumberTextField.getText());
        if (LICENSE_PATTERN.matcher(licenseInput).matches()) {
            this.warningLabel.setText("");
            this.okButton.setEnabled(true);
        } else {
            this.warningLabel.setText(Bundle.CTLicenseDialog_verifyInput_licenseNumberError());
            this.okButton.setEnabled(false);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        javax.swing.JLabel licenseNumberLabel = new javax.swing.JLabel();
        warningLabel = new javax.swing.JLabel();
        javax.swing.JPanel buttonPadding = new javax.swing.JPanel();
        okButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        licenseNumberTextField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(org.openide.util.NbBundle.getMessage(CTLicenseDialog.class, "CTLicenseDialog.title")); // NOI18N
        setAlwaysOnTop(true);
        setResizable(false);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        org.openide.awt.Mnemonics.setLocalizedText(licenseNumberLabel, org.openide.util.NbBundle.getMessage(CTLicenseDialog.class, "CTLicenseDialog.licenseNumberLabel.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(licenseNumberLabel, gridBagConstraints);

        warningLabel.setForeground(java.awt.Color.RED);
        org.openide.awt.Mnemonics.setLocalizedText(warningLabel, org.openide.util.NbBundle.getMessage(CTLicenseDialog.class, "CTLicenseDialog.warningLabel.text")); // NOI18N
        warningLabel.setMaximumSize(new java.awt.Dimension(419, 36));
        warningLabel.setMinimumSize(new java.awt.Dimension(419, 36));
        warningLabel.setPreferredSize(new java.awt.Dimension(419, 36));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        getContentPane().add(warningLabel, gridBagConstraints);

        javax.swing.GroupLayout buttonPaddingLayout = new javax.swing.GroupLayout(buttonPadding);
        buttonPadding.setLayout(buttonPaddingLayout);
        buttonPaddingLayout.setHorizontalGroup(
            buttonPaddingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        buttonPaddingLayout.setVerticalGroup(
            buttonPaddingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(buttonPadding, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(okButton, org.openide.util.NbBundle.getMessage(CTLicenseDialog.class, "CTLicenseDialog.okButton.text")); // NOI18N
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        getContentPane().add(okButton, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(cancelButton, org.openide.util.NbBundle.getMessage(CTLicenseDialog.class, "CTLicenseDialog.cancelButton.text")); // NOI18N
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        getContentPane().add(cancelButton, gridBagConstraints);

        licenseNumberTextField.setText(org.openide.util.NbBundle.getMessage(CTLicenseDialog.class, "CTLicenseDialog.licenseNumberTextField.text")); // NOI18N
        licenseNumberTextField.setToolTipText(org.openide.util.NbBundle.getMessage(CTLicenseDialog.class, "CTLicenseDialog.licenseNumberTextField.toolTipText")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 5);
        getContentPane().add(licenseNumberTextField, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        this.licenseString = this.licenseNumberTextField.getText();
        this.dispose();
    }//GEN-LAST:event_okButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        this.licenseString = null;
        this.dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JTextField licenseNumberTextField;
    private javax.swing.JButton okButton;
    private javax.swing.JLabel warningLabel;
    // End of variables declaration//GEN-END:variables
}
