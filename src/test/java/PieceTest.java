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
        assertTrue(piece.canMoveTo(newPosition, board));
    }

    @Test
    public void testCanMoveReturnFalseWhenNoPieceInStart() {
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        Position start = board.getPosition("A1");
        Position destination = board.getPosition("A2");
        Piece piece = new King(false);

        assertThrows(NullPointerException.class,() -> piece.canMoveTo(destination, board));
    }

    @Test
    public void testCanMoveToSamePositionReturnFalse() {
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        Position start = board.getPosition("A1");
        Position destination = board.getPosition("A1");
        Piece piece = new King(false);
        board.positionPiece(piece, "A1");
        assertFalse(piece.canMoveTo(destination, board));
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
        assertFalse(piece.canMoveTo(destination, board));
    }

    @Test
    public void testCanMoveToSamePositionReturnsTrueIfDifferentColorOccupies() {
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        Position start = board.getPosition("A1");
        Position destination = board.getPosition("A2");
        Piece piece = new King(false);
        board.positionPiece(piece, "A1");
        board.positionPiece(new Pawn(true), "A2");

        assertTrue(piece.canMoveTo(destination, board));
    }

}