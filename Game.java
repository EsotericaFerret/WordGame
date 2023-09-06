import java.awt.image.*;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import javax.imageio.*;


public class Game {
    private String phrase;
    private String maskedPhrase;
    private Physical physical;
    private boolean gameStarted;
    private int lettersRemaining;
    private String[] physicalRewards = {"Car", "Vacation", "Phone", "Patio Set", "Washer/Dryer Set"};
    public static String filePath;
    Graphics graphics;

    public Game(String phrase, List<Host> hosts, List<Player> players) {
        this.phrase = phrase;
        this.maskedPhrase = maskPhrase(phrase);
        this.gameStarted = false;
        this.lettersRemaining = phrase.replaceAll("\\s+", "").length();
    }

    private String maskPhrase(String phrase) {
        StringBuilder masked = new StringBuilder();
        for (char c : phrase.toCharArray()) {
            if (Character.isLetter(c)) {
                masked.append('_');
            } else {
                masked.append(c);
            }
        }
        return masked.toString();
    }

    public String getMaskedPhrase() {
        return maskedPhrase;
    }

    public void startGame() {
        gameStarted = true;
    }

    public void makeGuess(String guess, Player player) {
        if (gameStarted) {
            char guessedChar = guess.toLowerCase().charAt(0);
            boolean guessCorrect = false;

            // Check if the guessed character is in the phrase
            for (int i = 0; i < phrase.length(); i++) {
                char phraseChar = Character.toLowerCase(phrase.charAt(i));
                if (phraseChar == guessedChar && maskedPhrase.charAt(i) == '_') {
                    // Update masked phrase
                    StringBuilder updatedMasked = new StringBuilder(maskedPhrase);
                    updatedMasked.setCharAt(i, phrase.charAt(i));
                    maskedPhrase = updatedMasked.toString();
                    guessCorrect = true;
                    lettersRemaining--;
                    if (lettersRemaining <= 0) {
                        GamePlayGUI.gameOver = true;
                    }
                    else {
                        GamePlayGUI.gameOver = false;
                    }
                }

            }

            if (guessCorrect) {
                GamePlayGUI.outputArea.append("Correct Guess: " + guess + "\n");
                grantReward(player);
                if (lettersRemaining == 0) {
                    // Game over, all letters revealed
                    GamePlayGUI.outputArea.append("Congratulations! You won the game.\n");
                    endGame();
                }
            } 
            else {
                GamePlayGUI.outputArea.append("Incorrect Guess: " + guess + "\n");
                deductReward(player);
            }

        }
    }

    private void deductReward(Player player) {
        Random random = new Random();
        boolean moneyReward = random.nextBoolean();
        if (moneyReward) {
            int moneyAmount = random.nextInt(501) + 1; // Random amount between 1 and 500
            player.deductMoney(moneyAmount);
            GamePlayGUI.outputArea.append("You lost $" + moneyAmount + "!\n You now have $" + player.getMoney() + "\r\n");
        }
        else {
            String randomPhysicalReward = physicalRewards[random.nextInt(physicalRewards.length)];
            physical = new Physical(randomPhysicalReward);
            GamePlayGUI.outputArea.append("You lost out on a brand new " + physical.getItem() + " reward.\n");
            physical.setImage();
        }
    }

    private void grantReward(Player player) {
        Random random = new Random();
        boolean moneyReward = random.nextBoolean();
        if (moneyReward) {
            int moneyAmount = random.nextInt(501) + 500; // Random amount between 500 and 1000
            player.addMoney(moneyAmount);
            GamePlayGUI.outputArea.append("You won $" + moneyAmount + "!\n You now have $" + player.getMoney() + "\r\n");
        } 
        else {
            String randomPhysicalReward = physicalRewards[random.nextInt(physicalRewards.length)];
            physical = new Physical(randomPhysicalReward);
            GamePlayGUI.outputArea.append("You won a " + physical.getItem() + "!\n");
            physical.setImage();
        }
    }

    private void endGame() {
        gameStarted = false;
        GamePlayGUI.submitGuessButton.setEnabled(false);
        GamePlayGUI.playerGuessField.setEnabled(false);
        GamePlayGUI.phraseField.setEnabled(true);
        GamePlayGUI.submitPhraseButton.setEnabled(true);
        // Reset the game for a new round
        maskedPhrase = maskPhrase(phrase);
        lettersRemaining = phrase.replaceAll("\\s+", "").length();
        GamePlayGUI.startGameButton.setEnabled(true);
    }
}