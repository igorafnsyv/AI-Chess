package chess_game;

import chess_game.chess_pieces.King;
import chess_game.chess_pieces.Pawn;
import chess_game.chess_pieces.Rook;
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
        System.out.println(player.getLegalMoves(board));
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
        int depth = 4;
        System.out.println(player.findBestMove(moves, board, depth));


    }

}