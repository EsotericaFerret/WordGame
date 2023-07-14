import java.util.Scanner;
public class Hosts extends Person {
    public Hosts(Scanner scnr) {
        System.out.println("Please enter host's first name.");
        setFirstName(scnr.nextLine());
        System.out.println("Please enter host's last name.");
        setLastName(scnr.nextLine());
    }
    public void setPhrase(String phrase) {
        Phrases.setPhrase(phrase);
    }
    

}
