package pieces;

import board.ChessBoard;
import utils.Position;

public abstract class Piece {
    private String color;
    private Position position;

    public Piece(String color, Position position) {
        this.color = color;
        this.position = position;
    }

    public String getColor() {
        return color;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public abstract boolean isValidMove(ChessBoard board, Position newPosition);
    public abstract java.util.List<Position> possibleMoves(ChessBoard board);

    public boolean isCapture(ChessBoard board, Position newPosition) {
        Piece destinationPiece = board.getPieceAt(newPosition);
        return destinationPiece != null && !destinationPiece.getColor().equals(this.color);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName().charAt(0) + (color.equals("White") ? "W" : "B");
    }
}
