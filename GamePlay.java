import java.util.Scanner;

public class GamePlay {
     static Scanner scan = new Scanner(System.in);
     static boolean playAgain = true;
     static boolean b = false;
    public static void main(String[] args) {
        Players player = new Players();
        boolean bool = false;
        String hostNameFirst = "Bob";
        String hostNameLast = "Barker";
        Hosts host = new Hosts(hostNameFirst, hostNameLast);
        System.out.println("Please enter your first name!");
        String firstName = scan.nextLine();
        System.out.println("Would you like to enter a last name? (y/n)");
        while (bool == false) {
            String input = scan.nextLine();
            if (input.equals("y")) {
                System.out.println("Please enter your last name!");
                String lastName = scan.nextLine();
                player = new Players(firstName, lastName);
                bool = true;
                }
            else if (input.equals("n")) {
                System.out.println("Okay, no last name! Here we go!");
                player = new Players(firstName);
                bool = true;
                }
            else {
                System.out.println("Sorry, that's an invalid response! Please enter y for yes or n for no!");
                bool = false;
            }
            while (playAgain == true) {    
                host.randomizeNum();
                b = false;
                while (b == false) {
                    b = Turn.takeTurn(player, host);
                }
                System.out.println("Would you like to play again? (y/n)");
                bool = false;
                while (bool == false) {
                    String answer = scan.nextLine();
                    if (answer.equals("y")) {
                        playAgain = true;
                        bool = true;
                    }   
                    else if (answer.equals("n")) {
                        playAgain = false;
                        bool = true;
                    }
                    else {
                        System.out.println("Sorry, that's an invalid response! Please enter y for yes or n for no!");
                        bool = false;
                    }
                }
            }
        }
    }
}

