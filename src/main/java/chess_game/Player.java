package chess_game;

import chess_game.chess_pieces.Piece;

public class Player {

    private boolean white;
    private String name;

    public Player(boolean white, String name) {
        this.white = white;
        this.name = name;
    }

    public boolean isWhite() {
        return white;
    }

        public boolean makeMove(String start, String destination, ChessBoard board) {
            Piece piece = board.getPosition(start).getPiece();
            //Player can move only its own chess pieces
            boolean ownPieces = piece.isWhite() == this.white;
            if (ownPieces && piece.canMoveTo(board.getPosition(destination), board)) {
                board.movePiece(piece, destination);
                return true;
            }
            return false;

    }

    @Override
    public String toString() {
        return name + " : " + (isWhite() ? "White" : "Black");
    }
}
