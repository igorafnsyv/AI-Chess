package chess_game;

import chess_game.chess_pieces.Piece;

import java.util.Objects;

/**
 * Represents a chess piece position on the board
 * @author Igor Afanasyev
 */

public class Position {
    private final String position;
    private Piece piece;

    /**
     *
     * @param position - string representation of piece position in Column Row format (i.e. E4) where
     *                 column is a character between A and H
     */
    public Position(String position) {
        char[] chars = position.toLowerCase().toCharArray();
        if (chars[0] < 'a' || chars[0] > 'h' || chars[1] < '1' || chars[1] > '8') {
            throw new IllegalArgumentException(position + " is not a legal position");
        }
        this.position = position;
    }

    /*
    Position with no piece is created as piece will be later passed as a parameter
     */
    public Position makeCopy() {
        return new Position(this.position);
    }

    public Position(String position, Piece piece) {
        this(position);
        this.setPiece(piece);
    }


    /**
     * Position contains a chess Piece.
     * @param piece - a piece to be placed in this position
     */
    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public Piece getPiece() {
        return this.piece;
    }

    //TODO: replace distanceTo usage with below
    public int distanceTo(Position destination) {
        int colDistance = Math.abs(position.charAt(0) - destination.position.charAt(0));
        int rowDistance = Math.abs(position.charAt(1) - destination.position.charAt(1));
        // Same column, different rows
        if (colDistance == 0) return rowDistance;
        //Same row, different columns
        if (rowDistance == 0) return colDistance;
        //Diagonal move
        if (rowDistance == colDistance) return  rowDistance;
        return -1;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position1 = (Position) o;
        return this.position.equals(position1.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position);
    }

    @Override
    public String toString() {
        return this.position;
    }
}
