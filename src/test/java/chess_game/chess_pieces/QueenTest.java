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
    public void testCanMoveToEmpty() {
        Queen queen = new Queen(false);
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        Position start = board.getPosition("A1");
        Position end = board.getPosition("H1");
        board.positionPiece(queen, start.toString());
        assertTrue(queen.canMoveTo(end, board));
    }

    @Test
    public void testCantMoveToOccupiedPositionSameColor() {
        Queen queen = new Queen(false);
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        Position start = board.getPosition("A1");
        Position end = board.getPosition("H1");
        end.setPiece(new King(false));
        board.positionPiece(queen, start.toString());
        assertFalse(queen.canMoveTo(end, board));
    }


    @Test
    public void testCanMoveToOccupiedPositionDifferentColor() {
        Queen queen = new Queen(false);
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        Position start = board.getPosition("A1");
        Position end = board.getPosition("H1");
        end.setPiece(new King(true));
        board.positionPiece(queen, "A1");
        assertTrue(queen.canMoveTo(end, board));
    }

    @Test
    public void testCantMoveToDestinationPieceOnWay() {
        Queen queen = new Queen(false);
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        Position start = board.getPosition("A1");
        Position end = board.getPosition("H1");
        board.getPosition("C1").setPiece(new King(false));
        board.positionPiece(queen, "A1");
        assertFalse(queen.canMoveTo(end, board));
    }

    @Test
    public void testCanMoveToDiagonalUp() {
        Queen queen = new Queen(false);
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        Position start = board.getPosition("A1");
        board.positionPiece(queen, "A1");
        Position end = board.getPosition("D4");
        assertTrue(queen.canMoveTo(end, board));

    }

    @Test
    public void testCanMoveDiagonalDown() {
        Queen queen = new Queen(false);
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        Position start = board.getPosition("D4");
        board.positionPiece(queen, "D4");
        Position end = board.getPosition("A1");
        assertTrue(queen.canMoveTo(end, board));
    }

    @Test
    public void testCanMoveToRight() {
        Queen queen = new Queen(false);
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        Position start = board.getPosition("A1");
        board.positionPiece(queen, "A1");
        Position end = board.getPosition("B1");
        assertTrue(queen.canMoveTo(end, board));
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

    @Test
    public void testGetLegalPositions() {
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        Queen queen = new Queen(true);
        board.positionPiece(queen, "E4");
        assertEquals(27, queen.getLegalMovePositions(board).size());
    }

    @Test
    public void testGetLegalPositionsEnemyOnTheLeft() {
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        Queen queen = new Queen(true);
        board.positionPiece(queen, "E4");
        board.positionPiece(new Pawn(false), "C4");
        assertEquals(25, queen.getLegalMovePositions(board).size());
    }

    @Test
    public void testGetLegalPositionsEnemyLeftAndUp() {
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        Queen queen = new Queen(true);
        board.positionPiece(queen, "E4");
        board.positionPiece(new Pawn(false), "C4");
        board.positionPiece(new Pawn(false), "G6");
        assertEquals(24, queen.getLegalMovePositions(board).size());
    }

    @Test
    public void testGetLegalPositionsEnemyUpAllyLeft() {
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        Queen queen = new Queen(true);
        board.positionPiece(queen, "E4");
        board.positionPiece(new Pawn(true), "C4");
        board.positionPiece(new Pawn(false), "G6");
        assertEquals(23, queen.getLegalMovePositions(board).size());
    }








}