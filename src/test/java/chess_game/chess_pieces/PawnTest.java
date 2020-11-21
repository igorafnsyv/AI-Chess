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
        ChessBoard board = ChessBoard.initializeBoard();
        Position start = board.getPosition("A1");
        Position destination = board.getPosition("A2");
        start.setPiece(pawn);
        assertTrue(pawn.canMoveTo(start, destination, board));
    }

    @Test
    public void testPawnCanMoveToTwoForward(){
        Pawn pawn = new Pawn(true);
        ChessBoard board = ChessBoard.initializeBoard();
        Position start = board.getPosition("A1");
        Position destination = board.getPosition("A3");
        start.setPiece(pawn);
        assertTrue(pawn.canMoveTo(start, destination, board));
    }

    @Test
    public void testPawnCantMoveToMoreThanTwoForward() {
        Pawn pawn = new Pawn(true);
        ChessBoard board = ChessBoard.initializeBoard();
        Position start = board.getPosition("A1");
        Position destination = board.getPosition("A4");
        start.setPiece(pawn);
        assertFalse(pawn.canMoveTo(start, destination, board));
    }

    @Test
    public void testPawnCanMoveOneDiagonallyRightWhenEnemy() {
        Pawn pawn = new Pawn(true);
        ChessBoard board = ChessBoard.initializeBoard();
        Position start = board.getPosition("A1");
        Position destination = board.getPosition("B2");
        start.setPiece(pawn);
        destination.setPiece(new Pawn(false));
        assertTrue(pawn.canMoveTo(start, destination, board));
    }

    @Test
    public void testPawnCantMoveTwoForwardWhenEnemyBetween() {
        Pawn pawn = new Pawn(true);
        ChessBoard board = ChessBoard.initializeBoard();
        Position start = board.getPosition("A1");
        Position destination = board.getPosition("A3");
        Position middle = board.getPosition("A2");
        middle.setPiece(new Pawn(false));
        start.setPiece(pawn);
        assertFalse(pawn.canMoveTo(start, destination, board));
    }


}