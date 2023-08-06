import javax.swing.*;
import java.awt.*;
import java.util.*;

public class GamePlayGUI {
    static boolean saveMessages = false;
    private static boolean playAgain = true;
    static JFrame frame = new JFrame("Word Game");
    static JPanel outerPanel = new JPanel();
    static JPanel innerPanel =new JPanel();
    static JScrollPane scroll = new JScrollPane(innerPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    static JTextArea gameInfo = new JTextArea();
    static ArrayList<Players> currentPlayers = new ArrayList<Players>();
    static ArrayList<Hosts> currentHosts = new ArrayList<Hosts>();
    static JCheckBox checkBox = new JCheckBox("Save Messages");
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        new BoxLayout(innerPanel, BoxLayout.Y_AXIS);
        frame.setPreferredSize(new Dimension(800, 900));
        outerPanel.setPreferredSize(new Dimension(800, 900));
       
        scroll.setPreferredSize(new Dimension(700, 800));
        JMenuBar mainBar = new JMenuBar();
        JMenu menu1 = new JMenu("Game");
        JMenu menu2 = new JMenu("About"); 
        JMenuItem addPlayer = new JMenuItem("Add Player");
        addPlayer.addActionListener(e -> new Players());
        JMenuItem addHost = new JMenuItem("Add Host");
        addHost.addActionListener(e -> new Hosts());
        JMenuItem layout = new JMenuItem("Layout");
        layout.addActionListener(e -> launch.layout());

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton playButton = new JButton("New Game");
        playButton.addActionListener(e -> startGame());
        checkBox.addActionListener(e -> saveMessages = true);

        frame.setJMenuBar(mainBar);
        mainBar.add(menu1);
        menu1.setMnemonic('G');
        menu1.add(addHost);
        menu1.add(addPlayer);
        mainBar.add(menu2);
        menu2.setMnemonic('A');
        menu2.add(layout);
        frame.getContentPane().add(outerPanel);
        outerPanel.add(scroll);
        innerPanel.add(playButton);
        innerPanel.add(gameInfo);
        outerPanel.add(checkBox);
        frame.pack();
        frame.setVisible(true);
        gameInfo.setEditable(false);
    }

    private static void startGame() {
        if (currentHosts.isEmpty() || currentPlayers.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Must have at least 1 host and 1 player!");
        }

        while (playAgain) {
            Hosts host = currentHosts.get(0);
            String phrase = JOptionPane.showInputDialog("Enter the phrase!");
            host.setPhrase(phrase);

            boolean b = false;
            while (!b) {
                for (Players playerTurn : currentPlayers) {
                    b = Turn.takeTurn(playerTurn, host);
                    if (b) {
                        break;
                    }
                }
            }

            int answer = JOptionPane.showConfirmDialog(null, "Would you like to play again?", "Play Again", JOptionPane.YES_NO_OPTION);
            playAgain = (answer == JOptionPane.YES_OPTION);
        }
    }
}