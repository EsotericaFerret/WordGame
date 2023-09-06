import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class GamePlayGUI extends JFrame implements ActionListener{
    static Container contentPane;
    private JTextField playerNameField;
    private JRadioButton hostRadioButton, playerRadioButton;
    private JButton addButton;
    private JMenuBar menuBar;
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
    static JLabel label = new JLabel();
    private JMenu menu;
    private JMenu about;
    
    static ImageIcon carIcon = new ImageIcon("assets/car.png");
    static ImageIcon vacationIcon = new ImageIcon("assets/vacation.png");
    static ImageIcon phoneIcon = new ImageIcon("assets/phone.png");
    static ImageIcon patioSetIcon = new ImageIcon("assets/patioSet.png");
    static ImageIcon washerDryerIcon = new ImageIcon("assets/washerDryer.jpg");
    Image ferret = new ImageIcon("assets/FerretHappy.png").getImage();
    int xVelocity = 5;
    int xLocation = 0;
    int yLocation = 85;
    Graphics g;
    Graphics2D g2d = (Graphics2D) g;
    



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
        outputArea = new JTextArea(5, 20);
        menuBar = new JMenuBar();
        menu = new JMenu("Menu");
        about = new JMenu("About");
        JMenuItem exit = new JMenuItem("Exit");
        JMenuItem layoutMenuItem = new JMenuItem("Layout");
        JMenuItem audioCredits = new JMenuItem("Audio Credits");
        JMenuItem imageCredits = new JMenuItem("Image Credits");
        Timer timer = new Timer(100, this);
        timer.start();
        outputArea.setEditable(false);
        // Create radio button group
        ButtonGroup group = new ButtonGroup();
        group.add(hostRadioButton);
        group.add(playerRadioButton);
    
        // Create panels to organize components
        JPanel inputPanel = new JPanel();
        GroupLayout inputPanelLayout = new GroupLayout(inputPanel);
        inputPanel.setLayout(inputPanelLayout);
        inputPanelLayout.setHorizontalGroup(
            inputPanelLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(inputPanelLayout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(playerNameField, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(hostRadioButton)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(playerRadioButton)
                .addGap(38, 38, 38)
                .addComponent(addButton)
                .addContainerGap(15, Short.MAX_VALUE))
                );
            inputPanelLayout.setVerticalGroup(
                inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(inputPanelLayout.createSequentialGroup()
                    .addGap(14, 14, 14)
                    .addGroup(inputPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(playerNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(hostRadioButton)
                        .addComponent(playerRadioButton)
                        .addComponent(addButton))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

        JPanel phrasePanel = new JPanel();
        GroupLayout phrasePanelLayout = new GroupLayout(phrasePanel);
        phrasePanel.setLayout(phrasePanelLayout);
        phrasePanelLayout.setHorizontalGroup(
            phrasePanelLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(phrasePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(phrasePanelLayout.createParallelGroup(Alignment.LEADING)
                    .addComponent(submitPhraseButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(phraseField))
                .addContainerGap())
        );
        phrasePanelLayout.setVerticalGroup(
            phrasePanelLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(phrasePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(phraseField)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(submitPhraseButton, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    
        JPanel gamePanel = new JPanel();
        GroupLayout gamePanelLayout = new GroupLayout(gamePanel);
        gamePanel.setLayout(gamePanelLayout);
        gamePanelLayout.setHorizontalGroup(
            gamePanelLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(Alignment.TRAILING, gamePanelLayout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(startGameButton, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );
        gamePanelLayout.setVerticalGroup(
            gamePanelLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(gamePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(startGameButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    
        JPanel guessPanel = new JPanel();
        GroupLayout guessPanelLayout = new GroupLayout(guessPanel);
        guessPanel.setLayout(guessPanelLayout);
        guessPanelLayout.setHorizontalGroup(
            guessPanelLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(guessPanelLayout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(playerGuessField, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(submitGuessButton, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );
        guessPanelLayout.setVerticalGroup(
            guessPanelLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(guessPanelLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(playerGuessField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
            .addGroup(guessPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(submitGuessButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        // Create a scroll pane for the output area
        JScrollPane scrollPane = new JScrollPane(outputArea);

        menuBar.add(menu);
        menuBar.add(about);
        menu.add(exit);
        about.add(layoutMenuItem);
        about.add(audioCredits);
        about.add(imageCredits);
        
    
        // Create a container for buttons
        JPanel buttonContainer = new JPanel();
        GroupLayout buttonContainerLayout = new GroupLayout(buttonContainer);
        buttonContainer.setLayout(buttonContainerLayout);
        buttonContainerLayout.setHorizontalGroup(
            buttonContainerLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(buttonContainerLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(gamePanel, GroupLayout.PREFERRED_SIZE, 187, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(guessPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );
        buttonContainerLayout.setVerticalGroup(
            buttonContainerLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(buttonContainerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(buttonContainerLayout.createParallelGroup(Alignment.LEADING, false)
                    .addComponent(gamePanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(guessPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    
        // Create the main content pane
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(Alignment.LEADING)
            .addComponent(menuBar, 800, 800, 800)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                    .addComponent(buttonContainer, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(inputPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(phrasePanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 386, GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 50, 100)
                        .addComponent(label, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            
            layout.createParallelGroup(Alignment.LEADING)
            .addComponent(menuBar, 20, 20, 20)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                    .addComponent(phrasePanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(inputPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                    .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
                    .addComponent(label, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonContainer, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        label.setVisible(false);

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
                runGame();
            }
        });
    
        submitGuessButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                synchronized (lock) {
                    lock.notifyAll();
                }

            }
        });
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add your code for Exit action here
                System.exit(0); // For example, exit the application
            }
        });
        layoutMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add your code for Layout action here
                // You can open a new window or perform other actions
                JOptionPane.showMessageDialog(null, getLayoutDescription());
            }
        });
        audioCredits.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add your code for Credits action here
                // You can open a dialog to display credits information
                JOptionPane.showMessageDialog(null, getAudioCredits());
            }
        });
                imageCredits.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add your code for Credits action here
                // You can open a dialog to display credits information
                JOptionPane.showMessageDialog(null, getImageCredits());
            }
        });        
        // Create and set up the game logic
    
        // Configure JFrame properties
        setTitle("Game Play GUI");
        setMaximumSize(new Dimension(800, 600));
        setPreferredSize(new Dimension(800, 600));
        setResizable(false);
        setSize(new java.awt.Dimension(800, 700));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    public void paint(Graphics g) {
        super.paint(g);

       g2d = (Graphics2D) g;

        g2d.drawImage(ferret, xLocation, yLocation, null);
    }

    
    protected String getImageCredits() {
            return "Image Credits: \r\n Car Reward Image: Car Vehicle Sports Car Racing Car by lipetkd on pixabay.com \r\n https://pixabay.com/illustrations/car-vehicle-sports-car-racing-car-49278/ \r\n Vacation Reward Image: Vacations Suitcase To Travel by garten-gg on pixabay.com \r\n https://pixabay.com/illustrations/vacations-suitcase-to-travel-travel-1442020/ \r\n Phone Reward Image: Smartphone Apple Cellphone by OpenClipArt-Vectors on pixabay.com \r\n https://pixabay.com/vectors/smartphone-apple-cellphone-iphone-153650/ \r\n Patio Set Reward Image: Couch Soda Furniture by OpenClipart-Vectors on pixabay.com \r\n https://pixabay.com/vectors/couch-sofa-furniture-seating-seat-576128/ \r\n Washer/Dryer Reward Image: Washing machine, Laundry, Tumble drier by stevepb on pixabay.com \r\n https://pixabay.com/photos/washing-machine-laundry-tumble-drier-2668472/";
        }

    protected String getAudioCredits() {
            return "Audio Credits: \r\n Game Play Music: Anime Encounter Loop #1 by Sirkoto51 on freesound.org \r\n https://freesound.org/people/Sirkoto51/sounds/341362/ \r\n Game Intro Music: Safari Music Loop by Sirkoto51 on freesound.org \r\n https://freesound.org/people/Sirkoto51/sounds/338818/ \r\n Correct Bell Sound: Correct by bwg2020 on freesound.org \r\n https://freesound.org/people/bwg2020/sounds/456161/ \r\n Wrong Buzzer Sound: Wrong Answer by -Andreas on freesound.org \r\n https://freesound.org/people/-Andreas/sounds/648462/ \r\n Game Finish Music: gewonnen by Kastenfrosch on freesound.org \r\n https://freesound.org/people/Kastenfrosch/sounds/113989/"; 
        }

    protected String getLayoutDescription() {
            return "Layout custom built using GroupLayouts and proper padding.";
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
            outputArea.append("Phrase submitted! Game may start when ready! \r\n");
            phraseField.setEnabled(false);
            submitPhraseButton.setEnabled(false);
            startGameButton.setEnabled(true);
        } else {
            outputArea.append("Please enter a phrase and add at least 1 host and 1 player.\n");
        }
    }
 
      private void runGame() {
        if (currentGame != null) {
            currentGame.startGame();
            startGameButton.setEnabled(false);
            playerGuessField.setEnabled(true);
            submitGuessButton.setEnabled(true);
            outputArea.append("Game Started!\n");
            musicSet();
            new Thread(new Runnable() {
                public void run() {
                while (gameOver == false) {
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
                }
            }).start();}
            }

            static void musicSet() {
                if (currentGame.gameStarted) {
                    Main.intro.stop();
                    Main.playing.setFramePosition(0);
                    Main.playing.loop(Clip.LOOP_CONTINUOUSLY);
                    Main.playing.start();
                }
                else {
                    Main.playing.stop();
                    Main.over.stop();
                    Main.over.setFramePosition(0);
                    Main.over.start();
                    try {
                        TimeUnit.SECONDS.sleep(4);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    Main.intro.setFramePosition(0);
                    Main.intro.loop(Clip.LOOP_CONTINUOUSLY);
                    Main.intro.start();
                } 
        }
            @Override
            public void actionPerformed(ActionEvent e) {
                if (xLocation >= (this.getWidth()-ferret.getWidth(null)-250) || xLocation < 0 ) {
                    xVelocity *= -1;
                }
                xLocation = xLocation + xVelocity;
                repaint();
            }
}