package chess_game;

import org.junit.Test;

import static org.junit.Assert.*;

public class GameTest {

    @Test
    public void testGameCurrentPlayer() {
        Player player1 = new Player(true, "Player 1");
        Player player2 = new Player(false, "Player 2");
        Game game = new Game(player1, player2);
        assertEquals(player1, game.getCurrentPlayer());
    }

    @Test
    public void testGameNextPlayer() {
        Player player1 = new Player(true, "Player 1");
        Player player2 = new Player(false, "Player 2");
        Game game = new Game(player1, player2);
        assertEquals(player2, game.getNextPlayer());
    }

}