public class Money implements Award {
    static int reward = 1000;
    static int penalty = 200;

    public int displayWinnings(Players player, boolean bool) {
        if (bool == true) {
            player.addMoney(reward);
            System.out.println("You win $" + reward);
            System.out.println(player.toString());
            return reward;
        }
        else {
            player.subMoney(penalty);
            System.out.println("You lose $" + penalty);
            System.out.println(player.toString());
            return penalty * -1;
        }
    }
}