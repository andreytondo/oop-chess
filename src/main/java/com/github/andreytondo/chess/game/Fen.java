package com.github.andreytondo.chess.game;


import com.github.andreytondo.chess.game.pieces.Knight;
import com.github.andreytondo.chess.game.pieces.utils.ChessPiece;
import com.github.andreytondo.chess.game.pieces.utils.ChessPieceFactory;
import com.github.andreytondo.chess.game.pieces.utils.PieceColor;
import com.github.andreytondo.chess.game.pieces.utils.Position;

public class Fen {

    public static String DEFAULT_POSITION = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";

    public record GameInfo(ChessPiece[][] board, PieceColor activeColor) {}

    public static GameInfo parseFen(String fen) {
        ChessPiece[][] board = parse(fen);
        PieceColor activeColor = getActiveColor(fen);
        return new GameInfo(board, activeColor);
    }

    public static ChessPiece[][] parse(String fen) {
        ChessPiece[][] board = new ChessPiece[8][8];
        String[] parts = fen.split(" ");
        String piecePlacement = parts[0];

        int row = 0;
        int col = 0;

        for (char c : piecePlacement.toCharArray()) {
            if (c == '/') {
                row++;
                col = 0;
            } else if (Character.isDigit(c)) {
                col += Character.getNumericValue(c);
            } else {
                PieceColor color = Character.isUpperCase(c) ? PieceColor.WHITE : PieceColor.BLACK;
                char lowerCasedPiece = Character.toLowerCase(c);
                ChessPiece piece = ChessPieceFactory.createPiece(lowerCasedPiece, color, new Position(row, col));

                board[row][col] = piece;
                col++;
            }
        }
        return board;
    }

    public static PieceColor getActiveColor(String fen) {
        return fen.split(" ")[1].equals("w") ? PieceColor.WHITE : PieceColor.BLACK;
    }

    public static String stringify(ChessPiece[][] board) {
        StringBuilder fenBuilder = new StringBuilder();
        for (int row = 0; row < 8; row++) {
            int emptyCount = 0;
            for (int col = 0; col < 8; col++) {
                ChessPiece piece = board[row][col];
                if (piece == null) {
                    emptyCount++;
                } else {
                    if (emptyCount > 0) {
                        fenBuilder.append(emptyCount);
                        emptyCount = 0;
                    }
                    fenBuilder.append(pieceToFenChar(piece));
                }
            }
            if (emptyCount > 0) {
                fenBuilder.append(emptyCount);
            }
            if (row < 7) {
                fenBuilder.append('/');
            }
        }
        return fenBuilder.toString();
    }

    private static char pieceToFenChar(ChessPiece piece) {
        char fenChar = piece.getClass().getSimpleName().charAt(0);
        if (piece instanceof Knight) {
            fenChar = 'n';
        }
        if (piece.getColor() == PieceColor.BLACK) {
            fenChar = Character.toLowerCase(fenChar);
        }
        return fenChar;
    }

}
