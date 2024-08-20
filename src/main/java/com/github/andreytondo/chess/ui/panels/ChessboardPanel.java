package com.github.andreytondo.chess.ui.panels;

import com.github.andreytondo.chess.game.board.Chessboard;
import com.github.andreytondo.chess.game.moves.Move;
import com.github.andreytondo.chess.game.pieces.utils.ChessPiece;
import com.github.andreytondo.chess.game.pieces.utils.Position;
import com.github.andreytondo.chess.ui.images.ImageLoader;
import com.github.andreytondo.chess.ui.images.ImageObserverImpl;
import com.github.andreytondo.chess.ui.images.PieceImageMapper;
import com.github.andreytondo.chess.ui.utils.ChessboardConfig;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

public class ChessboardPanel extends JPanel {

    private final int squareSize;
    private final ChessboardConfig config;
    private final Chessboard chessboard;

    private Position selectedPosition;

    public ChessboardPanel(ChessboardConfig config, String fen) {
        this.squareSize = config.getSquareSize();
        this.config = config;
        this.chessboard = new Chessboard(fen);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleMouseClick(e.getX(), e.getY());
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBoard(g);
        drawPieces(g);

        if (selectedPosition != null) {
            highlightSquare(g, selectedPosition);
            highlightPossibleMoves(g, selectedPosition);
        }
    }

    private void drawBoard(Graphics g) {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if ((row + col) % 2 == 0) {
                    g.setColor(config.getLightColor());
                } else {
                    g.setColor(config.getDarkColor());
                }
                g.fillRect(col * squareSize, row * squareSize, squareSize, squareSize);
            }
        }
    }

    private void drawPieces(Graphics g) {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                ChessPiece piece = chessboard.getPieceAt(new Position(row, col));
                if (piece != null) {
                    drawPiece(g, piece, col, row);
                }
            }
        }
    }

    private void drawPiece(Graphics g, ChessPiece piece, int col, int row) {
        g.setColor(Color.BLACK);
        g.drawImage(ImageLoader.load(PieceImageMapper.getPath(piece)), col * squareSize, row * squareSize, squareSize, squareSize, ImageObserverImpl.getInstance());
    }

    private void highlightSquare(Graphics g, Position position) {
        g.setColor(Color.YELLOW);
        g.drawRect(position.column() * squareSize, position.row() * squareSize, squareSize, squareSize);
        g.drawRect(position.column() * squareSize + 1, position.row() * squareSize + 1, squareSize - 2, squareSize - 2);
    }

    private void highlightPossibleMoves(Graphics g, Position position) {
        Color color = new Color(255, 255, 0, 100);
        g.setColor(color);

        for (Move move : chessboard.getValidMoves(position)) {
            Position target = move.to();
            if (!Objects.equals(move.from(), target)) {
                g.fillRect(target.column() * squareSize, target.row() * squareSize, squareSize, squareSize);
                g.fillRect(target.column() * squareSize + 1, target.row() * squareSize + 1, squareSize - 2, squareSize - 2);
            }
        }
    }

    private void handleMouseClick(int x, int y) {
        int col = x / squareSize;
        int row = y / squareSize;
        Position clickedPosition = new Position(row, col);
        ChessPiece piece = chessboard.getPieceAt(clickedPosition);

        if (selectedPosition == null) {
            if (piece != null && Objects.equals(chessboard.getActiveColor(), piece.getColor())) {
                selectedPosition = clickedPosition;
            }
        } else {
            Move move = new Move(selectedPosition, clickedPosition);
            chessboard.movePiece(move);
            selectedPosition = null;
        }
        repaint();
    }

}
