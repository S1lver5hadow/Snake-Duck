import javax.swing.ImageIcon;

public class Bread extends Fruit {
	
	// Constructor method
	Bread() {
		// How much score is earned after eating the bread
		setScoreValue(200);
		
		// Used to set the correct image depending on what level size the user is playing on
		switch (GamePanel.unitSize) {
		
			// Large level
			case(21):
				setIiSprite(new ImageIcon ("src/Images/21x21/Bread21x21.png"));
				break;
				
			// Medium level
			case (42):
				setIiSprite(new ImageIcon ("src/Images/42x42/Bread42x42.png"));
				break;
				
			// Small level
			case(84):
				setIiSprite(new ImageIcon ("src/Images/84x84/Bread84x84.png"));
				break;
		}
		
		// Attaches the relevant image
		setSprite(getIiSprite().getImage());
	}
	
}
