package chess_game;

import chess_game.chess_pieces.Piece;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ChessBoardTest {

    @Test
    public void testBoardInitialization() {
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        assertEquals(64, board.size());
    }

    @Test
    public void testBoardGetPosition() {
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        assertEquals(board.getPosition("A1"), new Position("A1"));
    }

    @Test
    public void testBoardGetPositionOfA0ReturnsNull() {
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        assertNull(board.getPosition("A0"));
    }

    @Test
    public void testBoardGetPositionOfH9ReturnsNull() {
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        assertNull(board.getPosition("H9"));
    }


    @Test
    public void testBoardToString() {
        ChessBoard board = ChessBoard.initializeBoard();
        System.out.println(board.toString());
    }

    @Test
    public void testMovePiece() {
        ChessBoard board = ChessBoard.initializeBoard();
        Piece piece = board.getPosition("C2").getPiece();
        board.movePiece(piece, "C4");
        assertNull(board.getPosition("C2").getPiece());
        assertEquals(board.getPosition("C4").getPiece(), piece);
    }

    @Test
    public void testGetWhitePieces() {
        ChessBoard board = ChessBoard.initializeBoard();
        List<Piece> whitePieces = board.getWhitePieces();
        assertEquals(16, whitePieces.size());

    }

    @Test
    public void testGetBlackPieces(){
        ChessBoard board = ChessBoard.initializeBoard();
        List<Piece> whitePieces = board.getBlackPieces();
        assertEquals(16, whitePieces.size());
    }
    



}