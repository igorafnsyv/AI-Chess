package chess_game;


import chess_game.chess_pieces.*;

import java.util.*;
import java.util.stream.Collectors;

public class ChessBoard {
    private Map<String, Position> board;
    private Position whiteKingPosition;
    private Position blackKingPosition;

    private ChessBoard() {
        board = new HashMap<>();
        for (int row = 1; row <= 8; row++) {
            for (char col = 'A'; col <= 'H'; col++) {
                String position = String.valueOf(col) + row;
                board.put(position, new Position(position));
            }
        }
    }

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
        }

        board.whiteKingPosition = board.getPosition("E1");
        board.blackKingPosition = board.getPosition("E8");
        return board;
    }

    /*
    This method is used for initial positioning during board initialization
     */
    public void positionPiece(Piece piece, String positionStr) {
        Position position = this.getPosition(positionStr);
        position.setPiece(piece);
        piece.setPosition(position);
    }

    /*
    This method method is used for moves after initialization
    Before the move is made, need to check manually if the move is legal
     */
    public boolean movePiece(Piece piece, String positionStr) {
        if (piece instanceof King) {
            if (piece.isWhite()) {
                whiteKingPosition = piece.getPosition();
            } else {
                blackKingPosition = piece.getPosition();
            }
        }
        Position position = this.getPosition(positionStr);
        //Cannot capture opponent's king
        boolean kingOnDestinationPosition = position.getPiece() instanceof King;
        if (piece.canMoveTo(position, this) && !kingOnDestinationPosition) {
            piece.moveTo(position);
            return true;
        }
        return false;
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

    public Position getWhiteKingPosition() {
        return whiteKingPosition;
    }

    public void setWhiteKingPosition(Position whiteKingPosition) {
        this.whiteKingPosition = whiteKingPosition;
    }

    public Position getBlackKingPosition() {
        return blackKingPosition;
    }

    public void setBlackKingPosition(Position blackKingPosition) {
        this.blackKingPosition = blackKingPosition;
    }

    public List<Piece> getWhitePieces() {
        List<Piece> list = board.values().stream().filter(position -> position.getPiece() != null)
                .map(Position::getPiece)
                .filter(Piece::isWhite)
                .collect(Collectors.toList());
        return list;
    }

    public List<Piece> getBlackPieces() {
        List<Piece> list = board.values().stream().filter(position -> position.getPiece() != null)
                .map(Position::getPiece)
                .filter(piece -> !piece.isWhite())
                .collect(Collectors.toList());
        return list;
    }
}
