import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;

public class Accountant extends JFrame {
    JLabel customer, name, contact, food, drink, type;
    JTextField tfCustNum, tfName, tfContact;
    JButton print, receipt, reset;
    JComboBox<String> cb1, cb2;
    JRadioButton r1, r2;
    JTextArea area1;
    int total, dprice, fprice;

    // Constructor to initialize the Accountant frame
    public Accountant() {
        setTitle("Restaurant and Coffee");
        setSize(700, 500);
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Initialize labels and text fields
        customer = new JLabel("Customer ID:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(customer, gbc);

        tfCustNum = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 0;
        formPanel.add(tfCustNum, gbc);

        name = new JLabel("Customer Name:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(name, gbc);

        tfName = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 1;
        formPanel.add(tfName, gbc);

        contact = new JLabel("Contact Number:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(contact, gbc);

        tfContact = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 2;
        formPanel.add(tfContact, gbc);

        food = new JLabel("Foods:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(food, gbc);

        String[] foods = {"Pizza", "Chicken", "Burger"};
        cb1 = new JComboBox<>(foods);
        gbc.gridx = 1;
        gbc.gridy = 3;
        formPanel.add(cb1, gbc);

        drink = new JLabel("Drinks:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        formPanel.add(drink, gbc);

        String[] drinks = {"Fruit Juice", "Coca Cola", "Cold Coffee"};
        cb2 = new JComboBox<>(drinks);
        gbc.gridx = 1;
        gbc.gridy = 4;
        formPanel.add(cb2, gbc);

        type = new JLabel("Type:");
        gbc.gridx = 0;
        gbc.gridy = 5;
        formPanel.add(type, gbc);

        ButtonGroup bg = new ButtonGroup();
        r1 = new JRadioButton("Diet");
        r2 = new JRadioButton("Normal");
        bg.add(r1);
        bg.add(r2);
        gbc.gridx = 1;
        gbc.gridy = 5;
        formPanel.add(r1, gbc);
        gbc.gridx = 2;
        gbc.gridy = 5;
        formPanel.add(r2, gbc);

        // Buttons for reset, receipt, and print actions
        JPanel buttonPanel = new JPanel();
        reset = new JButton("Reset");
        receipt = new JButton("Receipt");
        print = new JButton("Print");

        buttonPanel.add(reset);
        buttonPanel.add(receipt);
        buttonPanel.add(print);

        // Text area for displaying the receipt
        area1 = new JTextArea(10, 20);
        area1.setEditable(false);

        // Reset button functionality
        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                tfCustNum.setText("");
                tfContact.setText("");
                tfName.setText("");
                area1.setText("");
                bg.clearSelection();
                cb1.setSelectedIndex(0);
                cb2.setSelectedIndex(0);
            }
        });

        // Print button functionality
        print.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                try {
                    area1.print();
                } catch (PrinterException ex) {
                    System.out.print(ex.getMessage());
                }
            }
        });

        // Receipt button functionality
        receipt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                area1.setText("Here's your bill:\n");
                area1.append("Customer ID: " + tfCustNum.getText() + "\n");
                area1.append("Customer Name: " + tfName.getText() + "\n");
                area1.append("Contact Number: " + tfContact.getText() + "\n");
                area1.append("Food: " + cb1.getSelectedItem() + "\n");
                area1.append("Drinks: " + cb2.getSelectedItem() + "\n");

                // Price calculation logic here

                area1.append("Total: " + total + "\n");
            }
        });

        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        add(new JScrollPane(area1), BorderLayout.EAST);

        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
