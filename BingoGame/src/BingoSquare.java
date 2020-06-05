import java.awt.Rectangle;
import java.util.Random;

public class BingoSquare extends Rectangle {
	private String value;
	private boolean status; //true if the value is called by the user
	private boolean isClicked; //true if the user clicked the square
	
	public BingoSquare(int x1, int y1, int width, int height) {
        super(x1, y1, width, height);
        status = false;
        isClicked = false;
	}
	
	public void setValue(String newValue) {
        value = newValue;
    }
	
	public String getValue() {
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
}
