/**
 * Random Password Generator
 * Copyright (c) 2025 Jericho Crosby (Chalwk)
 * <p>
 * This project is licensed under the MIT License.
 * See LICENSE file for details.
 */

package com.chalwk;

import com.chalwk.ui.MainFrame;

import javax.swing.*;

public class PasswordGenerator {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                System.err.println("Error setting look and feel: " + e.getMessage());
            }
            new MainFrame().setVisible(true);
        });
    }
}