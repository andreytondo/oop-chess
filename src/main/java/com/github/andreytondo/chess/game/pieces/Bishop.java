package com.github.andreytondo.chess.game.pieces;


import com.github.andreytondo.chess.game.moves.generators.DiagonalMoveGenerator;
import com.github.andreytondo.chess.game.moves.generators.MoveGenerator;
import com.github.andreytondo.chess.game.pieces.utils.ChessPiece;
import com.github.andreytondo.chess.game.pieces.utils.PieceColor;
import com.github.andreytondo.chess.game.pieces.utils.PieceType;

import java.util.List;

public class Bishop extends ChessPiece {

    public Bishop(PieceColor color) {
        super(color);
    }

    @Override
    public List<MoveGenerator> getMoveGenerators() {
        return List.of(new DiagonalMoveGenerator());
    }

    @Override
    public PieceType getType() {
        return PieceType.BISHOP;
    }
}
