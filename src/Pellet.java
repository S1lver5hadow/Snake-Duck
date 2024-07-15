import javax.swing.ImageIcon;

public class Pellet extends Fruit{
	
	// Constructor method
	Pellet() {
		
		// How much score is earned after eating the pellet
		setScoreValue(50);
		
		// Used to set the correct image for the right level size
		switch (GamePanel.unitSize) {
		
			// Large level
			case(21):
				setIiSprite(new ImageIcon ("src/Images/21x21/Pellet21x21.png"));
				break;
				
			// Medium level
			case (42):
				setIiSprite(new ImageIcon ("src/Images/42x42/Pellet42x42.png"));
				break;
				
			
			// Small level	
			case(84):
				setIiSprite(new ImageIcon ("src/Images/84x84/Pellet84x84.png"));
				break;
		}
		
		// Attaches the relevant image 
		setSprite(getIiSprite().getImage());
	}
}
