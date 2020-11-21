package chess_game;

import org.junit.Test;

import static org.junit.Assert.*;

public class ChessBoardTest {

    @Test
    public void testBoardInitialization() {
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        assertEquals(64, board.size());
    }

    @Test
    public void testBoardGetPosition() {
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        assertEquals(board.getPosition("A1"), new Position("A1"));
    }

    @Test
    public void testBoardGetPositionOfA0ReturnsNull() {
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        assertNull(board.getPosition("A0"));
    }

    @Test
    public void testBoardGetPositionOfH9ReturnsNull() {
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        assertNull(board.getPosition("H9"));
    }

    @Test
    public void testBoardInitializeWithPieces() {
        ChessBoard board = ChessBoard.initializeBoard();

    }

    @Test
    public void testBoardToString() {
        ChessBoard board = ChessBoard.initializeBoard();
        System.out.println(board.toString());
    }
    



}