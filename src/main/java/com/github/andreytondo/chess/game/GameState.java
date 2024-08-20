package com.github.andreytondo.chess.game;

public enum GameState {

    ONGOING("Ongoing"),
    DRAW("Draw"),
    WHITE_WON("White wins"),
    BLACK_WON("Black wins");

    private final String description;

    GameState(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
