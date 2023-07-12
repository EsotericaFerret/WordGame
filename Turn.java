import java.util.Scanner;
import java.util.Random;
public class Turn {
    static Scanner scan = new Scanner(System.in);
    public static boolean takeTurn(Players player, Hosts host) {
        String playerInfo = player.getNameOnly();
        String hostInfo = host.toString();
        System.out.println(hostInfo + " says \"" + playerInfo + ", enter your guess for my random number between 0 and 100. \"" );
        int guess = getGuess();
        Random rand = new Random();
        boolean prizeType = rand.nextBoolean();
        Numbers num = host.getRandNum();
        boolean bool = num.compareNumber(guess);
        if (prizeType == true) {
            Physical physical = new Physical();
            physical.displayWinnings(player, bool);
        }
        else {
            Money money = new Money();
            money.displayWinnings(player, bool);
        }
        return bool;
        }
        public static int getGuess() {
        boolean b;
        int guessNum = 0;
        do {
            String guess = scan.nextLine();
            try {
                 guessNum = Integer.parseInt(guess);
                b = false;
            }
            catch (Exception e) {
                System.out.println("Invalid value! Try again");
                b = true;
            }
        } while (b == true);
        return guessNum;
    

    }
}
