package com.github.andreytondo.chess.ui.utils;

import java.awt.*;

public class ChessboardConfig {

    private static final ChessboardConfig instance = new ChessboardConfig();

    private final Color lightColor;
    private final Color darkColor;
    private final int squareSize;

    private ChessboardConfig() {
        this.lightColor = new Color(255, 255, 255);
        this.darkColor = new Color(139, 69, 19);
        this.squareSize = 80;
    }

    public static ChessboardConfig getInstance() {
        return instance;
    }

    public Color getLightColor() {
        return lightColor;
    }

    public Color getDarkColor() {
        return darkColor;
    }

    public int getSquareSize() {
        return squareSize;
    }
}