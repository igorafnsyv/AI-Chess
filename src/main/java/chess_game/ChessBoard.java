package chess_game;


import chess_game.chess_pieces.*;

import java.util.*;

public class ChessBoard {
    private Map<String, Position> board;
    private Position whiteKingPosition;
    private Position blackKingPosition;

    public static ChessBoard initializeEmptyBoard() {
        return new ChessBoard();
    }

    public static ChessBoard initializeBoard() {
        ChessBoard board = new ChessBoard();
        board.getPosition("A1").setPiece(new Rook(true));
        board.getPosition("B1").setPiece(new Knight(true));
        board.getPosition("C1").setPiece(new Bishop(true));
        board.getPosition("D1").setPiece(new Queen(true));
        board.getPosition("E1").setPiece(new King(true));
        board.getPosition("F1").setPiece(new Bishop(true));
        board.getPosition("G1").setPiece(new Knight(true));
        board.getPosition("H1").setPiece(new Rook(true));
        for (char ch = 'A'; ch <= 'H'; ch++) {
            String position = String.valueOf(ch) + 2;
            board.getPosition(position).setPiece(new Pawn(true));
        }

        board.getPosition("A8").setPiece(new Rook(false));
        board.getPosition("B8").setPiece(new Knight(false));
        board.getPosition("C8").setPiece(new Bishop(false));
        board.getPosition("D8").setPiece(new Queen(false));
        board.getPosition("E8").setPiece(new King(false));
        board.getPosition("F8").setPiece(new Bishop(false));
        board.getPosition("G8").setPiece(new Knight(false));
        board.getPosition("H8").setPiece(new Rook(false));
        for (char ch = 'A'; ch <= 'H'; ch++) {
            String position = String.valueOf(ch) + 7;
            board.getPosition(position).setPiece(new Pawn(false));
        }

        return board;
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

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 8; i > 0; i--) {
            builder.append(i);
            builder.append(" ");
            for (char ch = 'A'; ch <= 'H'; ch++) {
                String position = String.valueOf(ch) + i;
                builder.append(Objects.toString(board.get(position).getPiece(), "x "));
                builder.append(" ");
            }
            builder.append(" ");
            builder.append(i);
            builder.append(System.lineSeparator());
        }
        builder.append(System.lineSeparator());
        builder.append("  ");
        for (char ch = 'A'; ch <= 'H'; ch++) {
            builder.append(ch);
            builder.append(" ");
            builder.append(" ");
        }

        return builder.toString();
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
