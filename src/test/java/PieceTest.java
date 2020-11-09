import chess_game.Position;
import chess_game.chess_pieces.King;
import chess_game.chess_pieces.Piece;
import org.junit.Test;

import static org.junit.Assert.*;

public class PieceTest {

    @Test
    public void testGetPositionReturnsSamePosition() {
        Position position = new Position("E4");
        Piece piece = new King(false);
        position.setPiece(piece);
        assertEquals(position.getPiece(), piece);
    }

    @Test
    public void testIsWhiteReturnsTrue() {
        Piece piece = new King(true);
        assertTrue(piece.isWhite());
    }

    @Test
    public void testIsWhiteReturnsFalse() {
        Piece piece = new King(false);
        assertFalse(piece.isWhite());
    }

    @Test
    public void testIllegalColumnThrowsException() {
        try {
            new Position("I8");
            fail();
        } catch (IllegalArgumentException ex) {
            assertEquals(ex.getMessage(), "I8 is not a legal position");
        }
    }

    @Test
    public void testIllegalRowThrowsException() {
        try {
            new Position("A9");
            fail();
        } catch (IllegalArgumentException ex) {
            assertEquals(ex.getMessage(), "A9 is not a legal position");
        }
    }

    @Test
    public void testCanMoveToReturnTrue() {
        Position position = new Position("A1");
        Piece piece = new King(false);
        position.setPiece(piece);
        Position newPosition = new Position("A2");
        assertTrue(piece.canMoveTo(position, newPosition, null));
    }

    @Test
    public void testCanMoveReturnFalseWhenNoPieceInStart() {
        Position start = new Position("A1");
        Position destination = new Position("A2");
        Piece piece = new King(false);
        assertThrows(NullPointerException.class,() -> piece.canMoveTo(start, destination, null));
    }

    @Test
    public void testCanMoveToSamePositionReturnFalse() {
        Position start = new Position("A1");
        Position destination = new Position("A1");
        Piece piece = new King(false);
        start.setPiece(piece);
        assertFalse(piece.canMoveTo(start, destination, null));
    }

    @Test
    public void testCanMoveToSamePositionReturnsFalseIfSameColorOccupies() {
        Position start = new Position("A1");
        Position destination = new Position("A2");
        Piece piece = new King(false);
        Piece piece1 = new King(false);
        start.setPiece(piece);
        destination.setPiece(piece1);
        assertFalse(piece.canMoveTo(start, destination, null));
    }

    @Test
    public void testCanMoveToSamePositionReturnsTrueIfDifferentColorOccupies() {
        Position start = new Position("A1");
        Position destination = new Position("A2");
        Piece piece = new King(false);
        Piece piece1 = new King(true);
        start.setPiece(piece);
        destination.setPiece(piece1);
        assertTrue(piece.canMoveTo(start, destination, null));
    }

    @Test
    public void testDistanceToSamePositionReturns0() {
        Position start = new Position("A1");
        Position destination = new Position("A1");
        assertEquals(start.distanceTo(destination), 0);
    }

    @Test
    public void testDistanceToSameRowReturnsCorrect() {
        Position start = new Position("A1");
        Position destination = new Position("H1");
        assertEquals(start.distanceTo(destination), 7);
    }

    @Test
    public void testDistanceSameColumnReturnsCorrect() {
        Position start = new Position("A1");
        Position destination = new Position("A8");
        assertEquals(start.distanceTo(destination), 7);

    }

    @Test
    public void testDestinationDiagonallyReturnsCorrect() {
        Position start = new Position("A1");
        Position destination = new Position("D4");
        assertEquals(start.distanceTo(destination), 3);
    }

    @Test
    public void testDestinationNotReachableReturnsMinusOne() {
        Position start = new Position("A1");
        Position destination = new Position("D8");
        assertEquals(start.distanceTo(destination), -1);
    }
}