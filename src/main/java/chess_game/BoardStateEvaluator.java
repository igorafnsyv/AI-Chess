package chess_game;

import chess_game.chess_pieces.Piece;

import java.util.List;

public class BoardStateEvaluator {

    public static int whitePieceTotalValue(ChessBoard board) {
        return board.getWhitePieces().stream().map(Piece::getValue).reduce(0, Integer::sum);
    }

    public static long whitePiecePossibleMoveCount(ChessBoard board) {
        return board.getWhitePieces().stream().map(piece -> piece.getLegalMovePositions(board))
                .map(List::size)
                .reduce(0, Integer::sum);
    }

    public static int blackPieceTotalValue(ChessBoard board) {
        return board.getWhitePieces().stream().map(Piece::getValue).reduce(0, Integer::sum);
    }

    public static long blackPiecePossibleMoveCount(ChessBoard board) {
        return board.getBlackPieces().stream().map(piece -> piece.getLegalMovePositions(board))
                .map(List::size)
                .reduce(0, Integer::sum);
    }

}
