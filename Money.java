public class Money extends Reward {
    private int amount;

    public Money(int amount) {
        this.amount = amount;
    }

    @Override
    public String getRewardDescription() {
        return "$" + amount + " Money";
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int value) {
        this.amount = value;
    }
}