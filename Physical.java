import java.util.Random;

public class Physical {
    String[] prizes = {"car", "house", "washer and dryer", "laptop", "fridge"};
    static public int getRandomPrize() {
        Random rand = new Random();
        int upperbound = 5;
        int int_random = rand.nextInt(upperbound);
        return int_random;
    }
    public int displayWinnings(Players player, boolean bool) {
         int randPrize = getRandomPrize();
        if (bool == true) {
            System.out.println("\"Congrats," + player.firstName + " you have won a brand new " + prizes[randPrize] + "!\"");
            System.out.println(player.toString());
            return 0;
        }
        else {
            System.out.println("\"Sorry, " + player.firstName + ", you lost out on a brand new " + prizes[randPrize] + "!\"" );
            System.out.println(player.toString());
            return 0;
        }
    }
}
