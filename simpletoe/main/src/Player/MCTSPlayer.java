package Player;

import Game.CellState;
import Player.Player;

import java.util.Random;

import javax.swing.JFrame;

public class MCTSPlayer implements Player {
    private Random rand;
    private int TIMEOUT;
    private CellState[][] board;
    private int rows, cols, K;
    private Node mcts;
    private Boolean[] winTableAgent;
    private Boolean[] winTableOpponent;
    private CellState agentCellState;
    private CellState opponentCellState;
    private CellState[] emptyCells;
    private JFrame frame;

    public MCTSPlayer(JFrame frame) {
        this.frame = frame;
        this.rand = new Random(System.currentTimeMillis());
    }
    @Override
    public void setPlayer(int rows, int cols, int k, boolean turn, int timeoutLimit) {
        this.rows = rows;
        this.cols = cols;
        this.K = k;
        TIMEOUT = timeoutLimit;
        board = new CellState[rows][cols];
        mcts = new Node(rows, cols, k, board, emptyCells, new CellButton[0], timeoutLimit);
        winTableAgent = new Boolean[k + 1];
        winTableOpponent = new Boolean[k + 1];
        agentCellState = turn ? CellState.PLAYER1 : CellState.PLAYER2;
        opponentCellState = turn ? CellState.PLAYER2 : CellState.PLAYER1;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = CellState.EMPTY;
            }
        }

        for (int i = 0; i <= k; i++) {
            winTableAgent[i] = false;
            winTableOpponent[i] = false;
        }
    }

    @Override
     public CellButton selectCell(CellButton[] emptyCells, CellButton[] markedCells) {
        if (emptyCells.length == 1) {
            return emptyCells[0];
        }

        if (markedCells.length > 0) {
            markedCells[markedCells.length - 1].setSymbol(opponentCellState);
        }

        mcts = new Node(rows, cols, K, board, emptyCells, markedCells, agentCellState);
        CellButton cell = mcts.bestMove();
        emptyCells[cell.getRow() * cols + cell.getCol()].setSymbol(agentCellState);

        // Update GUI or perform any necessary actions here
        frame.repaint();

        return emptyCells[cell.getRow() * cols + cell.getCol()];
    }

    @Override
    public String playerName() {
        return "MCTS Player.Player";
    }
    @Override
   public String getSymbol() {
    if(agentCellState == CellState.PLAYER1){
        return "X";
    }
    else{
        return "O";
    }
}
}

