package pieces;

import board.ChessBoard;
import utils.Position;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {
    private boolean firstMove = true;

    public Pawn(String color, Position position) {
        super(color, position);
    }

    @Override
    public boolean isValidMove(ChessBoard board, Position newPosition) {
        int rowDiff = newPosition.getRow() - getPosition().getRow();
        int colDiff = Math.abs(newPosition.getColumn() - getPosition().getColumn());

        if (firstMove) {
            // Pawn can move 2 spaces forward on first move
            if (colDiff == 0 && rowDiff == (getColor().equals("White") ? 1 : -1) && board.getPieceAt(newPosition) == null) {
                return true;
            }
            if (colDiff == 0 && rowDiff == (getColor().equals("White") ? 2 : -2) && board.getPieceAt(newPosition) == null) {
                Position intermediate = new Position(getPosition().getRow() + (getColor().equals("White") ? 1 : -1), getPosition().getColumn());
                return board.getPieceAt(intermediate) == null;
            }
        } else {
            // Regular pawn move
            if (colDiff == 0 && rowDiff == (getColor().equals("White") ? 1 : -1) && board.getPieceAt(newPosition) == null) {
                return true;
            }
            // Capture diagonally
            if (colDiff == 1 && rowDiff == (getColor().equals("White") ? 1 : -1) && isCapture(board, newPosition)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public List<Position> possibleMoves(ChessBoard board) {
        List<Position> moves = new ArrayList<>();
        int row = getPosition().getRow();
        int col = getPosition().getColumn();
        int direction = getColor().equals("White") ? 1 : -1;

        // One square forward
        if (row + direction >= 0 && row + direction < 8) {
            if (board.getPieceAt(row + direction, col) == null) {
                moves.add(new Position(row + direction, col));
                // Two squares forward on first move
                if (firstMove && row + 2 * direction >= 0 && row + 2 * direction < 8 && board.getPieceAt(row + 2 * direction, col) == null) {
                    moves.add(new Position(row + 2 * direction, col));
                }
            }
        }

        // Diagonal captures
        if (row + direction >= 0 && row + direction < 8) {
            if (col - 1 >= 0 && board.getPieceAt(row + direction, col - 1) != null && isCapture(board, new Position(row + direction, col - 1))) {
                moves.add(new Position(row + direction, col - 1));
            }
            if (col + 1 < 8 && board.getPieceAt(row + direction, col + 1) != null && isCapture(board, new Position(row + direction, col + 1))) {
                moves.add(new Position(row + direction, col + 1));
            }
        }

        return moves;
    }

    @Override
    public void setPosition(Position position) {
        super.setPosition(position);
        firstMove = false;  // Update firstMove status after the first move
    }

    @Override
    public String toString() {
        return getColor().equals("White") ? "P" : "p";
    }
}
