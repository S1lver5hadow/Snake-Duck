import javax.swing.ImageIcon;

public class Apple extends Fruit{
	
	// Apple constructor method
	Apple() {
		// How much score is earned after eating the apple
		setScoreValue(100);
		
		// Used to set the correct image depending on what level size the user is playing on
		switch (GamePanel.unitSize) {
		
			// Large level size
			case(21):
				setIiSprite(new ImageIcon ("src/Images/21x21/Apple21x21.png"));
				break;
			
			// Medium level size
			case (42):
				setIiSprite(new ImageIcon ("src/Images/42x42/Apple42x42.png"));
				break;
				
			
			// Small level size	
			case(84):
				setIiSprite(new ImageIcon ("src/Images/84x84/Apple84x84.png"));
				break;
		}
		
		// Attaches the relevant image to the apple
		setSprite(getIiSprite().getImage());
	}
}
