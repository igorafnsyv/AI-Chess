package chess_game.chess_pieces;

import chess_game.ChessBoard;
import chess_game.Position;

import java.util.List;

public abstract class Piece {


    private final boolean white;
    private Position position;

    public Piece(boolean white) {
        this.white = white;
    }

    public Piece(boolean white, Position position) {
        this.white = white;
        this.position = position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public void moveTo(Position start, Position destination) {
        start.setPiece(null);
        destination.setPiece(this);
        this.position = destination;
    }


    public boolean canMoveTo(Position destination, ChessBoard board) {
        return this.getLegalMovePositions(board).contains(destination);
    }

    public  abstract List<Position> getLegalMovePositions(ChessBoard board);


    public boolean isWhite() {
        return white;
    }


    @Override
    public String toString() {
        return isWhite() ? "W" : "B";
    }
}
