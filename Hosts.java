import javax.swing.*;

public class Hosts extends Person {
    private JTextField firstNameField;
    private JTextField lastNameField;

    public Hosts() {
        // Create a custom panel for entering host's information
        JPanel hostInfoPanel = new JPanel();
        hostInfoPanel.add(new JLabel("Please enter host's first name:"));
        firstNameField = new JTextField(20);
        hostInfoPanel.add(firstNameField);

        hostInfoPanel.add(new JLabel("Please enter host's last name:"));
        lastNameField = new JTextField(20);
        hostInfoPanel.add(lastNameField);
        

        int result = JOptionPane.showConfirmDialog(null, hostInfoPanel, "Host Information", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            if (GamePlayGUI.currentHosts.isEmpty()){
                setFirstName(firstNameField.getText());
                setLastName(lastNameField.getText());
                GamePlayGUI.currentHosts.add(0, this);
            }
            else {
                setFirstName(firstNameField.getText());
                setLastName(lastNameField.getText());
                GamePlayGUI.currentHosts.set(0, this);
            }
        } else {
            // Handle the case where the user cancels entering host information
        }
    }

    public void setPhrase(String phrase) {
        Phrases.setPhrase(phrase);
    }
}