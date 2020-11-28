package chess_game;

import chess_game.chess_pieces.Piece;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {

    @Test
    public void testPlayerIsWhite() {
        Player player = new Player(true, "Player 1");
        assertTrue(player.isWhite());
    }

    @Test
    public void testPlayerMakeMove() {
        Player player = new Player(true, "Player 1");
        ChessBoard chessBoard = ChessBoard.initializeBoard();
        Position start = chessBoard.getPosition("C2");
        Piece piece = start.getPiece();
        Position destination = chessBoard.getPosition("C4");
        assertTrue(player.makeMove(new Move(start.toString(), destination.toString()), chessBoard));
        assertSame(destination.getPiece(), piece);
    }

}