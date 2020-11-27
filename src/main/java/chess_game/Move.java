package chess_game;

import chess_game.chess_pieces.Piece;

public class Move {

    private final Position startPosition;
    private final Position moveDestination;

    public Move(Position startPosition, Position moveDestination) {
        this.startPosition = startPosition;
        this.moveDestination = moveDestination;
    }

    public Position getStartPosition() {
        return startPosition;
    }

    public Position getMoveDestination() {
        return moveDestination;
    }

    public ChessBoard getBoardStateAfterMove(ChessBoard board) {
        ChessBoard boardCopy = board.makeCopy();
        Piece piece = boardCopy.getPosition(startPosition.toString()).getPiece();
        boardCopy.movePiece(piece, moveDestination.toString());
        return boardCopy;
    }
}
