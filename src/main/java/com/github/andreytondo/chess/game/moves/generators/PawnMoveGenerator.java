package com.github.andreytondo.chess.game.moves.generators;

import com.github.andreytondo.chess.game.moves.Move;
import com.github.andreytondo.chess.game.pieces.utils.PieceColor;
import com.github.andreytondo.chess.game.pieces.utils.Position;

import java.util.ArrayList;
import java.util.List;

public class PawnMoveGenerator implements MoveGenerator {

    private final PieceColor color;

    public PawnMoveGenerator(PieceColor color) {
        this.color = color;
    }

    @Override
    public List<Move> generate(Position position) {
        int direction = color == PieceColor.WHITE ? -1 : 1;
        List<Move> moves = new ArrayList<>();

        int newRow = position.row() + direction;
        moves.add(new Move(position, new Position(newRow, position.column()))); // move one square forward
        moves.add(new Move(position, new Position(newRow, position.column() + 1))); // capture right
        moves.add(new Move(position, new Position(newRow, position.column() - 1)));  // capture left

        if (position.row() == (color == PieceColor.WHITE ? 6 : 1)) {
            moves.add(new Move(position, new Position(position.row() + 2 * direction, position.column()))); // move two squares forward
        }

        return moves;
    }
}
