import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.io.FileWriter;
import java.util.Random;

public class Main {
    public static JFrame frame = new JFrame("Create and Write To File");
    public static JPanel panel = new JPanel(null);
    public static JButton start = new JButton("New Roll");
    public static JLabel prompt = new JLabel("Roll die to generate a number 1-6");
    public static GridBagConstraints gbc = new GridBagConstraints();
    public static String userFile = "history.txt";

    public static void main(String[] args) {
        GUI();
    }

    public static void GUI() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(400, 200);
        frame.setContentPane(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        panel.setSize(400, 200);
        panel.setLayout(new GridBagLayout());

        gbc.insets = new Insets(5, 0, 5, 0);
        gbc.gridx = 1;

        panel.add(prompt, gbc);
        panel.add(start, gbc);

        start.addActionListener(e -> rollDie());

        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(WindowEvent winEvt) {
                clearHistory();
            }
        });
    }

    public static void clearHistory() {
        try (FileWriter outFS = new FileWriter(userFile, false)) {
            outFS.write("");
        } catch (Exception e) {
            System.out.println("Error, please try again! " + e.getMessage());
        }
    }

    public static void rollDie() {
        Random roll = new Random();
        int myRoll = roll.nextInt(6);

        try (FileWriter outFS = new FileWriter(userFile, true)) {
            outFS.write(String.valueOf(myRoll + 1));
            outFS.write('\n');
        } catch (Exception e) {
            System.out.println("Error, please try again! " + e.getMessage());
        }

        prompt.setText("Last roll: " + (myRoll + 1));
    }
}
