package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

public class FastCash extends JFrame implements ActionListener {

    JButton b1, b2, b3, b4, b5, b6, b7;
    String pin;

    public FastCash(String pin) {
        this.pin = pin;

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/atm2.png"));
        Image i2 = i1.getImage().getScaledInstance(1550, 830, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l3 = new JLabel(i3);
        l3.setBounds(0, 0, 1550, 830);
        add(l3);

        JLabel label = new JLabel("SELECT WITHDRAWAL AMOUNT");
        label.setBounds(445, 180, 700, 35);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("System", Font.BOLD, 23));
        l3.add(label);

        b1 = new JButton("Rs. 100");
        b1.setForeground(Color.WHITE);
        b1.setBackground(new Color(65, 125, 128));
        b1.setBounds(410, 274, 150, 35);
        b1.addActionListener(this);
        l3.add(b1);

        b2 = new JButton("Rs. 500");
        b2.setForeground(Color.WHITE);
        b2.setBackground(new Color(65, 125, 128));
        b2.setBounds(700, 274, 150, 35);
        b2.addActionListener(this);
        l3.add(b2);

        b3 = new JButton("Rs. 1000");
        b3.setForeground(Color.WHITE);
        b3.setBackground(new Color(65, 125, 128));
        b3.setBounds(410, 318, 150, 35);
        b3.addActionListener(this);
        l3.add(b3);

        b4 = new JButton("Rs. 2000");
        b4.setForeground(Color.WHITE);
        b4.setBackground(new Color(65, 125, 128));
        b4.setBounds(700, 318, 150, 35);
        b4.addActionListener(this);
        l3.add(b4);

        b5 = new JButton("Rs. 5000");
        b5.setForeground(Color.WHITE);
        b5.setBackground(new Color(65, 125, 128));
        b5.setBounds(410, 362, 150, 35);
        b5.addActionListener(this);
        l3.add(b5);

        b6 = new JButton("Rs. 10000");
        b6.setForeground(Color.WHITE);
        b6.setBackground(new Color(65, 125, 128));
        b6.setBounds(700, 362, 150, 35);
        b6.addActionListener(this);
        l3.add(b6);

        b7 = new JButton("BACK");
        b7.setForeground(Color.WHITE);
        b7.setBackground(new Color(65, 125, 128));
        b7.setBounds(700, 406, 150, 35);
        b7.addActionListener(this);
        l3.add(b7);

        setLayout(null);
        setSize(1550, 1080);
        setLocation(0, 0);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b7) {
            setVisible(false);
            new main_Class(pin);
        } else {
            String amountStr = ((JButton) e.getSource()).getText().replace("Rs. ", "").trim();
            int withdrawAmount = Integer.parseInt(amountStr);
            Connn c = new Connn();
            Date date = new Date();
            try {
                String selectQuery = "SELECT * FROM bank WHERE pin = ?";
                PreparedStatement selectStmt = c.prepareStatement(selectQuery);
                selectStmt.setString(1, pin);
                ResultSet resultSet = selectStmt.executeQuery();

                int balance = 0;
                while (resultSet.next()) {
                    String type = resultSet.getString("type");
                    int amt = Integer.parseInt(resultSet.getString("amount"));
                    if ("Deposit".equalsIgnoreCase(type)) {
                        balance += amt;
                    } else {
                        balance -= amt;
                    }
                }

                if (balance < withdrawAmount) {
                    JOptionPane.showMessageDialog(null, "Insufficient Balance. Current Balance: Rs. " + balance);
                    return;
                }

                String insertQuery = "INSERT INTO bank (pin, date, type, amount) VALUES (?, ?, 'Withdrawl', ?)";
                PreparedStatement insertStmt = c.prepareStatement(insertQuery);
                insertStmt.setString(1, pin);
                insertStmt.setString(2, date.toString());
                insertStmt.setString(3, amountStr);
                insertStmt.executeUpdate();

                JOptionPane.showMessageDialog(null, "Rs. " + amountStr + " Debited Successfully");
                setVisible(false);
                new main_Class(pin);

            } catch (Exception E) {
                E.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new FastCash("");
    }
}
