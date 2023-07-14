import java.util.Random;
public class Turn {
    public static boolean takeTurn(Players player, Hosts host) {
        String playerInfo = player.getNameOnly();
        String hostInfo = host.toString();
        System.out.println("The phrase to guess is: " + Phrases.currentSolution);
        System.out.println(hostInfo + " says \"" + playerInfo + ", enter a letter that you guess to be in the phrase!\"" );
        char letterGuess = Phrases.dataValidation();
        boolean bool = Phrases.compareLetter(letterGuess);
        boolean finish = Phrases.checkFinish();
        Random rand = new Random();
        boolean prizeType = rand.nextBoolean();
        if (prizeType == true) {
            Physical physical = new Physical();
            physical.displayWinnings(player, bool);
        }
        else {
            Money money = new Money();
            money.displayWinnings(player, bool);
        }
        return finish;

    }
}
