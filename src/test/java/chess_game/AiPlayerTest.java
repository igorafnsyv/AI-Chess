package chess_game;

import chess_game.chess_pieces.*;
import org.junit.Test;


import java.util.List;

import static org.junit.Assert.*;

public class AiPlayerTest {

    @Test
    public void testAiPlayerGetLegalMoves() {
        AiPlayer player = new AiPlayer(false, "Computer");
        ChessBoard board = ChessBoard.initializeBoard();
        assertEquals(20, player.getLegalMoves(board).size());
    }

    @Test
    public void testAiPLayerLegalMovesWhenCheckMate() {
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        King blackKing = new King(false);
        board.positionPiece(blackKing, "D8");
        board.positionPiece(new King(true), "D6");
        board.positionPiece(new Rook(true), "G8");
        AiPlayer player = new AiPlayer(false, "Computer");
        board.setBlackKingPosition(board.getPosition("D8"));
        assertEquals(0, player.getLegalMoves(board).size());
    }

    @Test
    public void testAiPLayerLegalMovesWhenCheck() {
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        King blackKing = new King(false);
        board.positionPiece(blackKing, "D8");
        board.setBlackKingPosition(board.getPosition("D8"));
        AiPlayer player = new AiPlayer(false, "Computer");
        board.positionPiece(new Rook(true), "G8");

        Move move = new Move("D8", "E7");
        ChessBoard boardAfterMove = move.getBoardStateAfterMove(board);
        assertEquals(3, player.getLegalMoves(board).size());
        assertEquals(3, blackKing.getLegalMovePositions(board).size());
    }

    @Test
    public void testAiPlayerMiniMaxAtStart() {
        ChessBoard board = ChessBoard.initializeBoard();
        AiPlayer player = new AiPlayer(false, "Computer");
        List<Move> moves =  player.getLegalMoves(board);
        int depth = 3;
//        System.out.println(player.findBestMove(moves, board, depth));
    }

    @Test
    public void testFindBestMoveReturnsCheckMove() {
        ChessBoard board = ChessBoard.initializeEmptyBoard();
        King blackKing = new King(false);
        board.positionPiece(blackKing, "D7");
        board.setBlackKingPosition(board.getPosition("D7"));
        Queen whiteQueen = new Queen(true);
        board.positionPiece(whiteQueen, "D6");
//        board.setBlackKingPosition(board.getPosition("D6"));
        Rook rook = new Rook(true);
        board.positionPiece(rook, "G7");
        Rook rook2 = new Rook(true);
        board.positionPiece(rook2, "B6");
        board.positionPiece(new Knight(true), "A7");
        board.positionPiece(new Knight(true), "F6");
        System.out.println(blackKing.getLegalMovePositions(board));
        System.out.println(rook2.getLegalMovePositions(board));

        System.out.println(BoardStateEvaluator.evaluateBlackPositions(board));
        AiPlayer player = new AiPlayer(false, "Computer");
        System.out.println(player.getLegalMoves(board));
        System.out.println(board);
        int depth = 1;
        CheckMateDetector detector = new CheckMateDetector();
        System.out.println(detector.isWhiteKingCheckMate(board));
        System.out.println(player.findBestMove(player.getLegalMoves(board), board ,depth));
    }

}