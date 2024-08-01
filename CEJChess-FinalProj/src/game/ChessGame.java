package game;

import board.SimpleChessBoard;
import board.ChessBoard;
import javax.swing.SwingUtilities;


public class ChessGame {
    public static void main(String[] args) {
        ChessBoard chessBoard = new ChessBoard();
        SwingUtilities.invokeLater(() -> {
            SimpleChessBoard board = new SimpleChessBoard(chessBoard);
            board.setVisible(true);
        });
    }
}
