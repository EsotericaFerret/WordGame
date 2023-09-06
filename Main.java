import javax.swing.SwingUtilities;

public class Main {
    static GamePlayGUI game;
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            game = new GamePlayGUI();
        });
    }
    
}
