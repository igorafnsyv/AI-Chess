package chess_game.chess_pieces;

import chess_game.ChessBoard;
import chess_game.Position;

public abstract class Piece {


    private final boolean white;

    public Piece(boolean white) {
        this.white = white;
    }

    public void moveTo(Position start, Position destination) {
        start.setPiece(null);
        destination.setPiece(this);
    }


    public abstract boolean canMoveTo(Position start, Position destination, ChessBoard board);


    public boolean isWhite() {
        return white;
    }

    @Override
    public String toString() {
        return isWhite() ? "W" : "B";
    }
}
