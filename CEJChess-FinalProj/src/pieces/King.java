package pieces;

import board.ChessBoard;
import utils.Position;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece {
    public King(String color, Position position) {
        super(color, position);
    }

    @Override
    public boolean isValidMove(ChessBoard board, Position newPosition) {
        int rowDiff = Math.abs(newPosition.getRow() - getPosition().getRow());
        int colDiff = Math.abs(newPosition.getColumn() - getPosition().getColumn());
        
        // King moves one square in any direction
        return rowDiff <= 1 && colDiff <= 1;
    }

    @Override
    public List<Position> possibleMoves(ChessBoard board) {
        List<Position> moves = new ArrayList<>();
        int row = getPosition().getRow();
        int col = getPosition().getColumn();

        // All potential king moves
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (i >= 0 && i < 8 && j >= 0 && j < 8 && (i != row || j != col)) {
                    moves.add(new Position(i, j));
                }
            }
        }

        return moves;
    }

    @Override
    public String toString() {
        return getColor().equals("White") ? "K" : "k";
    }
}
