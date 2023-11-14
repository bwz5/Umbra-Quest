
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class UmbraQuest {

    /**
     * Start the application.
     */
    public static void main(String[] args) {
        // Creation of window must occur on Event Dispatch Thread.
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        // Create frame.
        JFrame frame = new JFrame("UMBRA QUEST");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);

        // Create and add game board.
        GameComponent game = new GameComponent();
        frame.add(game);

        // Create and add start button.
        JButton startButton = new JButton("Start my Quest");
        startButton.setFocusable(false);
        Font f = new Font(Font.SANS_SERIF,1,25);
        startButton.setFont(f);
        frame.add(startButton, BorderLayout.SOUTH);

        try {
            SimpleAudioPlayer audioPlayer = new SimpleAudioPlayer();
            audioPlayer.playSound(audioPlayer.filePath);
        } catch (Exception ignored) {}

        ////////////////
        // Controller
        ////////////////

        // When the start button is clicked, start a new game.
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!game.isActive) {
                    game.startGame();
                    startButton.setVisible(false);
                    frame.pack();
                }
            }
        });

        // Stop game when window is closed to ensure that game background tasks
        // do not hold up application shutdown.
        // Use an anonymous subclass of WindowAdapter to avoid having to handle
        // other window events.
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                game.stopGame();
            }
        });

        // Compute ideal window size and show window.
        frame.pack();
        frame.setVisible(true);
    }
}
