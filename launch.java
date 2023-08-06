import javax.swing.*;

public class launch {
    static public void layout() {
        JPanel layout = new JPanel();
        GamePlayGUI.innerPanel.add(layout);
        JTextArea text = new JTextArea("Box Layout chosen as it is fairly simple and perfect for the limited number of GUI objects currently on screen.");
        text.setEditable(false);
        
        JOptionPane.showMessageDialog(layout, text);
    }
}
