package com.github.andreytondo.chess.game.moves.validators;

import com.github.andreytondo.chess.game.moves.Move;
import com.github.andreytondo.chess.game.pieces.Knight;
import com.github.andreytondo.chess.game.pieces.Pawn;
import com.github.andreytondo.chess.game.pieces.utils.ChessPiece;

public class CollisionMoveValidator implements MoveValidator {

    @Override
    public boolean isValidMove(ChessPiece[][] board, ChessPiece piece, Move move) {
        int row = move.to().row();
        int col = move.to().column();

        if (isNotWithinBounds(row, col)) {
            return false;
        }

        ChessPiece target = board[row][col];
        if (piece instanceof Knight) {
            return isTargetDifferentColor(piece, target);
        }

        if (piece instanceof Pawn) {
            return isPawnTargetReachable(board, move) && isTargetDifferentColor(piece, target);
        }


        return isTargetReachable(board, piece, move) && isTargetDifferentColor(piece, target);
    }

    private boolean isPawnTargetReachable(ChessPiece[][] board, Move move) {
        int fromCol = move.from().column();
        int toRow = move.to().row();
        int toCol = move.to().column();

        if (fromCol == toCol) {
            return board[toRow][toCol] == null;
        }

       if (Math.abs(fromCol - toCol) == 1) {
            return board[toRow][toCol] != null;
       }

        return true;
    }

    boolean isTargetDifferentColor(ChessPiece piece, ChessPiece target) {
        return target == null || target.getColor() != piece.getColor();
    }

    boolean isTargetReachable(ChessPiece[][] board, ChessPiece piece, Move move) {
        if (isDiagonalMove(move)) {
            return isDiagonalTargetReachable(board, piece, move);
        } else if (isStraightMove(move)) {
            return isStraightTargetReachable(board, piece, move);
        }
        return false;
    }

    private boolean isStraightTargetReachable(ChessPiece[][] board, ChessPiece piece, Move move) {
        int fromRow = move.from().row();
        int fromCol = move.from().column();
        int toRow = move.to().row();
        int toCol = move.to().column();

        if (fromRow == toRow) {
            int colDirection = Integer.signum(toCol - fromCol);
            for (int col = fromCol + colDirection; col != toCol; col += colDirection) {
                if (board[fromRow][col] != null) {
                    return false;
                }
            }
        } else if (fromCol == toCol) {
            int rowDirection = Integer.signum(toRow - fromRow);
            for (int row = fromRow + rowDirection; row != toRow; row += rowDirection) {
                if (board[row][fromCol] != null) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean isDiagonalTargetReachable(ChessPiece[][] board, ChessPiece piece, Move move) {
        int fromRow = move.from().row();
        int fromCol = move.from().column();
        int toRow = move.to().row();
        int toCol = move.to().column();

        int rowDirection = Integer.signum(toRow - fromRow);
        int colDirection = Integer.signum(toCol - fromCol);

        int row = fromRow + rowDirection;
        int col = fromCol + colDirection;

        while (row != toRow && col != toCol) {
            if (board[row][col] != null) {
                return false;
            }
            row += rowDirection;
            col += colDirection;
        }

        return true;
    }

    private boolean isNotWithinBounds(int row, int col) {
        return row < 0 || row >= 8 || col < 0 || col >= 8;
    }

    boolean isDiagonalMove(Move move) {
        return move.from().row() != move.to().row() && move.from().column() != move.to().column();
    }

    boolean isStraightMove(Move move) {
        return move.from().row() == move.to().row() || move.from().column() == move.to().column();
    }
}
