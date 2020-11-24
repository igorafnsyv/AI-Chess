package chess_game.chess_pieces;

import chess_game.ChessBoard;
import chess_game.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Knight extends Piece {

    public Knight(boolean white) {
        super(white);
    }

    @Override
    public boolean canMoveTo(Position start, Position destination, ChessBoard board) {
        return this.getLegalMovePositions(board).contains(destination);

    }

    @Override
    public List<Position> getLegalMovePositions(ChessBoard board) {
        char column = this.getPosition().toString().charAt(0);
        char row = this.getPosition().toString().charAt(1);
        List<Position> knightPositionList = new ArrayList<>();
        String knightPosition = String.valueOf((char) (column - 1)) + ((char) (row + 2));
        knightPositionList.add(board.getPosition(knightPosition));
        knightPosition = String.valueOf((char) (column + 1)) + ((char) (row + 2));
        knightPositionList.add(board.getPosition(knightPosition));
        knightPosition = String.valueOf((char) (column + 2)) + ((char) (row + 1));
        knightPositionList.add(board.getPosition(knightPosition));
        knightPosition = String.valueOf((char) (column + 2)) + ((char) (row - 1));
        knightPositionList.add(board.getPosition(knightPosition));
        knightPosition = String.valueOf((char) (column + 1)) + ((char) (row - 2));
        knightPositionList.add(board.getPosition(knightPosition));
        knightPosition = String.valueOf((char) (column - 1)) + ((char) (row - 2));
        knightPositionList.add(board.getPosition(knightPosition));
        knightPosition = String.valueOf((char) (column - 2)) + ((char) (row - 1));
        knightPositionList.add(board.getPosition(knightPosition));
        knightPosition = String.valueOf((char) (column - 2)) + ((char) (row + 1));
        knightPositionList.add(board.getPosition(knightPosition));
        knightPositionList = knightPositionList.stream()
                //filter for Valid positons, empty positions or positions occupied with pieces of opposite color
                .filter((positionEntry) -> positionEntry != null &&
                        (positionEntry.getPiece() == null || positionEntry.getPiece().isWhite() != this.isWhite()))
                .collect(Collectors.toList());
        return knightPositionList;
    }

    @Override
    public String toString() {
        String color = super.toString();
        return color + "N";
    }

}
