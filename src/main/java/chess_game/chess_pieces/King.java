package chess_game.chess_pieces;

import chess_game.ChessBoard;
import chess_game.Position;


//TODO: add castling

public class King extends Piece {

    private boolean checked = false;

    public King(boolean white) {
        super(white);
    }


    public boolean isChecked(ChessBoard board) {
        Position kingPosition = (this.isWhite() ? board.getWhiteKingPosition() : board.getBlackKingPosition());
        String position = kingPosition.getPosition();
        char column = position.charAt(0);
        char row = position.charAt(1);

        // Checking same row, columns on the left
        for (char currentCol = (char) (column - 1); currentCol >= 'A'; currentCol--) {
            Position currentPosition = board.getPosition("" + currentCol + row);
            Piece piece = currentPosition.getPiece();
            if (piece != null) {
                if (piece.isWhite() == this.isWhite()) return false;
                return piece.canMoveTo(currentPosition, kingPosition, board);
            }
        }

        // Checking same row, columns on the right
        for (char currentCol = (char) (column + 1); currentCol <= 'H'; currentCol++) {
            Position currentPosition = board.getPosition("" + currentCol + row);
            Piece piece = currentPosition.getPiece();
            if (piece != null) {
                if (piece.isWhite() == this.isWhite()) return false;
                return piece.canMoveTo(currentPosition, kingPosition, board);
            }
        }

        // Checking same column, rows below
        for (char currentRow = (char) (row - 1); currentRow >= '1'; currentRow--) {
            Position currentPosition = board.getPosition("" + column + currentRow);
            Piece piece = currentPosition.getPiece();
            if (piece != null) {
                if (piece.isWhite() == this.isWhite()) return false;
                return piece.canMoveTo(currentPosition, kingPosition, board);
            }
        }
        // Checking same column, rows above
        for (char currentRow = (char) (row + 1); currentRow <= '8'; currentRow++) {
            Position currentPosition = board.getPosition("" + column + currentRow);
            Piece piece = currentPosition.getPiece();
            if (piece != null) {
                if (piece.isWhite() == this.isWhite()) return false;
                return piece.canMoveTo(currentPosition, kingPosition, board);
            }
        }


        // Checking from diagonal below
        char currentRow = (char) (row - 1);
        char currentColumn = (char) (column - 1);
        while (currentColumn >= 'A' && currentRow >= '1') {
            Position currentPosition = board.getPosition("" + currentColumn + currentRow);
            Piece piece = currentPosition.getPiece();
            if (piece != null) {
                if (piece.isWhite() == this.isWhite()) return false;
                return piece.canMoveTo(currentPosition, kingPosition, board);
            }
            currentRow--;
            currentColumn--;
        }

        currentRow = (char) (row + 1);
        currentColumn = (char) (column + 1);
        while (currentColumn <= 'H' && currentRow <= '8') {
            Position currentPosition = board.getPosition("" + currentColumn + currentRow);
            Piece piece = currentPosition.getPiece();
            if (piece != null) {
                if (piece.isWhite() == this.isWhite()) return false;
                return piece.canMoveTo(currentPosition, kingPosition, board);
            }
            currentRow++;
            currentColumn++;
        }

        this.checked = false;
        return false;
    }



    //TODO: add checked support
    @Override
    public boolean canMoveTo(Position start, Position destination, ChessBoard board) {
        if (!start.getPiece().equals(this)) return false;
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
