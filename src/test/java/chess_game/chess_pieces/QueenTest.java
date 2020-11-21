package chess_game.chess_pieces;

import chess_game.ChessBoard;
import chess_game.Position;
import org.junit.Test;

import static org.junit.Assert.*;

public class QueenTest {

    @Test
    public void testIsWhite() {
        Queen queen = new Queen(true);
        assertTrue(queen.isWhite());
    }

    @Test
    public void testIsBlack(){
        Queen queen = new Queen(false);
        assertFalse(queen.isWhite());
    }

    @Test
    public void testCanGetToDestinationAllPositionFree() {
        Queen queen = new Queen(false);
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        Position start = board.getPosition("A1");
        Position end = board.getPosition("A7");
        start.setPiece(queen);
        end.setPiece(new Queen(true));
        assertTrue(queen.allBetweenPositionsFree(start, end, board));
    }

    @Test
    public void testCanGetToDestinationAllPositionFreeA1A2() {
        Queen queen = new Queen(false);
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        Position start = board.getPosition("A1");
        Position end = board.getPosition("A2");
        start.setPiece(queen);
        end.setPiece(new Queen(true));
        assertTrue(queen.allBetweenPositionsFree(start, end, board));
    }

    @Test
    public void testCanGetToDestinationAllPositionFreeColsMove() {
        Queen queen = new Queen(false);
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        Position start = board.getPosition("A1");
        Position end = board.getPosition("H1");
        start.setPiece(queen);
        end.setPiece(new Queen(true));
        assertTrue(queen.allBetweenPositionsFree(start, end, board));
    }

    @Test
    public void testAllOnePieceOnTheWay(){
        Queen queen = new Queen(false);
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        board.getPosition("D1").setPiece(new Queen(true));
        Position start = board.getPosition("A1");
        Position end = board.getPosition("H1");
        start.setPiece(queen);
        assertFalse(queen.allBetweenPositionsFree(start, end, board));
    }

    @Test
    public void testCanMoveToEmpty() {
        Queen queen = new Queen(false);
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        Position start = board.getPosition("A1");
        Position end = board.getPosition("H1");
        start.setPiece(queen);
        assertTrue(queen.canMoveTo(start, end, board));
    }

    @Test
    public void testCantMoveToOccupiedPositionSameColor() {
        Queen queen = new Queen(false);
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        Position start = board.getPosition("A1");
        Position end = board.getPosition("H1");
        end.setPiece(new King(false));
        start.setPiece(queen);
        assertFalse(queen.canMoveTo(start, end, board));
    }


    @Test
    public void testCanMoveToOccupiedPositionDifferentColor() {
        Queen queen = new Queen(false);
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        Position start = board.getPosition("A1");
        Position end = board.getPosition("H1");
        end.setPiece(new King(true));
        start.setPiece(queen);
        assertTrue(queen.canMoveTo(start, end, board));
    }

    @Test
    public void testCantMoveToDestinationPieceOnWay() {
        Queen queen = new Queen(false);
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        Position start = board.getPosition("A1");
        Position end = board.getPosition("H1");
        board.getPosition("C1").setPiece(new King(false));
        start.setPiece(queen);
        assertFalse(queen.canMoveTo(start, end, board));
    }

    @Test
    public void testCanMoveToDiagonalUp() {
        Queen queen = new Queen(false);
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        Position start = board.getPosition("A1");
        start.setPiece(queen);
        Position end = board.getPosition("D4");
        assertTrue(queen.canMoveTo(start, end, board));

    }

    @Test
    public void testCanMoveDiagonalDown() {
        Queen queen = new Queen(false);
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        Position start = board.getPosition("D4");
        start.setPiece(queen);
        Position end = board.getPosition("A1");
        assertTrue(queen.canMoveTo(start, end, board));
    }

    @Test
    public void testCanMoveToRight() {
        Queen queen = new Queen(false);
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        Position start = board.getPosition("A1");
        start.setPiece(queen);
        Position end = board.getPosition("B1");
        assertTrue(queen.canMoveTo(start, end, board));
    }

    @Test
    public void testAllBetweenPositionFreeLeft() {
        Queen queen = new Queen(false);
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        Position start = board.getPosition("H1");
        start.setPiece(queen);
        Position end = board.getPosition("A1");
        assertTrue(queen.allBetweenPositionsFree(start, end, board));
    }

    @Test
    public void testAllBetweenPositionFreeBelow() {
        Queen queen = new Queen(false);
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        Position start = board.getPosition("A3");
        start.setPiece(queen);
        Position end = board.getPosition("A2");
        assertTrue(queen.allBetweenPositionsFree(start, end, board));
    }

    @Test
    public void testToString() {
        Queen queen = new Queen(false);
        assertEquals(queen.toString(), "BQ");
    }

    @Test
    public void testWhite() {
        Queen queen = new Queen(true);
        assertEquals(queen.toString(), "WQ");
    }








}