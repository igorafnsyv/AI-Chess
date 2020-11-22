package chess_game.chess_pieces;

import chess_game.ChessBoard;
import chess_game.Position;
import org.junit.Test;

import static org.junit.Assert.*;

public class PawnTest {

    @Test
    public void testPawnIsWhiteReturnsTrueWhenWhite() {
        Pawn pawn = new Pawn(true);
        assertTrue(pawn.isWhite());
    }

    @Test
    public void testPawnCanMoveOneForward() {
        Pawn pawn = new Pawn(true);
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        Position start = board.getPosition("A1");
        Position destination = board.getPosition("A2");
        start.setPiece(pawn);
        assertTrue(pawn.canMoveTo(start, destination, board));
    }

    @Test
    public void testPawnCanMoveToTwoForward(){
        Pawn pawn = new Pawn(true);
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        Position start = board.getPosition("A1");
        Position destination = board.getPosition("A3");
        start.setPiece(pawn);
        assertTrue(pawn.canMoveTo(start, destination, board));
    }

    @Test
    public void testPawnCantMoveToMoreThanTwoForward() {
        Pawn pawn = new Pawn(true);
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        Position start = board.getPosition("A1");
        Position destination = board.getPosition("A4");
        start.setPiece(pawn);
        assertFalse(pawn.canMoveTo(start, destination, board));
    }

    @Test
    public void testPawnCanMoveOneDiagonallyRightWhenEnemy() {
        Pawn pawn = new Pawn(true);
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        Position start = board.getPosition("A1");
        Position destination = board.getPosition("B2");
        start.setPiece(pawn);
        destination.setPiece(new Pawn(false));
        assertTrue(pawn.canMoveTo(start, destination, board));
    }

    @Test
    public void testPawnCanMoveOneDiagonallyLeftWhenEnemy() {
        Pawn pawn = new Pawn(true);
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        Position start = board.getPosition("B1");
        Position destination = board.getPosition("A2");
        start.setPiece(pawn);
        destination.setPiece(new Pawn(false));
        assertTrue(pawn.canMoveTo(start, destination, board));
    }

    @Test
    public void testPawnCantMoveOneDiagonallyRightWhenNoEnemy() {
        Pawn pawn = new Pawn(true);
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        Position start = board.getPosition("A1");
        Position destination = board.getPosition("B2");
        start.setPiece(pawn);
        assertFalse(pawn.canMoveTo(start, destination, board));
    }

    @Test
    public void testPawnCantMoveTwoForwardWhenEnemyBetween() {
        Pawn pawn = new Pawn(true);
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        Position start = board.getPosition("A1");
        Position destination = board.getPosition("A3");
        Position middle = board.getPosition("A2");
        middle.setPiece(new Pawn(false));
        start.setPiece(pawn);
        assertFalse(pawn.canMoveTo(start, destination, board));
    }

    @Test
    public void testCantMoveUpWhenBlack() {
        Pawn pawn = new Pawn(false);
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        Position start = board.getPosition("D7");
        Position destination = board.getPosition("D8");
        start.setPiece(pawn);
        assertFalse(pawn.canMoveTo(start, destination, board));
    }

    @Test
    public void testCantMove2UpWhenBlack() {
        Pawn pawn = new Pawn(false);
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        Position start = board.getPosition("D6");
        Position destination = board.getPosition("D8");
        start.setPiece(pawn);
        assertFalse(pawn.canMoveTo(start, destination, board));
    }

    @Test
    public void testCanMoveDownWhenBlack() {
        Pawn pawn = new Pawn(false);
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        Position start = board.getPosition("D7");
        Position destination = board.getPosition("D6");
        start.setPiece(pawn);
        assertTrue(pawn.canMoveTo(start, destination, board));
    }

    @Test
    public void testCanMove2DownWhenBlack() {
        Pawn pawn = new Pawn(false);
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        Position start = board.getPosition("D7");
        Position destination = board.getPosition("D5");
        start.setPiece(pawn);
        assertTrue(pawn.canMoveTo(start, destination, board));
    }

    @Test
    public void testCantMove2DownWhenBlack() {
        Pawn pawn = new Pawn(false);
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        Position start = board.getPosition("D7");
        Position destination = board.getPosition("D4");
        start.setPiece(pawn);
        assertFalse(pawn.canMoveTo(start, destination, board));
    }

    @Test
    public void testCanAttackWhenBlack() {
        Pawn pawn = new Pawn(false);
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        Position start = board.getPosition("D7");
        Position destination = board.getPosition("C6");
        destination.setPiece(new Pawn(true));
        start.setPiece(pawn);
        assertTrue(pawn.canMoveTo(start, destination, board));
    }

    @Test
    public void testCanAttackRightWhenBlack() {
        Pawn pawn = new Pawn(false);
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        Position start = board.getPosition("D7");
        Position destination = board.getPosition("E6");
        destination.setPiece(new Pawn(true));
        start.setPiece(pawn);
        assertTrue(pawn.canMoveTo(start, destination, board));
    }

    @Test
    public void testCantMoveWhenOpposite() {
        Pawn pawn = new Pawn(false);
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        Position start = board.getPosition("D7");
        Position destination = board.getPosition("D6");
        destination.setPiece(new Pawn(true));
        start.setPiece(pawn);
        assertFalse(pawn.canMoveTo(start, destination, board));
    }



    @Test
    public void testToString() {
        Pawn pawn = new Pawn(false);
        assertEquals(pawn.toString(), "BP");
    }

    @Test
    public void testToStringWhite() {
        Pawn pawn = new Pawn(true);
        assertEquals(pawn.toString(), "WP");
    }

    @Test
    public void testMoveTo() {
        Pawn pawn = new Pawn(true);
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        Position start = board.getPosition("A2");
        Position destination = board.getPosition("A4");
        pawn.moveTo(start, destination);
        assertEquals(pawn, destination.getPiece());
        assertNull(start.getPiece());

    }

    @Test
    public void testCantMoveTwoRowsAfterFirstMove() {
        Pawn pawn = new Pawn(true);
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        Position start = board.getPosition("A2");
        Position destination = board.getPosition("A3");
        pawn.moveTo(start, destination);
        start = destination;
        destination = board.getPosition("A5");
        assertFalse(pawn.canMoveTo(start, destination, board));
    }


}