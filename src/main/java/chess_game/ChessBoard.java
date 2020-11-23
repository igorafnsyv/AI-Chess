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
        board.positionPiece(new Rook(true), "A1");
        board.positionPiece(new Knight(true), "B1");
        board.positionPiece(new Bishop(true), "C1");
        board.positionPiece(new Queen(true), "D1");
        board.positionPiece(new King(true), "E1");
        board.positionPiece(new Bishop(true), "F1");
        board.positionPiece(new Knight(true), "G1");
        board.positionPiece(new Rook(true), "H1");
        for (char ch = 'A'; ch <= 'H'; ch++) {
            String position = String.valueOf(ch) + 2;
            board.positionPiece(new Pawn(true), position);
        }

        board.positionPiece(new Rook(false), "A8");
        board.positionPiece(new Knight(false), "B8");
        board.positionPiece(new Bishop(false), "C8");
        board.positionPiece(new Queen(false), "D8");
        board.positionPiece(new King(false), "E8");
        board.positionPiece(new Bishop(false), "F8");
        board.positionPiece(new Knight(false), "G8");
        board.positionPiece(new Rook(false), "H8");

        for (char ch = 'A'; ch <= 'H'; ch++) {
            String position = String.valueOf(ch) + 7;
            board.positionPiece(new Pawn(false), position);
            board.getPosition(position).setPiece(new Pawn(false));
        }

        return board;
    }

    public void positionPiece(Piece piece, String positionStr) {
        Position position = this.getPosition(positionStr);
        position.setPiece(piece);
        piece.setPosition(position);
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

    public int size() {
        return board.size();
    }



}
