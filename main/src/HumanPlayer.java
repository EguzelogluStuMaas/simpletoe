import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HumanPlayer implements Player {
    private JFrame frame;
    private CellButton selectedCell;
    private boolean moveSelected;

    public HumanPlayer(JFrame frame) {
        this.frame = frame;
        this.moveSelected = false;
        this.selectedCell = null;
    }

    @Override
    public void setPlayer(int rows, int cols, int linesToWin, boolean isPlayer1, int timeoutLimit, CellButton[] emptyCells) {
        // Implement any initialization required
    }

    @Override
    public CellButton selectCell(CellButton[] emptyCells, CellButton[] selectedCells) {
        // Reset the move selection
        moveSelected = false;
        selectedCell = null;

        // Add mouse listener to each empty cell
        for (CellButton emptyCell : emptyCells) {
            emptyCell.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!moveSelected) {
                        selectedCell = emptyCell;
                        moveSelected = true;
                    }
                }
            });
        }

        // Wait until the user makes a move
        while (!moveSelected) {
            try {
                Thread.sleep(100);  // Sleep to avoid busy waiting
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Remove the action listeners after the move is selected
        for (CellButton emptyCell : emptyCells) {
            ActionListener[] listeners = emptyCell.getActionListeners();
            for (ActionListener listener : listeners) {
                emptyCell.removeActionListener(listener);
            }
        }

        return selectedCell;
    }

    @Override
    public String playerName() {
        return "Human Player";
    }
}
