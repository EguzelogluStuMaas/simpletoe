package Player;

import Game.CellButton;
import Game.CellState;

import java.util.*;

public class Node {
    private Node parent;
    private List<Node> children;
    private Random rand;
    private CellState[][] boardState;
    private CellButton[] emptyCells;
    private Queue<CellButton> frontier;
    private CellButton[] markedCells;
    private int M, N, K;
    private CellState nodeState;

    private int simulationCount;
    private double uct;
    private int value;
    private int timeoutLimit;

    public Node(int M, int N, int K, CellState[][] boardState, CellButton[] emptyCells, CellButton[] markedCells, int timeoutLimit) {
        rand = new Random(System.currentTimeMillis());
        children = new ArrayList<>();
        this.boardState = new CellState[M][N];
        this.nodeState = nodeState;
        this.timeoutLimit = timeoutLimit;
        this.emptyCells = new CellButton[M * N];
        this.markedCells = new CellButton[M * N];
        this.simulationCount = 0;
        this.uct = Double.POSITIVE_INFINITY;
        this.value = 0;
        this.M = M;
        this.N = N;
        this.K = K;

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                boardState[i][j] = CellState.EMPTY;
            }
        }

        this.frontier = new LinkedList<>(Arrays.asList(emptyCells));
        Collections.addAll(frontier, emptyCells);
    }

    // Getters and setters

    public Node(int rows, int cols, int k2, CellState[][] board, Object emptyCells2, Object markedCells2,
            CellState agentCellState) {
        //TODO Auto-generated constructor stub
    }

    public List<Node> getChildren() {
        return children;
    }

    public void addChild(Node child) {
        children.add(child);
    }

    public void removeChild(Node child) {
        children.remove(child);
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Node getParent() {
        return parent;
    }

    public double calcUctValue(int parentSimulationCount) {
        int t = parentSimulationCount;
        uct = Math.sqrt(2) * Math.sqrt(Math.log((double) t) / (double) simulationCount);
        uct += (double) ((double) (value) / (double) (simulationCount));
        return uct;
    }

    public double getUctValue() {
        return uct;
    }

    public void selection() {
        if (frontier.size() > 0) {
            expand();
        } else {
            Node bestNodeUCT = null;
            for (Node child : children) {
                if (bestNodeUCT == null || child.getUctValue() > bestNodeUCT.getUctValue()) {
                    bestNodeUCT = child;
                }
            }

            if (bestNodeUCT != null) {
                bestNodeUCT.selection();
            } else {
                simulate();
            }
        }
    }

    public void expand() {
        if (frontier.size() > 0) {
            CellButton cell = frontier.poll();
            Node child = new Node(M, N, K, boardState, emptyCells, markedCells, nodeState);
            child.boardState[cell.getRow()][cell.getCol()] = nodeState;
            child.emptyCells = Arrays.stream(emptyCells)
                    .filter(c -> !c.equals(cell))
                    .toArray(CellButton[]::new);
            child.markedCells = Arrays.copyOf(markedCells, markedCells.length + 1);
            child.markedCells[child.markedCells.length - 1] = cell;
            child.setParent(this);
            addChild(child);
            child.simulate();
        }
    }
    public CellButton bestMove(){
        CellButton currBest=null;
        Node bestNodeUCT = null;
        for (Node child : children) {
            if (bestNodeUCT == null) {
                bestNodeUCT = child;
            }
            if (child.getUctValue() > bestNodeUCT.getUctValue()) {
                bestNodeUCT = child;
            }
            if(bestNodeUCT!=null){
                currBest=bestNodeUCT.getCurrentMove();
                System.out.println("Best move: "+currBest.getRow()+","+currBest.getCol());
                System.out.println("UCT: "+bestNodeUCT.getUctValue());
            }
        }
        return currBest;
     }
     public CellButton getCurrentMove(){
        if(markedCells.length > 0){
            return markedCells[markedCells.length-1];
        }
        return null;
     }  
  

    private CellState[][] deepCopy(CellState[][] state) {
        return Arrays.stream(state)
                .map(CellState[]::clone)
                .toArray(CellState[][]::new);
    }

    public CellButton[] copyCells(CellButton[] cells){
        CellButton[] copy = new CellButton[cells.length];
        for(int i=0;i<cells.length;i++){
            copy[i]=cells[i];
        }
        return copy;
    }
    public CellState swapCellState(CellState currentState) {
        if (currentState == CellState.PLAYER1) {
            return CellState.PLAYER2;
        } else if(currentState == CellState.PLAYER2) {
            return CellState.PLAYER1;
        }
        return CellState.EMPTY;
    }
public boolean isWinningCell(int i, int j, CellState player) {
    CellState currentState = player;
    int count = 1;

    if (currentState == CellState.EMPTY) {
        return false;
    }

    // Horizontal check
    for (int k = 1; j - k >= 0 && boardState[i][j - k] == currentState; k++) {
        count++;
    }
    for (int k = 1; j + k < N && boardState[i][j + k] == currentState; k++) {
        count++;
    }
    if (count >= K) return true;

    // Vertical check
    count = 1;
    for (int k = 1; i - k >= 0 && boardState[i - k][j] == currentState; k++) {
        count++;
    }
    for (int k = 1; i + k < M && boardState[i + k][j] == currentState; k++) {
        count++;
    }
    if (count >= K) return true;

    // Diagonal check
    count = 1;
    for (int k = 1; i - k >= 0 && j - k >= 0 && boardState[i - k][j - k] == currentState; k++) {
        count++;
    }
    for (int k = 1; i + k < M && j + k < N && boardState[i + k][j + k] == currentState; k++) {
        count++;
    }
    if (count >= K) return true;

    // Anti-diagonal check
    count = 1;
    for (int k = 1; i - k >= 0 && j + k < N && boardState[i - k][j + k] == currentState; k++) {
        count++;
    }
    for (int k = 1; i + k < M && j - k >= 0 && boardState[i + k][j - k] == currentState; k++) {
        count++;
    }
    return count >= K;
}

public void simulate() {
    CellState currentState = nodeState;
    CellState[][] backupState = deepCopy(boardState);

    List<CellButton> listEC = new ArrayList<>(Arrays.asList(emptyCells));
    listEC.removeIf(Objects::isNull);

    int count = 0;
    Random rand = new Random();

    while (listEC.size() > 0) {
        int r = rand.nextInt(listEC.size());
        CellButton cell = listEC.get(r);
        listEC.remove(r);

        boardState[cell.getRow()][cell.getCol()] = currentState;

        if (isWinningCell(cell.getRow(), cell.getCol(), currentState)) {
            count = 1;
            simulationCount++;
            break;
        }

        currentState = swapCellState(currentState);
    }

    boardState = deepCopy(backupState);
}
public int getSimulationCount() {
    return simulationCount;
}
public void log()
 {
    for (int i = 0; i < M; i++) {
        for (int j = 0; j < N; j++) {
            System.out.print("[");
            if (boardState[i][j] == CellState.PLAYER1)
                System.out.print("X");
            else if (boardState[i][j] == CellState.PLAYER2)
                System.out.print("O");
            else
                System.out.print(" ");
            System.out.print("] ");
        }
        System.out.print("\n");
    }
    System.out.print("\n");
 }
}
