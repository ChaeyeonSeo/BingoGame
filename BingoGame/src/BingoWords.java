import java.util.Random;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class BingoWords extends JComponent
{
    public static ArrayList <String> words = new ArrayList<String>();

    public boolean isFound(String value)
    {
        for (String x : words) {
            if (value == (String) x)
                return true;
        }
        return false;
    }

    public void generateNumber() {
        
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        
        int fontSize = 45;
        int bigFont = 80;
        Font font = new Font("SansSerif", Font.PLAIN, fontSize);
        g2.setFont(font);

        String call = "Calling: ";
        g2.drawString(call, BingoGame.WIDTH / 2 - 85, 70);
        if (!numbers.isEmpty()) {
            int number = numbers.get(numbers.size() - 1);
            int xCoord = BingoGame.WIDTH / 2 - 100;
            int yCoord = 145;
            g2.setFont(new Font("SansSerif", Font.BOLD, bigFont));
            if (number <= 15) {
                g2.drawString("B-" + number, xCoord, yCoord);
            } else if (number <= 30) {
                g2.drawString(" I-" + number, xCoord, yCoord);
            } else if (number <= 45) {
                g2.drawString("N-" + number, xCoord, yCoord);
            } else if (number <= 60) {
                g2.drawString("G-" + number, xCoord, yCoord);
            } else if (number <= 75) {
                g2.drawString("O-" + number, xCoord, yCoord);
            }
        }
    }
}
