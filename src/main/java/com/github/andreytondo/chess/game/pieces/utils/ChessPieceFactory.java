package com.github.andreytondo.chess.game.pieces.utils;

import com.github.andreytondo.chess.game.pieces.*;

import java.util.HashMap;
import java.util.Map;

public class ChessPieceFactory {

    private static final Map<Character, Class<? extends ChessPiece>> pieceMap = new HashMap<>();

    static {
        pieceMap.put('p', Pawn.class);
        pieceMap.put('r', Rook.class);
        pieceMap.put('n', Knight.class);
        pieceMap.put('b', Bishop.class);
        pieceMap.put('q', Queen.class);
        pieceMap.put('k', King.class);
    }

    public static ChessPiece createPiece(char pieceChar, PieceColor color, Position position) {
        try {
            Class<? extends ChessPiece> pieceClass = pieceMap.get(pieceChar);
            if (pieceClass != null) {
                return pieceClass.getDeclaredConstructor(PieceColor.class)
                        .newInstance(color);
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Unable to create piece from FEN character: " + pieceChar, e);
        }
        throw new IllegalArgumentException("No matching piece class for FEN character: " + pieceChar);
    }
}
