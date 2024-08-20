package com.github.andreytondo.chess.game.pieces.utils;

import java.awt.*;

public enum PieceColor {

    WHITE(new Color(255, 255, 255)),
    BLACK(new Color(139, 69, 19));

    private final Color color;

    PieceColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public PieceColor getOppositeColor() {
        return switch (this) {
            case WHITE -> BLACK;
            case BLACK -> WHITE;
        };
    }
}
