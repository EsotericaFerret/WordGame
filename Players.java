import javax.swing.*;

public class Players extends Person {
    private int playerMoney;
    private int startingMoney = 1000;
    private JTextField firstNameField;
    private JTextField lastNameField;

    public Players() {
        JPanel playerInfoPanel = new JPanel();
        GamePlayGUI.innerPanel.add(playerInfoPanel);
        playerInfoPanel.add(new JLabel("Please enter players's first name:"));
        firstNameField = new JTextField(20);
        playerInfoPanel.add(firstNameField);

        playerInfoPanel.add(new JLabel("Please enter player's last name:"));
        lastNameField = new JTextField(20);
        playerInfoPanel.add(lastNameField);

        int result = JOptionPane.showConfirmDialog(null, playerInfoPanel, "Player Information", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            setFirstName(firstNameField.getText());
            setLastName(lastNameField.getText());
            playerMoney = startingMoney;
            GamePlayGUI.currentPlayers.add(this);
        } else {
            // Handle the case where the user cancels entering player information
        }
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

    public void setMoney(int money) {
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

    public void displayMoneyInfo() {
        // Display player's money information in a dialog box
        JOptionPane.showMessageDialog(null, "Player: " + getNameOnly() + "\nMoney: $" + playerMoney);
    }

    @Override
    public String toString() {
        String allPlayerInfo = getNameOnly() + ": $" + playerMoney;
        return allPlayerInfo;
    }
}