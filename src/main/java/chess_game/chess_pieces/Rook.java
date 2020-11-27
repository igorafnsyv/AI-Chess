package chess_game.chess_pieces;

import chess_game.ChessBoard;
import chess_game.Position;

import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece {

    public Rook(boolean white) {
        super(white);
    }

    @Override
    public Rook makeCopy() {
        return new Rook(this.isWhite());
    }

    @Override
    public List<Position> getLegalMovePositions(ChessBoard board) {
        List<Position> positions = new ArrayList<>();

        //top vertical
        char startCol = getPosition().toString().charAt(0);
        char startRow = getPosition().toString().charAt(1);
        int colStep = 0;
        int rowStep = 1;
        char currentCol = (char) (startCol + colStep);
        char currentRow = (char) (startRow + rowStep);
        while (currentRow <= '8') {
            String potentialPosition = String.valueOf(currentCol) + currentRow;
            Position position = board.getPosition(potentialPosition);
            Piece positionPiece = position.getPiece();
            if (positionPiece == null) {
                positions.add(position);
            } else  if (positionPiece.isWhite() != this.isWhite()){
                positions.add(position);
                break;
            } else {
                break;
            }
            currentCol = (char) (currentCol + colStep);
            currentRow = (char) (currentRow + rowStep);
        }

        //bottom vertical
        colStep = 0;
        rowStep = -1;
        currentCol = (char) (startCol + colStep);
        currentRow = (char) (startRow + rowStep);
        while (currentRow >= '1') {
            String potentialPosition = String.valueOf(currentCol) + currentRow;
            Position position = board.getPosition(potentialPosition);
            Piece positionPiece = position.getPiece();
            if (positionPiece == null) {
                positions.add(position);
            } else  if (positionPiece.isWhite() != this.isWhite()){
                positions.add(position);
                break;
            } else {
                break;
            }
            currentCol = (char) (currentCol + colStep);
            currentRow = (char) (currentRow + rowStep);
        }

        //left horizontal
        colStep = -1;
        rowStep = 0;
        currentCol = (char) (startCol + colStep);
        currentRow = (char) (startRow + rowStep);
        while (currentCol >= 'A') {
            String potentialPosition = String.valueOf(currentCol) + currentRow;
            Position position = board.getPosition(potentialPosition);
            Piece positionPiece = position.getPiece();
            if (positionPiece == null) {
                positions.add(position);
            } else  if (positionPiece.isWhite() != this.isWhite()){
                positions.add(position);
                break;
            } else {
                break;
            }
            currentCol = (char) (currentCol + colStep);
            currentRow = (char) (currentRow + rowStep);
        }

        //right horizontal

        colStep = 1;
        rowStep = 0;
        currentCol = (char) (startCol + colStep);
        currentRow = (char) (startRow + rowStep);
        while (currentCol <= 'H') {
            String potentialPosition = String.valueOf(currentCol) + currentRow;
            Position position = board.getPosition(potentialPosition);
            Piece positionPiece = position.getPiece();
            if (positionPiece == null) {
                positions.add(position);
            } else  if (positionPiece.isWhite() != this.isWhite()){
                positions.add(position);
                break;
            } else {
                break;
            }
            currentCol = (char) (currentCol + colStep);
            currentRow = (char) (currentRow + rowStep);
        }


        return positions;
    }

    @Override
    public int getValue() {
        return 5;
    }

    @Override
    public String toString() {
        String color = super.toString();
        return color + "R";
    }

}
