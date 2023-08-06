import javax.swing.*;

public class Phrases {
    static String playingPhrase;
    static char[] phraseAnswer;
    static char[] phraseBlank;
    static String currentSolution;

    static public String setPhrase(String phrase) {


        StringBuilder sb = new StringBuilder();
        playingPhrase = phrase;
        phraseAnswer = playingPhrase.toCharArray();
        phraseBlank = new char[phraseAnswer.length];
        for (int i = 0; i < phraseAnswer.length; ++i) {
            char ch = phraseAnswer[i];
            if (ch == ' ') {
                phraseBlank[i] = phraseAnswer[i];
            } else {
                phraseBlank[i] = '_';
            }
        }
        for (int i = 0; i < phraseBlank.length; ++i) {
            sb.append(phraseBlank[i]);
        }
        currentSolution = sb.toString();
        return currentSolution;
    }

    protected static boolean compareLetter(char charVar) {
        StringBuilder sb = new StringBuilder();
        boolean correct = false;
        for (int i = 0; i < phraseAnswer.length; ++i) {
            if (charVar == phraseAnswer[i]) {
                phraseBlank[i] = phraseAnswer[i];
                correct = true;
            }
            else if (Character.toUpperCase(charVar) == phraseAnswer[i]) {
                phraseBlank[i] = phraseAnswer[i];
                correct = true;
            }
            else if (Character.toLowerCase(charVar) == phraseAnswer[i]) {
                phraseBlank[i] = phraseAnswer[i];
                correct = true;
            }
        }
        for (int i = 0; i < phraseBlank.length; ++i) {
            sb.append(phraseBlank[i]);
        }
        currentSolution = sb.toString();
        return correct;
    }

    public static boolean checkFinish() {
        if (currentSolution.indexOf('_') == -1) {
            return true;
        } else {
            return false;
        }
    }

    public static char dataValidation() {
        String input = JOptionPane.showInputDialog("Please enter one letter!");
        char letter = '\0';
        boolean nullStatus = false;
        boolean multiStatus = false;
        while (nullStatus == false && multiStatus == false) {
            try {
                letter = input.charAt(0);
                nullStatus = true;
            } catch (Exception e) {
                input = JOptionPane.showInputDialog("Invalid input, please enter one single letter!");
            }
            try {
                char tempChar = input.charAt(1);
                multiStatus = true;
                throw new MultipleLettersException();
            } catch (MultipleLettersException e) {
                input = JOptionPane.showInputDialog("Invalid input, please enter one single letter!");
                multiStatus = false;
            } catch (Exception e) {
                multiStatus = true;
            }
        }
        return letter;
    }
}
