package chess_game;

public class Game {

    private ChessBoard board;
    private Player playerWhite;
    private Player playerBlack;
    private Player currentPlayer;
    private CheckMateDetector checkMateDetector;


    public Game(Player playerWhite, Player playerBlack) {
        this.board = ChessBoard.initializeBoard();
        this.playerWhite = playerWhite;
        this.playerBlack = playerBlack;
        this.currentPlayer = playerWhite;
        this.checkMateDetector = new CheckMateDetector();
    }

    public boolean isGameEnd() {
        return false;
    }

    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }

    public void setCurrentPlayer(Player player) {
        this.currentPlayer = player;
    }

    public Player getNextPlayer() {
        if (currentPlayer == playerWhite) {
            return playerBlack;
        } else {
            return playerWhite;
        }
    }

    public void start() {

    }

    public static void main(String[] args) {
        Player player1 = new Player(true);
        Player player2 = new Player(false);
        Game chessGame = new Game(player1, player2);
        chessGame.start();
    }
}
