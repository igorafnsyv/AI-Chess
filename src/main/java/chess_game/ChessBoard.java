package chess_game;


import java.util.*;

public class ChessBoard {
    private Map<String, Position> board;

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

    public int size() {
        return board.size();
    }

    public static ChessBoard initializeBoard() {
        return new ChessBoard();
    }

}
