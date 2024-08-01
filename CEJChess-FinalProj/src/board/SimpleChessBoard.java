package board;

import pieces.*;
import utils.Position;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleChessBoard extends JFrame {
    private ChessBoard chessBoard;
    private Position selectedPiece;

    private static final String WHITE_PAWN = "\u2659";
    private static final String WHITE_ROOK = "\u2656";
    private static final String WHITE_KNIGHT = "\u2658";
    private static final String WHITE_BISHOP = "\u2657";
    private static final String WHITE_QUEEN = "\u2655";
    private static final String WHITE_KING = "\u2654";

    private static final String BLACK_PAWN = "\u265F";
    private static final String BLACK_ROOK = "\u265C";
    private static final String BLACK_KNIGHT = "\u265E";
    private static final String BLACK_BISHOP = "\u265D";
    private static final String BLACK_QUEEN = "\u265B";
    private static final String BLACK_KING = "\u265A";

    private static final int FONT_SIZE = 35;

    public SimpleChessBoard(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
        initUI();
    }

    private void initUI() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 8));
        ButtonHandler handler = new ButtonHandler();

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                JButton button = new JButton();
                button.setBackground((row + col) % 2 == 0 ? Color.WHITE : Color.GRAY);
                button.setFont(new Font("SansSerif", Font.BOLD, FONT_SIZE));
                button.addActionListener(handler);
                button.setEnabled(true);
                panel.add(button);
            }
        }

        add(panel);

        setTitle("Chess");
        setSize(900, 900);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        refreshBoard(); // Refresh the board initially
    }

    private class ButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            JButton button = (JButton) event.getSource();
            int row = button.getY() / button.getHeight();
            int col = button.getX() / button.getWidth();

            Position clickedPosition = new Position(row, col);

            if (selectedPiece == null) {
                Piece pieceAtPosition = chessBoard.getPieceAt(clickedPosition);
                if (pieceAtPosition != null && pieceAtPosition.getColor().equals(chessBoard.getCurrentTurn())) {
                    selectedPiece = clickedPosition;
                    System.out.println("Selected piece at: " + selectedPiece);
                    refreshBoard(); // Refresh to highlight selected piece
                }
            } else {
                if (chessBoard.movePiece(selectedPiece, clickedPosition)) {
                    System.out.println("Moved piece from: " + selectedPiece + " to: " + clickedPosition);
                    selectedPiece = null;
                } else {
                    System.out.println("Invalid move from: " + selectedPiece + " to: " + clickedPosition);
                    selectedPiece = null; // Deselect if the move is invalid
                }
                refreshBoard();
            }
        }
    }

    private void refreshBoard() {
        JPanel panel = (JPanel) getContentPane().getComponent(0);
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                JButton button = (JButton) panel.getComponent(row * 8 + col);
                Piece piece = chessBoard.getPieceAt(row, col);
                button.setText(getPieceUnicode(piece));

                // Set background color based on piece and movement validity
                if (selectedPiece != null && isValidMove(selectedPiece, new Position(row, col))) {
                    button.setBackground(Color.GREEN); // Highlight valid move positions
                } else if (piece != null && !piece.getColor().equals(chessBoard.getCurrentTurn())) {
                    button.setBackground(Color.LIGHT_GRAY); // Grayed out for opponent's pieces
                } else {
                    button.setBackground((row + col) % 2 == 0 ? Color.WHITE : Color.GRAY);
                }
            }
        }
    }

    private boolean isValidMove(Position from, Position to) {
        Piece piece = chessBoard.getPieceAt(from);
        return piece != null && piece.isValidMove(chessBoard, to);
    }

    private String getPieceUnicode(Piece piece) {
        if (piece == null) return "";
        switch (piece.getClass().getSimpleName()) {
            case "Pawn":
                return piece.getColor().equals("White") ? WHITE_PAWN : BLACK_PAWN;
            case "Rook":
                return piece.getColor().equals("White") ? WHITE_ROOK : BLACK_ROOK;
            case "Knight":
                return piece.getColor().equals("White") ? WHITE_KNIGHT : BLACK_KNIGHT;
            case "Bishop":
                return piece.getColor().equals("White") ? WHITE_BISHOP : BLACK_BISHOP;
            case "Queen":
                return piece.getColor().equals("White") ? WHITE_QUEEN : BLACK_QUEEN;
            case "King":
                return piece.getColor().equals("White") ? WHITE_KING : BLACK_KING;
            default:
                return "";
        }
    }
}
