package com.github.andreytondo.chess.player;

import com.github.andreytondo.chess.game.moves.Move;

public interface ChessPlayer {

    Move makeMove();
    String getName();
}
