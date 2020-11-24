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
        board.positionPiece(bishop, "A1");
        Position destination = board.getPosition("B2");
        assertTrue(bishop.canMoveTo(destination, board));
    }

    @Test
    public void testBishopCanMoveOneDiagonalDown() {
        Bishop bishop = new Bishop(true);
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        Position start = board.getPosition("D4");
        board.positionPiece(bishop, "D4");
        Position destination = board.getPosition("C3");
        assertTrue(bishop.canMoveTo(destination, board));
    }

    @Test
    public void testBishopCanMoveMultipleUp() {
        Bishop bishop = new Bishop(true);
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        Position start = board.getPosition("A1");
        board.positionPiece(bishop, "A1");
        Position destination = board.getPosition("H8");
        assertTrue(bishop.canMoveTo(destination, board));
    }

    @Test
    public void testBishopCanMoveMultipleDiagonalDown() {
        Bishop bishop = new Bishop(true);
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        Position start = board.getPosition("H8");
        board.positionPiece(bishop, "H8");
        Position destination = board.getPosition("A1");
        assertTrue(bishop.canMoveTo(destination, board));
    }


    @Test
    public void testToString() {
        Bishop bishop = new Bishop(false);
        assertEquals(bishop.toString(), "BB");
    }

    @Test
    public void testGetLegalMovesPositions() {
        Bishop bishop = new Bishop(false);
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        board.positionPiece(bishop, "D3");
        assertEquals(bishop.getLegalMovePositions(board).size(), 11);
    }

    @Test
    public void testGetLegalMovesOnePositionBlockedBySameColor() {
        Bishop bishop = new Bishop(false);
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        board.positionPiece(bishop, "D3");
        board.positionPiece(new Pawn(false), "G6");
        assertEquals(bishop.getLegalMovePositions(board).size(), 9);
    }

    @Test
    public void testGetLegalMovesOnePositionBlockedByOppositeColor() {
        Bishop bishop = new Bishop(false);
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        board.positionPiece(bishop, "D3");
        board.positionPiece(new Pawn(true), "G6");
        assertEquals(bishop.getLegalMovePositions(board).size(), 10);
    }

    @Test
    public void testGetLegalMovesOnePositionBlockedByOppositeColorTwice() {
        Bishop bishop = new Bishop(false);
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        board.positionPiece(bishop, "D3");
        board.positionPiece(new Pawn(true), "G6");
        board.positionPiece(new Pawn(true), "C4");
        assertEquals(bishop.getLegalMovePositions(board).size(), 8);
    }

}