import javax.swing.*;

public class GamePlayGUI {
    private static boolean playAgain = true;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Word Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create and add GUI components here
        // For example, use JLabels, JTextFields, JButtons, etc.

        // Here's a simple example with just a "Play" button:
        JButton playButton = new JButton("Play");
        playButton.addActionListener(e -> startGame());

        frame.getContentPane().add(playButton);
        frame.pack();
        frame.setVisible(true);
    }

    private static void startGame() {
        Players[] currentPlayers = new Players[3];
        boolean bool;

        Hosts host = new Hosts();
        for (int i = 0; i < 3; i++) {
            String firstName = JOptionPane.showInputDialog("Please enter your first name!");
            int option = JOptionPane.showConfirmDialog(null, "Would you like to enter a last name?", "Last Name", JOptionPane.YES_NO_OPTION);

            String lastName = "";
            if (option == JOptionPane.YES_OPTION) {
                lastName = JOptionPane.showInputDialog("Please enter your last name!");
            }

            Players player;
            if (!lastName.isEmpty()) {
                player = new Players(firstName, lastName);
            } else {
                player = new Players(firstName);
            }

            currentPlayers[i] = player;
        }

        while (playAgain) {
            String phrase = JOptionPane.showInputDialog("Enter the phrase!");
            host.setPhrase(phrase);

            boolean b = false;
            while (!b) {
                for (Players playerTurn : currentPlayers) {
                    b = Turn.takeTurn(playerTurn, host);
                    if (b) {
                        break;
                    }
                }
            }

            int answer = JOptionPane.showConfirmDialog(null, "Would you like to play again?", "Play Again", JOptionPane.YES_NO_OPTION);
            playAgain = (answer == JOptionPane.YES_OPTION);
        }
    }
}