package Game;

import java.util.ArrayList;

import Player.CellButton;
import Player.Player;
import Game.GameBoard;

public class BoardState {
    private int id ;
    private GameBoard board;
    private int numberOfPlayers ;
    private int numberOfAIPlayers = 0;
    private Player currentPlayer ;
    private int currentColor ;
    private ArrayList<Player> players = new ArrayList<>();
    int boardSize;
    private CellButton[] emptyCells;
    private CellButton[] markedCells;
    public static int idCounter = 0;
    private GameState gameState;
    private int linesToWin;
    private boolean isGameOver;
    public BoardState(GameBoard board, ArrayList<Player> players) {
        this.id = idCounter++;
        this.board = board;
        this.players = players;
        this.numberOfPlayers = players.size();
        this.currentPlayer = players.get(0);
        this.currentColor = 0;
        this.boardSize = board.getRows();
        this.linesToWin = linesToWin;
        this.gameState = GameState.IN_PROGRESS;
        this.emptyCells = board.getEmptyCells();
        this.markedCells = board.getMarkedCells();
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public GameBoard getBoard() {
        return board;
    }
    public void setBoard(GameBoard board) {
        this.board = board;
    }
    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }
    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }
    public int getNumberOfAIPlayers() {
        return numberOfAIPlayers;
    }
    public void setNumberOfAIPlayers(int numberOfAIPlayers) {
        this.numberOfAIPlayers = numberOfAIPlayers;
    }
    public Player getCurrentPlayer() {
        return currentPlayer;
    }
    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

}
