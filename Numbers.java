import java.util.*;

public class Numbers {
    private int randomNum;
    
    public void setRandomNum(int num) {
        randomNum = num;
    }
    public int getRandomNum() {
        return randomNum;
    }
    public void generateNumber() {
        Random rand = new Random();
        int upperbound = 101;
        randomNum = rand.nextInt(upperbound);
    }
    public boolean compareNumber(int guess) {
        if (guess == randomNum) {
            System.out.println("Congratulations, you guessed the number!");
            return true;
        }
        else if (guess > randomNum) {
            System.out.println("I'm sorry. That guess was too high!");
            return false;
        }
        else {
            System.out.println("I'm sorry. That guess was too low!");
            return false;
        }
    }

}
