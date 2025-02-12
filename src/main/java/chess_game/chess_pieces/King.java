package chess_game.chess_pieces;

import chess_game.ChessBoard;
import chess_game.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;


//TODO: when king is checked, next move must prevent check

public class King extends Piece {

    private boolean checked = false;


    public King(boolean white) {
        super(white);
    }


    public boolean isChecked(ChessBoard board) {
        Position originalPosition = this.getPosition();
        this.checked = isCheckable(originalPosition, board);
        return checked;
    }


    /*
    This is require for king's legal moves. King cannot make a move which will make it checked
    However, if the check comes from a piece located above/below horizontally, the algorithm considers
    moving one square up/down a legal move (since the king's current position blocks the potential position)

    King will be temporary removed to workaround this problem.

    However, when checking diagonals and a piece found is pawn, need to return the king back in its original place.
    This is done because Pawns can make diagonal move only in case there is an enemy in the cell

     */
    private void removeKingFromBoard(Position position, ChessBoard board) {
        board.getPosition(position.toString()).setPiece(null);
    }

    private void positionKingBack(Position position, ChessBoard board, King king) {
        board.getPosition(position.toString()).setPiece(king);
    }


    private boolean checkFromHorizontalOrVertical(ChessBoard board, Position currentPosition, Position destination, Position kingOriginalPosition) {
        Piece piece = currentPosition.getPiece();
        if (piece != null) {
            if (piece.isWhite() == this.isWhite()) return false;
            if( piece.canMoveTo(destination, board)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkFromDiagonal(ChessBoard board, Position currentPosition, Position destination, Position kingOriginalPosition) {
        Piece piece = currentPosition.getPiece();
        if (piece != null) {
            if (piece.isWhite() == this.isWhite()) return false;
            //Pawn can make diagonal move only if opponent is present. Need to return the king
            if (piece instanceof Pawn) {
                this.positionKingBack(kingOriginalPosition, board, this);
                if (piece.canMoveTo(destination, board)) {
                    return true;
                }
                this.removeKingFromBoard(kingOriginalPosition, board);
            }
            else if (piece.canMoveTo(destination, board)) {
                return true;
            }
        }
        return false;
    }

    public boolean isCheckable(Position pos, ChessBoard board) {
        Position originalKingPosition = this.getPosition();
        this.removeKingFromBoard(this.getPosition(), board);
        boolean result =  isCheckableHelper(pos, board);
        positionKingBack(originalKingPosition, board, this);
        return result;
    }

    public boolean isCheckableHelper(Position pos, ChessBoard board) {

        String position = pos.toString();

        char column = position.charAt(0);
        char row = position.charAt(1);

        Position kingOriginalPosition = this.getPosition();
        // Checking same row, columns on the left
        for (char currentCol = (char) (column - 1); currentCol >= 'A'; currentCol--) {
            Position currentPosition = board.getPosition("" + currentCol + row);
            if (checkFromHorizontalOrVertical(board, currentPosition, pos, kingOriginalPosition)) return true;
        }

        // Checking same row, columns on the right
        for (char currentCol = (char) (column + 1); currentCol <= 'H'; currentCol++) {
            Position currentPosition = board.getPosition("" + currentCol + row);
            if (checkFromHorizontalOrVertical(board, currentPosition, pos, kingOriginalPosition)) return true;
        }
        // Checking same column, rows below
        for (char currentRow = (char) (row - 1); currentRow >= '1'; currentRow--) {
            Position currentPosition = board.getPosition("" + column + currentRow);
            if (checkFromHorizontalOrVertical(board, currentPosition, pos, kingOriginalPosition)) return true;
        }

        // Checking same column, rows above
        for (char currentRow = (char) (row + 1); currentRow <= '8'; currentRow++) {
            Position currentPosition = board.getPosition("" + column + currentRow);
            if (checkFromHorizontalOrVertical(board, currentPosition, pos, kingOriginalPosition)) return true;
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
            if (checkFromDiagonal(board, currentPosition, pos, kingOriginalPosition)) {
                return true;
            }
            currentRow--;
            currentColumn--;
        }

        // Checking from right low diagonal
        currentRow = (char) (row - 1);
        currentColumn = (char) (column + 1);
        while (currentColumn <= 'H' && currentRow >= '1') {
            Position currentPosition = board.getPosition("" + currentColumn + currentRow);
            if (checkFromDiagonal(board, currentPosition, pos, kingOriginalPosition)) {
                return true;
            }
            currentRow--;
            currentColumn++;
        }

        //Checking from diagonal right above
        currentRow = (char) (row + 1);
        currentColumn = (char) (column + 1);
        while (currentColumn <= 'H' && currentRow <= '8') {
            Position currentPosition = board.getPosition("" + currentColumn + currentRow);
            if (checkFromDiagonal(board, currentPosition, pos, kingOriginalPosition)) {
                return true;
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
            if (checkFromDiagonal(board, currentPosition, pos, kingOriginalPosition)) {
                return true;
            }
            currentRow++;
            currentColumn--;
        }
        this.positionKingBack(kingOriginalPosition, board, this);
        return false;


    }

    public boolean isMate(ChessBoard board) {
        if (!this.checked) return false;
        if (this.getLegalMovePositions(board).isEmpty()) return true;

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


        Predicate<Position> potentialMove = pos -> (pos.getPiece() == null ||
                (pos.getPiece() != null && pos.getPiece().isWhite() != this.isWhite()));
        positions = positions.stream()
                .filter(potentialMove)
                .filter(pos -> !isCheckable(pos, board))
        .collect(Collectors.toList());

        return positions;
    }

    @Override
    public King makeCopy() {
        King copy = new King(this.isWhite());
        copy.checked = this.checked;
        return copy;
    }

    @Override
    public int getValue() {
        //King has no value, its loss ends the game
        return 0;
    }

    @Override
    public String toString() {
        String color = super.toString();
        return color + "K";
    }
}
