import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.SwingUtilities;

public class Main {
    static GamePlayGUI game;
    static Clip intro;
    static Clip playing;
    static Clip correct;
    static Clip wrong;
    static Clip over;
    public static void main(String[] args) {
            try {
            intro = AudioSystem.getClip();
            AudioInputStream introStream = AudioSystem.getAudioInputStream(new File("assets/gameStart.wav"));
            intro.open(introStream);

            playing = AudioSystem.getClip();
            AudioInputStream playingStream = AudioSystem.getAudioInputStream(new File("assets/gameRunning.wav"));
            playing.open(playingStream);

            correct = AudioSystem.getClip();
            AudioInputStream correctStream = AudioSystem.getAudioInputStream(new File("assets/correct.wav"));
            correct.open(correctStream);

            wrong = AudioSystem.getClip();
            AudioInputStream wrongStream = AudioSystem.getAudioInputStream(new File("assets/wrong.wav"));
            wrong.open(wrongStream);

            over = AudioSystem.getClip();
            AudioInputStream overStream = AudioSystem.getAudioInputStream(new File("assets/gameFinish.wav"));
            over.open(overStream);
        }
        catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
        e.setStackTrace(null);
        }         
        SwingUtilities.invokeLater(() -> {
            game = new GamePlayGUI();
        });
    }
    
}
