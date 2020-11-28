package chess_game;

import chess_game.chess_pieces.Piece;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
Currently plays Black
 */
public class AiPlayer extends Player {

    public AiPlayer(boolean white, String name) {
        super(white, name);
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

        long maxValue = 0;
        Move bestMove = null;

        for (Move potentialMove : moves) {
            long value = max(potentialMove.getBoardStateAfterMove(board), depth);
            if (value > maxValue) {
                maxValue = value;
                bestMove = potentialMove;
            }

        }
        System.out.println(maxValue);
        return bestMove;
    }

    // make sure check and checkmate are handled
    public long max(ChessBoard board, int depth) {
        if (depth == 0 ) {
//            System.out.println(BoardStateEvaluator.evaluateBlackPositions(board) + " black val");
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
            long value = min(potentialMove.getBoardStateAfterMove(board), depth - 1);
            max = Math.max(value, max);
        }
        return max;
    }

    public long min(ChessBoard board, int depth) {
        if (depth == 0) {
//            System.out.println(BoardStateEvaluator.evaluateWhitePositions(board) + " white value");
            return BoardStateEvaluator.evaluateWhitePositions(board);
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
            if (board.getPosition(potentialMove.getStartPosition()).getPiece() == null) {
                System.out.println(potentialMove);
                System.out.println(board.getBlackKingPosition() + " Black king position");
                System.out.println(board.getBlackKingPosition().getPiece());
                System.out.println(board);
            }
            long value = max(potentialMove.getBoardStateAfterMove(board), depth - 1);
            min = Math.min(value, min);
        }
        return min;
    }


}
