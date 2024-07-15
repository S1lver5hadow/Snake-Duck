import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class InstructionsMenu {
	
	// The xcoords, ycoords and dimensions of each button is defined here
	// Return to menu button
	public static Rectangle exitButton = new Rectangle(250, 800, 500, 100);
	
	// Used to create all elements of the menu
	public static void createMenu(Graphics g) {
		
		// Used for graphics
		Graphics2D g2d = (Graphics2D) g;
		
		// Used to set and add a background image 
		ImageIcon iiBackground = new ImageIcon("src/Images/Background.jpg");
		Image background = iiBackground.getImage();
		g.drawImage(background, 0, 0, null);
		
		// Creates the buttons
		// Menu button
		g.setColor(Color.green);
		g2d.draw(exitButton);
		
		// Creates the button text
		// Menu button 
		Font btnFont = new Font("arial", Font.BOLD, 70);
		g.setFont(btnFont);
		g.setColor(Color.WHITE);
		g.drawString("Back to Menu", exitButton.x + 25, exitButton.y + 70);
		
		// Creates the instruction screen text
		// This is the control scheme text
		Font txtFont = new Font(Font.SERIF, 30, 30);
		g.setFont(txtFont);
		g.setColor(Color.black);
		g.drawString("To control the duck use the arrow keys or alternatatively:", 0, 200);
		g.drawString("W - Up, A - Left, S - Down, D - Right", 0, 230);
		
		// This is about what the settings menu does
		g.drawString("To customise the apperance of the game use the settings screen", 0, 270);
		
		// This is about the difficulty settings
		g.drawString("When entering the game you can change the difficulty by choosing a slower game", 0, 310);
		g.drawString("speed or a larger level size, these have changes on the score achieved", 0, 340);
		
		// This is about the modifiers on each game speed
		g.drawString("The game speeds give different multipliers to the end score achieved:", 0, 380);
		g.drawString("Slow - 1x, Medium - 2x, Fast - 3x", 0, 410);
		
		// This describes how changing the level size affects the scores achieved
		g.drawString("The level sizes each have their own leaderboard separate to one another", 0, 450);
		
		// This is about the different food types
		g.drawString("Different types of food appear while playing the game with their own score values:", 0, 490);
		g.drawString("Pellet - 50 points, Apple - 100 points, Bread - 200 points", 0, 520);
	}
}
