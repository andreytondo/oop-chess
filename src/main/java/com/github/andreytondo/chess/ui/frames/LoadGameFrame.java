package com.github.andreytondo.chess.ui.frames;

import com.github.andreytondo.chess.dao.ChessGameDAO;
import com.github.andreytondo.chess.models.ChessGame;
import com.github.andreytondo.chess.ui.utils.DefaultFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class LoadGameFrame extends DefaultFrame {

    private final DefaultListModel<ChessGame> savedGamesListModel = new DefaultListModel<>();
    private final JList<ChessGame> savedGamesList = new JList<>(savedGamesListModel);;

    public LoadGameFrame() {
        super("Load Game", new BorderLayout(), 400, 300);
        savedGamesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        add(new JScrollPane(savedGamesList), BorderLayout.CENTER);

        JButton loadButton = createButton("Load Game", new LoadGameListener());
        add(loadButton, BorderLayout.SOUTH);
        loadGames();
        setVisible(true);
    }

    private void loadGames() {
        List<ChessGame> savedGames = new ChessGameDAO().getAll();
        for (ChessGame game : savedGames) {
            savedGamesListModel.addElement(game);
        }
    }

    private class LoadGameListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ChessGame selectedGame = savedGamesList.getSelectedValue();
            if (selectedGame != null) {
                new ChessboardFrame(selectedGame.getFen());
                dispose();
            } else {
                JOptionPane.showMessageDialog(LoadGameFrame.this, "Please select a game to load.", "No Game Selected", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}
