package chess_game.chess_pieces;

import chess_game.ChessBoard;
import chess_game.Position;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;


//TODO: when king is checked, next move must prevent check
//TODO: current problem - for isCheckable, when considering new position, current positon block all between positions free
/*
Need to fix above mentioned all between position problem by: getting all legal moves for piece and checking if position
is in the list of legal moves
 */
public class King extends Piece {

    private boolean checked = false;


    public King(boolean white) {
        super(white);
    }


    public boolean isChecked(ChessBoard board) {
        this.checked = isCheckable(this.getPosition(), board);
        return checked;
    }

    private boolean isCheckable(Position pos, ChessBoard board) {

        String position = pos.toString();
        char column = position.charAt(0);
        char row = position.charAt(1);


        // Checking same row, columns on the left
        for (char currentCol = (char) (column - 1); currentCol >= 'A'; currentCol--) {
            Position currentPosition = board.getPosition("" + currentCol + row);
            Piece piece = currentPosition.getPiece();
            if (piece != null) {
                if (piece.isWhite() == this.isWhite()) break;
                if( piece.canMoveTo(currentPosition, pos, board)) {
                     return true;
                }
            }
        }

        // Checking same row, columns on the right
        for (char currentCol = (char) (column + 1); currentCol <= 'H'; currentCol++) {
            Position currentPosition = board.getPosition("" + currentCol + row);
            Piece piece = currentPosition.getPiece();
            if (piece != null) {
                if (piece.isWhite() == this.isWhite()) break;
                if (piece.canMoveTo(currentPosition, pos, board)) {
                    return true;
                }

            }
        }
        // Checking same column, rows below
        for (char currentRow = (char) (row - 1); currentRow >= '1'; currentRow--) {
            Position currentPosition = board.getPosition("" + column + currentRow);
            Piece piece = currentPosition.getPiece();
            if (piece != null) {
                if (piece.isWhite() == this.isWhite()) break;
                if (piece.canMoveTo(currentPosition, pos, board)){
                    return true;
                }
            }
        }

        // Checking same column, rows above
        for (char currentRow = (char) (row + 1); currentRow <= '8'; currentRow++) {
            Position currentPosition = board.getPosition("" + column + currentRow);
            Piece piece = currentPosition.getPiece();
            if (piece != null) {
                if (piece.isWhite() == this.isWhite()) break;
                if (piece.canMoveTo(currentPosition, pos, board)) {
                    return true;
                }
            }
        }


        // Check from Knight attack
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
                //knight must be of opposite color
                .filter((positionEntry) -> positionEntry != null && positionEntry.getPiece() instanceof Knight)
                .collect(Collectors.toList());
        if (knightPositionList.size() != 0) return true;




        // Checking from left low diagonal
        char currentRow = (char) (row - 1);
        char currentColumn = (char) (column - 1);
        while (currentColumn >= 'A' && currentRow >= '1') {
            Position currentPosition = board.getPosition("" + currentColumn + currentRow);
            Piece piece = currentPosition.getPiece();
            if (piece != null) {
                if (piece.isWhite() == this.isWhite()) break;
                if (piece.canMoveTo(currentPosition, pos, board)) {
                    return true;
                }
            }
            currentRow--;
            currentColumn--;
        }

        // Checking from right low diagonal
        currentRow = (char) (row - 1);
        currentColumn = (char) (column + 1);
        while (currentColumn <= 'H' && currentRow >= '1') {
            Position currentPosition = board.getPosition("" + currentColumn + currentRow);
            Piece piece = currentPosition.getPiece();
            if (piece != null) {
                if (piece.isWhite() == this.isWhite()) break;
                if (piece.canMoveTo(currentPosition, pos, board)) {
                    return true;
                }
            }
            currentRow--;
            currentColumn++;
        }

        //Checking from diagonal right above
        currentRow = (char) (row + 1);
        currentColumn = (char) (column + 1);
        while (currentColumn <= 'H' && currentRow <= '8') {
            Position currentPosition = board.getPosition("" + currentColumn + currentRow);
            Piece piece = currentPosition.getPiece();
            if (piece != null) {
                if (piece.isWhite() == this.isWhite()) break;
                if (piece.canMoveTo(currentPosition, pos, board)) {
                    return true;
                }
            }
            currentRow++;
            currentColumn++;
        }

        //Checking from diagonal left above
        currentRow = (char) (row + 1);
        currentColumn = (char) (column - 1);
        while (currentColumn >= 'A' && currentRow <= '8') {
            Position currentPosition = board.getPosition("" + currentColumn + currentRow);
            Piece piece = currentPosition.getPiece();
            if (piece != null) {
                if (piece.isWhite() == this.isWhite()) break;
                if (piece.canMoveTo(currentPosition, pos, board)) {
                    return true;
                }
            }
            currentRow++;
            currentColumn--;
        }
        return false;
    }

    public boolean isMate(ChessBoard board) {
        if (!this.checked) return false;
        if (this.getLegalMovePositions(board).isEmpty()) return true;

        return false;
    }



    //TODO: remove startPosition
    @Override
    public boolean canMoveTo(Position start, Position destination, ChessBoard board) {
        if (!start.getPiece().equals(this)) return false;
        if (!canReach(start, destination)) return false;
        boolean differentPosition = !start.equals(destination);
        boolean emptyPosition = destination.getPiece() == null;
        if (differentPosition && emptyPosition) return true;
        if (differentPosition) {
            // If destination is not empty, pieces at two position have to be of different color
            return start.getPiece().isWhite() != destination.getPiece().isWhite();
        }

        return false;
    }

    @Override
    public List<Position> getLegalMovePositions(ChessBoard board) {
        char[] position = this.getPosition().toString().toCharArray();
        List<Position> positions = new ArrayList<>();
        positions.add(board.getPosition(String.valueOf(position[0]) + ((char) (position[1] + 1))));
        positions.add(board.getPosition(String.valueOf((char) (position[0] + 1)) + ((char) (position[1] + 1))));
        positions.add(board.getPosition(String.valueOf((char) (position[0] + 1)) + (position[1])));
        positions.add(board.getPosition(String.valueOf((char) (position[0] + 1)) + ((char) (position[1] - 1))));
        positions.add(board.getPosition(String.valueOf(position[0]) + ((char) (position[1] - 1))));
        positions.add(board.getPosition(String.valueOf((char) (position[0] - 1)) + ((char) (position[1] - 1))));
        positions.add(board.getPosition(String.valueOf((char) (position[0] - 1)) + ((char) (position[1]))));
        positions.add(board.getPosition(String.valueOf((char) (position[0] - 1)) + ((char) (position[1] + 1))));
        positions = positions.stream().filter(Objects::nonNull).collect(Collectors.toList());
        Iterator<Position> iterator = positions.iterator();
        while (iterator.hasNext()) {
            Position pos = iterator.next();
            boolean potentialMovePosition = pos.getPiece() == null ||
                    (pos.getPiece() != null && pos.getPiece().isWhite() != this.isWhite());
            if (!potentialMovePosition || isCheckable(pos, board)) {
                // Use iterator, otherwise concurrent modification exception is thrown
                iterator.remove();
            }
        }
        return positions;
    }

    private boolean canReach(Position start, Position destination) {
        return start.distanceTo(destination) == 1;
    }

    @Override
    public String toString() {
        String color = super.toString();
        return color + "K";
    }
}
