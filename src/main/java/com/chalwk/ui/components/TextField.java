/**
 * Random Password Generator
 * Copyright (c) 2025 Jericho Crosby (Chalwk)
 * <p>
 * This project is licensed under the MIT License.
 * See LICENSE file for details.
 */

package com.chalwk.ui.components;

import javax.swing.*;
import java.awt.*;

public class TextField extends JTextField {

    public TextField() {
        setModernStyle();
    }

    private void setModernStyle() {
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(206, 212, 218)),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        setBackground(Color.WHITE);
        setFont(new Font("Segoe UI", Font.PLAIN, 14));
        setSelectionColor(new Color(0, 123, 255, 100));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Paint background
        g2.setColor(getBackground());
        g2.fillRect(0, 0, getWidth(), getHeight());

        super.paintComponent(g2);
        g2.dispose();
    }
}