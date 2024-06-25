package KTCK;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import DAO.AuthDAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login extends JFrame {

    private static final long serialVersionUID = 1L;
    private BackgroundPanel contentPane;
    private JPasswordField passwordField;
    private JTextField userField;
    private boolean isPasswordVisible = false;
    private JTextField passwordTextField;
    private boolean isFullScreen = true;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Login frame = new Login();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Login() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);

        contentPane = new BackgroundPanel("C:\\Users\\PC\\Downloads\\backgroundanimation2.gif");
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
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
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblNewLabel = new JLabel("LOGIN");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
        lblNewLabel.setPreferredSize(new Dimension(300, 50));
        lblNewLabel.setForeground(Color.RED);
        contentPane.add(lblNewLabel, gbc);

        userField = new JTextField();
        userField.setFont(new Font("Tahoma", Font.PLAIN, 20));
        userField.setPreferredSize(new Dimension(300, 50));
        contentPane.add(userField, gbc);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Tahoma", Font.PLAIN, 20));
        passwordField.setPreferredSize(new Dimension(300, 50));
        contentPane.add(passwordField, gbc);

        passwordTextField = new JTextField();
        passwordTextField.setFont(new Font("Tahoma", Font.PLAIN, 20));
        passwordTextField.setPreferredSize(new Dimension(300, 50));
        passwordTextField.setVisible(false);
        contentPane.add(passwordTextField, gbc);

        JButton togglePasswordButton = new JButton("Show/Hide Password");
        togglePasswordButton.setPreferredSize(new Dimension(300, 50));
        togglePasswordButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                togglePasswordVisibility();
            }
        });
        contentPane.add(togglePasswordButton, gbc);

        JButton loginButton = new JButton("Login");
        loginButton.setBackground(new Color(255, 0, 0));
        loginButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
        loginButton.setPreferredSize(new Dimension(300, 50));
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String userData = userField.getText();
                String passwordData = isPasswordVisible ? passwordTextField.getText() : new String(passwordField.getPassword());
                if (AuthDAO.authenticate(userData, passwordData)) {                
                    new GameScreen();
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(Login.this, "Invalid login credentials", "Login Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        contentPane.add(loginButton, gbc);

        JButton newAccountButton = new JButton("New Account");
        newAccountButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
        newAccountButton.setPreferredSize(new Dimension(300, 50));
        newAccountButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new NewAcc();
            }
        });
        contentPane.add(newAccountButton, gbc);

        setVisible(true);
    }

    private void togglePasswordVisibility() {
        if (isPasswordVisible) {
            passwordField.setText(passwordTextField.getText());
            passwordField.setVisible(true);
            passwordTextField.setVisible(false);
        } else {
            passwordTextField.setText(new String(passwordField.getPassword()));
            passwordTextField.setVisible(true);
            passwordField.setVisible(false);
        }
        isPasswordVisible = !isPasswordVisible;
        revalidate();
        repaint();
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

    private boolean checkIfNewPlayer(String username) {
        // Thực hiện kiểm tra xem người chơi có phải là người mới hay không
        // Ví dụ: truy vấn cơ sở dữ liệu để xem người chơi có lưu trạng thái nào không
        // Ở đây, tôi giả sử luôn là người chơi mới để minh họa
        return true;
    }
}
