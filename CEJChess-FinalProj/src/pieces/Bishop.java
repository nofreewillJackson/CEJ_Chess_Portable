package pieces;

import board.ChessBoard;
import utils.Position;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {
    public Bishop(String color, Position position) {
        super(color, position);
    }

    @Override
    public boolean isValidMove(ChessBoard board, Position newPosition) {
        int rowDiff = Math.abs(newPosition.getRow() - getPosition().getRow());
        int colDiff = Math.abs(newPosition.getColumn() - getPosition().getColumn());

        // Bishop moves diagonally
        return rowDiff == colDiff && isPathClear(board, newPosition);
    }

    private boolean isPathClear(ChessBoard board, Position newPosition) {
        int row = getPosition().getRow();
        int col = getPosition().getColumn();
        int newRow = newPosition.getRow();
        int newCol = newPosition.getColumn();

        int rowStep = (newRow > row) ? 1 : -1;
        int colStep = (newCol > col) ? 1 : -1;

        int r = row + rowStep;
        int c = col + colStep;

        while (r != newRow && c != newCol) {
            if (board.getPieceAt(r, c) != null) {
                return false;
            }
            r += rowStep;
            c += colStep;
        }

        return true;
    }

    @Override
    public List<Position> possibleMoves(ChessBoard board) {
        List<Position> moves = new ArrayList<>();
        int row = getPosition().getRow();
        int col = getPosition().getColumn();

        // Diagonal moves
        for (int i = row + 1, j = col + 1; i < 8 && j < 8; i++, j++) {
            if (board.getPieceAt(i, j) == null) {
                moves.add(new Position(i, j));
            } else {
                moves.add(new Position(i, j));
                break;
            }
        }
        for (int i = row + 1, j = col - 1; i < 8 && j >= 0; i++, j--) {
            if (board.getPieceAt(i, j) == null) {
                moves.add(new Position(i, j));
            } else {
                moves.add(new Position(i, j));
                break;
            }
        }
        for (int i = row - 1, j = col + 1; i >= 0 && j < 8; i--, j++) {
            if (board.getPieceAt(i, j) == null) {
                moves.add(new Position(i, j));
            } else {
                moves.add(new Position(i, j));
                break;
            }
        }
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board.getPieceAt(i, j) == null) {
                moves.add(new Position(i, j));
            } else {
                moves.add(new Position(i, j));
                break;
            }
        }

        return moves;
    }

    @Override
    public String toString() {
        return getColor().equals("White") ? "B" : "b";
    }
}
