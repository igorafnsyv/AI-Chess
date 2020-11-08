package chess_game.chess_pieces;

import chess_game.Position;
import org.junit.Test;

import static org.junit.Assert.*;

public class KingTest {

    @Test

    public void testIsCheckedFalse() {
        King king = new King(false);
        assertFalse(king.isChecked());
    }

    @Test
    public void testIsCheckedTrue() {
        King king = new King(false);
        king.setChecked(true);
        assertTrue(king.isChecked());
    }

    @Test
    public void testCanMakeMoveWithOneSpaceRight() {
        King king = new King(false);
        Position start = new Position("A1");
        start.setPiece(king);
        Position destination = new Position("B1");
        assertTrue(king.canMoveTo(start,destination, null));
    }

    @Test
    public void testCanMakeMoveOneSpaceUp() {
        King king = new King(false);
        Position start = new Position("A1");
        start.setPiece(king);
        Position destination = new Position("A2");
        assertTrue(king.canMoveTo(start,destination, null));
    }

    @Test
    public void testCanMakeMoveDiagonally() {
        King king = new King(false);
        Position start = new Position("A1");
        start.setPiece(king);
        Position destination = new Position("B2");
        assertTrue(king.canMoveTo(start,destination, null));
    }

    @Test
    public void testCanMakeMoveTwoUpReturnsFalse() {
        King king = new King(false);
        Position start = new Position("A1");
        start.setPiece(king);
        Position destination = new Position("A3");
        assertFalse(king.canMoveTo(start,destination, null));
    }

    @Test
    public void testCanMakeMoveTwoRightReturnsFalse() {
        King king = new King(false);
        Position start = new Position("A1");
        start.setPiece(king);
        Position destination = new Position("C1");
        assertFalse(king.canMoveTo(start,destination, null));
    }

    @Test
    public void testCanMakeMoveTwoDiagonallyReturnsFalse() {
        King king = new King(false);
        Position start = new Position("A1");
        start.setPiece(king);
        Position destination = new Position("C3");
        assertFalse(king.canMoveTo(start,destination, null));
    }

}