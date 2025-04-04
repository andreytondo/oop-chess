package com.github.andreytondo.chess.player.java;

import com.github.andreytondo.chess.game.board.Chessboard;
import com.github.andreytondo.chess.game.evaluator.ChessboardEvaluator;
import com.github.andreytondo.chess.game.moves.Move;
import com.github.andreytondo.chess.game.pieces.utils.ChessPiece;
import com.github.andreytondo.chess.game.pieces.utils.PieceColor;
import com.github.andreytondo.chess.game.pieces.utils.Position;
import com.github.andreytondo.chess.player.ChessPlayer;

import java.util.ArrayList;
import java.util.List;

public class JavaChessPlayer implements ChessPlayer {

    private final PieceColor color;
    private final Chessboard chessboard;
    private Move move;

    public JavaChessPlayer(PieceColor color, Chessboard chessboard) {
        this.color = color;
        this.chessboard = new Chessboard(deepCopyBoard(chessboard.getBoard()), chessboard.getActiveColor());
    }

    @Override
    public Move makeMove() {
        return move;
    }

    @Override
    public String getName() {
        return "Java Player v1.0";
    }

    public int search(int depth, int alpha, int beta) {
        if (depth == 0) {
            return new ChessboardEvaluator(chessboard).evaluate();
        }

        List<Move> moves = generateMoves();

        if (moves.isEmpty()) {
            return new ChessboardEvaluator(chessboard).evaluate();
        }

        for (Move move : moves) {
            chessboard.movePiece(move);
            int score = -search(depth - 1, alpha, beta);
            chessboard.undoMove();

            if (score >= beta) {
                this.move = move;
                return beta;
            }

            alpha = Math.max(alpha, score);
        }

        if (move == null) {
            move = moves.get(0);
        }
        return alpha;
    }

    public List<Move> generateMoves() {
        List<Move> moves = new ArrayList<>();
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Position pos = new Position(row, col);
                if (chessboard.getPieceAt(pos) != null && chessboard.getPieceAt(pos).getColor() == color) {
                    moves.addAll(chessboard.getValidMoves(pos));
                }
            }
        }
        return moves;
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

}
