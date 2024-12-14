import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;

public class Authentication {

    private ArrayList<ArrayList<String>> accounts;
    private JFrame sign;

    public Authentication() {
        accounts = new ArrayList<>();
        initializeUI();
    }

    private void initializeUI() {
        sign = new JFrame();
        sign.setSize(480, 320);
        sign.setLayout(null);
        sign.setUndecorated(true);
        sign.setLocationRelativeTo(null);
        sign.setResizable(false);
        sign.setShape(new RoundRectangle2D.Double(0, 0, sign.getWidth(), sign.getHeight(), 30, 30));

        addHeader();
        addCenterPanel();

        sign.setVisible(true);
    }

    private void addHeader() {
        JPanel header = new JPanel(null);
        header.setBackground(new Color(30, 40, 50));
        header.setBounds(0, 0, 480, 30);

        JButton exit = createButton("X", new Font("MV Boli", Font.PLAIN, 13), Color.WHITE, header.getBackground());
        exit.setBounds(437, 0, 43, 30);
        exit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                exit.setBackground(new Color(255, 69, 58));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                exit.setBackground(header.getBackground());
            }
        });
        exit.addActionListener(_ -> System.exit(0));

        header.add(exit);
        sign.add(header);
    }

    private void addCenterPanel() {
        JPanel center = new JPanel(null);
        center.setBackground(new Color(53, 63, 74));
        center.setBounds(0, 30, 480, 290);

        JLabel usernameLabel = createLabel("Username", new Font("Arial", Font.PLAIN, 14), Color.WHITE);
        usernameLabel.setBounds(110, 60, 100, 20);
        center.add(usernameLabel);

        JTextField usernameField = createTextField(center.getBackground());
        usernameField.setBounds(200, 55, 200, 30);
        center.add(usernameField);

        JLabel passwordLabel = createLabel("Password", new Font("Arial", Font.PLAIN, 14), Color.WHITE);
        passwordLabel.setBounds(110, 110, 100, 20);
        center.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setForeground(new Color(200, 200, 200));
        passwordField.setBackground(center.getBackground());
        passwordField.setHorizontalAlignment(JTextField.CENTER);
        passwordField.setBounds(200, 105, 200, 30);
        center.add(passwordField);

        addLoginButton(usernameField, passwordField, center);
        addSignupButton(usernameField, passwordField, center);

        sign.add(center);
    }

    private void addLoginButton(JTextField usernameField, JPasswordField passwordField, JPanel center) {
        JButton loginButton = createButton("Login", new Font("Arial", Font.PLAIN, 14), Color.WHITE,
                new Color(52, 152, 219));
        loginButton.setBounds(160, 170, 80, 35);
        loginButton.addActionListener(_ -> handleLogin(usernameField, passwordField));

        center.add(loginButton);
    }

    private void addSignupButton(JTextField usernameField, JPasswordField passwordField, JPanel center) {
        JButton signupButton = createButton("Sign Up", new Font("Arial", Font.PLAIN, 14), Color.WHITE,
                new Color(39, 174, 96));
        signupButton.setBounds(260, 170, 100, 35);
        signupButton.addActionListener(_ -> handleSignup(usernameField, passwordField));

        center.add(signupButton);
    }

    private JButton createButton(String text, Font font, Color textColor, Color backgroundColor) {
        JButton button = new JButton(text);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setFont(font);
        button.setOpaque(true);
        button.setForeground(textColor);
        button.setBackground(backgroundColor);
        return button;
    }

    private JLabel createLabel(String text, Font font, Color textColor) {
        JLabel label = new JLabel(text);
        label.setForeground(textColor);
        label.setFont(font);
        return label;
    }

    private JTextField createTextField(Color background) {
        JTextField textField = new JTextField();
        textField.setForeground(new Color(200, 200, 200));
        textField.setBackground(background);
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        return textField;
    }

    private void handleLogin(JTextField usernameField, JPasswordField passwordField) {
        if (usernameField.getText().isEmpty() || passwordField.getPassword().length == 0) {
            JOptionPane.showMessageDialog(sign, "Username and password cannot be empty!", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (accounts.isEmpty()) {
            JOptionPane.showMessageDialog(sign, "No accounts available! Please sign up first.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean found = false;
        for (ArrayList<String> account : accounts) {
            if (usernameField.getText().equals(account.get(0))
                    && new String(passwordField.getPassword()).equals(account.get(1))) {
                sign.setVisible(false);
                found = true;
                break;
            }
        }

        if (!found) {
            JOptionPane.showMessageDialog(sign, "Invalid username or password!", "Error", JOptionPane.ERROR_MESSAGE);
        }

        usernameField.setText(null);
        passwordField.setText(null);
    }

    private void handleSignup(JTextField usernameField, JPasswordField passwordField) {
        String newUsername = usernameField.getText();
        char[] newPassword = passwordField.getPassword();
        usernameField.setText(null);
        passwordField.setText(null);

        if (newUsername.isEmpty() || newPassword.length == 0) {
            JOptionPane.showMessageDialog(sign, "Username and password cannot be empty!", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        for (ArrayList<String> account : accounts) {
            if (account.get(0).equals(newUsername)) {
                JOptionPane.showMessageDialog(sign, "Username already exists!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        accounts.add(new ArrayList<>());
        accounts.get(accounts.size() - 1).add(newUsername);
        accounts.get(accounts.size() - 1).add(new String(newPassword));
        JOptionPane.showMessageDialog(sign, "Account Created Successfully!", "Success",
                JOptionPane.INFORMATION_MESSAGE);
    }
}