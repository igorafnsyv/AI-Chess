package chess_game.chess_pieces;

import chess_game.ChessBoard;
import chess_game.Position;

public class Queen extends Piece{

    public Queen(boolean white) {
        super(white);
    }


    //TODO: need to check how to attack king
    @Override
    public boolean canMoveTo(Position start, Position destination, ChessBoard board) {
        if (start.getPiece() != this) return false;
        if (!canReach(start, destination)) return false;
        if (!allBetweenPositionsFree(start, destination, board)) return false;
        boolean destinationFree = destination.getPiece() == null;
        boolean result = destinationFree;
        if (!destinationFree) {
            result = destination.getPiece().isWhite() != this.isWhite();
        }


        return result;
    }

    public boolean canReach(Position start, Position destination) {
        return start.distanceTo(destination) != -1;

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

        if (startCol == destinationCol) {
            colStep = 0;
        }

        if (startRow == destinationRow) {
            rowStep = 0;
        }

        for (int i = 0; i < start.distanceTo(destination) - 1; i++) {
            Position position = board.getPosition(String.valueOf( startCol += colStep) + String.valueOf(startRow += rowStep));
            if (position.getPiece() != null) return false;
        }

        return true;
    }


}
