import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class DifficultyMenu {
	
	// Constants that define button width and heights
	private final static int buttonWidth = 200;
	private final static int buttonHeight = 100;
	
	// The xcoords, ycoords and dimensions of each button is defined here
	// Buttons for each of the speed settings
	public static Rectangle speedSlow = new Rectangle(250, 300, buttonWidth, buttonHeight);
	public static Rectangle speedMed = new Rectangle(500, 300, buttonWidth, buttonHeight);
	public static Rectangle speedFast = new Rectangle(750, 300, buttonWidth, buttonHeight);
	
	// Buttons for each of the level size settings
	public static Rectangle sizeSmall = new Rectangle(250, 450, buttonWidth, buttonHeight);
	public static Rectangle sizeMed = new Rectangle(500, 450, buttonWidth, buttonHeight);
	public static Rectangle sizeBig = new Rectangle(750, 450, buttonWidth, buttonHeight);
	
	// Button used to return to the main menu and a play button to continue to the game
	public static Rectangle back = new Rectangle(225, 750, buttonWidth, buttonHeight);
	public static Rectangle play = new Rectangle(625, 750, buttonWidth, buttonHeight);
	
	// Method that is used to create every part of the menu 
	public static void createMenu(Graphics g) {
		
		// Used to implement graphics
		Graphics2D g2d = (Graphics2D) g;
		
		// Used to set and add a background image 
		ImageIcon iiBackground = new ImageIcon("src/Images/Background.jpg");
		Image background = iiBackground.getImage();
		g.drawImage(background, 0, 0, null);
		
		// Creates the buttons
		// Creates the speed buttons
		g.setColor(Color.green);
		g2d.draw(speedSlow);
		g2d.draw(speedMed);
		g2d.draw(speedFast);
		
		// Creates the level size buttons
		g2d.draw(sizeSmall);
		g2d.draw(sizeMed);
		g2d.draw(sizeBig);
		
		// Creates the back and play buttons
		g2d.draw(back);
		g2d.draw(play);
		
		// Creates the button text
		// This creates the slow and fast speed setting text
		Font btnFont = new Font("arial", Font.BOLD, 70);
		g.setFont(btnFont);
		g.setColor(Color.blue);
		g.drawString("Slow", speedSlow.x + 20, speedSlow.y + 70);
		g.drawString("Fast", speedFast.x + 20, speedFast.y + 70);
		
		// Creates the small and big level size button text
		g.drawString("Small", sizeSmall.x + 5, sizeSmall.y + 70);
		g.drawString("Big", sizeBig.x + 35, sizeBig.y + 70);
		
		// Creates the back and play button text
		g.drawString("Back", back.x + 20, back.y + 70);
		g.drawString("Play", play.x + 20, play.y + 70);
		
		// Due to not having enough space on the screen at the previous font size, the medium button text for both speed and level had to be done using a smaller font size
		g.setFont(new Font("arial", Font.BOLD, 53));
		g.drawString("Medium", speedMed.x, speedMed.y + 70);
		g.drawString("Medium", sizeMed.x, sizeMed.y + 70);
		
		// Creates extra text that labels what each button does
		// Game speed label
		g.setFont(new Font("arial", Font.ITALIC, 40));
		g.setColor(Color.yellow);
		g.drawString("Choose the", 10, 340);
		g.drawString("game speed", 10, 390);
		
		// Level size label
		g.drawString("Choose the", 10, 490);
		g.drawString("level size", 10, 540);
		
		// Shows the user what their current speed selected is by drawing a red rectangle around it
		g.setColor(Color.red);
		int currentSpeedX = 0;
		switch (GamePanel.delay) {
		
			// Fast
			case 50:
				currentSpeedX = 735;
				break;
			
			// Medium
			case 75:
				currentSpeedX = 485;
				break;
			
			// Slow
			case 150:
				currentSpeedX = 235;
				break;
		}
		g2d.drawRect(currentSpeedX, 285, buttonWidth+30, buttonHeight+30);
		
		// Shows the user what their current level size selected is by drawing a red rectangle around it
		g.setColor(Color.red);
		int currentSizeX = 0;
		switch (GamePanel.unitSize) {
			// Small
			case 84:
				currentSizeX = 235;
				break;
			
			// Medium
			case 42:
				currentSizeX = 485;
				break;
			
			// Large
			case 21:
				currentSizeX = 735;
				break;
		}
		g2d.drawRect(currentSizeX, 435, buttonWidth+30, buttonHeight+30);
		
	}
}
