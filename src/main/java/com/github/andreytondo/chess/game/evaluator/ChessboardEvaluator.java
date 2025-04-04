package com.github.andreytondo.chess.game.evaluator;

import com.github.andreytondo.chess.game.board.Chessboard;
import com.github.andreytondo.chess.game.pieces.utils.ChessPiece;
import com.github.andreytondo.chess.game.pieces.utils.PieceColor;
import com.github.andreytondo.chess.game.pieces.utils.Position;

public class ChessboardEvaluator {

    private final Chessboard chessboard;

    public ChessboardEvaluator(Chessboard chessboard) {
        this.chessboard = chessboard;
    }

    public int evaluate() {
        int whiteValue = countMaterial(PieceColor.WHITE);
        int blackValue = countMaterial(PieceColor.BLACK);

        int evaluation = whiteValue - blackValue;
        int perspective = chessboard.getActiveColor() == PieceColor.WHITE ? 1 : -1;

        return perspective * evaluation;
    }

    public int countMaterial(PieceColor color) {
        int value = 0;
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Position pos = new Position(row, col);
                ChessPiece piece = chessboard.getPieceAt(pos);
                if (piece != null && piece.getColor() == color) {
                    value += getValue(piece);
                }
            }
        }
        return value;
    }

    private int getValue(ChessPiece piece) {
        return switch (piece.getType()) {
            case PAWN -> 100;
            case KNIGHT -> 320;
            case BISHOP -> 330;
            case ROOK -> 500;
            case QUEEN -> 900;
            default -> 0;
        };
    }
}
