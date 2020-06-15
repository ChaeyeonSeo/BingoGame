import java.awt.Rectangle;
import java.util.Random;

public class BingoSquare extends Rectangle{
	private int value;
	private boolean status;
	private boolean isClicked;
	private boolean isWinner;
	private static final int RANGE = 15;
	private Random generator = new Random();
	
	public BingoSquare(int x1, int y1, int width, int height) {
        super(x1, y1, width, height);
        status = false;
        isClicked = false;
        isWinner=false;
	}
	
	public void setValue(int newValue) {
        value = newValue;
    }
	
	public int getValue() {
        return value;
    }
	
	public void setStatus(boolean newStatus) {
		status = newStatus;
	}
	
	public boolean getStatus() {
		return status;
	}
	
	public void setIsClicked(boolean newIsClicked) {
        isClicked = newIsClicked;
    }

    public boolean getIsClicked() {
        return isClicked;
    }
    
    public void setIsWinner(boolean newIsWinner) {
        isWinner = newIsWinner;
    }

    public boolean getIsWinner() {
        return isWinner;
    }
    
    public int createNum(int col) {
        int temp = RANGE * (col - 1) + (generator.nextInt(15) + 1);
        value = temp;
        return temp;
    }
}
