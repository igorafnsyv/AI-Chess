package chess_game.chess_pieces;

import chess_game.ChessBoard;
import chess_game.Position;
import org.junit.Test;

import static org.junit.Assert.*;

public class KingTest {

    @Test
    public void testIsCheckedFalseWhenSameColorQueenOnTheLeft() {
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        King king = new King(false);
        board.getPosition("B1").setPiece(king);
        board.getPosition("A1").setPiece(new Queen(false));
        board.setBlackKingPosition(board.getPosition("B1"));
        assertFalse(king.isChecked(board));
    }

    @Test
    public void testIsCheckedTrueWhenDifferentColorQueenOnTheLeft() {
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        King king = new King(false);
        Queen queen = new Queen(true);
        Position kingPosition = board.getPosition("D1");
        kingPosition.setPiece(king);
        board.getPosition("A1").setPiece(queen);
        board.setBlackKingPosition(kingPosition);
        assertTrue(king.isChecked(board));

    }

    @Test
    public void testIsCheckedTrueWhenDifferentColorQueenOnTheRight() {
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        King king = new King(false);
        Queen queen = new Queen(true);
        Position kingPosition = board.getPosition("A1");
        kingPosition.setPiece(king);
        board.getPosition("C1").setPiece(queen);
        board.setBlackKingPosition(kingPosition);
        assertTrue(king.isChecked(board));
    }

    @Test
    public void testIsCheckedWhenQueenBelow() {
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        King king = new King(false);
        Queen queen = new Queen(true);
        Position kingPosition = board.getPosition("A5");
        kingPosition.setPiece(king);
        board.getPosition("A1").setPiece(queen);
        board.setBlackKingPosition(kingPosition);
        assertTrue(king.isChecked(board));
    }

    @Test
    public void testIsCheckedWhenQueenAbove() {
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        King king = new King(false);
        Queen queen = new Queen(true);
        Position kingPosition = board.getPosition("A1");
        kingPosition.setPiece(king);
        board.getPosition("A5").setPiece(queen);
        board.setBlackKingPosition(kingPosition);
        assertTrue(king.isChecked(board));
    }

    @Test
    public void testIsCheckedLowerDiagonal() {
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        King king = new King(false);
        Queen queen = new Queen(true);
        Position kingPosition = board.getPosition("C3");
        kingPosition.setPiece(king);
        board.getPosition("A1").setPiece(queen);
        board.setBlackKingPosition(kingPosition);
        assertTrue(king.isChecked(board));
    }

    @Test
    public void testIsCheckedUpperDiagonal() {
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        King king = new King(false);
        Queen queen = new Queen(true);
        Position kingPosition = board.getPosition("A1");
        kingPosition.setPiece(king);
        board.getPosition("C3").setPiece(queen);
        board.setBlackKingPosition(kingPosition);
        assertTrue(king.isChecked(board));
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

    @Test
    public void testToString() {
        King king = new King(false);
        assertEquals(king.toString(), "BK");
    }

    @Test
    public void testToStringWhite() {
        King king = new King(true);
        assertEquals(king.toString(), "WK");
    }


}