package Player;

public interface Player {
    public void setPlayer(int rows, int cols, int linesToWin, boolean isPlayer1, int timeoutLimit);
    public CellButton selectCell(CellButton[] emptyCells, CellButton[] selectedCells);
    public String playerName();
    public String getSymbol();


}
