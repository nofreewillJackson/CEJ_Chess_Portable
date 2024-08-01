package board;

import pieces.*;
import utils.Position;

public class ChessBoard {
    private Piece[][] board;
    private String currentTurn; // "White" or "Black"

    public ChessBoard() {
        board = new Piece[8][8];
        initializeBoard();
        currentTurn = "White"; // Start with White's turn
    }

    private void initializeBoard() {
        // Initialize White Pieces
        board[0][0] = new Rook("White", new Position(0, 0));
        board[0][1] = new Knight("White", new Position(0, 1));
        board[0][2] = new Bishop("White", new Position(0, 2));
        board[0][3] = new Queen("White", new Position(0, 3));
        board[0][4] = new King("White", new Position(0, 4));
        board[0][5] = new Bishop("White", new Position(0, 5));
        board[0][6] = new Knight("White", new Position(0, 6));
        board[0][7] = new Rook("White", new Position(0, 7));

        for (int col = 0; col < 8; col++) {
            board[1][col] = new Pawn("White", new Position(1, col));
        }

        // Initialize Black Pieces
        board[7][0] = new Rook("Black", new Position(7, 0));
        board[7][1] = new Knight("Black", new Position(7, 1));
        board[7][2] = new Bishop("Black", new Position(7, 2));
        board[7][3] = new Queen("Black", new Position(7, 3));
        board[7][4] = new King("Black", new Position(7, 4));
        board[7][5] = new Bishop("Black", new Position(7, 5));
        board[7][6] = new Knight("Black", new Position(7, 6));
        board[7][7] = new Rook("Black", new Position(7, 7));

        for (int col = 0; col < 8; col++) {
            board[6][col] = new Pawn("Black", new Position(6, col));
        }
    }

    public Piece getPieceAt(int row, int col) {
        return board[row][col];
    }

    public Piece getPieceAt(Position position) {
        return getPieceAt(position.getRow(), position.getColumn());
    }

    public void placePiece(Piece piece, Position position) {
        board[position.getRow()][position.getColumn()] = piece;
    }

    public boolean movePiece(Position from, Position to) {
        Piece piece = getPieceAt(from);
        if (piece != null && piece.getColor().equals(currentTurn) && piece.isValidMove(this, to)) {
            Piece targetPiece = getPieceAt(to);
            if (targetPiece != null && targetPiece.getColor().equals(piece.getColor())) {
                return false; // Can't move to a square occupied by your own piece
            }
            board[to.getRow()][to.getColumn()] = piece;
            board[from.getRow()][from.getColumn()] = null;
            piece.setPosition(to);

            // Check if move results in check
            if (isCheck(currentTurn)) {
                // Undo move if it results in a check
                board[from.getRow()][from.getColumn()] = piece;
                board[to.getRow()][to.getColumn()] = targetPiece;
                piece.setPosition(from);
                return false;
            }

            // Pawn Promotion
            if (piece instanceof Pawn && (to.getRow() == 0 || to.getRow() == 7)) {
                promotePawn(to, new Queen(piece.getColor(), to));
            }

            // Switch turn
            switchTurn();
            return true;
        }
        return false;
    }

    private boolean isCheck(String color) {
        Position kingPos = findKing(color);
        if (kingPos == null) return false;

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece piece = getPieceAt(row, col);
                if (piece != null && !piece.getColor().equals(color)) {
                    if (piece.isValidMove(this, kingPos)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private Position findKing(String color) {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece piece = getPieceAt(row, col);
                if (piece instanceof King && piece.getColor().equals(color)) {
                    return piece.getPosition();
                }
            }
        }
        return null;
    }

    public String getCurrentTurn() {
        return currentTurn;
    }

    public void switchTurn() {
        currentTurn = currentTurn.equals("White") ? "Black" : "White";
    }

    public void promotePawn(Position position, Piece newPiece) {
        Piece pawn = getPieceAt(position);
        if (pawn instanceof Pawn) {
            board[position.getRow()][position.getColumn()] = newPiece;
            newPiece.setPosition(position);
        }
    }
}
