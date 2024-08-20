package com.github.andreytondo.chess.game.moves.generators;

import com.github.andreytondo.chess.game.moves.Move;
import com.github.andreytondo.chess.game.pieces.utils.Position;

import java.util.ArrayList;
import java.util.List;

public class DiagonalMoveGenerator implements MoveGenerator {

    @Override
    public List<Move> generate(Position position) {
        List<Move> moves = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                int row = i - position.row();
                int col = j - position.column();

                if (Math.abs(row) == Math.abs(col)) {
                    moves.add(new Move(position, new Position(position.row() + row, position.column() + col)));
                }

            }
        }

        return moves;
    }
}
