package com.github.andreytondo.chess.ui.utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public abstract class DefaultFrame extends JFrame {

    private final String title;
    private final LayoutManager layoutManager;
    private final int width;
    private final int height;

    public DefaultFrame(String title, LayoutManager layoutManager, int width, int height) {
        this.title = title;
        this.layoutManager = layoutManager;
        this.width = width;
        this.height = height;
        createFrame();
    }

    private void createFrame() {
        setTitle(this.title);
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(this.layoutManager);
    }

    protected JButton createButton(String text, ActionListener listener) {
        JButton button = new JButton(text);
        button.addActionListener(listener);
        return button;
    }

    protected void createGrid(JComponent... components) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        for (JComponent component : components) {
            add(component, gbc);
            gbc.gridy++;
        }
    }

}
