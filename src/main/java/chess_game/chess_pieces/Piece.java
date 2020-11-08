package chess_game.chess_pieces;//TODO: make abstract

import chess_game.ChessBoard;
import chess_game.Position;

public abstract class Piece {


    private final boolean white;

    public Piece(boolean white) {
        this.white = white;
    }


    //TODO: make abstract
    public abstract boolean canMoveTo(Position start, Position destination, ChessBoard board);


    public boolean isWhite() {
        return white;
    }

}
