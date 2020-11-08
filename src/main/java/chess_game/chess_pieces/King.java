package chess_game.chess_pieces;

import chess_game.ChessBoard;
import chess_game.Position;


//TODO: add castling

public class King extends Piece {

    private boolean checked = false;

    public King(boolean white) {
        super(white);
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean isChecked() {
        return checked;
    }


    //TODO: add checked support
    @Override
    public boolean canMoveTo(Position start, Position destination, ChessBoard board) {
        if (start.getPiece() != this) return false;
        if (!canReach(start, destination)) return false;
        boolean differentPosition = !start.equals(destination);
        boolean emptyPosition = destination.getPiece() == null;
        if (differentPosition && emptyPosition) return true;
        if (differentPosition) {
            // If destination is not empty, pieces at two position have to be of different color
            return start.getPiece().isWhite() != destination.getPiece().isWhite();
        }

        return false;
    }

    private boolean canReach(Position start, Position destination) {
        return start.distanceTo(destination) == 1;
    }
}
