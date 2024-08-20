package com.github.andreytondo.chess.game.pieces;

import com.github.andreytondo.chess.game.moves.generators.KnightMoveGenerator;
import com.github.andreytondo.chess.game.moves.generators.MoveGenerator;
import com.github.andreytondo.chess.game.pieces.utils.ChessPiece;
import com.github.andreytondo.chess.game.pieces.utils.PieceColor;

import java.util.List;

public class Knight extends ChessPiece {

    public Knight(PieceColor color) {
        super(color);
    }

    @Override
    public List<MoveGenerator> getMoveGenerators() {
        return List.of(new KnightMoveGenerator());
    }
}
