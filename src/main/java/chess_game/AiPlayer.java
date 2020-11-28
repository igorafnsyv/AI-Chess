package chess_game;

import chess_game.chess_pieces.Piece;

import java.util.LinkedList;
import java.util.List;

/*
Currently plays Black
 */
public class AiPlayer extends Player {

    public AiPlayer(boolean white, String name) {
        super(white, name);
    }

    @Override
    public boolean isComputer() {
        return true;
    }

    /*
        Idea behind this method:
        Collect all legal moves AI player can perform and filter out moves which will put Black King in check
        As according to chess rules, when a King is checked, the next move must cancel the check.

        In case no legal moves, the game ends?
         */
    public List<Move> getLegalMoves(ChessBoard board) {
        List<Move> moves = new LinkedList<>();
        for (Piece piece : board.getBlackPieces()) {
            List<Position> legalPositions = piece.getLegalMovePositions(board);
            for (Position potentialPosition : legalPositions) {
                Move potentialMove = new Move(piece.getPosition().toString(), potentialPosition.toString());
                ChessBoard boardAfterMove = potentialMove.getBoardStateAfterMove(board);
                CheckMateDetector detector = new CheckMateDetector();
                if (!detector.isBlackKingChecked(boardAfterMove)) {
                    moves.add(potentialMove);
                }
            }

        }
        return moves;
    }

    public Move findBestMove(List<Move> moves, ChessBoard board, int depth) {

        long maxValue = Long.MIN_VALUE;
        Move bestMove = null;

        for (Move potentialMove : moves) {
            long value = max(potentialMove.getBoardStateAfterMove(board), depth, Long.MIN_VALUE, Long.MAX_VALUE);
            if (value > maxValue) {
                maxValue = value;
                bestMove = potentialMove;
            }

        }
        return bestMove;
    }

    // make sure check and checkmate are handled
    public long max(ChessBoard board, int depth, long alpha, long beta) {
        if (depth == 0) {
            return BoardStateEvaluator.evaluateBlackPositions(board);
        }
        long max = Long.MIN_VALUE;
        List<Move> whiteLegalMoves = new LinkedList<>();
        for (Piece whitePiece : board.getWhitePieces()) {
            List<Position> legalMovePositions = whitePiece.getLegalMovePositions(board);
            for (Position potentialPosition : legalMovePositions) {
                Move potentialMove = new Move(whitePiece.getPosition().toString(), potentialPosition.toString());
                whiteLegalMoves.add(potentialMove);
            }
        }
        for (Move potentialMove : whiteLegalMoves) {
            long value = min(potentialMove.getBoardStateAfterMove(board), depth - 1, alpha, beta);
            max = Math.max(value, max);
            if (value >= beta) {
                return value;
            }
            alpha = Math.max(alpha, value);
        }
        return max;
    }

    public long min(ChessBoard board, int depth, long alpha, long beta) {
        if (depth == 0) {
            return -BoardStateEvaluator.evaluateWhitePositions(board);
        }
        long min = Long.MAX_VALUE;
        List<Move> blackLegalMoves = new LinkedList<>();
        for (Piece blackPiece : board.getBlackPieces()) {
            List<Position> legalMovePositions = blackPiece.getLegalMovePositions(board);
            for (Position potentialMovePosition : legalMovePositions) {
                Move potentialMove = new Move(blackPiece.getPosition().toString(), potentialMovePosition.toString());
                blackLegalMoves.add(potentialMove);
            }
        }
        for (Move potentialMove : blackLegalMoves) {
            long value = max(potentialMove.getBoardStateAfterMove(board), depth - 1, alpha, beta);
            min = Math.min(value, min);
            if (value <= alpha) {
                return value;
            }
            beta = Math.min(beta, value);
        }
        return min;
    }


}
