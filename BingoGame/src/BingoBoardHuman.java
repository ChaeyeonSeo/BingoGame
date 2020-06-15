import javax.swing.ImageIcon;
import javax.swing.JComponent;
import java.awt.RenderingHints;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Font;
import java.util.ArrayList;
import java.awt.Color;

public class BingoBoardHuman extends BingoBoard{

	public BingoBoardHuman() {
        super();        

        indentX = 50;
        indentY = 250;        
        initializeBoard();
    }
	
	 public void highlightSquare(int x, int y) {
	        for (int row = 0; row < WIDTH; row++) {
	            for (int col = 0; col < LENGTH; col++) {
	                BingoSquare square = board[row][col];
	                if (square.contains(x, y)) {
	                    if (square.getIsClicked()) {
	                        square.setIsClicked(false);
	                    } else {
	                        square.setIsClicked(true);
	                    }
	                    break;
	                }
	            }
	        }
	        repaint();
	    }

	    public void paintComponent(Graphics g) {        
	        Graphics2D g2 = (Graphics2D) g;

	        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

	        int fontSize = 25; 
	        Font font = new Font("SansSerif", Font.PLAIN, fontSize);

	        int letterFont = 48;
	        Font font2 = new Font("SansSerif", Font.BOLD, letterFont);

	        g2.setFont(font);

	        for (int row = 0; row < board.length; row++) {
	            for (int col = 0; col < board.length; col++) {
	                BingoSquare square = board[row][col];

	                g2.draw(square);

	                if (square.getIsClicked() ) {
	                    g2.setColor(Color.YELLOW);
	                    g2.fill(square);                    
	                    g2.setColor(Color.BLACK);
	                    g2.draw(square);
	                }

	                 int value = board[row][col].getValue();
	                 int xCoord = (int)square.getX() + (SQUARE_SIZE / 4);
	                 int yCoord = (int)square.getY() + (SQUARE_SIZE / 2) + (SQUARE_SIZE / 8);
	                 if (value < 10)
	                     g2.drawString(" " + value + "", xCoord, yCoord);
	                 else
	                     g2.drawString(value + "", xCoord, yCoord);

	                if (row == 0) {
	                    g2.setFont(font2);
	                    g2.drawString(BINGO[col], (int)square.getX() + (SQUARE_SIZE / 6), (int)square.getY() - (SQUARE_SIZE / 4) );
	                    g2.setFont(font);
	                    
	                    
	                }

	            }
	            
	        }
	        
	        ImageIcon icon=new ImageIcon("src/images.jpg");
                Image img=icon.getImage();
                g2.drawImage(img,50,5,this);

	        g2.setColor(Color.RED);
	        g2.drawString(winnerMessage, 50, 190);
	    }
}
