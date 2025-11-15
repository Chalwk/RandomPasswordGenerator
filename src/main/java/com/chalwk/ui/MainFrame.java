/**
 * Random Password Generator
 * Copyright (c) 2025 Jericho Crosby (Chalwk)
 * <p>
 * This project is licensed under the MIT License.
 * See LICENSE file for details.
 */

package com.chalwk.ui;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class MainFrame extends JFrame {

    public MainFrame() {
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Random Password Generator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800, 600));
        setMinimumSize(new Dimension(700, 500));

        // Set application icon
        try {
            URL iconURL = getClass().getResource("/icon.png");
            if (iconURL != null) {
                ImageIcon icon = new ImageIcon(iconURL);
                setIconImage(icon.getImage());
            } else {
                System.err.println("Warning: Could not find application icon at /icon.png");
            }
        } catch (Exception e) {
            System.err.println("Error loading application icon: " + e.getMessage());
        }

        // Create main panel with modern layout
        setLayout(new BorderLayout());
        add(new PasswordPanel(), BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null); // Center on screen
    }
}