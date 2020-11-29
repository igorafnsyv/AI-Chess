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
    private final static int MAX_DEPTH = 3;


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
            this.printBoardAndPlayerInfo();
            if (!currentPlayer.isComputer()) {
                this.handleHumanPlayer(scanner, moveCommandPattern);
            } else {
                AiPlayer computer = (AiPlayer) currentPlayer;
                List<Move>  moves = computer.getLegalMoves(board);
                Move computerMove = computer.findBestMove(moves, board, MAX_DEPTH);
                if (computerMove == null) {
                    break;
                }
                computer.makeMove(computerMove, board);
                this.printMoveInformation(computerMove, true);
            }
            this.setCurrentPlayer(getNextPlayer());

        }

    }

    private void printBoardAndPlayerInfo() {
        System.out.println(board);
        System.out.println();
        System.out.println("Current Player is " + currentPlayer);
        System.out.println("Move format: start destination (example: A2 A3)");
    }

    private void handleHumanPlayer(Scanner scanner, Pattern moveCommandPattern) {
        boolean correctMoveMade = false;
        while (!correctMoveMade) {
            System.out.print("Enter move: ");
            String command = this.readMoveCommand(scanner, moveCommandPattern);
            String[] positionsArray = command.split("\\s");
            String startPosition = positionsArray[0];
            String destinationPosition = positionsArray[1];
            Piece piece = board.getPosition(startPosition).getPiece();
            if (piece == null) {
                System.out.println("No piece at " + startPosition);
                continue;
            }
            Move move = new Move(startPosition, destinationPosition);
            correctMoveMade = currentPlayer.makeMove(move, board);
            this.printMoveInformation(move, correctMoveMade);
        } // correct move made
    }

    private String readMoveCommand(Scanner scanner, Pattern moveCommandPattern) {
        String command = scanner.nextLine();
        Matcher matcher = moveCommandPattern.matcher(command);
        while (!matcher.matches()) {
            this.printIncorrectMoveMessage();
            command = scanner.nextLine();
            matcher = moveCommandPattern.matcher(command);
        }
        return command;
    }

    private void printMoveInformation(Move move, boolean correctMoveMade) {
        String start = move.getStartPosition();
        String destination = move.getMoveDestination();

        if (correctMoveMade) {
            Piece piece = board.getPosition(destination).getPiece();
            System.out.println("Move " +piece + " from " + start + " to " + destination);
            this.printCheckInformation();
        } else {
            Piece piece = board.getPosition(start).getPiece();
            System.out.println("Illegal Move for " + piece + " from " + start + " to " + destination);
        }
    }

    private void printIncorrectMoveMessage() {
        System.out.println("Invalid Move Format!");
        System.out.print("Enter move: ");
    }

    private void printCheckInformation() {
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
    }

    public static void main(String[] args) {
        Player player1 = new Player(true, "Player 1");
        Player player2 = new AiPlayer(false, "Computer");
        Game chessGame = new Game(player1, player2);
        chessGame.start();
    }
}
