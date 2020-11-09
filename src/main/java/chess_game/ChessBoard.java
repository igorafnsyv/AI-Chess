package chess_game;


import java.util.*;

public class ChessBoard {
    private Map<String, Position> board;
    private Position whiteKingPosition;
    private Position blackKingPosition;

    public static ChessBoard initializeBoard() {
        return new ChessBoard();
    }

    private ChessBoard() {
        board = new HashMap<>();
        for (int row = 1; row <= 8; row++) {
           for (char col = 'A'; col <= 'H'; col++) {
               String position = String.valueOf(col) + row;
               board.put(position, new Position(position));
           }
        }
    }

    public Position getPosition(String position) {
        return board.get(position);
    }

    public void setWhiteKingPosition(Position whiteKingPosition) {
        this.whiteKingPosition = whiteKingPosition;
    }

    public Position getWhiteKingPosition() {
        return whiteKingPosition;
    }

    public void setBlackKingPosition(Position blackKingPosition) {
        this.blackKingPosition = blackKingPosition;
    }

    public Position getBlackKingPosition() {
        return blackKingPosition;
    }

    public int size() {
        return board.size();
    }



}
