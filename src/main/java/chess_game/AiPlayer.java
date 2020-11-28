package chess_game;

import chess_game.chess_pieces.Piece;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
Currently plays Black
 */
public class AiPlayer extends Player {

    public AiPlayer(boolean white, String name) {
        super(white, name);
    }

    /*
    Idea behind this method:
    Collect all legal moves AI player can perform and filter out moves which will put Black King in check
    As according to chess rules, when a King is checked, the next move must cancel the check.

    In case no legal moves, the game ends?
     */
    public List<Move> getLegalMoves(ChessBoard board) {
        List<Move> moves = new LinkedList<>();
        for (Piece piece : board.getBlackPieces()) {
            List<Position> legalPositions = piece.getLegalMovePositions(board);
            for (Position potentialPosition : legalPositions) {
                Move potentialMove = new Move(piece.getPosition().toString(), potentialPosition.toString());
                ChessBoard boardAfterMove = potentialMove.getBoardStateAfterMove(board);
                CheckMateDetector detector = new CheckMateDetector();
                if (!detector.isBlackKingChecked(boardAfterMove)) {
                    moves.add(potentialMove);
                }
            }

        }
        return moves;
    }


}
