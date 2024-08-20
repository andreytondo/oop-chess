package com.github.andreytondo.chess.ui.images;

import com.github.andreytondo.chess.game.pieces.utils.ChessPiece;

public class PieceImageMapper {

    public static String getPath(ChessPiece piece) {
        String name = piece.getClass().getSimpleName().toLowerCase();
        String color = piece.getColor().toString().toLowerCase();
        return String.format("src/main/resources/assets/%s_%s.svg", color, name);
    }
}
