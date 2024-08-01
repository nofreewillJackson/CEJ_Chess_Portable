package pieces;

import board.ChessBoard;
import utils.Position;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {
    public Knight(String color, Position position) {
        super(color, position);
    }

    @Override
    public boolean isValidMove(ChessBoard board, Position newPosition) {
        int rowDiff = Math.abs(newPosition.getRow() - getPosition().getRow());
        int colDiff = Math.abs(newPosition.getColumn() - getPosition().getColumn());
        
        // Knight moves in an L-shape: 2+1 or 1+2
        return (rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2);
    }

    @Override
    public List<Position> possibleMoves(ChessBoard board) {
        List<Position> moves = new ArrayList<>();
        int row = getPosition().getRow();
        int col = getPosition().getColumn();

        // All potential knight moves
        int[][] potentialMoves = {
            {row + 2, col + 1}, {row + 2, col - 1},
            {row - 2, col + 1}, {row - 2, col - 1},
            {row + 1, col + 2}, {row + 1, col - 2},
            {row - 1, col + 2}, {row - 1, col - 2}
        };

        for (int[] move : potentialMoves) {
            int newRow = move[0];
            int newCol = move[1];
            if (newRow >= 0 && newRow < 8 && newCol >= 0 && newCol < 8) {
                moves.add(new Position(newRow, newCol));
            }
        }

        return moves;
    }

    @Override
    public String toString() {
        return getColor().equals("White") ? "N" : "n";
    }
}
