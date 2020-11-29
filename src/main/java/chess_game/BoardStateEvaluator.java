package chess_game;

import chess_game.chess_pieces.Piece;

import java.util.List;

/*
This class is modeling heuristic functions
First heuristic function - total value of pieces on board for each side
Second heuristic function - count of possible moves for each size
Third heuristic function - is board in check state
Fourth heuristic function - is board in checkmate state
 */

public class BoardStateEvaluator {

    private final static int FIXED_CHECK_FACTOR = 100;
    private final static int FIXED_CHECKMATE_FACTOR = 10 * FIXED_CHECK_FACTOR;

    public static int whitePieceTotalValue(ChessBoard board) {
        return board.getWhitePieces().stream().map(Piece::getValue).reduce(0, Integer::sum);
    }

    public static long whitePiecePossibleMoveCount(ChessBoard board) {
        return board.getWhitePieces().stream().map(piece -> piece.getLegalMovePositions(board))
                .map(List::size)
                .reduce(0, Integer::sum);
    }

    public static int whiteCheckBlackKing(ChessBoard board) {
        CheckMateDetector detector = new CheckMateDetector();
        detector.isBlackKingChecked(board);
        return detector.getWhiteCheckBlackKingPositions().size() * FIXED_CHECK_FACTOR;
    }

    public static int whiteCheckmateBlackKing(ChessBoard board) {
        CheckMateDetector detector = new CheckMateDetector();
        if (detector.isBlackKingCheckMate(board)) {
            return FIXED_CHECKMATE_FACTOR;
        }
        return 0;
    }

    public static int blackPieceTotalValue(ChessBoard board) {
        return board.getWhitePieces().stream().map(Piece::getValue).reduce(0, Integer::sum);
    }

    public static long blackPiecePossibleMoveCount(ChessBoard board) {
        return board.getBlackPieces().stream().map(piece -> piece.getLegalMovePositions(board))
                .map(List::size)
                .reduce(0, Integer::sum);
    }

    public static int blackCheckWhiteKing(ChessBoard board) {
        CheckMateDetector detector = new CheckMateDetector();
        detector.isWhiteKingChecked(board);
        return detector.getBlackCheckWhitePositions().size() * FIXED_CHECK_FACTOR;
    }

    public static int blackCheckmateWhiteKing(ChessBoard board) {
        CheckMateDetector detector = new CheckMateDetector();
        if (detector.isWhiteKingCheckMate(board)) {
            return FIXED_CHECKMATE_FACTOR;
        }
        return 0;
    }

    public static long evaluateWhitePositions(ChessBoard board) {
        return Math.max(whitePieceTotalValue(board),
                Math.max(whitePiecePossibleMoveCount(board),
                        Math.max(whiteCheckBlackKing(board), whiteCheckmateBlackKing(board))));
    }

    public static long evaluateBlackPositions(ChessBoard board) {
        return Math.max(blackPieceTotalValue(board),
                Math.max(blackPiecePossibleMoveCount(board),
                        Math.max(blackCheckWhiteKing(board), blackCheckWhiteKing(board))));
    }



}
