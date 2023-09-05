public class Physical extends Reward {
    private String item;

    public Physical(String item) {
        this.item = item;
    }

        public String getItem() {
        return item;
    }

    @Override
    public String getRewardDescription() {
        return item + " (Physical Reward)";
    }
}
