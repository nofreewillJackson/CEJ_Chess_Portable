package main;

import board.ChessBoard;
import board.SimpleChessBoard;

public class Main {
    public static void main(String[] args) {
        ChessBoard chessBoard = new ChessBoard();
        SimpleChessBoard simpleChessBoard = new SimpleChessBoard(chessBoard);
        simpleChessBoard.setVisible(true);
    }
}
