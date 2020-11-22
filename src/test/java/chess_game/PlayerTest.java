package chess_game;

import chess_game.chess_pieces.Piece;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {

    @Test
    public void testPlayerIsWhite() {
        Player player = new Player(true);
        assertTrue(player.isWhite());
    }

    @Test
    public void testPlayerMakeMove() {
        Player player = new Player(true);
        ChessBoard chessBoard = ChessBoard.initializeBoard();
        Position start = chessBoard.getPosition("C2");
        Piece piece = start.getPiece();
        Position destination = chessBoard.getPosition("C4");
        assertTrue(player.makeMove(start, destination, chessBoard));
        assertSame(destination.getPiece(), piece);
        System.out.println(chessBoard);
    }

}