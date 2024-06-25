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

public class man3 extends JFrame {

    private boolean isFullScreen = true;
    private Timer timer;
    private MusicPlayer musicPlayer;
    private String playerName;

    public man3(String playerName) {
        this.playerName = playerName;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);

        JPanel contentPane = new BackgroundPanel("C:\\Users\\PC\\Downloads\\backgroundanimation2.gif");
        contentPane.setLayout(new BorderLayout());
        setContentPane(contentPane);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);
        JLabel label = new JLabel("Màn 3", SwingConstants.LEFT);
        label.setFont(new Font("Arial", Font.BOLD, 50));
        label.setForeground(Color.RED);
        topPanel.add(label, BorderLayout.WEST);

        JLabel timerLabel = new JLabel("05:00", SwingConstants.RIGHT);
        timerLabel.setFont(new Font("Arial", Font.BOLD, 50));
        timerLabel.setForeground(Color.RED);
        topPanel.add(timerLabel, BorderLayout.EAST);

        contentPane.add(topPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(4, 4, 10, 10));
        buttonPanel.setOpaque(false);
        contentPane.add(buttonPanel, BorderLayout.CENTER);

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
                "C:\\Users\\PC\\Downloads\\scary6.jpg",
            	"C:\\Users\\PC\\Downloads\\scary6.jpg",
            	"C:\\Users\\PC\\Downloads\\scary6.jpg",
            	"C:\\Users\\PC\\Downloads\\scary6.jpg",
            
        };

        Random rand = new Random();
        int correctButtonIndex = rand.nextInt(16);

        for (int i = 0; i < 16; i++) {
            RoundButton button = new RoundButton("");
            button.setPreferredSize(new Dimension(20, 20));

            if (i == correctButtonIndex) {
                button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        timer.stop();
                        savePlayerData(playerName, 3, timerLabel.getText());
                        // Implement logic to proceed to the next level or finish the game
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

        timer = new Timer(1000, new ActionListener() {
            int time = 75;

            public void actionPerformed(ActionEvent e) {
                if (time > 0) {
                    time--;
                    int minutes = time / 60;
                    int seconds = time % 60;
                    timerLabel.setText(String.format("%02d:%02d", minutes, seconds));
                } else {
                    timer.stop();
                    dispose();
                    new man3(playerName);
                }
            }
        });
        timer.start();

        // Add background music
    //    musicPlayer = new MusicPlayer("C:\\Users\\PC\\Downloads\\background_music.mp3");
    //    musicPlayer.play();

        setVisible(true);
    }

    private void showScareGif(String gifPath) {
        JDialog scareDialog = new JDialog(this, "Scare!", true);
        scareDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        scareDialog.setUndecorated(true);
        scareDialog.setBounds(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds());
        scareDialog.setLayout(new BorderLayout());

        ImageIcon gifIcon = new ImageIcon(gifPath);
        JLabel gifLabel = new JLabel(gifIcon);
        scareDialog.add(gifLabel, BorderLayout.CENTER);

        scareDialog.setLocationRelativeTo(null);
        scareDialog.setVisible(true);

        new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                scareDialog.dispose();
            }
        }).start();
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
