package com.github.andreytondo.chess.game.pieces.utils;

public record Position(int row, int column) {

    public boolean isWithinBounds() {
        return row >= 0 && row < 8 && column >= 0 && column < 8;
    }
}