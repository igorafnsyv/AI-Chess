package chess_game;

import chess_game.chess_pieces.Piece;

import java.sql.SQLOutput;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        return (checkMateDetector.isBlackKingCheckMate(board) || (checkMateDetector.isWhiteKingCheckMate(board)));

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
        Scanner scanner = new Scanner(System.in);
        Pattern moveCommandPattern = Pattern.compile("[A-H][1-8]\\s[A-H][1-8]");
        while (!isGameEnd()) {
            System.out.println(board);
            System.out.println();
            System.out.println("Current Player is " + currentPlayer);
            System.out.println("Move format: start destination (example: A2 A3)");
            boolean correctMoveMade = false;
            while (!correctMoveMade) {
                System.out.print("Enter move: ");
                String move = scanner.nextLine();
                Matcher matcher = moveCommandPattern.matcher(move);
                while (!matcher.matches()) {
                    System.out.println("Invalid Move Format!");
                    System.out.print("Enter move: ");
                    move = scanner.nextLine();
                    matcher = moveCommandPattern.matcher(move);
                }
                String[] positionsArray = move.split("\\s");
                String startPosition = positionsArray[0];
                String destinationPosition = positionsArray[1];
                Piece piece = board.getPosition(startPosition).getPiece();
                if (currentPlayer.makeMove(startPosition, destinationPosition, board)) {
                    System.out.println("Move " + piece + " from " + startPosition + " to " + destinationPosition);
                    correctMoveMade = true;
                } else {
                    System.out.println("Illegal Move for " + piece + " from " + startPosition + " to " + destinationPosition);
                }
            } // correct move made
            setCurrentPlayer(getNextPlayer());

        }

    }

    public static void main(String[] args) {
        Player player1 = new Player(true, "Player 1");
        Player player2 = new Player(false, "Player 2");
        Game chessGame = new Game(player1, player2);
        chessGame.start();
    }
}
