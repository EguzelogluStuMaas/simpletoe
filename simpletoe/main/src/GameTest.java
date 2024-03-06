

import javax.swing.JFrame;

import Game.GameType;
import Player.HumanPlayer;
import Player.Player;

public class GameTest {
    public static void main(String[] args) {
        // Assuming you have a main method or an initialization method in SimpleToe
        launchGame();
    }

    public static void launchGame() {
        // Create players (you can replace this with your actual player creation logic)
        Player player1 = new HumanPlayer(new JFrame());
        Player player2 = new HumanPlayer(new JFrame());

        // Create SimpleToe instance with players and other necessary parameters
        SimpleToe game = new SimpleToe(player1, player2, 3, 3, 3, GameType.HUMAN_VS_HUMAN);

        // Start the game
        game.startGame();
    }
}
