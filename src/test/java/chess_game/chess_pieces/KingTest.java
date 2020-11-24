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
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        King king = new King(false);
        Position start = board.getPosition("A1");
        board.positionPiece(king, "A1");
        Position destination = board.getPosition("B1");
        assertTrue(king.canMoveTo(start,destination, board));
    }

    @Test
    public void testCanMakeMoveWithOneSpaceLeft() {
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        King king = new King(false);
        Position start = board.getPosition("B1");
        board.positionPiece(king, "B1");
        Position destination = board.getPosition("A1");
        assertTrue(king.canMoveTo(start,destination, board));
    }

    @Test
    public void testCanMakeMoveOneSpaceUp() {
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        King king = new King(false);
        Position start = board.getPosition("A1");
        board.positionPiece(king, "A1");
        Position destination = board.getPosition("A2");
        assertTrue(king.canMoveTo(start,destination, board));
    }
    @Test
    public void testCanMakeMoveOneSpaceDown() {
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        King king = new King(false);
        Position start = board.getPosition("A2");
        Position destination = board.getPosition("A1");
        board.positionPiece(king, "A2");
        assertTrue(king.canMoveTo(start,destination, board));
    }

    @Test
    public void testCanMakeMoveDiagonally() {
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        King king = new King(false);
        Position start = board.getPosition("A1");
        board.positionPiece(king, "A1");
        Position destination = board.getPosition("B2");
        assertTrue(king.canMoveTo(start,destination, board));
    }

    @Test
    public void testCanMakeMoveDiagonallyDown() {
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        King king = new King(false);
        Position start = board.getPosition("B2");
        board.positionPiece(king, "B2");
        Position destination = board.getPosition("A1");
        assertTrue(king.canMoveTo(start,destination, board));
    }

    @Test
    public void testCanMakeMoveDiagonallyUpLeft() {
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        King king = new King(false);
        Position start = board.getPosition("B1");
        board.positionPiece(king, "B1");
        Position destination = new Position("A2");
        assertTrue(king.canMoveTo(start,destination, board));
    }

    @Test
    public void testCanMakeMoveDiagonallyDownRight() {
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        King king = new King(false);
        Position start = board.getPosition("B2");
        board.positionPiece(king, "B2");
        Position destination = board.getPosition("C1");
        assertTrue(king.canMoveTo(start,destination, board));
    }

    @Test
    public void testCanMakeMoveTwoUpReturnsFalse() {
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        King king = new King(false);
        Position start = board.getPosition("A1");
        board.positionPiece(king, "A1");
        Position destination = new Position("A3");
        assertFalse(king.canMoveTo(start,destination, board));
    }

    @Test
    public void testCanMakeMoveTwoRightReturnsFalse() {
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        King king = new King(false);
        Position start = board.getPosition("A1");
        board.positionPiece(king, "A1");
        Position destination = board.getPosition("C1");
        assertFalse(king.canMoveTo(start,destination, board));
    }

    @Test
    public void testCanMakeMoveTwoDiagonallyReturnsFalse() {
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        King king = new King(false);
        Position start = board.getPosition("A1");
        board.positionPiece(king, "A1");
        Position destination = board.getPosition("C3");
        assertFalse(king.canMoveTo(start,destination, board));
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
    public void testIsKingCheckedByPawnFewSquaresAway1() {
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        King king = new King(false);
        Pawn pawn = new Pawn(true);
        board.positionPiece(king, "E8");
        board.positionPiece(pawn, "D7");
        assertTrue(king.isChecked(board));
    }

    @Test
    public void testIsKingCheckedByPawnFewSquaresAway2() {
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        King king = new King(true);
        Pawn pawn = new Pawn(false);
        board.positionPiece(king, "D7");
        board.positionPiece(pawn, "C8");
        assertTrue(king.isChecked(board));
    }

    @Test
    public void testIsKingCheckedByPawnFewSquaresAway3() {
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        King king = new King(true);
        Pawn pawn = new Pawn(false);
        board.positionPiece(king, "D7");
        board.positionPiece(pawn, "E8");
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
        System.out.println(board);
        System.out.println(king.getLegalMovePositions(board));
        assertTrue(king.isCheckable(board.getPosition("C3"), board));
        assertEquals(3, king.getLegalMovePositions(board).size());
    }



}