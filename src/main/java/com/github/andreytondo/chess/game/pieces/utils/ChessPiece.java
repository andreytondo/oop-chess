package com.github.andreytondo.chess.game.pieces.utils;

import com.github.andreytondo.chess.game.moves.Move;
import com.github.andreytondo.chess.game.moves.generators.MoveGenerator;

import java.util.ArrayList;
import java.util.List;

public abstract class ChessPiece implements Cloneable {

    private final PieceColor color;

    public ChessPiece(PieceColor color) {
        this.color = color;
    }

    public PieceColor getColor() {
        return color;
    }

    public List<Move> generateMoves(Position position) {
        List<Move> moves = new ArrayList<>();

        for (MoveGenerator moveGenerator : getMoveGenerators()) {
            moves.addAll(moveGenerator.generate(position));
        }

        return moves;
    }

    public abstract List<MoveGenerator> getMoveGenerators();

    @Override
    public ChessPiece clone() {
        try {
            return (ChessPiece) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
