import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameBoard extends JPanel {
    private int rows;
    private int cols;
    private int linesToWin;
    private CellButton[][] cells;
    private CellState currentPlayer;

    public GameBoard(int rows, int cols, int linesToWin) {
        this.rows = rows;
        this.cols = cols;
        this.linesToWin = linesToWin;
        this.cells = new CellButton[rows][cols];
        this.currentPlayer = CellState.PLAYER1;

        setLayout(new GridLayout(rows, cols));
        initializeCells();
    }

    private void initializeCells() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                cells[i][j] = new CellButton(i, j);
                cells[i][j].setFont(new Font("Arial", Font.PLAIN, 40));
                cells[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        CellButton clickedCell = (CellButton) e.getSource();
                        if (clickedCell.isEmpty()) {
                            clickedCell.setSymbol(currentPlayer);
                            if (checkForWin(clickedCell.getRow(), clickedCell.getCol())) {
                                JOptionPane.showMessageDialog(null, "Player " + currentPlayer + " wins!");
                                resetBoard();
                            } else if (isBoardFull()) {
                                JOptionPane.showMessageDialog(null, "It's a draw!");
                                resetBoard();
                            } else {
                                currentPlayer = (currentPlayer == CellState.PLAYER1) ? CellState.PLAYER2 : CellState.PLAYER1;
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Invalid move. Cell already taken!");
                        }
                    }
                });
                add(cells[i][j]);
            }
        }
    }

    private boolean checkForWin(int row, int col) {
        // Check row
        if (checkLine(cells[row], linesToWin)) return true;

        // Check column
        JButton[] column = new JButton[rows];
        for (int i = 0; i < rows; i++) {
            column[i] = cells[i][col];
        }
        if (checkLine(column, linesToWin)) return true;

        // Check diagonals
        if (row == col && checkLine(getDiagonal(true), linesToWin)) return true;
        if (row + col == rows - 1 && checkLine(getDiagonal(false), linesToWin)) return true;

        return false;
    }

    private boolean checkLine(JButton[] line, int lengthToWin) {
       CellState state = currentPlayer;
        int count = 0;
        for (JButton cell : line) {
            if (((CellButton) cell).getState() == state) {
                count++;
                if (count == lengthToWin) {
                    return true;
                }
            } else {
                count = 0;
            }
        }
        return false;
    }

    private JButton[] getDiagonal(boolean mainDiagonal) {
        JButton[] diagonal = new JButton[rows];
        for (int i = 0; i < rows; i++) {
            diagonal[i] = mainDiagonal ? cells[i][i] : cells[i][rows - 1 - i];
        }
        return diagonal;
    }

    private boolean isBoardFull() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (((CellButton) cells[i][j]).isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    private void resetBoard() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                ((CellButton) cells[i][j]).reset();
            }
        }
        currentPlayer = CellState.PLAYER1;
    }
}
