package chess_game;

import chess_game.chess_pieces.King;
import chess_game.chess_pieces.Piece;

import java.util.ArrayList;
import java.util.List;

public class CheckMateDetector {

    /*
    Position from which white pieces can check the Black King
     */
    private List<Position> whiteCheckPositions;

    /*
    Position from which black pieces can check the White King
     */
    private List<Position> blackCheckPositions;

    public CheckMateDetector() {}

    public boolean isWhiteKingChecked(ChessBoard board) {
        List<Piece> blackPieces = board.getBlackPieces();
        Position whiteKingPosition = board.getWhiteKingPosition();
        boolean result = false;
        blackCheckPositions = new ArrayList<>();
        for (Piece piece : blackPieces) {
            if (piece instanceof King) continue;
            if (piece.getLegalMovePositions(board).contains(whiteKingPosition)) {
                blackCheckPositions.add(piece.getPosition());
                result = true;
            }
        }
        return result;
    }

    /*
    Mate on a king happens when
    King cannot make a legal move
    A piece cannot be placed between King and checking piece -> not implemented yet
    A piece which checked King cannot be attacked
     */
    public boolean isMateOnWhiteKing(ChessBoard board) {
        if (blackCheckPositions.isEmpty()) return false;
        King whiteKing = (King) board.getWhiteKingPosition().getPiece();
        if (!whiteKing.getLegalMovePositions(board).isEmpty()) return false;
        List<Piece> whitePieces = board.getWhitePieces();
        for (Piece piece : whitePieces) {
            List<Position> legalMovePositions = piece.getLegalMovePositions(board);
            for (Position legalMovePosition : legalMovePositions) {
                //boolean operation will return false in case no legalMovePosition
                blackCheckPositions.remove(legalMovePosition);
            }
        }

        return !blackCheckPositions.isEmpty();
    }

    public boolean isWhiteKingCheckMate(ChessBoard board) {
        return isWhiteKingChecked(board) && isMateOnWhiteKing(board);
    }

    public boolean isBlackKingChecked(ChessBoard board) {
        List<Piece> whitePieces = board.getWhitePieces();
        Position blackKingPosition = board.getBlackKingPosition();
        whiteCheckPositions = new ArrayList<>();
        boolean result = false;
        for (Piece piece : whitePieces) {
            if (piece instanceof King) continue;
            if (piece.getLegalMovePositions(board).contains(blackKingPosition)) {
                whiteCheckPositions.add(piece.getPosition());
                result = true;
            }
        }
        return result;
    }

    public boolean isMateOnBlackKing(ChessBoard board) {
        if (whiteCheckPositions.isEmpty()) return false;
        King blackKing = (King) board.getBlackKingPosition().getPiece();
        if (!blackKing.getLegalMovePositions(board).isEmpty()) return false;
        List<Piece> blackPieces = board.getBlackPieces();
        for (Piece piece : blackPieces) {
            List<Position> legalMovePositions = piece.getLegalMovePositions(board);
            for (Position legalMovePosition : legalMovePositions) {
                //boolean operation will return false in case no legalMovePosition
                whiteCheckPositions.remove(legalMovePosition);
            }
        }

        return !whiteCheckPositions.isEmpty();
    }

    public boolean isBlackKingCheckMate(ChessBoard board) {
        return isBlackKingChecked(board) && isMateOnBlackKing(board);
    }

}
