package com.github.andreytondo.chess.game.moves.generators;

import com.github.andreytondo.chess.game.moves.Move;
import com.github.andreytondo.chess.game.pieces.utils.Position;

import java.util.List;

public interface MoveGenerator {

    List<Move> generate(Position position);
}
