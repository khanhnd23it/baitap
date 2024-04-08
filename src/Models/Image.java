package Models;

import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Image extends JPanel {
    private JLabel imageLabel;

    public Image() {
        imageLabel = new JLabel();
        add(imageLabel);
    }

    public void setImage(BufferedImage image) {
        imageLabel.setIcon(new ImageIcon());
    }
}
