package KTCK;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import DAO.Conn;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class man2 extends JFrame {

    private boolean isFullScreen = true;
    private Timer timer;
    private MusicPlayer musicPlayer;

    public man2() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);

        // Create the content pane
        JPanel contentPane = new BackgroundPanel("C:\\Users\\PC\\Downloads\\backgroundanimation2.gif");
        contentPane.setLayout(new BorderLayout());
        setContentPane(contentPane);

        // Create top panel with label and timer
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);
        JLabel label = new JLabel("Màn 2", SwingConstants.LEFT);
        label.setFont(new Font("Arial", Font.BOLD, 50));
        label.setForeground(Color.RED);
        topPanel.add(label, BorderLayout.WEST);

        JLabel timerLabel = new JLabel("00:50", SwingConstants.RIGHT);
        timerLabel.setFont(new Font("Arial", Font.BOLD, 50));
        timerLabel.setForeground(Color.RED);
        topPanel.add(timerLabel, BorderLayout.EAST);

        contentPane.add(topPanel, BorderLayout.NORTH);

        // Create the panel with buttons
        JPanel buttonPanel = new JPanel(new GridLayout(3, 3, 150, 150));
        buttonPanel.setOpaque(false);
        contentPane.add(buttonPanel, BorderLayout.CENTER);

        // Array of GIF file paths
        String[] gifPaths = {
        	"C:\\Users\\PC\\Downloads\\scary6.jpg",
        	"C:\\Users\\PC\\Downloads\\scary6.jpg",
        	"C:\\Users\\PC\\Downloads\\scary6.jpg",
        	"C:\\Users\\PC\\Downloads\\scary6.jpg",
        	"C:\\Users\\PC\\Downloads\\scary6.jpg",
            "C:\\Users\\PC\\Downloads\\scary6.jpg",
            "C:\\Users\\PC\\Downloads\\scary6.jpg",
            "C:\\Users\\PC\\Downloads\\scary6.jpg",
            "C:\\Users\\PC\\Downloads\\scary6.jpg",
            "C:\\Users\\PC\\Downloads\\scary6.jpg",
            "C:\\Users\\PC\\Downloads\\scary6.jpg",
        };

        // Create the buttons
        Random rand = new Random();
        int correctButtonIndex = rand.nextInt(9);

        for (int i = 0; i < 12; i++) {
            RoundButton button = new RoundButton("");
            button.setPreferredSize(new Dimension(10, 10));

            if (i == correctButtonIndex) {
                button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        timer.stop();
                        savePlayerData("PlayerName", 2, timerLabel.getText()); // Replace "PlayerName" with actual player name
                        dispose();
                        new man3("PlayerName"); // Replace "PlayerName" with actual player name
                    }
                });
            } else {
                final String gifPath = gifPaths[i % gifPaths.length];
                button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        ScareScreen scareScreen = new ScareScreen(gifPath);
                        scareScreen.display();
                    }
                });
            }

            buttonPanel.add(button);
        }

        // Initialize and start the timer
        timer = new Timer(1000, new ActionListener() {
            int time = 50;

            public void actionPerformed(ActionEvent e) {
                if (time > 0) {
                    time--;
                    int minutes = time / 60;
                    int seconds = time % 60;
                    timerLabel.setText(String.format("%02d:%02d", minutes, seconds));
                } else {
                    timer.stop();
                    dispose();
                    new man2();
                }
            }
        });
        timer.start();
        // Add background music
   //   musicPlayer = new MusicPlayer("C:\\Users\\PC\\Downloads\\background_music.mp3");
   //   musicPlayer.play();

        setVisible(true);
    }

    private void savePlayerData(String playerName, int level, String timeElapsed) {
        Connection con = null;
        try {
            con = Conn.getConnection();
            String sql = "INSERT INTO PlayerData (PlayerName, Level, TimeElapsed) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, playerName);
            ps.setInt(2, level);
            ps.setString(3, timeElapsed);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void exitFullScreenMode() {
        if (isFullScreen) {
            dispose();
            setUndecorated(false);
            setExtendedState(JFrame.NORMAL);
            setVisible(true);
            isFullScreen = false;
        }
    }

    private void toggleFullScreenMode() {
        if (isFullScreen) {
            exitFullScreenMode();
        } else {
            dispose();
            setUndecorated(true);
            setExtendedState(JFrame.MAXIMIZED_BOTH);
            setVisible(true);
            isFullScreen = true;
        }
    }
}
