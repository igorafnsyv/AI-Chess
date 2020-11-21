package chess_game.chess_pieces;

import chess_game.ChessBoard;
import chess_game.Position;

public class Knight extends Piece {

    public Knight(boolean white) {
        super(white);
    }

    @Override
    public boolean canMoveTo(Position start, Position destination, ChessBoard board) {
        if (start.getPiece() != this) return false;
        if (!canReach(start, destination)) return false;
        boolean result = true;
        if (destination.getPiece() != null && destination.getPiece().isWhite() == this.isWhite()) {
            result = false;
        }

        return result;
    }

    @Override
    public String toString() {
        String color = super.toString();
        return color + "N";
    }

    private boolean canReach(Position start, Position destination) {
        char[] startChars = start.getPosition().toCharArray();
        char[] destinationChars= destination.getPosition().toCharArray();

        int rowMove = Math.abs(startChars[0] - destinationChars[0]);
        int columnMove = Math.abs(destinationChars[1] - startChars[1]);
        //Move 2 up/down 1 left/right
        if (rowMove == 1 && columnMove == 2) {
            return true;
        }
        //Move 1 up/down 2 left/right
        if (rowMove == 2 && columnMove == 1) {
            return true;
        }
        return false;
    }
}
