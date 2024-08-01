package pieces;

import board.ChessBoard;
import utils.Position;

import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece {
    public Rook(String color, Position position) {
        super(color, position);
    }

    @Override
    public boolean isValidMove(ChessBoard board, Position newPosition) {
        int rowDiff = newPosition.getRow() - getPosition().getRow();
        int colDiff = newPosition.getColumn() - getPosition().getColumn();
        
        // Rook can only move horizontally or vertically
        if (rowDiff == 0 || colDiff == 0) {
            return isPathClear(board, newPosition);
        }

        return false;
    }

    private boolean isPathClear(ChessBoard board, Position newPosition) {
        int row = getPosition().getRow();
        int col = getPosition().getColumn();
        int newRow = newPosition.getRow();
        int newCol = newPosition.getColumn();

        // Check vertical path
        if (row == newRow) {
            int startCol = Math.min(col, newCol) + 1;
            int endCol = Math.max(col, newCol);
            for (int i = startCol; i < endCol; i++) {
                if (board.getPieceAt(row, i) != null) {
                    return false;
                }
            }
        }

        // Check horizontal path
        if (col == newCol) {
            int startRow = Math.min(row, newRow) + 1;
            int endRow = Math.max(row, newRow);
            for (int i = startRow; i < endRow; i++) {
                if (board.getPieceAt(i, col) != null) {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public List<Position> possibleMoves(ChessBoard board) {
        List<Position> moves = new ArrayList<>();
        int row = getPosition().getRow();
        int col = getPosition().getColumn();

        // Horizontal and vertical moves
        for (int i = row + 1; i < 8; i++) {
            if (board.getPieceAt(i, col) == null) {
                moves.add(new Position(i, col));
            } else {
                moves.add(new Position(i, col));
                break;
            }
        }
        for (int i = row - 1; i >= 0; i--) {
            if (board.getPieceAt(i, col) == null) {
                moves.add(new Position(i, col));
            } else {
                moves.add(new Position(i, col));
                break;
            }
        }
        for (int i = col + 1; i < 8; i++) {
            if (board.getPieceAt(row, i) == null) {
                moves.add(new Position(row, i));
            } else {
                moves.add(new Position(row, i));
                break;
            }
        }
        for (int i = col - 1; i >= 0; i--) {
            if (board.getPieceAt(row, i) == null) {
                moves.add(new Position(row, i));
            } else {
                moves.add(new Position(row, i));
                break;
            }
        }

        return moves;
    }

    @Override
    public String toString() {
        return getColor().equals("White") ? "R" : "r";
    }
}
