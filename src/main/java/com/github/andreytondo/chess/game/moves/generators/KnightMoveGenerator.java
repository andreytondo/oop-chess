package com.github.andreytondo.chess.game.moves.generators;

import com.github.andreytondo.chess.game.moves.Move;
import com.github.andreytondo.chess.game.pieces.utils.Position;

import java.util.ArrayList;
import java.util.List;

public class KnightMoveGenerator implements MoveGenerator {

    @Override
    public List<Move> generate(Position position) {
        List<Move> moves = new ArrayList<>();

        for (int i = -2; i <= 2; i++) {
            if (i == 0) {
                continue;
            }
            for (int j = -2; j <= 2; j++) {
                if (j == 0 || Math.abs(i) == Math.abs(j)) {
                    continue;
                }
                moves.add(new Move(position, new Position(position.row() + i, position.column() + j)));
            }
        }

        return moves;
    }
}
