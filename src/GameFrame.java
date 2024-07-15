import javax.swing.JFrame;


// The frame is the window on which the game is run on
public class GameFrame extends JFrame{
	GameFrame() {
		// Instantiates a new GamePanel
		this.add(new GamePanel());
		
		// Details about the Frame itself 
		// Sets the title of the frame
		this.setTitle("Duck");
		
		// Allows the frame to be closed when the exit button is clicked
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Prevents the frame from being resized
		this.setResizable(false);
		
		// Allows the frame to be visible to the user
		this.pack();
		this.setVisible(true);
		
		// The frame is opened in the middle of the screen or wherever the user last left it
		this.setLocationRelativeTo(null);
	}
}
