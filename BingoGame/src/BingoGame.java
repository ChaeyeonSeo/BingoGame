import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.JToolTip;

import java.awt.Container;

import java.awt.event.MouseListener;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.Timer;

public class BingoGame extends JFrame{
    public static final int WIDTH = 900;
    public static final int LENGTH = 700;
    
    private JPanel panel;
    private JButton reset;
    private JButton bingo;
    private JButton nextNum;
    private JButton start;
    private JButton stop;
    private Container layout;
    private Container boxLayout;
    
    private BingoNumbers bingoNumbers;
    private BingoBoard dummyGrid;
    private BingoBoardHuman userGrid;
    private ClientFrame bingoChat;
    
    private MouseListener mouseListener;
    private ActionListener timer;
    private ActionListener buttonListener;
    private final int DELAY;
    private boolean startGame=false;
    private boolean winner=false;
    
    public BingoGame() {
       setSize(WIDTH, LENGTH);
         
         panel=new JPanel();
         bingoChat = new ClientFrame();
         
         //JButtons
         reset=new JButton("Reset");
         bingo=new JButton("Bingo!");
         nextNum=new JButton("Call Next Number");
         start=new JButton("Start");
         stop=new JButton("Stop");
         
         //add JButtons to the panel
         panel.add(start);
         panel.add(stop);
         panel.add(reset);
         panel.add(bingo);
         panel.add(nextNum);
         
         //creates tooltips for buttons
         reset.setToolTipText("Reset the game");
         bingo.setToolTipText("I have bingo!");
         nextNum.setToolTipText("Call next number");
         start.setToolTipText("Starts/Resume the game");
         stop.setToolTipText("Stop/pauses the game");
         
         buttonListener = new ButtonListener();
           reset.addActionListener(buttonListener);
           bingo.addActionListener(buttonListener);
           nextNum.addActionListener(buttonListener);
           start.addActionListener(buttonListener);
           stop.addActionListener(buttonListener);
           
           layout=this.getContentPane();
           layout.add(panel,"South");
           //layout.add(bingoChat,"East");
           setVisible(true);
           
           /*ImageIcon icon=new ImageIcon("src/images.jpg");
           JLabel label=new JLabel(icon);
           getContentPane().add(label);
           setVisible(true);*/
           
           mouseListener=new MouseClickListener();
           timer=new MyTimer();
           DELAY=2500;
           Timer t=new Timer(DELAY,timer);
           t.start();
           
           userGrid=new BingoBoardHuman();
           bingoNumbers=new BingoNumbers();
           
           add(userGrid);
           setVisible(true);

           add(bingoNumbers);
           setVisible(true);
           
           userGrid.addMouseListener(mouseListener);
           setVisible(true);
                  
    }
    
    class MyTimer implements ActionListener{
       public void actionPerformed(ActionEvent event) {
          if (startGame && !winner) {//맨처음시작
             bingoNumbers.generateNumber();
             userGrid.isCalled();
             
             userGrid.setWinnerMessage("");
             bingoNumbers.repaint();
          }
       }
    }
     
     class MouseClickListener implements MouseListener{
       public void mousePressed(MouseEvent event) {
          int x=event.getX();
          int y=event.getY();
          userGrid.highlightSquare(x, y);
       }
       
       public void mouseReleased(MouseEvent event) {}
       public void mouseClicked(MouseEvent event) {}
       public void mouseEntered(MouseEvent event) {}
       public void mouseExited(MouseEvent event) {}
    }
       
    class ButtonListener implements ActionListener{
       public void actionPerformed(ActionEvent event) {
          Object source=event.getSource();
          if(source==reset) { //resets the board and the bingo number callings
             userGrid.initializeBoard();
             bingoNumbers.numbers.clear();
             winner=false;
          }
          else if(source==bingo) { //checks if the human grid has won
             if(!winner) {
                if(userGrid.checkWin()) {
                   userGrid.setWinnerMessage("WINNER: You");
                   winner=true;
                }
                else {
                   userGrid.setWinnerMessage("Sorry, you haven't gotten bingo.");
                }
                
             }
          }
          else if(source==nextNum) {//calls the next bingo number and checks for winner
             if(!winner) {
                bingoNumbers.generateNumber();
                userGrid.setWinnerMessage("");
                userGrid.isCalled();
                
             }
          }
          else if(source==start) {//starts the game
             startGame=true;
          }
          else if(source==stop) {
             startGame=false;
          }
          
          //색깔로 하이라이트
          
          userGrid.repaint();
          bingoNumbers.repaint();
          layout.repaint();
          
       }
    }
    

}