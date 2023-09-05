public class Player {
    private String name;
    private Money money;
    
    public Player(String name) {
        this.name = name;
        money = new Money(1000);
    }
    
    public String getName() {
        return name;
    }

    public int getMoney() {
        return money.getAmount();
    }

    public void deductMoney(int amount) {
        money.setAmount(money.getAmount() - amount);
    }

    public void addMoney(int amount) {
        money.setAmount(amount + money.getAmount());
    }

}