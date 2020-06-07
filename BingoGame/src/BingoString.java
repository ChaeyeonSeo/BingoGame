import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class BingoString extends BingoGridHuman{
	 
	 private static final int N = 25;
	 
	 private void display() {
         JFrame f = new JFrame("GridTest");
         f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         JPanel panel = new JPanel(new GridLayout(5, 5));
         for (int i = 1; i < N+1; i++) {
             panel.add(createPanel(i));
         }
         f.add(new JScrollPane(panel));
         f.pack();
         f.setLocationRelativeTo(null);
         f.setVisible(true);
     }
	 
	 private static JPanel createPanel(int i) {
         JPanel panel = new JPanel(new GridLayout(4, 4, 6, 6));
         panel.setBorder(BorderFactory.createLineBorder(Color.black));
         panel.add(new JLabel(String.valueOf(i)));
         panel.add(new JLabel());
         JLabel label=new JLabel("ют╥б");
         panel.add(label);
         JTextField textfield = new JTextField();
         panel.add(textfield,BorderLayout.CENTER);
         JButton button=new JButton("OK");
         button.addActionListener(new ActionListener(){
        	 
        	 public void actionPerformed(ActionEvent e) {
        		 words.add(textfield.getText());
        	 }
         });
         panel.add(button, BorderLayout.SOUTH);
         return panel;
     }
	 
	 
	 public static void main(String[] args) {
         EventQueue.invokeLater(new BingoString()::display);
     }

}
