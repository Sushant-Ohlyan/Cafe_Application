import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class Mang extends JFrame {
    JLabel title, emp, ord;
    JButton addnew, delete;
    JTextArea area1, area2;

    // Constructor to initialize the Manager frame
    public Mang() {
        setTitle("Restaurant and Coffee");
        setSize(900, 600);
        setLayout(null);

        title = new JLabel("Manager Window");
        title.setBounds(20, 50, 200, 30);

        emp = new JLabel("Employee Details");
        emp.setBounds(20, 175, 200, 30);

        ord = new JLabel("Latest Orders");
        ord.setBounds(350, 50, 200, 30);

        area1 = new JTextArea();
        area1.setBounds(20, 200, 300, 200);

        area2 = new JTextArea();
        area2.setBounds(350, 100, 500, 420);

        addnew = new JButton("Addition");
        addnew.setBounds(20, 120, 100, 40);

        delete = new JButton("Delete");
        delete.setBounds(150, 120, 100, 40);

        add(title);
        add(emp);
        add(ord);
        add(area1);
        add(area2);
        add(delete);
        add(addnew);

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        data_conn();
        data_conn1();
    }

    // Method to fetch and display employee details
    public void data_conn() {
        String url = "jdbc:mysql://localhost:3306/acc_app";
        String user = "root";
        String pass = "9654558088";

        area1.setText("Employee_id\tEmployee Name\tEmployee Number\n");

        try (Connection connection = DriverManager.getConnection(url, user, pass);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM employee ORDER BY emp_id")) {

            while (resultSet.next()) {
                int emp_id = resultSet.getInt("emp_id");
                String emp_name = resultSet.getString("emp_name").trim();
                int emp_number = resultSet.getInt("emp_number");
                area1.append(emp_id + "\t" + emp_name + "\t" + emp_number + "\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to fetch and display latest orders
    public void data_conn1() {
        String url = "jdbc:mysql://localhost:3306/acc_app";
        String user = "root";
        String pass = "9654558088";

        area2.setText("Customer_ID\tCustomer_Name\tContact_Number\tFood\tDrink\tPaid:\n");

        try (Connection connection = DriverManager.getConnection(url, user, pass);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM customer ORDER BY cust_id DESC")) {

            while (resultSet.next()) {
                int cust_id = resultSet.getInt("cust_id");
                String cust_name = resultSet.getString("cust_name").trim();
                int cust_phno = resultSet.getInt("cust_phno");
                String food = resultSet.getString("food").trim();
                String drink = resultSet.getString("drink").trim();
                int t_price = resultSet.getInt("t_price");
                area2.append(cust_id + "\t" + cust_name + "\t" + cust_phno + "\t" + food + "\t" + drink + "\t" + t_price + "\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Mang managerWindow = new Mang();
            managerWindow.setVisible(true);
        });
    }
}
