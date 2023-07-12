public class Players extends Person{
    private int playerMoney;
    private int startingMoney = 1000;

    public Players() {
    }
    public Players(String first) {
        setFirstName(first);
        setLastName("");
        setMoney(startingMoney);
    }
    public Players(String first, String last) {
        setFirstName(first);
        setLastName(last);
        setMoney(startingMoney);
    }
    public void setMoney(int money){
        playerMoney = money;
    }
    public void addMoney(int reward) {
        playerMoney += reward;
    }
    public void subMoney(int penalty) {
        playerMoney -= penalty;
    }
    public int getMoney() {
        return playerMoney;
    }
    public String getNameOnly() {
        String fullName = firstName + " " + lastName;
        return fullName;
    }
    @Override
    public String toString() {
        String allPlayerInfo = firstName + " " + lastName + ": $" + playerMoney;
        return allPlayerInfo;
    }
}
