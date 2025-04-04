package com.github.andreytondo.chess.game.pieces;

import com.github.andreytondo.chess.game.moves.generators.DiagonalMoveGenerator;
import com.github.andreytondo.chess.game.moves.generators.MoveGenerator;
import com.github.andreytondo.chess.game.moves.generators.StraigthMoveGenerator;
import com.github.andreytondo.chess.game.pieces.utils.ChessPiece;
import com.github.andreytondo.chess.game.pieces.utils.PieceColor;
import com.github.andreytondo.chess.game.pieces.utils.PieceType;

import java.util.List;

public class Queen extends ChessPiece {

    public Queen(PieceColor color) {
        super(color);
    }

    @Override
    public List<MoveGenerator> getMoveGenerators() {
        return List.of(new StraigthMoveGenerator(), new DiagonalMoveGenerator());
    }

    @Override
    public PieceType getType() {
        return PieceType.QUEEN;
    }
}
