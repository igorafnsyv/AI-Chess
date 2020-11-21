package chess_game.chess_pieces;

import chess_game.ChessBoard;
import chess_game.Position;
import org.junit.Test;

import static org.junit.Assert.*;

public class RookTest {

    @Test
    public void testRookIsWhiteReturnsTrueWhenWhite() {
        Rook rook = new Rook(true);
        assertTrue(rook.isWhite());
    }

    @Test
    public void testRookCanMoveToTrueWhenPositionNextToRight() {
        Rook rook = new Rook(true);
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        Position start = board.getPosition("A1");
        Position end = board.getPosition("B1");
        start.setPiece(rook);
        assertTrue(rook.canMoveTo(start, end, board));
    }

    @Test
    public void testRookCanMoveToTrueWhenPositionNextFewToRight() {
        Rook rook = new Rook(true);
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        Position start = board.getPosition("A1");
        Position end = board.getPosition("H1");
        start.setPiece(rook);
        assertTrue(rook.canMoveTo(start, end, board));
    }

    @Test
    public void testRookCanMoveToTrueWhenPositionOccupiedOppositeColor() {
        Rook rook = new Rook(true);
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        Position start = board.getPosition("A1");
        Position end = board.getPosition("H1");
        end.setPiece(new Queen(false));
        start.setPiece(rook);
        assertTrue(rook.canMoveTo(start, end, board));
    }

    @Test
    public void testRookCanMoveToTrueWhenPositionOccupiedSameColor() {
        Rook rook = new Rook(true);
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        Position start = board.getPosition("A1");
        Position end = board.getPosition("H1");
        end.setPiece(new Queen(true));
        start.setPiece(rook);
        assertFalse(rook.canMoveTo(start, end, board));
    }

    @Test
    public void testRookCanMoveToTrueWhenPositionOccupiedSameColorLeft() {
        Rook rook = new Rook(true);
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        Position start = board.getPosition("H1");
        Position end = board.getPosition("A1");
        end.setPiece(new Queen(true));
        start.setPiece(rook);
        assertFalse(rook.canMoveTo(start, end, board));
    }

    @Test
    public void testRookCanMoveToTrueWhenPositionOccupiedSameColorBelow() {
        Rook rook = new Rook(true);
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        Position start = board.getPosition("A8");
        Position end = board.getPosition("A1");
        end.setPiece(new Queen(true));
        start.setPiece(rook);
        assertFalse(rook.canMoveTo(start, end, board));
    }

    @Test
    public void testRookCanMoveToFalseDiagonal() {
        Rook rook = new Rook(true);
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        Position start = board.getPosition("C3");
        Position end = board.getPosition("A1");
        end.setPiece(new Queen(true));
        start.setPiece(rook);
        assertFalse(rook.canMoveTo(start, end, board));
    }

    @Test
    public void testRookCanMoveToFalseWhenPositionOccupiedBetween() {
        Rook rook = new Rook(true);
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        Position start = board.getPosition("H1");
        board.getPosition("B1").setPiece(new Queen(true));
        Position end = board.getPosition("A1");
        end.setPiece(new Queen(true));
        start.setPiece(rook);
        assertFalse(rook.canMoveTo(start, end, board));
    }

    @Test
    public void testToString() {
        Rook rook = new Rook(false);
        assertEquals(rook.toString(), "BR");
    }

    @Test
    public void testToStringWhite() {
        Rook rook = new Rook(true);
        assertEquals(rook.toString(), "WR");
    }
}