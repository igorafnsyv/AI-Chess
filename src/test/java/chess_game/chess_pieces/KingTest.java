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
        board.positionPiece(king, "B1");
        board.positionPiece(new Queen(false), "A1");
        assertFalse(king.isChecked(board));
    }

    @Test
    public void testIsCheckedTrueWhenDifferentColorQueenOnTheLeft() {
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        King king = new King(false);
        Queen queen = new Queen(true);
        board.positionPiece(king, "D1");
        board.positionPiece(queen, "A1");
        assertTrue(king.isChecked(board));
    }

    @Test
    public void testIsCheckedTrueWhenDifferentColorQueenOnTheRight() {
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        King king = new King(false);
        Queen queen = new Queen(true);

        board.positionPiece(king, "A1");
        board.positionPiece(queen, "C1");
        assertTrue(king.isChecked(board));
    }

    @Test
    public void testIsCheckedWhenQueenBelow() {
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        King king = new King(false);
        Queen queen = new Queen(true);
        board.positionPiece(king, "A5");
        board.positionPiece(queen, "A1");
        assertTrue(king.isChecked(board));
    }

    @Test
    public void testIsCheckedWhenQueenAbove() {
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        King king = new King(false);
        Queen queen = new Queen(true);
        board.positionPiece(king, "A1");
        board.positionPiece(queen, "A5");
        assertTrue(king.isChecked(board));
    }

    @Test
    public void testIsCheckedLowerDiagonal() {
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        King king = new King(false);
        Queen queen = new Queen(true);

        board.positionPiece(king, "C3");
        board.positionPiece(queen, "A1");
        assertTrue(king.isChecked(board));
    }

    @Test
    public void testIsCheckedUpperDiagonal() {
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        King king = new King(false);
        Queen queen = new Queen(true);
        board.positionPiece(king, "A1");
        board.positionPiece(queen, "C3");
        assertTrue(king.isChecked(board));
    }

    @Test
    public void testIsCheckedWhenUpperLeftDiagonal() {
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        King king = new King(false);
        Queen queen = new Queen(true);
        board.positionPiece(king, "C1");
        board.positionPiece(queen, "A3");
        assertTrue(king.isChecked(board));
    }

    @Test
    public void testIsCheckedWhenLowerRight() {
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        King king = new King(false);
        Queen queen = new Queen(true);
        board.positionPiece(king, "C3");
        board.positionPiece(queen, "E1");
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
    public void testCanMakeMoveWithOneSpaceLeft() {
        King king = new King(false);
        Position start = new Position("B1");
        start.setPiece(king);
        Position destination = new Position("A1");
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
    public void testCanMakeMoveOneSpaceDown() {
        King king = new King(false);
        Position start = new Position("A2");
        start.setPiece(king);
        Position destination = new Position("A1");
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
    public void testCanMakeMoveDiagonallyDown() {
        King king = new King(false);
        Position start = new Position("B2");
        start.setPiece(king);
        Position destination = new Position("A1");
        assertTrue(king.canMoveTo(start,destination, null));
    }

    @Test
    public void testCanMakeMoveDiagonallyUpLeft() {
        King king = new King(false);
        Position start = new Position("B1");
        start.setPiece(king);
        Position destination = new Position("A2");
        assertTrue(king.canMoveTo(start,destination, null));
    }

    @Test
    public void testCanMakeMoveDiagonallyDownRight() {
        King king = new King(false);
        Position start = new Position("B2");
        start.setPiece(king);
        Position destination = new Position("C1");
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

    @Test
    public void testIsMateReturnsFalseWhenNotChecked() {
        ChessBoard board = ChessBoard.initializeBoard();
        King king = (King) board.getPosition("E1").getPiece();
        assertFalse(king.isChecked(board));
        assertFalse(king.isMate(board));
    }



    @Test
    public void testIsMateReturnsTrueWhenCheckedAndNoMove() {
        ChessBoard board = ChessBoard.initializeBoard();
        King king = (King) board.getPosition("E8").getPiece();
        Knight knight = (Knight) board.getPosition("G1").getPiece();
        knight.moveTo(knight.getPosition(), board.getPosition("D6"));
        assertTrue(king.isChecked(board));
        assertTrue(king.isMate(board));
    }

    @Test
    public void testIsKingCheckedByPawnFewSquaresAway() {
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        King king = new King(false);
        Pawn pawn = new Pawn(true);
        board.positionPiece(king, "E8");
        board.positionPiece(pawn, "F7");
        assertTrue(king.isChecked(board));
    }

    @Test
    public void testKingGetLegalMovePositions() {
        ChessBoard board = ChessBoard.initializeBoard();
        King king = (King) board.getPosition("E8").getPiece();
        assertEquals(king.getLegalMovePositions(board).size(), 0);
    }

    @Test
    public void testKingGetLegalMovePositionsWhenInMiddleOfBoard() {
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        King king = new King(false);
        board.positionPiece(king, "C4");
        assertEquals(king.getLegalMovePositions(board).size(), 8);
    }

    @Test
    public void testKingGetLegalMovePositionsWhenInMiddleOfBoardAndNeighbourSameColor() {
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        King king = new King(false);
        board.positionPiece(king, "C4");
        board.positionPiece(new Queen(false), "C5");
        assertEquals(king.getLegalMovePositions(board).size(), 7);
    }

    @Test
    public void testKingGetLegalMovePositionsWhenInMiddleOfBoardAndNeighbourOppositeColor() {
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        King king = new King(false);
        board.positionPiece(king, "C4");
        board.positionPiece(new Queen(true), "C5");
        assertEquals(king.getLegalMovePositions(board).size(), 3);
    }



}