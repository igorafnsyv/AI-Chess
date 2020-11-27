package chess_game.chess_pieces;

import chess_game.ChessBoard;
import chess_game.Position;

import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece{

    public Queen(boolean white) {
        super(white);
    }


    @Override
    public List<Position> getLegalMovePositions(ChessBoard board) {
        List<Position> positions = new ArrayList<>();

        char[] positionChars = this.getPosition().toString().toCharArray();
        char startCol = positionChars[0];
        char startRow = positionChars[1];

        //top right diagonal
        int colStep = 1;
        int rowStep = 1;
        char currentCol = (char) (startCol + 1);
        char currentRow = (char) (startRow + 1);
        while (currentCol <= 'H' && currentRow <= '8') {
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

        //top left diagonal
        colStep = -1;
        rowStep = 1;
        currentCol = (char) (startCol + colStep);
        currentRow = (char) (startRow + rowStep);
        while (currentCol >= 'A' && currentRow <= '8') {
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

        // low right diagonal
        colStep = +1;
        rowStep = -1;
        currentCol = (char) (startCol + colStep);
        currentRow = (char) (startRow + rowStep);
        while (currentCol <= 'H' && currentRow >= '1') {
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

        //low left diagonal
        colStep = -1;
        rowStep = -1;
        currentCol = (char) (startCol + colStep);
        currentRow = (char) (startRow + rowStep);
        while (currentCol >= 'A' && currentRow >= '1') {
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

        //top vertical
        colStep = 0;
        rowStep = 1;
        currentCol = (char) (startCol + colStep);
        currentRow = (char) (startRow + rowStep);
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
        return 9;
    }

    @Override
    public String toString() {
        String color = super.toString();
        return color + "Q";
    }


}
