import java.util.*;
public class Phrases {
    static String playingPhrase;
    static char[] phraseAnswer;
    static char[] phraseBlank;
    static String currentSolution;
    
    static public void setPhrase(String phrase) {
        StringBuilder sb = new StringBuilder();
        playingPhrase = phrase;
        phraseAnswer = playingPhrase.toCharArray();
        phraseBlank = new char[phraseAnswer.length];
        for (int i = 0; i < phraseAnswer.length; ++i) {
            char ch = phraseAnswer[i];
            if (ch == ' ') {
                phraseBlank[i] = phraseAnswer[i];
            }
            else {
                phraseBlank[i] = '_';
            }
        }
        for (int i = 0; i < phraseBlank.length; ++i) {
            sb.append(phraseBlank[i]);
        }
        currentSolution = sb.toString();
    }
        public static char dataValidation() {
            Scanner scan = new Scanner(System.in);
            boolean nullStatus = false;
            boolean multiStatus = false;
            char letter = '\0';
            while (nullStatus == false && multiStatus == false) {
                System.out.println("Please enter in one letter!");
                String input = scan.nextLine();
                try {
                    letter = input.charAt(0);
                    nullStatus = true;
                }
                catch (Exception e) {
                    System.out.println("Invalid input, please enter one single letter!");
                }
                try {
                    char tempChar = input.charAt(1);
                    multiStatus = true;
                    throw new MultipleLettersException();
                }
                catch (MultipleLettersException e) {
                    multiStatus = false;
                    System.out.println("Invalid input, please enter one single letter!");
                }
                catch (Exception e) {
                    multiStatus = true;
                }
            }
            return letter;
        }
    protected static boolean compareLetter(char charVar) {
        StringBuilder sb = new StringBuilder();
        boolean correct = false;
        for (int i = 0; i < phraseAnswer.length; ++i) {
           if (charVar == phraseAnswer[i]) {
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
        }
        else {
            return false;
        }
    }
}