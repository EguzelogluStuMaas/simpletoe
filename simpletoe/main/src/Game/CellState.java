package Game;

public enum CellState {
    EMPTY(" "), PLAYER1("X"), PLAYER2("O");
    private final String symbol;

    CellState(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
