package com.github.andreytondo.chess.game.moves.validators;

import com.github.andreytondo.chess.game.moves.Move;
import com.github.andreytondo.chess.game.pieces.utils.ChessPiece;

public interface MoveValidator {

    boolean isValidMove(ChessPiece[][] board, ChessPiece piece, Move move);
}
