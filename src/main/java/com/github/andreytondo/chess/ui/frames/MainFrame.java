package com.github.andreytondo.chess.ui.frames;

import com.github.andreytondo.chess.game.Fen;
import com.github.andreytondo.chess.ui.utils.DefaultFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends DefaultFrame {

    public MainFrame() {
        super("Chess Game Menu", new GridBagLayout(), 400, 200);
        JComponent button1 = createButton("New Game", new NewGameListener());
        JComponent button2 = createButton("Load Game", new LoadGameListener());
        createGrid(button1, button2);
        setVisible(true);
    }

    private class NewGameListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new ChessboardFrame(Fen.DEFAULT_POSITION);
            dispose();
        }
    }

    private class LoadGameListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new LoadGameFrame();
            dispose();
        }
    }

}