import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class LeaderboardMenu {
	
	// Constants that define button width and heights
	private final static int buttonWidth = 200;
	private final static int buttonHeight = 100;
	
	// The xcoords, ycoords and dimensions of each button is defined here
	// Used to create a rectangle around the score values as a boundary
	public static Rectangle leaderboardBorder = new Rectangle(50,50,900,600);
	
	// Buttons that change the leader board values shown to a different level size
	public static Rectangle smallButton = new Rectangle(50, 750, buttonWidth, buttonHeight);
	public static Rectangle medButton = new Rectangle(400, 750, buttonWidth, buttonHeight);
	public static Rectangle bigButton = new Rectangle(750, 750, buttonWidth, buttonHeight);
	
	// Menu button
	public static Rectangle menuButton = new Rectangle(400, 900, buttonWidth, buttonHeight);
	
	// Variable that is used to determine the level size which has a default value of medium because the unitSize default value is 42
	static String currentSize = "Med";
	
	// On the game's startup the text from the 3 different leaderboards will be put into 3 different ArrayLists
	public static void txtStartUp() {
		
		// Big level size
		try {
			// Opens the text file
			BufferedReader reader = new BufferedReader(new FileReader("LeaderboardBig.txt"));
			String line;
			// Reads all the lines and adds the values to the arraylist
			while ((line = reader.readLine()) != null) {
				GamePanel.scoresBig.add(line);
			}
			// Closes the text file
			reader.close();
		} catch (IOException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
		
		// Medium level size
		try {
			// Opens the text file
			BufferedReader reader = new BufferedReader(new FileReader("LeaderboardMed.txt"));
			String line;
			// Reads all the lines and adds the values to the array list
			while ((line = reader.readLine()) != null) {
				GamePanel.scoresMed.add(line);
			}
			// Closes the text file
			reader.close();
		} catch (IOException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
		
		// Small level size
		try {
			// Opens the text file
			BufferedReader reader = new BufferedReader(new FileReader("LeaderboardSmall.txt"));
			String line;
			// Reads all the lines and adds the values to the array list
			while ((line = reader.readLine()) != null) {
				GamePanel.scoresSmall.add(line);
			}
			// Closes the text file
			reader.close();
		} catch (IOException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// Used to update the text files when a new score is saved
	public static void txtAddition() {
		// Utilises the level size to determine what textfile needs to be updated
		switch (GamePanel.unitSize) {
			// Big level size
			case 21:
				// Opens the text file
				try { BufferedWriter writer = new BufferedWriter(new FileWriter("LeaderboardBig.txt")); 
				// Writes the array list to the text file
					for(int i = 0; i < GamePanel.scoresBig.size(); i++) {
						writer.write(GamePanel.scoresBig.get(i) + "\n");
					}
					// Closes the text file
					writer.close(); 
				} catch (IOException e) {
					// Auto-generated catch block
				    e.printStackTrace(); 
				}
				break;
				
			// Medium level size
			case 42:
				// Opens the text file
				try { BufferedWriter writer = new BufferedWriter(new FileWriter("LeaderboardMed.txt"));
				// Writes the array list to the text file
					for(int i = 0; i < GamePanel.scoresMed.size(); i++) {
						writer.write(GamePanel.scoresMed.get(i) + "\n");
					}
					// Closes the text file
					writer.close(); 
				} catch (IOException e) {
					// Auto-generated catch block
				    e.printStackTrace(); 
				}
				break;
			// Small level size
			case 84:
				// Opens the text file
				try { BufferedWriter writer = new BufferedWriter(new FileWriter("LeaderboardSmall.txt")); 
				// Writes the array list to the text file
					for(int i = 0; i < GamePanel.scoresSmall.size(); i++) {
						writer.write(GamePanel.scoresSmall.get(i) + "\n");
					}
					// Closes the text file
					writer.close(); 
				} catch (IOException e) {
					// Auto-generated catch block
				    e.printStackTrace(); 
				}
			break;
		}
		 
	}
	
	// Part of an insertion sort algorithm that inserts the current score achieved into the correct position and then deletes the final value in the ArrayList so that only 5 elements are ever in the ArrayList
	public static void insertionSort(ArrayList<String> List) {
		
		// Used to get the score that has just been achieved
		String currentCompared = List.get(List.size()-1);
		int currentNum = Integer.parseInt(currentCompared.substring(currentCompared.lastIndexOf(" ") + 1));
		
		// For loop that loops through every element in the ArrayList
		for (int i = 0; i < List.size() - 1; i++) {
			
			// Used to get the score in the current index
			String tempComparison = List.get(i);
			int tempNumber = Integer.parseInt(tempComparison.substring(tempComparison.lastIndexOf(" ") + 1));
			
			// If the score achieved is higher than the current index then everything is shifted along by a single index
			if (currentNum > tempNumber) {
				for (int j = i; j < List.size(); j++) {
					tempComparison = List.get(j);
					List.set(j, currentCompared);
					currentCompared = tempComparison;
				}
				
				// Ends the previous for loop
				i = Integer.MAX_VALUE - 2;
				
			}
		}
		// Removes the final item in the list so that only the top 5 elements are ever in the ArrayList
		List.remove(5);
	}
	
	// Used to create the actual leaderboard menu itself
	public static void createMenu(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		// Used to set and add a background image 
		ImageIcon iiBackground = new ImageIcon("src/Images/Background.jpg");
		Image background = iiBackground.getImage();
		g.drawImage(background, 0, 0, null);
		
		g.setColor(Color.red);
		g.setFont(new Font("Oxygen", Font.BOLD, 50));
		
		int currentSizex = 0;
		
		// Text that details the current leaderboard selected and draws a rectangle around the relevant button
		switch (currentSize) {
			case "Big":
				g.drawString("Large Size", 360, 40);
				currentSizex = 735;
				break;
			case "Med":
				g.drawString("Medium Size", 350, 40);
				currentSizex = 385;
				break;
			case "Small":
				g.drawString("Small Size", 360, 40);
				currentSizex = 35;
				break;	
		}
		g2d.drawRect(currentSizex, 735, buttonWidth + 30, buttonHeight + 30);
		
		// Draws the buttons
		// Draws the outer border for the score values
		g.setColor(Color.GRAY);
		g2d.draw(leaderboardBorder);
		
		// Draws the different size buttons for the leaderboard
		g2d.draw(smallButton);
		g2d.draw(medButton);
		g2d.draw(bigButton);
		
		// Return to menu button 
		g2d.draw(menuButton);
		
		
		// Button text
		// Small and big size options
		Font btnFont = new Font("arial", Font.BOLD, 70);
		g.setFont(btnFont);
		g.setColor(Color.blue);
		g.drawString("Small", smallButton.x + 5, smallButton.y + 70);
		g.drawString("Big", bigButton.x + 35, bigButton.y + 70);
		
		// Menu button text
		g.drawString("Menu", menuButton.x + 10, menuButton.y + 70);
		
		// There is not enough space for medium to fit on the screen so a smaller font is used and it is draw in the correct place
		g.setFont(new Font("arial", Font.BOLD, 53));
		g.drawString("Medium", medButton.x, medButton.y + 70);
		
		// Draws the actual leaderboard values
		g.setFont(btnFont);
		g.setColor(Color.white);
		switch (currentSize) {
			// Big level size
			case "Big":
				for (int i = 0; i < 5; i++) {
					g.drawString(GamePanel.scoresBig.get(i), 100, 130 + 110*i);
				}
				break;
			
			// Medium level size
			case "Med":
				for (int i = 0; i < 5; i++) {
					g.drawString(GamePanel.scoresMed.get(i), 100, 130 + 110*i);
				}
				break;
			
			// Small level size
			case "Small":
				for (int i = 0; i < 5; i++) {
					g.drawString(GamePanel.scoresSmall.get(i), 100, 130 + 110*i);
				}
				break;
		}
	}
}
