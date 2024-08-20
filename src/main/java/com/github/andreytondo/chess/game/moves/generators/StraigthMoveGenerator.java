package com.github.andreytondo.chess.game.moves.generators;

import com.github.andreytondo.chess.game.moves.Move;
import com.github.andreytondo.chess.game.pieces.utils.Position;

import java.util.ArrayList;
import java.util.List;

public class StraigthMoveGenerator implements MoveGenerator {

    @Override
    public List<Move> generate(Position position) {
        List<Move> moves = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (i == position.row() || j == position.column()) {
                    moves.add(new Move(position, new Position(i, j)));
                }
            }
        }

        return moves;
    }
}
