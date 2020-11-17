package chess_game.chess_pieces;

import chess_game.ChessBoard;
import chess_game.Position;

public class Bishop extends Piece {


    public Bishop(boolean white) {
        super(white);
    }

    @Override
    public boolean canMoveTo(Position start, Position destination, ChessBoard board) {
        if (!start.getPiece().equals(this)) return false;
        if (start.diagonalDistanceTo(destination) == -1) return false;
        if (!allBetweenPositionsFree(start, destination, board)) return false;
        boolean destinationFree = destination.getPiece() == null;
        boolean result = true;

        // If destination cell occupied, can move there only if pieces are of different color
        if (!destinationFree) {
            result = this.isWhite() != destination.getPiece().isWhite();
        }

        return result;
    }

    public boolean allBetweenPositionsFree(Position start, Position destination, ChessBoard board) {
        String position1 = start.getPosition();
        char startCol = position1.charAt(0);
        char startRow = position1.charAt(1);

        String position2 = destination.getPosition();
        char destinationCol = position2.charAt(0);
        char destinationRow = position2.charAt(1);

        int colStep = 1;
        int rowStep = 1;
        if (startCol > destinationCol && startRow > destinationRow) {
            colStep = -1;
            rowStep = -1;
        }

        for (int i = 0; i < destination.diagonalDistanceTo(destination); i++) {
            Position position = board.getPosition(String.valueOf(startCol += colStep) + String.valueOf(startRow += rowStep));
            if (position.getPiece() != null) return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return "B";
    }
}
