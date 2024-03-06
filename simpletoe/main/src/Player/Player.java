package Player;

import Game.CellButton;

public interface Player {
    public void setPlayer(int rows, int cols, int linesToWin, boolean isPlayer1, int timeoutLimit, CellButton[] emptyCells);
    public CellButton selectCell(CellButton[] emptyCells, CellButton[] selectedCells);
    public String playerName();
    public enum PlayerType {
        HUMAN, AI
    }

}
