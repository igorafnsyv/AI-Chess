package chess_game;

import chess_game.chess_pieces.Piece;

import java.util.List;

/*
This class is modeling heuristic functions
First heuristic function - total value of pieces on board for each side
Second heuristic function - count of possible moves for each size
Third heuristic function - is board in check state
 */

public class BoardStateEvaluator {

    private static int FIXED_CHECK_FACTOR = 100;

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

}
