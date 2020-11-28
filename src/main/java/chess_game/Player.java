package chess_game;

import chess_game.chess_pieces.Piece;

public class Player {

    private boolean white;
    private String name;

    public Player(boolean white, String name) {
        this.white = white;
        this.name = name;
    }

    public boolean isComputer() {
        return false;
    }

    public boolean isWhite() {
        return white;
    }

    public boolean makeMove(Move move,  ChessBoard board) {

        Piece piece = board.getPosition(move.getStartPosition().toString()).getPiece();
        //Player can move only its own chess pieces
        boolean ownPieces = piece.isWhite() == this.white;
        if (ownPieces) {
            return board.movePiece(piece, move.getMoveDestination().toString());

        }
        return false;

    }

    @Override
    public String toString() {
        return name + " : " + (isWhite() ? "White" : "Black");
    }
}
