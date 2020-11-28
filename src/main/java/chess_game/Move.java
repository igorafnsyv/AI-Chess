package chess_game;

import chess_game.chess_pieces.Piece;

public class Move {

    private final String startPosition;
    private final String moveDestination;

    public Move(String startPosition, String moveDestination) {
        this.startPosition = startPosition;
        this.moveDestination = moveDestination;
    }

    public String getStartPosition() {
        return startPosition;
    }

    public String getMoveDestination() {
        return moveDestination;
    }

    public ChessBoard getBoardStateAfterMove(ChessBoard board) {
        ChessBoard boardCopy = board.makeCopy();
        Piece piece = boardCopy.getPosition(startPosition).getPiece();
        boardCopy.movePiece(piece, moveDestination);
        return boardCopy;
    }

    @Override
    public String toString() {
        return startPosition + " - " + moveDestination;
    }
}
