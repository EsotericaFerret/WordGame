import java.util.Scanner;

public class GamePlay {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Person player = new Person();
        boolean bool = false;

        System.out.println("Please enter your first name!");
        String firstName = scan.nextLine();
        System.out.println("Would you like to enter a last name? (1/2)");
        while (bool == false) {
            String answer;
            int parsedAnswer;
            answer = scan.nextLine();
           parsedAnswer = validationCheck(answer);
            if (parsedAnswer == 1) {
                System.out.println("Please enter your last name!");
                String lastName = scan.nextLine();
                player = new Person(firstName, lastName);
                bool = true;
            }
            else if (parsedAnswer == 2) {
                System.out.println("Okay, no last name! Here we go!");
                player = new Person(firstName);
                bool = true;
            }
            else {
                System.out.println("Sorry, that's an invalid response! Please enter 1 for yes or 2 for no!");
                bool = false;
            }
        }
        Numbers guessNumber = new Numbers();
        guessNumber.generateNumber();
        bool = false;
        String name = player.getName();
        while (bool == false) {
            System.out.println("Okay, " + name + ", guess a number between 0-100!");
            String guess = scan.nextLine();
            int guessInt = validationCheck(guess);
            bool = guessNumber.compareNumber(guessInt);
        }
        scan.close();
    }
    public static int validationCheck(String foo) {
        int i = 0;
        try {
            i = Integer.parseInt(foo);
        }
        catch (Exception e) {
            System.out.println("Invalid value! Closing!");
            System.exit(400);
        }
        return i;

    }
}
