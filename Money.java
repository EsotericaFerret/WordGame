import javax.swing.*;

public class Money implements Award {
    static int reward = 1000;
    static int penalty = 200;

    public void displayWinnings(Players player, boolean bool) {
        String message;
        if (bool) {
            player.addMoney(reward);
            message = "You win $" + reward;
        } else {
            player.subMoney(penalty);
            message = "You lose $" + penalty;
        }

        // Display the message in a dialog box
        JOptionPane.showMessageDialog(null, message);

        // You can optionally print the player details to the console as before
        System.out.println(player.toString());
    }
}