import javax.swing.JComponent;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Font;
import java.util.ArrayList;
import java.awt.Color;

public class BingoBoard {
	protected BingoSquare[][] board;
    protected ArrayList<String> bingoBoardWords;
    protected final int SQUARE_SIZE = 60;
    protected final int LENGTH = 5;
    protected final int WIDTH = 5;
    public int indentX;
    public int indentY;
    protected String winnerMessage;
    protected boolean isWinner;

    /**
     * BingoGrid constructor.
     */
    public BingoBoard() {
        board = new BingoSquare[WIDTH][LENGTH];
        bingoBoardWords = new ArrayList<String>();
        winnerMessage = "";
        isWinner = false;
    }
    
    
    /**
     * Checks if the value created is already in the array
     * @param value the number to check
     * @return true if the value is in the array
     */
    public boolean isFound(String value, int r , int c)
    {
        for (int row = 0; row < r; row++) {
            for (int col = 0; col <= c; col++) {
                if (value.equals(board[row][col].getValue())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Creates the BingoSquare objects onto the grid and assigns them a value 
     */
    public void initializeBoard() {

    }

    /**
     * Checks if the grid has won according to the rules of Bingo
     * return true if grid has won
     */
    public boolean checkWin() {
        int winningNumber = 5;
        int count;
        int bingoCount = 0;

        //checks the rows
        for (int row = 0; row < board.length; row++) {
            count = 0;
            for (int col = 0; col < board[0].length; col++) {
                if (board[row][col].getStatus()) {
                    count++;                
                }
                if (count == winningNumber) {
                    bingoCount++;             
                }
            }
            this.removeIsWinnerMark();
        }
        
        //checks the columns
        for (int col = 0; col < board[0].length; col++) {
            count = 0;
            for (int row = 0; row < board.length; row++) {
                if (board[row][col].getStatus()) {
                	board[row][col].setIsWinner(true);
                    count++;                
                }
                if (count == winningNumber) {
                	bingoCount++;            
                }
            }
            this.removeIsWinnerMark();
        }
        
        //check diagonal from top-left to bottom-right
        count = 0;
        for (int index = 0; index < board.length; index++) {
            BingoSquare square = board[index][index];
            if (square.getStatus()) {
                square.setIsWinner(true);
                count++;            
            }
            if (count == winningNumber) {
            	bingoCount++;
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
            	bingoCount++;
                }
        }
        this.removeIsWinnerMark();
        if(bingoCount == winningNumber) {
        	return true;
        }
        else return false;
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
