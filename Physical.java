import java.util.Random;
import javax.swing.*;

public class Physical implements Award {
    String[] prizes = {"car", "house", "washer and dryer", "laptop", "fridge"};

    static public int getRandomPrize() {
        Random rand = new Random();
        int upperbound = 5;
        int int_random = rand.nextInt(upperbound);
        return int_random;
    }

    public void displayWinnings(Players player, boolean bool) {
        int randPrize = getRandomPrize();
        String message;
        if (bool) {
            message = "Congrats, " + player.firstName + ", you have won a brand new " + prizes[randPrize] + "!";
        } else {
            message = "Sorry, " + player.firstName + ", you lost out on a brand new " + prizes[randPrize] + "!";
        }
        
        // Display the message in a dialog box
        JOptionPane.showMessageDialog(null, message);

        // You can optionally print the player details to the console as before
        System.out.println(player.toString());
    }
}
