import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class SettingsMenu {
	
	// Constants that define button width and heights
	private final static int buttonWidth = 250;
	private final static int buttonHeight = 80;
	
	// Variables used when drawing red rectangles around the currently selected values in this menu
	public static int currentSkinX;
	public static int currentWallX;
	public static int currentBack1X;
	public static int currentBack2X;
	
	
	// The xcoords, ycoords and dimensions of each button is defined here
	// Exit button
	public static Rectangle exitButton = new Rectangle(250, 920, 500, buttonHeight);
	
	// Different duck skin options
	public static Rectangle topHat = new Rectangle(75, 100, buttonWidth, buttonHeight);
	public static Rectangle duckCap = new Rectangle(360, 100, buttonWidth + 25, buttonHeight);
	public static Rectangle noDuckSkin = new Rectangle(675, 100, buttonWidth, buttonHeight);
	
	// Different wall colour options
	public static Rectangle redWall = new Rectangle(75, 300, buttonWidth, buttonHeight);
	public static Rectangle blueWall = new Rectangle(375, 300, buttonWidth, buttonHeight);
	public static Rectangle greenWall = new Rectangle(675, 300, buttonWidth, buttonHeight);
	
	// Different colours for the primary colour of the background
	public static Rectangle black1 = new Rectangle(75, 500, buttonWidth, buttonHeight);
	public static Rectangle gray1 = new Rectangle(375, 500, buttonWidth, buttonHeight);
	public static Rectangle white1 = new Rectangle(675, 500, buttonWidth, buttonHeight);
	
	// Different colours for the secondary colour of the background
	public static Rectangle black2 = new Rectangle(75, 700, buttonWidth, buttonHeight);
	public static Rectangle gray2 = new Rectangle(375, 700, buttonWidth, buttonHeight);
	public static Rectangle white2 = new Rectangle(675, 700, buttonWidth, buttonHeight);
	
	// Creates the menu itself
	public static void createMenu(Graphics g) {
		
		// Used to create graphics
		Graphics2D g2d = (Graphics2D) g;
		
		// Used to set and add a background image 
		ImageIcon iiBackground = new ImageIcon("src/Images/Background.jpg");
		Image background = iiBackground.getImage();
		g.drawImage(background, 0, 0, null);
		
		// Creates the buttons
		// Exit button
		g.setColor(Color.green);
		g2d.draw(exitButton);
		
		// Duck skin buttons
		g2d.draw(topHat);
		g2d.draw(duckCap);
		g2d.draw(noDuckSkin);
		
		// Wall colours
		g2d.draw(redWall);
		g2d.draw(blueWall);
		g2d.draw(greenWall);
		
		// Primary background colour
		g2d.draw(black1);
		g2d.draw(gray1);
		g2d.draw(white1);
		
		// Secondary background colour
		g2d.draw(black2);
		g2d.draw(gray2);
		g2d.draw(white2);
		
		// Creates the button text
		// Menu text
		Font btnFont = new Font("arial", Font.BOLD, 60);
		g.setFont(btnFont);
		g.setColor(Color.white);
		g.drawString("Back to Menu", exitButton.x + 50, exitButton.y + 60);
		
		// Duck skin button text as well as a label
		g.drawString("Choose a duck skin", topHat.x - 10, topHat.y - 30);
		g.drawString("Top Hat", topHat.x + 10, topHat.y + 60);
		g.drawString("Duck Cap", duckCap.x, duckCap.y + 60);
		g.drawString("No Skin", noDuckSkin.x + 10, noDuckSkin.y + 60);
		
		// Wall colour text as well as a label
		g.drawString("Choose a wall colour", redWall.x - 10, redWall.y - 30);
		g.drawString("Red", redWall.x, redWall.y + 60);
		g.drawString("Blue", blueWall.x, blueWall.y + 60);
		g.drawString("Green", greenWall.x, greenWall.y + 60);
		
		// Primary background colour text as well as a label
		g.drawString("Choose the first back colour", black1.x - 10, black1.y - 30);
		g.drawString("Black", black1.x, black1.y + 60);
		g.drawString("Gray", gray1.x, gray1.y + 60);
		g.drawString("White", white1.x, white1.y + 60);
		
		// Secondary background colour text as well as a label
		g.drawString("Choose the second back colour", black2.x - 10, black2.y - 30);
		g.drawString("Black", black2.x, black2.y + 60);
		g.drawString("Gray", gray2.x, gray2.y + 60);
		g.drawString("White", white2.x, white2.y + 60);
		
		// Creates a red rectangle around the currently selected options
		// Duck skin
		g.setColor(Color.red);
		switch(GamePanel.currentDuckImage) {
			case "TopHat":
				currentSkinX = topHat.x - 25;
				break;
			
			case "DuckCap":
				currentSkinX = duckCap.x - 10;
				break;
			
			case "None":
				currentSkinX = noDuckSkin.x - 25;
				break;
		}
		g2d.drawRect(currentSkinX, 75, buttonWidth + 50, buttonHeight + 50);
		
		// Wall colour
		switch(GamePanel.wallColour) {
			case "Red":
				currentWallX = redWall.x - 25;
				break;
			
			case "Blue":
				currentWallX = blueWall.x - 25;
				break;
				
			case "Green":
				currentWallX = greenWall.x - 25;
				break;
		}
		g2d.drawRect(currentWallX, redWall.y - 25, buttonWidth + 50, buttonHeight + 50);
		
		// Primary background colour
		switch(GamePanel.backgroundColour1) {
			case "Black":
				currentBack1X = black1.x - 25;
				break;
			
			case "Gray":
				currentBack1X = gray1.x - 25;
				break;
				
			case "White":
				currentBack1X = white1.x - 25;
				break;
		}
		g2d.drawRect(currentBack1X, black1.y - 25, buttonWidth + 50, buttonHeight + 50);
		
		// Secondary background colour
		switch(GamePanel.backgroundColour2) {
		case "Black":
			currentBack2X = black2.x - 25;
			break;
		
		case "Gray":
			currentBack2X = gray2.x - 25;
			break;
			
		case "White":
			currentBack2X = white2.x - 25;
			break;
	}
	g2d.drawRect(currentBack2X, black2.y - 25, buttonWidth + 50, buttonHeight + 50);
	}
}
