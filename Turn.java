import java.util.Scanner;
public class Turn {
    static int reward = 1000;
    static int penalty = 200;
    static Scanner scan = new Scanner(System.in);
    public static boolean takeTurn(Players player, Hosts host) {
        String playerInfo = player.getNameOnly();
        String hostInfo = host.toString();
        System.out.println(hostInfo + " says " + playerInfo + ", enter your guess for my random number between 0 and 100" );
        int guess = getGuess();
        Numbers num = host.getRandNum();
        boolean bool = num.compareNumber(guess);
        if (bool == true) {
            player.addMoney(reward);
            System.out.println("You win $" + reward);
            System.out.println(player.toString());
        }
        else {
            player.subMoney(penalty);
            System.out.println("You lose $" + penalty);
            System.out.println(player.toString());
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
