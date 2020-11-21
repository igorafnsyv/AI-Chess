package chess_game.chess_pieces;

import chess_game.ChessBoard;
import chess_game.Position;
import org.junit.Test;

import static org.junit.Assert.*;

public class BishopTest {

    @Test
    public void testBishopConstructorWhiteReturnsTrue() {
        Bishop bishop = new Bishop(true);
        assertTrue(bishop.isWhite());
    }

    @Test
    public void testBishopToString() {
        Bishop bishop = new Bishop(true);
        assertEquals(bishop.toString(), "WB");
    }

    @Test
    public void testBishopCanMoveOneUpDiagonal() {
        Bishop bishop = new Bishop(true);
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        Position start = board.getPosition("A1");
        start.setPiece(bishop);
        Position destination = board.getPosition("B2");
        assertTrue(bishop.canMoveTo(start, destination, board));
    }

    @Test
    public void testBishopCanMoveOneDiagonalDown() {
        Bishop bishop = new Bishop(true);
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        Position start = board.getPosition("D4");
        Position destination = board.getPosition("C3");
        start.setPiece(bishop);
        assertTrue(bishop.canMoveTo(start, destination, board));
    }

    @Test
    public void testBishopCanMoveMultipleUp() {
        Bishop bishop = new Bishop(true);
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        Position start = board.getPosition("A1");
        start.setPiece(bishop);
        Position destination = board.getPosition("H8");
        assertTrue(bishop.canMoveTo(start, destination, board));
    }

    @Test
    public void testBishopCanMoveMultipleDiagonalDown() {
        Bishop bishop = new Bishop(true);
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        Position start = board.getPosition("H8");
        Position destination = board.getPosition("A1");
        start.setPiece(bishop);
        assertTrue(bishop.canMoveTo(start, destination, board));
    }


    @Test
    public void testAllBetweenPositionsFree() {
        Bishop bishop = new Bishop(true);
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        Position start = board.getPosition("A1");
        start.setPiece(bishop);
        Position destination = board.getPosition("H8");
        assertTrue(bishop.allBetweenPositionsFree(start, destination, board));
    }

    @Test
    public void testAllBetweenPositionsFreeFalse() {
        Bishop bishop = new Bishop(true);
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        Position start = board.getPosition("A1");
        start.setPiece(bishop);
        board.getPosition("D4").setPiece(new Queen(false));
        Position destination = board.getPosition("H8");
        assertTrue(bishop.allBetweenPositionsFree(start, destination, board));
    }

    @Test
    public void testToString() {
        Bishop bishop = new Bishop(false);
        assertEquals(bishop.toString(), "BB");
    }

}