import java.util.Scanner;

public class GamePlay {
     static Scanner scan = new Scanner(System.in);
     static boolean playAgain = true;
     static boolean b = false;
     static Money money;
    public static void main(String[] args) {
        Players player = new Players();
        Players[] currentPlayers = new Players[3];
        boolean bool = false;
        String hostNameFirst = "Bob";
        String hostNameLast = "Barker";
        Hosts host = new Hosts(hostNameFirst, hostNameLast);
        for (int i = 0; i < 3; i++) {
        System.out.println("Please enter your first name!");
        String firstName = scan.nextLine();
        System.out.println("Would you like to enter a last name? (y/n)");
        bool = false;
        while (bool == false) {
            String input = scan.nextLine();
            if (input.equals("y")) {
                System.out.println("Please enter your last name!");
                String lastName = scan.nextLine();
                player = new Players(firstName, lastName);
                bool = true;
                currentPlayers[i] = player;
                }
            else if (input.equals("n")) {
                System.out.println("Okay, no last name! Here we go!");
                player = new Players(firstName);
                bool = true;
                currentPlayers[i] = player;
                }
            else {
                System.out.println("Sorry, that's an invalid response! Please enter y for yes or n for no!");
                bool = false;
            }
        }
    }
            while (playAgain == true) { 
                  
                host.randomizeNum();
                b = false;
                while (b == false) {
                    for (Players playerTurn : currentPlayers) { 
                       b = Turn.takeTurn(playerTurn, host);
                    }
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
