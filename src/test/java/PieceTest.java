import chess_game.ChessBoard;
import chess_game.Position;
import chess_game.chess_pieces.King;
import chess_game.chess_pieces.Pawn;
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
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        Position position = board.getPosition("A1");
        Piece piece = new King(false);
        board.positionPiece(piece, "A1");
        Position newPosition = board.getPosition("A2");
        assertTrue(piece.canMoveTo(position, newPosition, board));
    }

    @Test
    public void testCanMoveReturnFalseWhenNoPieceInStart() {
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        Position start = board.getPosition("A1");
        Position destination = board.getPosition("A2");
        Piece piece = new King(false);

        assertThrows(NullPointerException.class,() -> piece.canMoveTo(start, destination, board));
    }

    @Test
    public void testCanMoveToSamePositionReturnFalse() {
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        Position start = board.getPosition("A1");
        Position destination = board.getPosition("A1");
        Piece piece = new King(false);
        board.positionPiece(piece, "A1");
        assertFalse(piece.canMoveTo(start, destination, board));
    }

    @Test
    public void testCanMoveToSamePositionReturnsFalseIfSameColorOccupies() {
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        Position start = board.getPosition("A1");
        Position destination = board.getPosition("A2");
        Piece piece = new King(false);
        Piece piece1 = new King(false);

        board.positionPiece(piece, "A1");
        board.positionPiece(piece1, "A2");
        assertFalse(piece.canMoveTo(start, destination, board));
    }

    @Test
    public void testCanMoveToSamePositionReturnsTrueIfDifferentColorOccupies() {
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        Position start = board.getPosition("A1");
        Position destination = board.getPosition("A2");
        Piece piece = new King(false);
        board.positionPiece(piece, "A1");
        board.positionPiece(new Pawn(true), "A2");

        assertTrue(piece.canMoveTo(start, destination, board));
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