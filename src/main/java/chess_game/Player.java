package chess_game;

import chess_game.chess_pieces.Piece;

public class Player {

    private boolean white;

    public Player(boolean white) {
        this.white = white;
    }

    public boolean isWhite() {
        return white;
    }

        public boolean makeMove(Position start, Position destination, ChessBoard board) {
            Piece piece = start.getPiece();
            //Player can move only its own chess pieces
            boolean ownPieces = piece.isWhite() == this.white;
            if (ownPieces && piece.canMoveTo(destination, board)) {
                piece.moveTo(start, destination);
                return true;
            }
            return false;

    }
}
