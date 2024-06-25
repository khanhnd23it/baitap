package KTCK;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class DemoScreen extends JFrame {

    private boolean isFullScreen = true;
    private MusicPlayer musicPlayer; // Thêm biến nhạc nền

    public DemoScreen() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);

        JPanel contentPane = new BackgroundPanel("C:\\Users\\PC\\Downloads\\backgroundanimation2.gif");
        contentPane.setLayout(new BorderLayout());
        setContentPane(contentPane);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);
        JLabel label = new JLabel("Demo", SwingConstants.LEFT);
        label.setFont(new Font("Arial", Font.BOLD, 50));
        label.setForeground(Color.RED);
        topPanel.add(label, BorderLayout.WEST);

        contentPane.add(topPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        buttonPanel.setOpaque(false);
        contentPane.add(buttonPanel, BorderLayout.CENTER);

        String[] gifPaths = {
        		"C:\\Users\\PC\\Downloads\\scary1.jpg",
                "C:\\Users\\PC\\Downloads\\scary2.jpg",
                "C:\\Users\\PC\\Downloads\\scary3.jpg",
                "C:\\Users\\PC\\Downloads\\scary4.jpg"
        };

        Random rand = new Random();
        int correctButtonIndex = rand.nextInt(4);

        for (int i = 0; i < 4; i++) {
            RoundButton button = new RoundButton("");
            button.setPreferredSize(new Dimension(20, 20));

            if (i == correctButtonIndex) {
                button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                        JOptionPane.showMessageDialog(DemoScreen.this, "Bạn đã hoàn thành hướng dẫn!");
                        new GameScreen(); 
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

        // Khởi động nhạc nền
  //      musicPlayer = new MusicPlayer("C:\\Users\\PC\\Downloads\\background_music.mp3");
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
