package com.github.andreytondo.chess.game.board;

import com.github.andreytondo.chess.game.Fen;
import com.github.andreytondo.chess.game.moves.Move;
import com.github.andreytondo.chess.game.moves.validators.CollisionMoveValidator;
import com.github.andreytondo.chess.game.moves.validators.MoveValidator;
import com.github.andreytondo.chess.game.moves.validators.SelfCheckValidator;
import com.github.andreytondo.chess.game.pieces.utils.ChessPiece;
import com.github.andreytondo.chess.game.pieces.utils.PieceColor;
import com.github.andreytondo.chess.game.pieces.utils.Position;

import java.util.List;

public class Chessboard {

    private final ChessPiece[][] board;
    private PieceColor activeColor;

    public Chessboard(ChessPiece[][] board, PieceColor pieceColor) {
        this.board = board;
        this.activeColor = pieceColor;
    }

    public Chessboard(String fen) {
        Fen.GameInfo gameInfo = Fen.parseFen(fen);
        this.board = gameInfo.board();
        this.activeColor = gameInfo.activeColor();
    }

    public List<Move> getValidMoves(Position position) {
        ChessPiece piece = getPieceAt(position);

        if (piece == null) {
            return List.of();
        }

        List<Move> allMoves = piece.generateMoves(position);
        for (MoveValidator validator : getValidators()) {
            allMoves.removeIf(move -> !validator.isValidMove(board, piece, move));
        }

        return allMoves;
    }

    public PieceColor getActiveColor() {
        return activeColor;
    }

    public ChessPiece getPieceAt(Position position) {
        return board[position.row()][position.column()];
    }

    public List<MoveValidator> getValidators() {
        return List.of(
                new CollisionMoveValidator(),
                new SelfCheckValidator()
        );
    }

    public void movePiece(Move move) {
        ChessPiece piece = getPieceAt(move.from());
        if (piece == null || piece.getColor() != activeColor) {
            return;
        }

        if (getValidMoves(move.from()).contains(move)) {
            applyMove(move, piece);
            activeColor = activeColor.getOppositeColor();
        }

    }

    public void applyMove(Move move, ChessPiece piece) {
        board[move.to().row()][move.to().column()] = piece;
        board[move.from().row()][move.from().column()] = null;
    }

}
