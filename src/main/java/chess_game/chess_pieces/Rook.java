package chess_game.chess_pieces;

import chess_game.ChessBoard;
import chess_game.Position;

public class Rook extends Piece {

    public Rook(boolean white) {
        super(white);
    }

    @Override
    public boolean canMoveTo(Position start, Position destination, ChessBoard board) {
        if (start.getPiece() != this) return false;
        // Rook can move only vertically, horizontally
        if (!canReach(start, destination)) return false;
        if (!allBetweenPositionsFree(start, destination, board)) return false;
        boolean destinationFree = destination.getPiece() == null;
        boolean result = true;
        if (!destinationFree) {
            if (destination.getPiece().isWhite() == this.isWhite()) result = false;
        }
        return result;
    }

    @Override
    public String toString() {
        String color = super.toString();
        return color + "R";
    }

    private boolean canReach(Position start, Position destination) {
        boolean result = false;
        if (start.columnDistanceTo(destination) != -1 || start.rowDistanceTo(destination) != -1) {
            result  = true;
        }
        return result;
    }

    private boolean allBetweenPositionsFree(Position start, Position destination, ChessBoard board) {
        String position1 = start.getPosition();
        char startCol = position1.charAt(0);
        char startRow = position1.charAt(1);

        String position2 = destination.getPosition();
        char destinationCol = position2.charAt(0);
        char destinationRow = position2.charAt(1);

        int colStep = 0;
        int rowStep = 0;

        if (startCol == destinationCol) {
            rowStep = 1;
        }
        else if (startRow == destinationRow) {
            colStep = 1;
        }

        // in this case we are moving left
        if (startRow > destinationRow) {
            rowStep = -rowStep;
        }
        //or moving down
        else if (startCol > destinationCol) {
            colStep = -colStep;
        }

        for (int i = 0; i < start.distanceTo(destination) - 1; i++) {
            Position position = board.getPosition(String.valueOf( startCol += colStep) + String.valueOf(startRow += rowStep));
            if (position.getPiece() != null) return false;
        }
        return true;
    }


}
