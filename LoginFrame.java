import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
public class LoginFrame extends JFrame {
    JLabel userLabel, passwordLabel;
    JTextField userField;
    JPasswordField passwordField;
    JButton loginButton;

    // Constructor to initialize the LoginFrame
    public LoginFrame() {
        setTitle("Login");
        setSize(300, 150);
        setLayout(new GridLayout(3, 2));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        userLabel = new JLabel("Username:");
        passwordLabel = new JLabel("Password:");
        userField = new JTextField();
        passwordField = new JPasswordField();
        loginButton = new JButton("Login");

        // Adding components to the frame
        add(userLabel);
        add(userField);
        add(passwordLabel);
        add(passwordField);
        add(new JLabel()); // Empty label for spacing
        add(loginButton);

        // Adding action listener to the login button
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String username = userField.getText();
                String password = new String(passwordField.getPassword());
                if (authenticateUser(username, password)) {
                    dispose(); // Close the login frame
                    if (isManager(username)) {
                        Mang managerWindow = new Mang();
                        managerWindow.setVisible(true);
                    } else {
                        Accountant acct = new Accountant();
                        acct.setVisible(true);
                    }
                } else {
                    JOptionPane.showMessageDialog(LoginFrame.this, "Invalid credentials", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    // Method to authenticate user credentials
    private boolean authenticateUser(String username, String password) {
        String url = "jdbc:mysql://localhost:3306/acc_app";
        String user = "root";
        String pass = "9654558088";
        String query = "SELECT * FROM users WHERE emp_id = ? AND emp_number = ?";

        try (Connection connection = DriverManager.getConnection(url, user, pass);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next(); // If there is a match, the user is authenticated
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to check if the user is a manager
    private boolean isManager(String username) {
        return username.equals("manager");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginFrame loginFrame = new LoginFrame();
            loginFrame.setVisible(true);
        });
    }
}
