package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class mini extends JFrame implements ActionListener {
    String pin;
    JButton button;

    public mini(String pin) {
        this.pin = pin;
        getContentPane().setBackground(new Color(255, 204, 204));
        setSize(400, 600);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel label1 = new JLabel();
        label1.setBounds(20, 140, 360, 240);
        add(label1);

        JLabel label2 = new JLabel("Bank Statement");
        label2.setFont(new Font("System", Font.BOLD, 16));
        label2.setBounds(130, 20, 200, 20);
        add(label2);

        JLabel label3 = new JLabel();
        label3.setBounds(20, 80, 350, 20);
        add(label3);

        JLabel label4 = new JLabel();
        label4.setBounds(20, 400, 350, 20);
        add(label4);

        try {
            Connn c = new Connn();
            String q1 = "SELECT * FROM login WHERE pin = ?";
            PreparedStatement pstmt1 = c.prepareStatement(q1);
            pstmt1.setString(1, pin);
            ResultSet resultSet1 = pstmt1.executeQuery();

            while (resultSet1.next()) {
                String card = resultSet1.getString("card_number");
                if (card != null && card.length() >= 16) {
                    label3.setText("Card Number:  " + card.substring(0, 4) + "XXXXXXXX" + card.substring(12));
                } else {
                    label3.setText("Card Number:  " + card);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            int balance = 0;
            Connn c = new Connn();
            String q2 = "SELECT * FROM bank WHERE pin = ?";
            PreparedStatement pstmt2 = c.prepareStatement(q2);
            pstmt2.setString(1, pin);
            ResultSet resultSet2 = pstmt2.executeQuery();

            StringBuilder htmlContent = new StringBuilder("<html>");
            while (resultSet2.next()) {
                String dateStr = resultSet2.getString("date");
                String type = resultSet2.getString("type");
                String amtStr = resultSet2.getString("amount");
                int amount = Integer.parseInt(amtStr);

                htmlContent.append(dateStr).append("&nbsp;&nbsp;&nbsp;")
                           .append(type).append("&nbsp;&nbsp;&nbsp;Rs. ")
                           .append(amtStr).append("<br>");

                if ("Deposit".equalsIgnoreCase(type)) {
                    balance += amount;
                } else {
                    balance -= amount;
                }
            }
            htmlContent.append("</html>");
            label1.setText(htmlContent.toString());
            label4.setText("Your Total Balance is Rs " + balance);
        } catch (Exception e) {
            e.printStackTrace();
        }

        button = new JButton("Exit");
        button.setBounds(20, 500, 100, 25);
        button.addActionListener(this);
        button.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE);
        add(button);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setVisible(false);
    }

    public static void main(String[] args) {
        new mini("");
    }
}
