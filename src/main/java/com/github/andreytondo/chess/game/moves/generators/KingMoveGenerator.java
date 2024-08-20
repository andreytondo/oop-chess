package com.github.andreytondo.chess.game.moves.generators;

import com.github.andreytondo.chess.game.moves.Move;
import com.github.andreytondo.chess.game.pieces.utils.PieceColor;
import com.github.andreytondo.chess.game.pieces.utils.Position;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class KingMoveGenerator implements MoveGenerator {

    private final int[] dx = {1, 1, 1, 0, 0, -1, -1, -1};
    private final int[] dy = {1, 0, -1, 1, -1, 1, 0, -1};

    private final PieceColor color;

    public KingMoveGenerator(PieceColor color) {
        this.color = color;
    }

    @Override
    public List<Move> generate(Position position) {
        List<Move> moves = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            int x = position.row() + dx[i];
            int y = position.column() + dy[i];
            if (x >= 0 && x < 8 && y >= 0 && y < 8) {
                moves.add(new Move(position, new Position(x, y)));
            }
        }

        if (position.row() == (color == PieceColor.WHITE ? 7 : 0) && position.column() == 4) {
            moves.addAll(generateCastlingMoves(position));
        }

        return moves;
    }

    private Collection<Move> generateCastlingMoves(Position position) {
        List<Move> moves = new ArrayList<>();
        // castling
        Move castlingLeft = new Move(position, new Position(position.row(), position.column() - 2));
        Move castlingRight = new Move(position, new Position(position.row(), position.column() + 2));

        moves.add(castlingLeft);
        moves.add(castlingRight);
        return moves;
    }

}