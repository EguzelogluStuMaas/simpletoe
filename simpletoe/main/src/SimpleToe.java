

import Game.CellState;
import Game.GameType;
import Player.*;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


import Game.GameType;
import Player.*;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class SimpleToe extends JFrame {
    private Player player1;
    private Player player2;
    private int rows;
    private int cols;
    private int linesToWin;
    private JFrame frame;
    private CellButton[][] buttons;
    private CellState[][] board;
    private Player currentPlayer;
    private GameType gameType;

    public SimpleToe(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public SimpleToe(Player player1, Player player2, int rows, int cols, int linesToWin, GameType gameType) {
        this.player1 = player1;
        this.player2 = player2;
        this.rows = rows;
        this.cols = cols;
        this.linesToWin = linesToWin;
        this.gameType = gameType;

        frame = new JFrame("Simple Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        buttons = new CellButton[rows][cols];
        board = new CellState[rows][cols];

        initializeGameBoard();

        currentPlayer = player1;  // Start with player1
    }

    private void initializeGameBoard() {
        frame.setLayout(new GridLayout(rows, cols));
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                buttons[i][j] = new CellButton(i, j);
                buttons[i][j].setPreferredSize(new Dimension(100, 100));
                buttons[i][j].addActionListener(new CellClickListener(i, j));
                frame.add(buttons[i][j]);
            }
        }
        frame.pack();
        frame.setVisible(true);
    }

    private class CellClickListener implements ActionListener {
        private int row;
        private int col;

        public CellClickListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            CellButton button = (CellButton) e.getSource();
            currentPlayer = getCurrentPlayer();

            // Update the button text based on the current player's move
            button.setText(currentPlayer.getSymbol());
            button.setEnabled(false);

            // Update the board state
            board[row][col] = (currentPlayer == player1) ? CellState.PLAYER1 : CellState.PLAYER2;

            // Check for a win or a tie
            if (checkWin()) {
                // Handle win
            } else if (checkTie()) {
                // Handle tie
            } else {
                // Switch players
                switchPlayers();
            }
        }

        private Player getCurrentPlayer() {
            return currentPlayer;
        }

        private boolean checkWin() {
            // Implement win-check logic
            return false;
        }

        private boolean checkTie() {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (buttons[i][j].isEnabled()) {
                        // If there is any enabled (empty) button, the game is not a tie
                        return false;
                    }
                }
            }
            // All buttons are disabled, indicating a tie
            JOptionPane.showMessageDialog(frame, "It's a tie!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
            resetGame();
            return true;
        }

        private void resetGame() {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    buttons[i][j].setText("");
                    buttons[i][j].setEnabled(true);
                    board[i][j] = CellState.EMPTY;
                }
            }
            currentPlayer = player1;  // Start with player1
        }
    }

    private void switchPlayers() {
        
        Player tmp = player1;
        player1 = player2;
        player2 = tmp;
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }


    public void startGame() {
        if (gameType == GameType.HUMAN_VS_HUMAN) {
            player1.setPlayer(rows, cols, linesToWin, true, 15);
            player2.setPlayer(rows, cols, linesToWin, false, 15);
        } else if (gameType == GameType.COMPUTER_VS_HUMAN) {
            player1.setPlayer(rows, cols, linesToWin, true, 15);
            // Declare and initialize emptyCells variable
            player2.setPlayer(rows, cols, linesToWin, false, 15);
        } else if (gameType == GameType.COMPUTER_VS_COMPUTER) {
            player1.setPlayer(rows, cols, linesToWin, true, 15);
            player2.setPlayer(rows, cols, linesToWin, false, 15);
        }
    }
}


