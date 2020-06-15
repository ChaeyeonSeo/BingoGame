import javax.swing.JComponent;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Font;
import java.util.ArrayList;
import java.awt.Color;

public class BingoBoard extends JComponent{
	protected BingoSquare[][] board;
    protected ArrayList<Integer> bingoBoardWords;
    public static final String[] BINGO = {"B", " I", "N", "G", "O"};
    protected final int SQUARE_SIZE = 60;
    protected final int LENGTH = 5;
    protected final int WIDTH = 5;
    public int indentX;
    public int indentY;
    protected String winnerMessage;
    
    public BingoBoard() {
        board = new BingoSquare[WIDTH][LENGTH];
        bingoBoardWords = new ArrayList<Integer>();
        winnerMessage = "";
    }
    
    public boolean isFound(int value, int r , int c)
    {
        for (int row = 0; row < r; row++) {
            for (int col = 0; col <= c; col++) {
                if (value==board[row][col].getValue()) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public void initializeBoard() {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                board[row][col] = new BingoSquare(col * SQUARE_SIZE + indentX, row * SQUARE_SIZE + indentY, SQUARE_SIZE, SQUARE_SIZE);
                int value = board[row][col].createNum(col + 1);
                while (isFound(value,row,col))
                    value = board[row][col].createNum(col + 1);
            }
        }
    }
    
    public boolean checkWin() {
    	int realwinner=0;
        int winningNumber = 5;
        int count;

        for (int row = 0; row < board.length; row++) {
            count = 0;
            for (int col = 0; col < board[0].length; col++) {
                if (board[row][col].getStatus()) {
                    board[row][col].setIsWinner(true);
                    count++;                
                }
                if (count == winningNumber) {
                	realwinner++;
                }
                if(count==winningNumber&&realwinner==5) {
                	return true;
                }
            }
            this.removeIsWinnerMark();
        }

        for (int col = 0; col < board[0].length; col++) {
            count = 0;
            for (int row = 0; row < board.length; row++) {
                if (board[row][col].getStatus()) {
                    board[row][col].setIsWinner(true);
                    count++;                
                }
                if (count == winningNumber) {
                	realwinner++;
                }
                if(count==winningNumber&&realwinner==5) {
                	return true;
                }
            }
            this.removeIsWinnerMark();
        }

        count = 0;
        for (int index = 0; index < board.length; index++) {
            BingoSquare square = board[index][index];
            if (square.getStatus()) {
                square.setIsWinner(true);
                count++;            
            }
            if (count == winningNumber) {
                realwinner++;
            }
            if(count==winningNumber&&realwinner==5) {
            	return true;
            }
        }        
        this.removeIsWinnerMark();

        //check diagonal from bottom-right to top-left
        count = 0;
        for (int index = board.length - 1; index >= 0; index--) {
            BingoSquare square = board[index][(board.length - 1) - index];
            if (square.getStatus()) {
                square.setIsWinner(true);
                count++;
            }
            if (count == winningNumber) {
                realwinner++;
            }
            if(count==winningNumber&&realwinner==5) {
            	return true;
            }
        }
        this.removeIsWinnerMark();
        return false;
    }
    
    public void removeIsWinnerMark() {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                board[row][col].setIsWinner(false);
            }
        }
    }

    public void setWinnerMessage(String newMsg) {
        winnerMessage = newMsg;
    }
   
}
