//package KTCK;
//
//import java.awt.*;
//import javax.swing.JButton;
//
//public class RoundButton extends JButton {
//
//    private static final long serialVersionUID = 1L;
//
//    public RoundButton(String label) {
//        super(label);
//        setOpaque(false);
//        setContentAreaFilled(false);
//        setBorderPainted(false);
//        setFocusPainted(false);
//        setFont(new Font("Tahoma", Font.BOLD, 20));
//        setForeground(Color.WHITE);
//    }
//
//    @Override
//    protected void paintComponent(Graphics g) {
//        if (getModel().isArmed()) {
//            g.setColor(Color.RED);
//        } else {
//            g.setColor(Color.BLUE);
//        }
//        g.fillOval(0, 0, getWidth(), getHeight());
//        super.paintComponent(g);
//    }
//
//    @Override
//    protected void paintBorder(Graphics g) {
//        g.setColor(Color.WHITE);
//        g.drawOval(0, 0, getWidth() - 1, getHeight() - 1);
//    }
//
//    @Override
//    public Dimension getPreferredSize() {
//        int size = Math.min(getWidth(), getHeight());
//        return new Dimension(size, size);
//    }
//}
package KTCK;

import java.awt.*;
import javax.swing.JButton;

public class RoundButton extends JButton {

    private static final long serialVersionUID = 1L;

    public RoundButton(String label) {
        super(label);
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
        setFont(new Font("Tahoma", Font.BOLD, 8)); // Giảm kích thước font cho phù hợp với nút nhỏ
        setForeground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (getModel().isArmed()) {
            g.setColor(Color.RED);
        } else {
            g.setColor(Color.BLUE);
        }
        g.fillOval(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawOval(0, 0, getWidth() - 1, getHeight() - 1);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(5,5); // Đặt kích thước cố định là 20x20
    }
}

