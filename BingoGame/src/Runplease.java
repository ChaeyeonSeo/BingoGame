import javax.swing.JFrame;

public class Runplease {
	public static void main(String[] args) {
        BingoGame game = new BingoGame();
        
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.setVisible(true);
    }

}
