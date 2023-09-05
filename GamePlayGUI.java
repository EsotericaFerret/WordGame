import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GamePlayGUI extends JFrame {
    private JTextField playerNameField;
    private JRadioButton hostRadioButton, playerRadioButton;
    private JButton addButton;
    static JButton submitPhraseButton;
    static JButton startGameButton;
	static JButton submitGuessButton;
    static JTextField phraseField;
    static JTextField playerGuessField;
    static JTextArea outputArea;
    private List<Host> hosts = new ArrayList<>();
    static List<Player> players = new ArrayList<>();
    static Game currentGame;
    static Object lock = new Object();
    static boolean gameOver = false;

        public GamePlayGUI() {
        // Initialize and configure GUI components
        playerNameField = new JTextField(20);
        hostRadioButton = new JRadioButton("Host");
        playerRadioButton = new JRadioButton("Player");
        addButton = new JButton("Add");
        submitPhraseButton = new JButton("Submit Phrase");
        startGameButton = new JButton("Start Game");
        submitGuessButton = new JButton("Submit Guess");
        phraseField = new JTextField(20);
        playerGuessField = new JTextField(10);
        outputArea = new JTextArea(15, 40);
        outputArea.setEditable(false);
    
        // Create radio button group
        ButtonGroup group = new ButtonGroup();
        group.add(hostRadioButton);
        group.add(playerRadioButton);
    
        // Create panels to organize components
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());
        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(playerNameField);
        inputPanel.add(hostRadioButton);
        inputPanel.add(playerRadioButton);
        inputPanel.add(addButton);
    
        JPanel phrasePanel = new JPanel();
        phrasePanel.setLayout(new FlowLayout());
        phrasePanel.add(new JLabel("Phrase:"));
        phrasePanel.add(phraseField);
        phrasePanel.add(submitPhraseButton);
    
        JPanel gamePanel = new JPanel();
        gamePanel.setLayout(new FlowLayout());
        gamePanel.add(startGameButton);
    
        JPanel guessPanel = new JPanel();
        guessPanel.setLayout(new FlowLayout());
        guessPanel.add(new JLabel("Guess:"));
        guessPanel.add(playerGuessField);
        guessPanel.add(submitGuessButton);
    
        // Create a scroll pane for the output area
        JScrollPane scrollPane = new JScrollPane(outputArea);
    
        // Create a container for buttons
        JPanel buttonContainer = new JPanel();
        buttonContainer.setLayout(new GridLayout(1, 2, 10, 10));
        buttonContainer.add(gamePanel);
        buttonContainer.add(guessPanel);
    
        // Create the main content pane
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout(10, 10));
        contentPane.add(inputPanel, BorderLayout.NORTH);
        contentPane.add(phrasePanel, BorderLayout.CENTER);
        contentPane.add(buttonContainer, BorderLayout.SOUTH);
        contentPane.add(scrollPane, BorderLayout.WEST);
    
        // Add action listeners to buttons
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addHostOrPlayer();
            }
        });
    
        submitPhraseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                submitPhrase();
            }
        });
    
        startGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startGame();
            }
        });
    
        submitGuessButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                synchronized (lock) {
                    lock.notifyAll();
                }

            }
        });
    
        // Create and set up the game logic
    
        // Configure JFrame properties
        setTitle("Game Play GUI");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    private void addHostOrPlayer() {
        String name = playerNameField.getText();
        if (hostRadioButton.isSelected()) {
            Host host = new Host(name);
            hosts.add(host);
            outputArea.append("Added Host: " + name + "\n");
        } else if (playerRadioButton.isSelected()) {
            Player player = new Player(name);
            players.add(player);
            outputArea.append("Added Player: " + name + "\n");
        }
    }

    private void submitPhrase() {
        String phrase = phraseField.getText();
        if (!phrase.isEmpty() && !hosts.isEmpty() && !players.isEmpty()) {
            currentGame = new Game(phrase, hosts, players);
            outputArea.append("Submitted Phrase: " + phrase + "\n");
            phraseField.setEnabled(false);
            submitPhraseButton.setEnabled(false);
            startGameButton.setEnabled(true);
        } else {
            outputArea.append("Please enter a phrase and add at least 1 host and 1 player.\n");
        }
    }
 
      private void startGame() {
        if (currentGame != null) {
            currentGame.startGame();
            startGameButton.setEnabled(false);
            playerGuessField.setEnabled(true);
            submitGuessButton.setEnabled(true);
            outputArea.append("Game Started!\n");
            while (gameOver == false) {
                new Thread(new Runnable() {
                    public void run() {
                    for (Player player : players) {
                    outputArea.append("Phrase to guess is " + currentGame.getMaskedPhrase() + "\n");
                    outputArea.append(player.getName() + ", please submit a single letter guess \r\n");
                    synchronized (lock) {
                    try {
                    GamePlayGUI.lock.wait();
                    }
                    catch (InterruptedException e) {
            
                    }
                }
                    String guess = playerGuessField.getText();
                    if (!guess.isEmpty()) {
                        currentGame.makeGuess(guess, player);
                        playerGuessField.setText("");
                    }
                    }
                }
                }).start();
                }
            }   
        }



    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new GamePlayGUI();
        });
    }
}