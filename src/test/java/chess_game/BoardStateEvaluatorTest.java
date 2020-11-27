package chess_game;

import chess_game.chess_pieces.King;
import chess_game.chess_pieces.Knight;
import chess_game.chess_pieces.Queen;
import chess_game.chess_pieces.Rook;
import org.junit.Test;

import static org.junit.Assert.*;

public class BoardStateEvaluatorTest {

    @Test
    public void testEmptyBoardValue() {
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        assertEquals(0, BoardStateEvaluator.blackPieceTotalValue(board));
        assertEquals(0, BoardStateEvaluator.whitePieceTotalValue(board));

    }

    @Test
    public void testFullBoardValue() {
        ChessBoard board = ChessBoard.initializeBoard();
        assertEquals(39, BoardStateEvaluator.whitePieceTotalValue(board));
        assertEquals(39, BoardStateEvaluator.blackPieceTotalValue(board));
    }

    @Test
    public void testPiecesPositionOptionsWhenEmpty() {
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        assertEquals(0, BoardStateEvaluator.whitePiecePossibleMoveCount(board));
        assertEquals(0, BoardStateEvaluator.blackPiecePossibleMoveCount(board));
    }

    @Test
    public void testPiecesPositionsWhenInitialized() {
        ChessBoard board = ChessBoard.initializeBoard();
        /*
        Each pawn has 2 move options (16 moves) + 2 knights each having 2 options -> 20
         */
        assertEquals(20 ,BoardStateEvaluator.whitePiecePossibleMoveCount(board));
        assertEquals(20 ,BoardStateEvaluator.blackPiecePossibleMoveCount(board));
    }

    @Test
    public void testWhiteKnightAndQueenMoveOptionsSum() {
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        board.positionPiece(new Knight(true), "F5");
        board.positionPiece(new Queen(true), "D4");
        //D4 is occupied by queen
        int knightMoves = 7;
        int queenMoves = 27;
        assertEquals(knightMoves + queenMoves, BoardStateEvaluator.whitePiecePossibleMoveCount(board));
    }

    @Test
    public void testBlackeKnightBoardCheckedValue() {
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        board.positionPiece(new King(true), "D8");
        board.setWhiteKingPosition(board.getPosition("D8"));
        board.positionPiece(new King(false), "D6");
        board.positionPiece(new Rook(false), "G8");
        assertEquals(100, BoardStateEvaluator.blackCheckWhiteKing( board));
    }

    @Test
    public void testBlackKingBoardCheckedValue() {
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        board.positionPiece(new King(false), "D8");
        board.setBlackKingPosition(board.getPosition("D8"));
        board.positionPiece(new King(true), "D6");
        board.positionPiece(new Rook(true), "G8");
        assertEquals(100, BoardStateEvaluator.whiteCheckBlackKing( board));
    }

    @Test
    public void testBlackKingBoardNotCheckedValue() {
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        board.positionPiece(new King(false), "D8");
        board.setBlackKingPosition(board.getPosition("D8"));
        board.positionPiece(new King(true), "D6");
        board.positionPiece(new Rook(true), "G7");
        assertEquals(0, BoardStateEvaluator.whiteCheckBlackKing( board));
    }

    @Test
    public void testValueOfCheckMate() {
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        board.positionPiece(new King(false), "D8");
        board.setBlackKingPosition(board.getPosition("D8"));
        board.positionPiece(new King(true), "D6");
        board.positionPiece(new Rook(true), "G8");
        assertEquals(1000, BoardStateEvaluator.whiteCheckmateBlackKing(board));
    }

    @Test
    public void testValueOfCheckMateWhiteKing() {
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        board.positionPiece(new King(true), "D8");
        board.setWhiteKingPosition(board.getPosition("D8"));
        board.positionPiece(new King(false), "D6");
        board.positionPiece(new Rook(false), "G8");
        CheckMateDetector detector = new CheckMateDetector();
        assertEquals(1000, BoardStateEvaluator.blackCheckmateWhiteKing(board));
    }


    @Test
    public void testWhitePiecesPositionValue() {
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        board.positionPiece(new King(false), "D8");
        board.setBlackKingPosition(board.getPosition("D8"));
        board.positionPiece(new King(true), "D6");
        board.positionPiece(new Queen(true), "C8");
        board.positionPiece(new Rook(true), "G8");
        assertEquals(200, BoardStateEvaluator.evaluateWhitePositions( board));
    }

    @Test
    public void testBlackPiecesPositionValue() {
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        board.positionPiece(new King(true), "D8");
        board.setWhiteKingPosition(board.getPosition("D8"));
        board.positionPiece(new King(false), "D6");
        board.positionPiece(new Queen(false), "C8");
        board.positionPiece(new Rook(false), "G8");
        assertEquals(200, BoardStateEvaluator.evaluateBlackPositions( board));
    }


}