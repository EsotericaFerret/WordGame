import javax.swing.JLabel;
import java.util.concurrent.TimeUnit;

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

    public void setImage() {
        switch (this.item) {
           case "Car": GamePlayGUI.label.setIcon(GamePlayGUI.carIcon);
           break;
           case "Vacation": GamePlayGUI.label.setIcon(GamePlayGUI.vacationIcon);
           break;
           case "Phone": GamePlayGUI.label.setIcon(GamePlayGUI.phoneIcon);
           break;
           case "Patio Set": GamePlayGUI.label.setIcon(GamePlayGUI.patioSetIcon);
           break;
           case "Washer/Dryer Set": GamePlayGUI.label.setIcon(GamePlayGUI.washerDryerIcon);
           break;
        }
        GamePlayGUI.label.setVisible(true);
        Main.game.repaint();
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        GamePlayGUI.label.setVisible(false);
        Main.game.repaint();     
        
    }

}

