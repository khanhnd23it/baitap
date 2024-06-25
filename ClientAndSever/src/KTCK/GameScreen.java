package KTCK;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameScreen extends JFrame {

    private static final long serialVersionUID = 1L;
    private BackgroundPanel contentPane;
    private boolean isFullScreen = true;
    private MusicPlayer musicPlayer; // Thêm biến nhạc nền

    public GameScreen() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        contentPane = new BackgroundPanel("C:\\Users\\PC\\Downloads\\iconmatcuoi.jpg");
        contentPane.setLayout(new GridBagLayout());
        setContentPane(contentPane);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    exitFullScreenMode();
                } else if (e.getKeyCode() == KeyEvent.VK_ENTER && e.isAltDown()) {
                    toggleFullScreenMode();
                }
            }
        });
        setFocusable(true);
        requestFocusInWindow();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("Vui Vẻ");
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 60));
        titleLabel.setForeground(Color.RED);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(titleLabel, gbc);

        JButton playButton = new JButton("Chơi");
        playButton.setFont(new Font("Tahoma", Font.PLAIN, 40));
        playButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        playButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new man1();
                dispose();
            }
        });
        contentPane.add(playButton, gbc);

        JButton guideButton = new JButton("Hướng dẫn chơi");
        guideButton.setFont(new Font("Tahoma", Font.PLAIN, 40));
        guideButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        guideButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new DemoScreen();
                dispose();
            }
        });
        contentPane.add(guideButton, gbc);

        // Khởi động nhạc nền
      //  musicPlayer = new MusicPlayer("C:\\Users\\PC\\Downloads\\background_music.mp3");
      //  musicPlayer.play();

        setVisible(true);
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

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GameScreen frame = new GameScreen();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
