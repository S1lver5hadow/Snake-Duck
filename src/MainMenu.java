import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class MainMenu  {
	
	// Constants that define button width and heights
	private final static int buttonWidth = 420;
	private final static int buttonHeight = 80;
	
	// The xcoords, ycoords and dimensions of each button is defined here
	public static Rectangle playButton = new Rectangle(300, 400, buttonWidth, buttonHeight);
	public static Rectangle leaderboardButton = new Rectangle(300, 520, buttonWidth, buttonHeight);
	public static Rectangle instructionsButton = new Rectangle(300, 640, buttonWidth, buttonHeight);
	public static Rectangle settingsButton = new Rectangle(300, 760, buttonWidth, buttonHeight);
	public static Rectangle quitButton = new Rectangle(300, 880, buttonWidth, buttonHeight);
	
	public static void createMenu(Graphics g) {
		// Used to set and add a background image 
		ImageIcon iiBackground = new ImageIcon("src/Images/Background.jpg");
		Image background = iiBackground.getImage();
		g.drawImage(background, 0, 0, null);
		
		
		Graphics2D g2d = (Graphics2D) g;
		
		// Creates the buttons that hold the text
		g.setColor(Color.green);
		g2d.draw(playButton);
		g2d.draw(leaderboardButton);
		g2d.draw(instructionsButton);
		g2d.draw(settingsButton);
		g2d.draw(quitButton);
		
		// Creates the text on each button
		Font btnFont = new Font("arial", Font.BOLD, 70);
		g.setFont(btnFont);
		g.setColor(Color.WHITE);
		g.drawString("Play", playButton.x + 140, playButton.y + 60);
		g.drawString("Leaderboard", leaderboardButton.x - 1, leaderboardButton.y + 60);
		g.drawString("Instructions", instructionsButton.x + 10, instructionsButton.y + 60);
		g.drawString("Settings", settingsButton.x + 85, settingsButton.y + 60);
		g.drawString("Quit", quitButton.x + 140, quitButton.y + 60);
	}
}
