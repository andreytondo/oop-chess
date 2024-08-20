package com.github.andreytondo.chess.game.pieces;

import com.github.andreytondo.chess.game.moves.generators.MoveGenerator;
import com.github.andreytondo.chess.game.moves.generators.PawnMoveGenerator;
import com.github.andreytondo.chess.game.pieces.utils.ChessPiece;
import com.github.andreytondo.chess.game.pieces.utils.PieceColor;

import java.util.List;

public class Pawn extends ChessPiece {

    public Pawn(PieceColor color) {
        super(color);
    }

    @Override
    public List<MoveGenerator> getMoveGenerators() {
        return List.of(new PawnMoveGenerator(getColor()));
    }
}