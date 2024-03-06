//
//
//import Game.GameType;
//import Player.Player;
//
//import java.awt.Dimension;
//import java.awt.GridLayout;
//import java.awt.event.ActionEvent;
//
//import javax.swing.JButton;
//import javax.swing.JFrame;
//
//
//public class SimpleToe extends JFrame {
//    private Player player1;
//    private Player player2;
//    private int rows;
//    private int cols;
//    private int linesToWin;
//    private JFrame frame;
//    private JButton[][] buttons;
//
//    public SimpleToe(Player player1, Player player2) {
//        this.player1 = player1;
//        this.player2 = player2;
//    }
//
//    public SimpleToe(Player player1, Player player2, int rows, int cols, int linesToWin) {
//        this.player1 = player1;
//        this.player2 = player2;
//        this.rows = rows;
//        this.cols = cols;
//        this.linesToWin = linesToWin;
//        frame = new JFrame("Simple Toe");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        buttons = new JButton[rows][cols];
//        initializeGameBoard();
//    }
//    private void initializeGameBoard() {
//        frame.setLayout(new GridLayout(rows, cols));
//        for (int i = 0; i < rows; i++) {
//            for (int j = 0; j < cols; j++) {
//                buttons[i][j] = new JButton();
//                buttons[i][j].setPreferredSize(new Dimension(100, 100));
//                buttons[i][j].addActionListener(new CellClickListener(i, j));
//                frame.add(buttons[i][j]);
//            }
//        }
//        frame.pack();
//        frame.setVisible(true);
//    }
//    private class CellClickListener implements ActionListener {
//        private int row;
//        private int col;
//
//        public CellClickListener(int row, int col) {
//            this.row = row;
//            this.col = col;
//        }
//
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            JButton button = (JButton) e.getSource();
//            Player currentPlayer = getCurrentPlayer();
//
//            // Update the button text based on the current player's move
//            button.setText(currentPlayer.getSymbol());
//            button.setEnabled(false);
//
//            // Check for a win or a tie
//            if (checkWin()) {
//                // Handle win
//            } else if (checkTie()) {
//                // Handle tie
//            } else {
//                // Switch players
//                switchPlayers();
//            }
//        }
//
//        private Player getCurrentPlayer() {
//
//        }
//
//        private boolean checkWin() {
//
//        }
//
//        private boolean checkTie() {
//            // Implement logic to check for a tie
//            // Return true if it's a tie, false otherwise
//        }
//
//        private void switchPlayers() {
//            // ...
//
//            if (player1 == null) {
//                if (gameType == GameType.HUMAN_VS_HUMAN) {
//                    player1 = new Player(Player.PlayerType.HUMAN);
//                    player2 = new Player(Player.PlayerType.HUMAN);
//                } else if (gameType == GameType.COMPUTER_VS_HUMAN) {
//                    player1 = new Player(Player.PlayerType.AI);
//                    player2 = new Player(Player.PlayerType.HUMAN);
//                } else if (gameType == GameType.COMPUTER_VS_COMPUTER) {
//                    player1 = new Player(Player.PlayerType.AI);
//                    player2 = new Player(Player.PlayerType.AI);
//                }
//            } else {
//                Player tmp1 = player1;
//                player1 = player2;
//                player2 = tmp1;
//            }
//        }
//    }
//
//    public void startGame() {
//
//    }
//}
//
