
import Game.GameType;
import Player.*;


import java.util.Scanner;

import javax.swing.JFrame;

public class GameLauncher {
   public static void main(String[] args) {
       Scanner scanner = new Scanner(System.in);

       System.out.println("Select Game Type:");
       System.out.println("1. Human vs Human");
       System.out.println("2. Human vs AI");
       System.out.println("3. AI vs AI");

       int choice = scanner.nextInt();
       switch (choice) {
           case 1:
               playHumanVsHuman();
               break;
           case 2:
               playHumanVsAI();
               break;
           case 3:
               watchAIVsAI();
               break;
           default:
               System.out.println("Invalid choice. Exiting...");

       }
   }

   private static void playHumanVsHuman() {
       // Initialize a Player.Player.HumanPlayer for Player.Player 1 and Player.Player 2
       GameType gameType = GameType.HUMAN_VS_HUMAN;
       Scanner scanner = new Scanner(System.in);

       System.out.println("Enter the number of rows: ");
       int rows = scanner.nextInt();

       System.out.println("Enter the number of columns: ");
       int cols = scanner.nextInt();

       System.out.println("Enter the number of lines needed to win: ");
       int linesToWin = scanner.nextInt();
       JFrame frame = new JFrame("Simple Toe");
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       Player player1 = new HumanPlayer(frame);
       Player player2 = new HumanPlayer(frame);
       // Create and start the game
       SimpleToe game = new SimpleToe(player1, player2);
       game.startGame();
   }

   private static void playHumanVsAI() {
       GameType gameType = GameType.COMPUTER_VS_HUMAN;
       Scanner scanner = new Scanner(System.in);

       System.out.println("Enter the number of rows: ");
       int rows = scanner.nextInt();

       System.out.println("Enter the number of columns: ");
       int cols = scanner.nextInt();

       System.out.println("Enter the number of lines needed to win: ");
       int linesToWin = scanner.nextInt();

       System.out.println("Enter the timeout limit for AI moves (in milliseconds): ");
       int timeoutLimit = scanner.nextInt();

       // Initialize a Player.Player.HumanPlayer for Player.Player 1 and Player.MCTSPlayer for Player.Player 2
       JFrame frame = new JFrame("Simple Toe");
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       Player player1 = new HumanPlayer(frame);
       Player player2 = new MCTSPlayer(frame);
       CellButton[] emptyCells = new CellButton[rows * cols];
       for (int i = 0; i < rows; i++) {
       for (int j = 0; j < cols; j++) {
           emptyCells[i * cols + j] = new CellButton(i, j);
       }
   }
       player2.setPlayer(rows, cols, linesToWin, false, timeoutLimit);

       // Creates and start the game
      SimpleToe game = new SimpleToe(player1, player2);
       game.startGame();
   }

   private static void watchAIVsAI() {
       GameType gameType = GameType.COMPUTER_VS_COMPUTER;
       // Initialize MCTSPlayers for Player.Player 1 and Player.Player 2
       JFrame frame = new JFrame("Tic Tac Toe");
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       Player player1 = new MCTSPlayer(frame);
       Player player2 = new MCTSPlayer(frame);

       // Set the players for both instances
       ((MCTSPlayer) player1).setPlayer(3, 3, 3, true, 1000);
       ((MCTSPlayer) player2).setPlayer(3, 3, 3, false, 1000);

       // Create and start the game
       SimpleToe game = new SimpleToe(player1, player2);
       game.startGame();
   }
}
