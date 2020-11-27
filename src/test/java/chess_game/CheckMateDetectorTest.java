package chess_game;

import chess_game.chess_pieces.King;
import chess_game.chess_pieces.Knight;
import chess_game.chess_pieces.Rook;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class CheckMateDetectorTest {

    @Test
    public void testCheckMateDetectorWhiteKingChecked() {
        ChessBoard board = ChessBoard.initializeBoard();
        assertFalse(new CheckMateDetector().isWhiteKingChecked(board));
    }

    @Test
    public void testCheckMateDetectorWhiteKingCheckedTrue() {
        ChessBoard board = ChessBoard.initializeBoard();
        Knight knight = (Knight) board.getPosition("G8").getPiece();
        knight.moveTo(board.getPosition("D3"));
        assertTrue(new CheckMateDetector().isWhiteKingChecked(board));
    }

    @Test
    public void testWhiteKingMateFalse() {
        ChessBoard board = ChessBoard.initializeBoard();
        Knight knight = (Knight) board.getPosition("G8").getPiece();
        knight.moveTo(board.getPosition("D3"));
        CheckMateDetector detector = new CheckMateDetector();
        detector.isWhiteKingChecked(board);
        assertFalse(detector.isWhiteKingCheckMate(board));
    }

    @Test
    public void testWhiteKingMateTrue() {
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        board.positionPiece(new King(true), "D8");
        board.setWhiteKingPosition(board.getPosition("D8"));
        board.positionPiece(new King(false), "D6");
        board.positionPiece(new Rook(false), "G8");
        CheckMateDetector detector = new CheckMateDetector();
        assertTrue(detector.isWhiteKingChecked(board));
        /*
        Mate because: check from Rook but not from King.
        Any move along horizontal line will still be check from Rook
        Any move down vertical or diagonal will be check from King
         */
        assertTrue(detector.isWhiteKingCheckMate(board));
    }

    @Test
    public void testWhiteKingCheckTrueMateFalse() {
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        board.positionPiece(new King(true), "D8");
        board.setWhiteKingPosition(board.getPosition("D8"));
//        board.positionPiece(new King(false), "D6");
        board.positionPiece(new Rook(false), "G8");
        CheckMateDetector detector = new CheckMateDetector();
        assertTrue(detector.isWhiteKingChecked(board));
        assertFalse(detector.isWhiteKingCheckMate(board));
    }

    @Test
    public void testBlackKingCheckedFalse() {
        ChessBoard board = ChessBoard.initializeBoard();
        assertFalse(new CheckMateDetector().isBlackKingChecked(board));
    }

    @Test
    public void testBlackKingCheckedTrue() {
        ChessBoard board = ChessBoard.initializeBoard();
        Knight knight = (Knight) board.getPosition("G1").getPiece();
        knight.moveTo(board.getPosition("D6"));
        assertTrue(new CheckMateDetector().isBlackKingChecked(board));
    }

    @Test
    public void testBlackKingCheckedTrueMateFalse() {
        ChessBoard board = ChessBoard.initializeBoard();
        Knight knight = (Knight) board.getPosition("G1").getPiece();
        knight.moveTo(board.getPosition("D6"));
        CheckMateDetector detector = new CheckMateDetector();
        assertTrue(detector.isBlackKingChecked(board));
        assertFalse(detector.isBlackKingCheckMate(board));
    }

    @Test
    public void testBlackKingMateTrue() {
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        board.positionPiece(new King(false), "D8");
        board.setBlackKingPosition(board.getPosition("D8"));
        board.positionPiece(new King(true), "D6");
        board.positionPiece(new Rook(true), "G8");
        CheckMateDetector detector = new CheckMateDetector();
        assertTrue(detector.isBlackKingChecked(board));
        /*
        Mate because: check from Rook but not from King.
        Any move along horizontal line will still be check from Rook
        Any move down vertical or diagonal will be check from King
         */
        assertTrue(detector.isBlackKingCheckMate(board));
    }

    @Test
    public void testGetWhiteKingCheckList() {
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        board.positionPiece(new King(true), "D8");
        board.setWhiteKingPosition(board.getPosition("D8"));
        board.positionPiece(new King(false), "D6");
        board.positionPiece(new Rook(false), "G8");
        CheckMateDetector detector = new CheckMateDetector();
        assertTrue(detector.isWhiteKingChecked(board));
        assertEquals(List.of(board.getPosition("G8")), detector.getBlackCheckPositions());
    }

    @Test
    public void testGetBlackKingCheckList() {
        ChessBoard board = ChessBoard.initializeBoard();
        Knight knight = (Knight) board.getPosition("G1").getPiece();
        knight.moveTo(board.getPosition("D6"));
        CheckMateDetector detector = new CheckMateDetector();
        assertTrue(detector.isBlackKingChecked(board));
        assertEquals(List.of(board.getPosition("D6")), detector.getWhiteCheckPositions());
    }

}