package chess_game;

import chess_game.chess_pieces.Piece;
import chess_game.chess_pieces.Queen;
import org.junit.Test;

import static org.junit.Assert.*;

public class MoveTest {

    @Test
    public void testMoveSamePieceReturned() {
        Queen queen = new Queen(false);

        Move move = new Move(new Position("A1"), new Position("A2"));
        assertEquals(new Position("A1"), move.getStartPosition());
    }

    @Test
    public void testMoveBoardAfterMove() {
        ChessBoard board = ChessBoard.initializeBoard();
        Position destination = board.getPosition("A4");
        Position start = board.getPosition("A2");
        Piece pawn = board.getPosition("A2").getPiece();
        Move move = new Move(start, destination);
        ChessBoard newBoard = move.getBoardStateAfterMove(board);
        System.out.println(board);
        System.out.println(newBoard);
    }

}