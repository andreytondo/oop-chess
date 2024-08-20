package com.github.andreytondo.chess.game.pieces;


import com.github.andreytondo.chess.game.moves.generators.KingMoveGenerator;
import com.github.andreytondo.chess.game.moves.generators.MoveGenerator;
import com.github.andreytondo.chess.game.pieces.utils.ChessPiece;
import com.github.andreytondo.chess.game.pieces.utils.PieceColor;

import java.util.List;

public class King extends ChessPiece {

    public King(PieceColor color) {
        super(color);
    }

    @Override
    public List<MoveGenerator> getMoveGenerators() {
        return List.of(new KingMoveGenerator(getColor()));
    }
}