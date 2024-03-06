package Game;

import Game.CellState;

import javax.swing.*;

public class CellButton extends JButton {
    private int row;
    private int col;
    private CellState state;

    public CellButton(int row, int col) {
        this.row = row;
        this.col = col;
        this.state=CellState.EMPTY;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public CellState getState() {
        return state;
    }

    public void setSymbol(CellState currentPlayer) {
        this.state = (currentPlayer.equals("X")) ? CellState.PLAYER1 : CellState.PLAYER2;
        this.setText(String.valueOf(currentPlayer));
    }

    public boolean isEmpty() {
        return state == CellState.EMPTY;
    }

    public void reset() {
        state = CellState.EMPTY;
        setText("");
    }
}
