package com.github.andreytondo.chess.game.moves.validators;

import com.github.andreytondo.chess.game.board.Chessboard;
import com.github.andreytondo.chess.game.moves.Move;
import com.github.andreytondo.chess.game.pieces.King;
import com.github.andreytondo.chess.game.pieces.utils.ChessPiece;
import com.github.andreytondo.chess.game.pieces.utils.PieceColor;
import com.github.andreytondo.chess.game.pieces.utils.Position;

import java.util.stream.Stream;

public class SelfCheckValidator implements MoveValidator {

    @Override
    public boolean isValidMove(ChessPiece[][] board, ChessPiece piece, Move move) {
        ChessPiece[][] boardCopy = deepCopyBoard(board);

        Chessboard chessBoardCopy = new Chessboard(boardCopy, piece.getColor());
        chessBoardCopy.applyMove(move, piece);

        Position kingPosition = findKingPosition(chessBoardCopy, piece.getColor());

        // Check if any opponent piece can attack the king's position
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Position pos = new Position(row, col);
                ChessPiece opponentPiece = chessBoardCopy.getPieceAt(pos);

                if (opponentPiece != null && opponentPiece.getColor() != piece.getColor()) {
                    for (Move opponentMove : opponentPiece.generateMoves(pos)) {
                        boolean isValidMove = isValidMove(opponentMove, boardCopy, opponentPiece);
                        if (isValidMove && opponentMove.to().equals(kingPosition)) {
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }

    private boolean isValidMove(Move opponentMove, ChessPiece[][] boardCopy, ChessPiece opponentPiece) {
        return Stream.of(new CollisionMoveValidator())
                .allMatch(validator -> validator.isValidMove(boardCopy, opponentPiece, opponentMove));
    }

    private ChessPiece[][] deepCopyBoard(ChessPiece[][] board) {
        ChessPiece[][] boardCopy = new ChessPiece[board.length][];

        for (int i = 0; i < board.length; i++) {
            boardCopy[i] = new ChessPiece[board[i].length];
            for (int j = 0; j < board[i].length; j++) {
                ChessPiece piece = board[i][j];
                if (piece != null) {
                    boardCopy[i][j] = piece.clone();
                }
            }
        }

        return boardCopy;
    }

    private Position findKingPosition(Chessboard board, PieceColor color) {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                ChessPiece piece = board.getPieceAt(new Position(row, col));
                if (piece instanceof King && piece.getColor() == color) {
                    return new Position(row, col);
                }
            }
        }
        throw new IllegalStateException("King not found on the board");
    }
}
