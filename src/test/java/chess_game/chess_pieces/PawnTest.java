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
        board.positionPiece(pawn, "A1");
        assertTrue(pawn.canMoveTo(destination, board));
    }

    @Test
    public void testPawnCanMoveToTwoForward(){
        Pawn pawn = new Pawn(true);
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        Position start = board.getPosition("A1");
        Position destination = board.getPosition("A3");
        board.positionPiece(pawn, "A1");
        assertTrue(pawn.canMoveTo(destination, board));
    }

    @Test
    public void testPawnCantMoveToMoreThanTwoForward() {
        Pawn pawn = new Pawn(true);
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        Position start = board.getPosition("A1");
        Position destination = board.getPosition("A4");
        board.positionPiece(pawn, "A1");
        assertFalse(pawn.canMoveTo(destination, board));
    }

    @Test
    public void testPawnCanMoveOneDiagonallyRightWhenEnemy() {
        Pawn pawn = new Pawn(true);
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        Position start = board.getPosition("A1");
        Position destination = board.getPosition("B2");
        board.positionPiece(pawn, "A1");
        board.positionPiece(new Pawn(false), "B2");
        assertTrue(pawn.canMoveTo(destination, board));
    }

    @Test
    public void testPawnCanMoveOneDiagonallyLeftWhenEnemy() {
        Pawn pawn = new Pawn(true);
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        Position start = board.getPosition("B1");
        Position destination = board.getPosition("A2");
        board.positionPiece(pawn, "B1");
        board.positionPiece(new Pawn(false), "A2");
        assertTrue(pawn.canMoveTo(destination, board));
    }

    @Test
    public void testPawnCantMoveOneDiagonallyRightWhenNoEnemy() {
        Pawn pawn = new Pawn(true);
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        Position start = board.getPosition("A1");
        Position destination = board.getPosition("B2");
        board.positionPiece(pawn, "A1");
        assertFalse(pawn.canMoveTo(destination, board));
    }

    @Test
    public void testPawnCantMoveTwoForwardWhenEnemyBetween() {
        Pawn pawn = new Pawn(true);
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        Position start = board.getPosition("A1");
        Position destination = board.getPosition("A3");
        Position middle = board.getPosition("A2");
        board.positionPiece(pawn, "A1");
        board.positionPiece(new Pawn(false), "A2");
        start.setPiece(pawn);
        assertFalse(pawn.canMoveTo(destination, board));
    }

    @Test
    public void testCantMoveUpWhenBlack() {
        Pawn pawn = new Pawn(false);
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        Position start = board.getPosition("D7");
        Position destination = board.getPosition("D8");
        board.positionPiece(pawn, "D7");
        assertFalse(pawn.canMoveTo(destination, board));
    }

    @Test
    public void testCantMove2UpWhenBlack() {
        Pawn pawn = new Pawn(false);
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        Position start = board.getPosition("D6");
        Position destination = board.getPosition("D8");
        board.positionPiece(pawn, "D6");
        assertFalse(pawn.canMoveTo(destination, board));
    }

    @Test
    public void testCanMoveDownWhenBlack() {
        Pawn pawn = new Pawn(false);
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        Position start = board.getPosition("D7");
        Position destination = board.getPosition("D6");
        board.positionPiece(pawn, "D7");
        assertTrue(pawn.canMoveTo(destination, board));
    }

    @Test
    public void testCanMove2DownWhenBlack() {
        Pawn pawn = new Pawn(false);
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        Position start = board.getPosition("D7");
        Position destination = board.getPosition("D5");
        board.positionPiece(pawn, "D7");
        assertTrue(pawn.canMoveTo(destination, board));
    }

    @Test
    public void testCantMove2DownWhenBlack() {
        Pawn pawn = new Pawn(false);
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        Position start = board.getPosition("D7");
        Position destination = board.getPosition("D4");
        board.positionPiece(pawn, "D7");
        assertFalse(pawn.canMoveTo(destination, board));
    }

    @Test
    public void testCanAttackWhenBlack() {
        Pawn pawn = new Pawn(false);
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        Position start = board.getPosition("D7");
        Position destination = board.getPosition("C6");
        board.positionPiece(new Pawn(true), "C6");
        board.positionPiece(pawn, "D7");
        assertTrue(pawn.canMoveTo(destination, board));
    }

    @Test
    public void testCanAttackRightWhenBlack() {
        Pawn pawn = new Pawn(false);
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        Position start = board.getPosition("D7");
        Position destination = board.getPosition("E6");
        board.positionPiece(pawn, "D7");
        board.positionPiece(new Pawn(true), "E6");
        start.setPiece(pawn);
        assertTrue(pawn.canMoveTo(destination, board));
    }

    @Test
    public void testCantMoveWhenOpposite() {
        Pawn pawn = new Pawn(false);
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        Position start = board.getPosition("D7");
        Position destination = board.getPosition("D6");
        board.positionPiece(pawn, "D7");
        board.positionPiece(new Pawn(true), "D6");
        assertFalse(pawn.canMoveTo(destination, board));
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
        board.positionPiece(pawn,"A2");
        Position destination = board.getPosition("A4");
        pawn.moveTo(destination);
        assertEquals(pawn, destination.getPiece());
        assertNull(board.getPosition("A2").getPiece());

    }

    @Test
    public void testCantMoveTwoRowsAfterFirstMove() {
        Pawn pawn = new Pawn(true);
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        board.positionPiece(pawn,"A2");
        Position destination = board.getPosition("A3");
        pawn.moveTo(destination);
        destination = board.getPosition("A5");
        assertFalse(pawn.canMoveTo(destination, board));
    }

    @Test
    public void testGetLegalMoves() {
        Pawn pawn = new Pawn(true);
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        board.positionPiece(pawn, "A2");
        assertEquals(2, pawn.getLegalMovePositions(board).size());
    }

    @Test
    public void testGetLegalMovesWhenOpponentOnLeft() {
        Pawn pawn = new Pawn(true);
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        board.positionPiece(pawn, "A2");
        board.positionPiece(new Pawn(false), "B3");
        assertEquals(3, pawn.getLegalMovePositions(board).size());
    }

    @Test
    public void testGetLegalMovesWhenOpponentOnLeftAndRight() {
        Pawn pawn = new Pawn(true);
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        board.positionPiece(pawn, "B2");
        board.positionPiece(new Pawn(false), "C3");
        board.positionPiece(new Pawn(false), "A3");
        assertEquals(4, pawn.getLegalMovePositions(board).size());
    }

    @Test
    public void testGetLegalMovesWhenAllOccupied() {
        Pawn pawn = new Pawn(true);
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        board.positionPiece(pawn, "B2");
        board.positionPiece(new Pawn(true), "B3");
        board.positionPiece(new Pawn(true), "C3");
        board.positionPiece(new Pawn(true), "A3");
        assertEquals(0, pawn.getLegalMovePositions(board).size());
    }




}