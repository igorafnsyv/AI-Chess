package chess_game.chess_pieces;

import chess_game.ChessBoard;
import chess_game.Position;

public class Pawn extends Piece {

    private boolean madeFirstMove;

    public Pawn(boolean white) {
        super(white);
        madeFirstMove = false;
    }

    @Override
    public void moveTo(Position start, Position destination) {
        super.moveTo(start, destination);
        if (!madeFirstMove) {
            madeFirstMove = true;
        }
    }

    @Override
    public String toString() {
        String color = super.toString();
        return color + "P";
    }

    @Override
    public boolean canMoveTo(Position start, Position destination, ChessBoard board) {
        if (start == destination) return false;
        if (start.getPiece() != this) return false;
        if (!canReach(start, destination)) return false;
        if (!allBetweenPositionsFree(start, destination, board)) return false;
        boolean result;
        //White pawn can only move up
        if (this.isWhite()) {
            result = start.getPosition().charAt(1) < destination.getPosition().charAt(1);

        //Black pawn can only move down
        } else {
            result = start.getPosition().charAt(1) > destination.getPosition().charAt(1);
        }

        //If it is a diagonal move, destination needs to contain piece of opposite color
        if (start.diagonalDistanceTo(destination) == 1) {
            //No diagonal move when no enemy
            if (destination.getPiece() == null) return false;
            result = result && (this.isWhite() != destination.getPiece().isWhite());
        } else if (start.rowDistanceTo(destination) <= 2) {
            result = result && destination.getPiece() == null;
        }

        return result;
    }

    private boolean canReach(Position start, Position destination) {
        boolean result = false;
        if (!madeFirstMove) {
            int rowDistance = start.rowDistanceTo(destination);
            result = rowDistance == 1 || rowDistance == 2 ;
        }
        result = result || start.rowDistanceTo(destination) == 1;
        if (start.diagonalDistanceTo(destination) == 1) {
            result = true;
        }
        return result;
    }

    public boolean allBetweenPositionsFree(Position start, Position destination, ChessBoard board) {
        char[] startChars = start.getPosition().toCharArray();
        char[] destinationChars = destination.getPosition().toCharArray();

        int verticalDistance = Math.abs(destinationChars[1] - startChars[1]);
        for (int i = 1; i < verticalDistance; i++) {
            Position position = board.getPosition(String.valueOf(startChars[0]) + (char)(startChars[1] + i));
            if (position.getPiece() != null) return false;
        }
        return true;
    }
}
