package chess_game.chess_pieces;

import chess_game.ChessBoard;
import chess_game.Position;
import org.junit.Test;

import static org.junit.Assert.*;

public class KnightTest {

    @Test
    public void testIsWhiteReturnsTrueWhenWhite() {
        Knight knight = new Knight(true);
        assertTrue(knight.isWhite());
    }

    @Test
    public void testCanMoveTo2Forward1Right(){
        Knight knight = new Knight(true);
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        Position start = board.getPosition("E4");
        board.positionPiece(knight, "E4");
        Position end = board.getPosition("F6");
        assertTrue(knight.canMoveTo(start, end, board));
    }

    @Test
    public void testCanMoveTo2Forward1Left() {
        Knight knight = new Knight(true);
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        Position start = board.getPosition("E4");
        board.positionPiece(knight, "E4");
        Position end = board.getPosition("D6");
        assertTrue(knight.canMoveTo(start, end, board));
    }

    @Test
    public void testCanMoveTo1Forward2Left() {
        Knight knight = new Knight(true);
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        Position start = board.getPosition("E4");
        board.positionPiece(knight, "E4");
        Position end = board.getPosition("C5");
        assertTrue(knight.canMoveTo(start, end, board));
    }

    @Test
    public void testCanMoveTo1Forward2Right() {
        Knight knight = new Knight(true);
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        Position start = board.getPosition("E4");
        board.positionPiece(knight, "E4");
        Position end = board.getPosition("G5");
        assertTrue(knight.canMoveTo(start, end, board));
    }

    @Test
    public void testCanMoveTo1Down2Right() {
        Knight knight = new Knight(true);
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        Position start = board.getPosition("E4");
        board.positionPiece(knight, "E4");
        Position end = board.getPosition("G3");
        assertTrue(knight.canMoveTo(start, end, board));
    }

    @Test
    public void testCanMoveTo1Down2Left() {
        Knight knight = new Knight(true);
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        Position start = board.getPosition("E4");
        board.positionPiece(knight, "E4");
        Position end = board.getPosition("C3");
        assertTrue(knight.canMoveTo(start, end, board));
    }

    @Test
    public void testCanMoveTo2Down1Right(){
        Knight knight = new Knight(true);
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        Position start = board.getPosition("E4");
        board.positionPiece(knight, "E4");
        Position end = board.getPosition("F2");
        assertTrue(knight.canMoveTo(start, end, board));
    }

    @Test
    public void testCanMoveTo2Down1Left() {
        Knight knight = new Knight(true);
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        Position start = board.getPosition("E4");
        board.positionPiece(knight, "E4");
        Position end = board.getPosition("D2");
        assertTrue(knight.canMoveTo(start, end, board));
    }

    @Test
    public void testToString() {
        Knight knight = new Knight(false);
        assertEquals(knight.toString(), "BN");
    }

    @Test
    public void testToStringWhite() {
        Knight knight = new Knight(true);
        assertEquals(knight.toString(), "WN");
    }



}