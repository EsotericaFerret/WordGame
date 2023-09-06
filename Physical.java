import javax.swing.ImageIcon;
import javax.swing.JLabel;

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

    public void getImage() {
        switch (this.item) {
            case "Car": Game.filePath = "assets/car.png";
            break;
            case "Vacation": Game.filePath = "assets/vacation.png";
            break;
            case "Phone": Game.filePath = "assets/phone.png";
            break;
            case "Patio Set": Game.filePath = "assets/patioSet.png";
            break;
            case "Washer/Dryer Set": Game.filePath = "assets/washerDryer.jpg";
        }
    }
    public void setImage() {
        this.getItem();
        ImageIcon imageIcon = new ImageIcon(Game.filePath);
        JLabel image = new JLabel(imageIcon);
        image.setVisible(true);
        GamePlayGUI.contentPane.add(image);
        GamePlayGUI.contentPane.setVisible(true);
        GamePlayGUI.contentPane.repaint();
    }
}

