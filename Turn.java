import java.util.Random;
import javax.swing.*;

public class Turn {
    public static boolean takeTurn(Players player, Hosts host) {
        String playerInfo = player.getNameOnly();
        String hostInfo = host.toString();
        String phraseSolution = Phrases.currentSolution;
        if (GamePlayGUI.saveMessages == true) {
        GamePlayGUI.gameInfo.append("The phrase to guess is: " + phraseSolution + "\n");
        GamePlayGUI.gameInfo.append(hostInfo + " says \"" + playerInfo + ", enter a letter that you guess to be in the phrase!\"\n");
        }
        else {
            GamePlayGUI.gameInfo.setText("The phrase to guess is: " + phraseSolution + "\n");
        GamePlayGUI.gameInfo.setText(hostInfo + " says \"" + playerInfo + ", enter a letter that you guess to be in the phrase!\"\n");
        }
        char letterGuess = dataValidation();

        boolean isLetterCorrect = Phrases.compareLetter(letterGuess);
        boolean isGameFinished = Phrases.checkFinish();

        Random rand = new Random();
        boolean prizeType = rand.nextBoolean();

        if (prizeType) {
            Physical physical = new Physical();
            physical.displayWinnings(player, isLetterCorrect);
        } else {
            Money money = new Money();
            money.displayWinnings(player, isLetterCorrect);
        }

        return isGameFinished;
    }

    private static char dataValidation() {
        boolean validInput = false;
        char letter = '\0';

        while (!validInput) {
            String input = JOptionPane.showInputDialog("Please enter one letter!");
            try {
                letter = input.charAt(0);
                validInput = true;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Invalid input, please enter one single letter!");
            }
        }

        return letter;
    }
}