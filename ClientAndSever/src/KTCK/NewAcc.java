package KTCK;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import DAO.Conn;

public class NewAcc extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField U;
    private JPasswordField P;
    private JPasswordField PA;
    private JTextField PText;
    private JTextField PAText;
    private boolean isPVisible = false;
    private boolean isPAVisible = false;
    private boolean isFullScreen = true; // Biến để theo dõi trạng thái toàn màn hình

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    NewAcc frame = new NewAcc();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public NewAcc() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Bật toàn màn hình
        setUndecorated(true); // Ẩn thanh tiêu đề

        contentPane = new BackgroundPanel("C:\\Users\\PC\\Downloads\\backgroundanimation2.gif");
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new GridBagLayout()); // Sử dụng GridBagLayout để căn giữa
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
        gbc.insets = new Insets(10, 10, 10, 10); // Điều chỉnh khoảng cách giữa các thành phần
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Row 0 - Title
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        JLabel lblNewLabel = new JLabel("NEW ACCOUNT");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
        lblNewLabel.setPreferredSize(new Dimension(300, 50)); // Điều chỉnh kích thước JLabel
        lblNewLabel.setForeground(Color.RED); // Chỉnh màu cho JLabel
        contentPane.add(lblNewLabel, gbc);

        // Reset gridwidth for next rows
        gbc.gridwidth = 1;

        // Row 1 - User Label and TextField
        gbc.gridy++;
        gbc.gridx = 0;
        JLabel lblNewLabel_1 = new JLabel("User");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_1.setPreferredSize(new Dimension(200, 50));
        contentPane.add(lblNewLabel_1, gbc);

        gbc.gridx = 1;
        U = new JTextField();
        U.setFont(new Font("Tahoma", Font.PLAIN, 20));
        U.setPreferredSize(new Dimension(300, 50)); // Điều chỉnh kích thước JTextField
        contentPane.add(U, gbc);

        // Row 2 - Password Label and PasswordField
        gbc.gridy++;
        gbc.gridx = 0;
        JLabel lblNewLabel_1_1 = new JLabel("Password");
        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_1_1.setPreferredSize(new Dimension(200, 50));
        contentPane.add(lblNewLabel_1_1, gbc);

        gbc.gridx = 1;
        P = new JPasswordField();
        P.setFont(new Font("Tahoma", Font.PLAIN, 20));
        P.setPreferredSize(new Dimension(300, 50)); // Điều chỉnh kích thước JPasswordField
        contentPane.add(P, gbc);

        PText = new JTextField();
        PText.setFont(new Font("Tahoma", Font.PLAIN, 20));
        PText.setPreferredSize(new Dimension(300, 50)); // Điều chỉnh kích thước JTextField
        PText.setVisible(false);
        contentPane.add(PText, gbc);

        gbc.gridx = 2;
        JButton togglePButton = new JButton("Show/Hide Password");
        togglePButton.setPreferredSize(new Dimension(200, 50)); // Điều chỉnh kích thước JButton
        togglePButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                togglePasswordVisibility(P, PText);
                isPVisible = !isPVisible;
            }
        });
        contentPane.add(togglePButton, gbc);

        // Row 3 - Again Password Label and PasswordField
        gbc.gridy++;
        gbc.gridx = 0;
        JLabel lblNewLabel_1_2 = new JLabel("Again Password");
        lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel_1_2.setPreferredSize(new Dimension(200, 50));
        contentPane.add(lblNewLabel_1_2, gbc);

        gbc.gridx = 1;
        PA = new JPasswordField();
        PA.setFont(new Font("Tahoma", Font.PLAIN, 20));
        PA.setPreferredSize(new Dimension(300, 50)); // Điều chỉnh kích thước JPasswordField
        contentPane.add(PA, gbc);

        PAText = new JTextField();
        PAText.setFont(new Font("Tahoma", Font.PLAIN, 20));
        PAText.setPreferredSize(new Dimension(300, 50)); // Điều chỉnh kích thước JTextField
        PAText.setVisible(false);
        contentPane.add(PAText, gbc);

        gbc.gridx = 2;
        JButton togglePAButton = new JButton("Show/Hide Password");
        togglePAButton.setPreferredSize(new Dimension(200, 50)); // Điều chỉnh kích thước JButton
        togglePAButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                togglePasswordVisibility(PA, PAText);
                isPAVisible = !isPAVisible;
            }
        });
        contentPane.add(togglePAButton, gbc);

        // Row 4 - New Account Button
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        JButton New = new JButton("New Account");
        New.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
        New.setPreferredSize(new Dimension(300, 50)); // Điều chỉnh kích thước JButton
        New.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String u = U.getText();
                String p = isPVisible ? PText.getText() : new String(P.getPassword());
                String pa = isPAVisible ? PAText.getText() : new String(PA.getPassword());

                if (u.isEmpty() || p.isEmpty() || pa.isEmpty()) {
                    JOptionPane.showMessageDialog(contentPane, "User, Password, and Again Password fields cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (!p.equals(pa)) {
                    JOptionPane.showMessageDialog(contentPane, "Password and Again Password do not match.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    try (Connection con = Conn.getConnection();
                         PreparedStatement pstmt = con.prepareStatement("INSERT INTO NewAcc (username, pass, salt) VALUES (?, ?, ?)")) {
                        String hashedPassword = hashPassword(p);
                        pstmt.setString(1, u);
                        pstmt.setString(2, hashedPassword);
                        pstmt.setString(3, ""); // Bạn có thể thay đổi để lưu trữ salt nếu cần
                        pstmt.executeUpdate();
                        JOptionPane.showMessageDialog(contentPane, "Account created successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                        new Login();
                        dispose();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(contentPane, "Error creating account: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    } catch (NoSuchAlgorithmException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(contentPane, "Error hashing password: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        contentPane.add(New, gbc);

        setVisible(true);
    }

    private void togglePasswordVisibility(JPasswordField passwordField, JTextField passwordTextField) {
        if (passwordField.isVisible()) {
            passwordTextField.setText(new String(passwordField.getPassword()));
            passwordTextField.setVisible(true);
            passwordField.setVisible(false);
        } else {
            passwordField.setText(passwordTextField.getText());
            passwordField.setVisible(true);
            passwordTextField.setVisible(false);
        }
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

    private String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hash = md.digest(password.getBytes());
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            hexString.append(String.format("%02x", b));
        }
        return hexString.toString();
    }
}
