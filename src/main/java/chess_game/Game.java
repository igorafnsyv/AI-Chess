package chess_game;

import chess_game.chess_pieces.Piece;

import java.util.List;
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
                String command = scanner.nextLine();
                Matcher matcher = moveCommandPattern.matcher(command);
                while (!matcher.matches()) {
                    System.out.println("Invalid Move Format!");
                    System.out.print("Enter move: ");
                    command = scanner.nextLine();
                    matcher = moveCommandPattern.matcher(command);
                }
                String[] positionsArray = command.split("\\s");
                String startPosition = positionsArray[0];
                String destinationPosition = positionsArray[1];
                Piece piece = board.getPosition(startPosition).getPiece();
                if (piece == null) {
                    System.out.println("No piece at " + startPosition);
                    continue;
                }
                Move move = new Move(startPosition, destinationPosition);
                if (currentPlayer.makeMove(move, board)) {
                    System.out.println("Move " + piece + " from " + startPosition + " to " + destinationPosition);
                    if (checkMateDetector.isWhiteKingChecked(board) || checkMateDetector.isBlackKingChecked(board)) {
                        List<Position> whiteKingCheckedFromPosition = checkMateDetector.getBlackCheckWhitePositions();
                        List<Position> blackKingCheckedFromPosition = checkMateDetector.getWhiteCheckBlackKingPositions();
                        if (!whiteKingCheckedFromPosition.isEmpty()) {
                            System.out.println("White King is checked from: " + whiteKingCheckedFromPosition);
                        }
                        if (!blackKingCheckedFromPosition.isEmpty()) {
                            System.out.println("Black King is checked from: " + blackKingCheckedFromPosition);
                        }
                    }
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
