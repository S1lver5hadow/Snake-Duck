import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class EndMenu {
	
	// The xcoords, ycoords and dimensions of each button is defined here
	// Buttons used to return to the menu or press the play button to go back to the difficulty screen
	public static Rectangle backToMenu = new Rectangle(10, 750, 512, 100);
	public static Rectangle playAgain = new Rectangle(530, 750, 465, 100);
	
	// Buttons used to input and then submit a name to add to the score board
	public static Rectangle submitName = new Rectangle(740, 400, 250, 100);
	public static Rectangle nameTxt = new Rectangle(10, 400, 700, 100);
	
	// Used to create all aspects of the menu on the screen
	public static void createMenu(Graphics g) {
		
		// Implements graphics
		Graphics2D g2d = (Graphics2D) g;
		
		// Used to set and add a background image 
		ImageIcon iiBackground = new ImageIcon("src/Images/Background.jpg");
		Image background = iiBackground.getImage();
		g.drawImage(background, 0, 0, null);
		
		// Creates the buttons
		// Back and play buttons
		g.setColor(Color.green);
		g2d.draw(backToMenu);
		g2d.draw(playAgain);
		
		// Text input and the submit button
		g2d.draw(submitName);
		g2d.draw(nameTxt);
		
		// Creates the button text and any extra text
		// Creates the menu and play button text
		Font btnFont = new Font("arial", Font.BOLD, 70);
		g.setFont(btnFont);
		g.setColor(Color.blue);
		g.drawString("Return to Menu", backToMenu.x, backToMenu.y + 70);
		g.drawString("Play again", playAgain.x + 50, playAgain.y + 70);
		
		// Creates a label so the user knows to enter text below
		g.drawString("Enter name to save score", 100, 370);
		
		// Draws the text that the user has entered onto the screen into the nameTxt box
		g.drawString(GamePanel.currentName, nameTxt.x, nameTxt.y + 70);
		
		// Submit button text
		g.drawString("Submit", submitName.x, submitName.y + 70);
		
		// Used to determine if a name has already been stored and displays the appropriate text
		if (GamePanel.nameStored == true) {
			g.setColor(Color.magenta);
			g.drawString("Score has been saved!", 10, nameTxt.y + 170);
			// Shows if a high score has been achieved on the specified level size
			switch (GamePanel.unitSize) {
				// Large
				case 21:
					if (scoreCheck(GamePanel.scoresBig, GamePanel.fruitEaten) == true) {
						g.drawString("New high score saved!", 10, nameTxt.y + 240);
					}
					break;
				// Medium
				case 42:
					if (scoreCheck(GamePanel.scoresMed, GamePanel.fruitEaten) == true) {
						g.drawString("New high score saved!", 10, nameTxt.y + 240);
					}
					break;
				// Small
				case 84:
					if (scoreCheck(GamePanel.scoresSmall, GamePanel.fruitEaten) == true) {
						g.drawString("New high score saved!", 10, nameTxt.y + 240);
					}
					break;
			}
		}
		
		// Text that shows the game is over and what score the user has achieved
		g.setColor(Color.red);
		g.setFont(new Font("Oxygen", Font.BOLD, 75));
		g.drawString("Game Over", 330, 150);
		g.drawString("Score: " + GamePanel.fruitEaten, 330, 150 + g.getFont().getSize());

	}
	
	// Function used to determine if the current score is a new highscore or not
	public static boolean scoreCheck(ArrayList<String> List, int Score) {
		// Takes the first value in the array list as a string
		String stringCompared = List.get(1);
		
		// Extracts the score value from that string value
		int numCompared = Integer.parseInt(stringCompared.substring(stringCompared.lastIndexOf(" ") + 1));
		
		// Compares the score achieved with the score value from the string
		if (Score > numCompared) {
			return true;
		}
		else {
			return false;
		}
	}
}
