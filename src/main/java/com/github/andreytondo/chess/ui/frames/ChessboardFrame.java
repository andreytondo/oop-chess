package com.github.andreytondo.chess.ui.frames;

import com.github.andreytondo.chess.ui.panels.ChessboardPanel;
import com.github.andreytondo.chess.ui.utils.ChessboardConfig;
import com.github.andreytondo.chess.ui.utils.DefaultFrame;

import java.awt.*;

public class ChessboardFrame extends DefaultFrame {

    private static final ChessboardConfig config = ChessboardConfig.getInstance();

    public ChessboardFrame(String fen) {
        super(
                "Chessboard",
                new BorderLayout(),
                config.getSquareSize() * 8,
                config.getSquareSize() * 8 + 35
        );

        add(new ChessboardPanel(config, fen));
        setVisible(true);
    }

}