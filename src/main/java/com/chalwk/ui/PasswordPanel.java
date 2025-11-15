/**
 * Random Password Generator
 * Copyright (c) 2025 Jericho Crosby (Chalwk)
 * <p>
 * This project is licensed under the MIT License.
 * See LICENSE file for details.
 */

package com.chalwk.ui;

import com.chalwk.model.PasswordConfig;
import com.chalwk.ui.components.Buttons;
import com.chalwk.ui.components.TextField;
import com.chalwk.util.ClipboardUtil;
import com.chalwk.util.PasswordGeneratorEngine;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

public class PasswordPanel extends JPanel {

    private TextField passwordField;
    private JSpinner lengthSpinner;
    private JCheckBox uppercaseCheckbox;
    private JCheckBox lowercaseCheckbox;
    private JCheckBox numbersCheckbox;
    private JCheckBox symbolsCheckbox;
    private JCheckBox excludeSimilarCheckbox;
    private JCheckBox excludeAmbiguousCheckbox;
    private JSlider entropySlider;
    private JLabel entropyLabel;
    private JProgressBar strengthBar;

    public PasswordPanel() {
        initializePanel();
        setupComponents();
        setupLayout();
    }

    private void initializePanel() {
        setBackground(new Color(245, 245, 245));
        setBorder(new EmptyBorder(20, 20, 20, 20));
        setLayout(new BorderLayout(15, 15));
    }

    private void setupComponents() {
        // Password display field
        passwordField = new TextField();
        passwordField.setEditable(false);
        passwordField.setFont(new Font("Monospaced", Font.BOLD, 16));

        // Length spinner
        SpinnerNumberModel lengthModel = new SpinnerNumberModel(16, 8, 128, 1);
        lengthSpinner = new JSpinner(lengthModel);
        lengthSpinner.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        // Checkboxes
        uppercaseCheckbox = createCheckbox("Uppercase Letters (A-Z)", true);
        lowercaseCheckbox = createCheckbox("Lowercase Letters (a-z)", true);
        numbersCheckbox = createCheckbox("Numbers (0-9)", true);
        symbolsCheckbox = createCheckbox("Symbols (!@#$%^&*)", true);
        excludeSimilarCheckbox = createCheckbox("Exclude Similar Characters (i,l,1,L,o,0,O)", false);
        excludeAmbiguousCheckbox = createCheckbox("Exclude Ambiguous Characters ({ } [ ] ( ) | ` ~ ; : , . < >)", false);

        // Entropy slider
        entropySlider = new JSlider(0, 100, 50);
        entropySlider.setMajorTickSpacing(25);
        entropySlider.setPaintTicks(true);
        entropySlider.setPaintLabels(true);
        entropyLabel = new JLabel("Entropy Level: Medium");
        entropyLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));

        // Strength progress bar
        strengthBar = new JProgressBar(0, 100);
        strengthBar.setStringPainted(true);
        strengthBar.setForeground(new Color(220, 53, 69)); // Red by default

        // Buttons
        Buttons generateButton = new Buttons("Generate Password");
        Buttons copyButton = new Buttons("Copy to Clipboard");

        // Add action listeners
        generateButton.addActionListener(this::generatePassword);
        copyButton.addActionListener(e -> copyToClipboard());

        // Add components to panel
        add(createHeaderPanel(), BorderLayout.NORTH);
        add(createCenterPanel(generateButton, copyButton), BorderLayout.CENTER);
        add(createFooterPanel(), BorderLayout.SOUTH);
    }

    private void setupLayout() {
        // Layout is handled in setupComponents
    }

    private JCheckBox createCheckbox(String text, boolean selected) {
        JCheckBox checkbox = new JCheckBox(text, selected);
        checkbox.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        checkbox.setBackground(new Color(245, 245, 245));
        return checkbox;
    }

    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(245, 245, 245));

        JLabel titleLabel = new JLabel("Random Password Generator");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(new Color(33, 37, 41));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        headerPanel.add(titleLabel, BorderLayout.CENTER);
        headerPanel.setBorder(new EmptyBorder(0, 0, 20, 0));

        return headerPanel;
    }

    private JPanel createCenterPanel(Buttons generateButton, Buttons copyButton) {
        JPanel centerPanel = new JPanel(new BorderLayout(15, 15));
        centerPanel.setBackground(new Color(245, 245, 245));

        // Password display panel
        JPanel passwordPanel = new JPanel(new BorderLayout(10, 10));
        passwordPanel.setBackground(new Color(245, 245, 245));

        JLabel passwordLabel = new JLabel("Generated Password:");
        passwordLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));

        passwordPanel.add(passwordLabel, BorderLayout.NORTH);
        passwordPanel.add(passwordField, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        buttonPanel.setBackground(new Color(245, 245, 245));
        buttonPanel.add(generateButton);
        buttonPanel.add(copyButton);

        passwordPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Options panel
        JPanel optionsPanel = createOptionsPanel();

        centerPanel.add(passwordPanel, BorderLayout.NORTH);
        centerPanel.add(optionsPanel, BorderLayout.CENTER);

        return centerPanel;
    }

    private JPanel createOptionsPanel() {
        JPanel optionsPanel = new JPanel(new GridLayout(0, 2, 20, 10));
        optionsPanel.setBackground(new Color(245, 245, 245));
        optionsPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                "Password Options"
        ));

        // Length option
        JPanel lengthPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        lengthPanel.setBackground(new Color(245, 245, 245));
        lengthPanel.add(new JLabel("Password Length:"));
        lengthPanel.add(lengthSpinner);

        // Add components in two columns
        optionsPanel.add(lengthPanel);
        optionsPanel.add(uppercaseCheckbox);
        optionsPanel.add(new JLabel()); // Empty cell for layout
        optionsPanel.add(lowercaseCheckbox);
        optionsPanel.add(numbersCheckbox);
        optionsPanel.add(symbolsCheckbox);
        optionsPanel.add(excludeSimilarCheckbox);
        optionsPanel.add(excludeAmbiguousCheckbox);

        // Entropy slider
        JPanel entropyPanel = new JPanel(new BorderLayout(5, 5));
        entropyPanel.setBackground(new Color(245, 245, 245));
        entropyPanel.add(entropyLabel, BorderLayout.NORTH);
        entropyPanel.add(entropySlider, BorderLayout.CENTER);

        optionsPanel.add(entropyPanel);
        optionsPanel.add(strengthBar);

        return optionsPanel;
    }

    private JPanel createFooterPanel() {
        JPanel footerPanel = new JPanel(new BorderLayout());
        footerPanel.setBackground(new Color(245, 245, 245));
        footerPanel.setBorder(new EmptyBorder(20, 0, 0, 0));

        JLabel infoLabel = new JLabel(
                "© 2025 Random Password Generator - Jericho Crosby (Chalwk). All rights reserved.",
                SwingConstants.CENTER
        );
        infoLabel.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        infoLabel.setForeground(new Color(108, 117, 125));

        footerPanel.add(infoLabel, BorderLayout.CENTER);
        return footerPanel;
    }

    private void generatePassword(ActionEvent e) {
        PasswordConfig config = new PasswordConfig(
                (Integer) lengthSpinner.getValue(),
                uppercaseCheckbox.isSelected(),
                lowercaseCheckbox.isSelected(),
                numbersCheckbox.isSelected(),
                symbolsCheckbox.isSelected(),
                excludeSimilarCheckbox.isSelected(),
                excludeAmbiguousCheckbox.isSelected(),
                entropySlider.getValue()
        );

        String password = PasswordGeneratorEngine.generatePassword(config);
        passwordField.setText(password);
        updateStrengthIndicator(password);
    }

    private void copyToClipboard() {
        String password = passwordField.getText();
        if (!password.isEmpty()) {
            ClipboardUtil.copyToClipboard(password);
            JOptionPane.showMessageDialog(this,
                    "Password copied to clipboard!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void updateStrengthIndicator(String password) {
        int strength = calculatePasswordStrength(password);
        strengthBar.setValue(strength);

        // Update color based on strength
        if (strength < 25) {
            strengthBar.setForeground(new Color(220, 53, 69)); // Red
            strengthBar.setString("Weak");
        } else if (strength < 50) {
            strengthBar.setForeground(new Color(255, 193, 7)); // Yellow
            strengthBar.setString("Fair");
        } else if (strength < 75) {
            strengthBar.setForeground(new Color(40, 167, 69)); // Green
            strengthBar.setString("Good");
        } else {
            strengthBar.setForeground(new Color(0, 123, 255)); // Blue
            strengthBar.setString("Strong");
        }
    }

    private int calculatePasswordStrength(String password) {
        if (password.isEmpty()) return 0;

        int strength = 0;
        int length = password.length();

        // Length factor (max 40 points)
        strength += Math.min(length * 2, 40);

        // Character variety (max 60 points)
        boolean hasUpper = !password.equals(password.toLowerCase());
        boolean hasLower = !password.equals(password.toUpperCase());
        boolean hasDigit = password.matches(".*\\d.*");
        boolean hasSpecial = !password.matches("[A-Za-z0-9]*");

        if (hasUpper) strength += 10;
        if (hasLower) strength += 10;
        if (hasDigit) strength += 10;
        if (hasSpecial) strength += 10;

        // Entropy bonus (max 20 points)
        int uniqueChars = (int) password.chars().distinct().count();
        strength += Math.min((uniqueChars * 20) / length, 20);

        return Math.min(strength, 100);
    }
}