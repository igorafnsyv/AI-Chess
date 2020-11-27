package chess_game.chess_pieces;

import chess_game.ChessBoard;
import chess_game.Position;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {

    private boolean madeFirstMove;

    public Pawn(boolean white) {
        super(white);
        madeFirstMove = false;
    }

    @Override
    public void moveTo(Position destination) {
        super.moveTo(destination);
        if (!madeFirstMove) {
            madeFirstMove = true;
        }
    }

    @Override
    public String toString() {
        String color = super.toString();
        return color + "P";
    }


    @Override
    public List<Position> getLegalMovePositions(ChessBoard board) {
        List<Position> positions = new ArrayList<>();
        char currentColumn = this.getPosition().toString().charAt(0);
        char currentRow = this.getPosition().toString().charAt(1);

        int offset = 1;
        //Black pawns move only down
        //White pawns move only up
        if (!this.isWhite()) {
            offset = -1;
        }
        //Can make move forward only if next square is empty
        Position pos = board.getPosition(currentColumn + String.valueOf((char)(currentRow + offset)));

        if (pos != null && pos.getPiece() == null) {
            positions.add(pos);
            //If the first move was not made pawn can move 2 square
            if (!madeFirstMove) {
                Position extraPos = board.getPosition(currentColumn + String.valueOf((char)(currentRow + (2 * offset))));
                if (extraPos != null && extraPos.getPiece() == null) {
                    positions.add(extraPos);
                }
            }
        }
        pos = board.getPosition(((char) (currentColumn + 1)) + String.valueOf((char)(currentRow + offset)));
        if (pos != null && pos.getPiece() != null && pos.getPiece().isWhite() != this.isWhite()) {
            positions.add(pos);
        }
        pos = board.getPosition(((char) (currentColumn - 1)) + String.valueOf((char)(currentRow + offset)));
        if (pos != null && pos.getPiece() != null && pos.getPiece().isWhite() != this.isWhite()) {
            positions.add(pos);
        }

        return positions;
    }

    @Override
    public int getValue() {
        return 1;
    }
}
