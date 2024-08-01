package pieces;

import board.ChessBoard;
import utils.Position;

import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece {
    public Queen(String color, Position position) {
        super(color, position);
    }

    @Override
    public boolean isValidMove(ChessBoard board, Position newPosition) {
        int rowDiff = Math.abs(newPosition.getRow() - getPosition().getRow());
        int colDiff = Math.abs(newPosition.getColumn() - getPosition().getColumn());
        
        // Queen moves horizontally, vertically, or diagonally
        return (rowDiff == colDiff || rowDiff == 0 || colDiff == 0) && isPathClear(board, newPosition);
    }

    private boolean isPathClear(ChessBoard board, Position newPosition) {
        int row = getPosition().getRow();
        int col = getPosition().getColumn();
        int newRow = newPosition.getRow();
        int newCol = newPosition.getColumn();

        if (row == newRow || col == newCol) {
            // Check horizontal or vertical path
            int rowStep = (row == newRow) ? 0 : (newRow > row ? 1 : -1);
            int colStep = (col == newCol) ? 0 : (newCol > col ? 1 : -1);
            int r = row + rowStep;
            int c = col + colStep;

            while (r != newRow || c != newCol) {
                if (board.getPieceAt(r, c) != null) {
                    return false;
                }
                r += rowStep;
                c += colStep;
            }
        } else if (Math.abs(row - newRow) == Math.abs(col - newCol)) {
            // Check diagonal path
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
        }

        return true;
    }

    @Override
    public List<Position> possibleMoves(ChessBoard board) {
        List<Position> moves = new ArrayList<>();
        int row = getPosition().getRow();
        int col = getPosition().getColumn();

        // Horizontal, vertical, and diagonal moves
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
        return getColor().equals("White") ? "Q" : "q";
    }
}
