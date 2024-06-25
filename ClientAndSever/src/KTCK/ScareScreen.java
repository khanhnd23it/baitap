package KTCK;

import javax.swing.*;
import java.awt.*;

public class ScareScreen extends JWindow {
    private static final long serialVersionUID = 1L;

    public ScareScreen(String imagePath) {
        // Đặt JWindow thành toàn màn hình
        setBounds(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds());
        setLayout(new BorderLayout());

        // Tạo ImageIcon từ đường dẫn ảnh hoặc GIF
        ImageIcon imageIcon = new ImageIcon(imagePath);

        // Thay đổi kích thước của ImageIcon để phù hợp với kích thước màn hình
        Image image = imageIcon.getImage();
       Image scaledImage = image.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
       ImageIcon scaledIcon = new ImageIcon(scaledImage);
       JLabel imageLabel = new JLabel(scaledIcon);
       imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
       imageLabel.setVerticalAlignment(SwingConstants.CENTER);
        add(imageLabel, BorderLayout.CENTER);

        // Đặt Timer để đóng JWindow sau 5 giây
        Timer closeTimer = new Timer(5000, e -> dispose());
        closeTimer.setRepeats(false);
       closeTimer.start();
    }

    public void display() {
        setVisible(true);
        toFront();
    }
}



