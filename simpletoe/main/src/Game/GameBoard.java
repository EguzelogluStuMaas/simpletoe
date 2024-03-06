package Game;

import javax.swing.*;

import Player.CellButton;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;

public class GameBoard {
    private int rows;
    private int cols;
    private int linesToWin;
    private CellButton[][] cells;
    private CellState currentStateOfCell;
    private CellState[][] board;
    protected LinkedList<CellButton> MC;  // Marked Cells
	protected HashSet<CellButton>    FC;  // Free Cells

    public GameBoard(int rows, int cols, int linesToWin)throws IllegalArgumentException {
        if (rows <= 0) throw new IllegalArgumentException("M cannot be smaller than 1");
		if (cols <= 0) throw new IllegalArgumentException("N cannot be smaller than 1");
		if (linesToWin <= 0) throw new IllegalArgumentException("K cannot be smaller than 1");
        
        this.rows = rows;
        this.cols = cols;
        this.linesToWin = linesToWin;
        this.currentStateOfCell = CellState.PLAYER1;
        this.board = new CellState[rows][cols];
        initializeCells();
    }

    private void initializeCells() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = CellState.EMPTY;
            }
        }
    }
    public int getNumberOfEmptyCells(){
        return FC.size();
    }
    private boolean checkForWin(int row, int col) {
        // Check row
        if (checkLine(cells[row], linesToWin)) return true;

        // Check column
        CellButton[] column = new CellButton[rows];
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
       CellState state = currentStateOfCell;
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
        currentStateOfCell = CellState.EMPTY;
    }
    private void initBoard() {
		for(int i = 0; i < rows; i++)
			for(int j = 0; j < cols; j++)
				board[rows][cols] = CellState.EMPTY;
	}
    private void initMarkedCells(){
        this.MC = new LinkedList<CellButton>();
    }
    private void resetEmptyCells(){
        this.FC.clear();
		for(int i = 0; i < rows; i++)
			for(int j = 0; j < cols; j++)
				this.FC.add(new CellButton(i,j));
    }
    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getCols() {
        return cols;
    }

    public void setCols(int cols) {
        this.cols = cols;
    }

    public int getLinesToWin() {
        return linesToWin;
    }

    public void setLinesToWin(int linesToWin) {
        this.linesToWin = linesToWin;
    }

    public JButton[][] getCells() {
        return cells;
    }

    public void setCells(CellButton[][] cells) {
        this.cells = cells;
    }

    public CellState getCurrentStateOfCell() {
        return currentStateOfCell;
    }

    public void setCurrentStateOfCell(CellState currentStateOfCell) {
        this.currentStateOfCell = currentStateOfCell;
    }
    public CellButton[] getEmptyCells(){
        return FC.toArray(new CellButton[FC.size()]);
    }
    public CellButton[] getMarkedCells(){
        return MC.toArray(new CellButton[MC.size()]);
    }

}
