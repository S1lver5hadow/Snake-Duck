import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

// The Panel defines what is shown within the window itself
public class GamePanel extends JPanel implements ActionListener{
	
	// Defines the dimensions of the game
	final static int screenWidth = 1008;
	final static int screenHeight = 1008;
	
	// Defines the size of items and how many tiles are on the screen
	public static int unitSize = 42;
	// 1008 divided by 42 => 24 tiles 
	static int gameUnits = (screenWidth * screenHeight) / unitSize;
	
	// Delay between each consecutive movement of the Duck hence it is the movement speed of the duck
	public static int delay = 75;
	
	// Holds x and y coordinates of the Duck
	int x_Coord[] = new int[gameUnits];
	int y_Coord[] = new int[gameUnits];
	
	// Starting Duck size
	int duckSize;
	
	// Denotes the Fruit's X and Y coordinates
	int tempfruitX;
	int tempfruitY;
	
	// Denotes the direction that the Duck is facing
	char direction;
	
	// A boolean attribute used to determine if the user has already changed direction in a single timer tick
	boolean hasChangedDirection = false;
	
	// Defines whether the player has lost the game yet
	boolean running = false;
	
	// Defined so that methods can use it below
	// Different timer settings for each game speed
	Timer timerSlow;
	Timer timerMed;
	Timer timerFast;
	
	// Random variable to implement random number generators below
	Random random;
	
	// Menu timer so that aspects of the menu are updated as needed
	Timer mtimer;
	
	// Used to make sure that the startGame() method is run correctly
	boolean fromMenu = true;
	
	// Variables used to store images
	private Image head;
	private Image body;
	
	// Defines all the states available in the game
	public static enum STATE {
		MENU,
		GAME,
		INSTRUCTIONS,
		LEADERBOARD,
		SETTINGS,
		DIFFICULTY,
		END
	}
	
	// Sets the current state to the menu screen meaning the player will start on the menu
	public static STATE State = STATE.MENU;
	
	// Defines 3 different ArrayLists which are used to store the different scores inside of
	public static ArrayList<String> scoresBig = new ArrayList<String>();
	public static ArrayList<String> scoresMed = new ArrayList<String>();
	public static ArrayList<String> scoresSmall = new ArrayList<String>();
	
	// Variables used to set the score accompanying a score on the end screen
	public static String currentName = "";
	public static boolean nameStored = false;
	
	// Variable used to determine what the head duck will look like inside the game
	public static String currentDuckImage = "DuckCap";
	
	// Variable used to determine what the wall colour will be
	public static String wallColour = "Green";
	
	// Variables used to determine what the background colour pattern will be
	public static String backgroundColour1 = "Black";
	public static String backgroundColour2 = "Gray";
	
	// Variable used to determine current fruit type
	Fruit currentFruit = new Fruit();
	
	// Score counter
	static int fruitEaten;
	
	
	// Constructor method that is used to setup the panel and any extra variables
	GamePanel(){
		random = new Random();
		// Sets the size of the screen
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		
		// Background colour of the screen
		this.setBackground(Color.black);
		this.setFocusable(true);
		
		// Allows the user to enter inputs with the arrow keys
		this.addKeyListener(new MyKeyAdapter());
		
		// Allows the user to enter inputs with the mouse
		this.addMouseListener(new MouseInput());
		
		// Menu timer
		mtimer = new Timer(300,this);
		mtimer.start();
		
		// Used to setup the different duck speed timers
		timerFast = new Timer(50,this);
		timerMed = new Timer(75,this);
		timerSlow = new Timer(150,this);
		
		// Calls the txtStartUp method which is used to load the data stored in the text file scoreboards
		LeaderboardMenu.txtStartUp();
	}	
	
	// Used to load any images needed while playing the game
	public void loadImagesBig() {
		
		// Loads an image of the head duck under the name "head"
		ImageIcon iiHead;
		// Finds the correct image depending on what duck skin the user has chosen
		switch (currentDuckImage) {
			case "TopHat":
				iiHead = new ImageIcon("src/Images/21x21/TopHatDuck21x21.png");
				head = iiHead.getImage();
				break;
			
			case "DuckCap":
				iiHead = new ImageIcon("src/Images/21x21/DuckCap21x21.png");
				head = iiHead.getImage();
				break;
		}
			
		// Loads an image of the body ducks under the name body
		ImageIcon iiBody = new ImageIcon("src/Images/21x21/bodyDuck21x21.jpg");
		body = iiBody.getImage();
	}

	public void loadImagesMed() {
		
		// Loads an image of the head duck under the name "head"
		ImageIcon iiHead;
		// Finds the correct image depending on what duck skin the user has chosen
		switch (currentDuckImage) {
			case "TopHat":
				iiHead = new ImageIcon("src/Images/42x42/TopHatDuck42x42.png");
				head = iiHead.getImage();
				break;
			
			case "DuckCap":
				iiHead = new ImageIcon("src/Images/42x42/DuckCap42x42.png");
				head = iiHead.getImage();
				break;
		}
			
		// Loads an image of the body ducks under the name body
		ImageIcon iiBody = new ImageIcon("src/Images/42x42/bodyDuck42x42.jpg");
		body = iiBody.getImage();
	}
	
	public void loadImagesSmall() {
		
		// Loads an image of the head duck under the name "head"
		ImageIcon iiHead;
		// Finds the correct image depending on what duck skin the user has chosen
		switch (currentDuckImage) {
			case "TopHat":
				iiHead = new ImageIcon("src/Images/84x84/TopHatDuck84x84.png");
				head = iiHead.getImage();
				break;
			
			case "DuckCap":
				iiHead = new ImageIcon("src/Images/84x84/DuckCap84x84.png");
				head = iiHead.getImage();
				break;
		}
			
		// Loads an image of the body ducks under the name body
		ImageIcon iiBody = new ImageIcon("src/Images/84x84/bodyDuck84x84.jpg");
		body = iiBody.getImage();
	}
	
	// Used to set the starting values of variables and spawn in a fruit so the game can start
	public void startGame() {
		// Sets running to true hence the game has begun
		running = true;
		
		// Disables the fromMenu variable so that the game state will load correctly rather than heading straight to the end screen upon first startup
		fromMenu = false;
		
		// Starts the relevant timer depending on the speed of the duck which is used to delay the Duck's movement speed
		switch (delay) {
			// Fast speed
			case 50:
				timerFast.start();
				break;
			
			// Medium speed
			case 75:
				timerMed.start();
				break;
			
			// Slow speed
			case 150:
				timerSlow.start();
				break;
		}
		
		
		// Sets the starting values for the game to begin
		// Duck starts at a length of 6
		duckSize = 6;
		
		// Score is 0
		fruitEaten = 0;
		
		// Default value for food coordinates
		tempfruitX = 0;
		tempfruitY = 0;
		
		// Duck starts the game facing right
		direction = 'R';
		
		// Sets the starting Duck location to the centre of the screen
		for(int i=0; i<duckSize; i++) {
			x_Coord[i] = (int)((screenWidth/2)-(i*unitSize));
			y_Coord[i] = (int)(screenHeight/2);
		}
		
		// Loads any images needed in running the game depending on the level size
		switch (unitSize) {
			// Big level size
			case 21:
				loadImagesBig();
				break;
			
			// Medium level size
			case 42:
				loadImagesMed();
				break;
				
			// Small level size
			case 84:
				loadImagesSmall();
				break;
		}
		
		
		// Calls the method in charge of spawning fruit
		fruitSpawn();
	}
	
	// Used for graphics
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		createLevel(g);
	}
	
	// Creates the level if the game is running otherwise sends to the gameOver method
	public void createLevel(Graphics g){
		// Switch case statement that lets the program know which state the game is in so it knows what graphics to create
		switch (State) {
			case GAME:
				
				// If the player hasn't lost then:
				if (running) {
					
					// Used to draw in grid lines if enabled 
//					for(int i=0; i<screenHeight/unitSize; i++) {
//						g.drawLine(i*unitSize, 0, i*unitSize, screenHeight);
//						g.drawLine(0, i*unitSize, screenWidth, i*unitSize);
//					}
					
					// Used to change colour of wall according to the user's preference 
					switch (wallColour) {
						case "Green":
							g.setColor(Color.green);
							break;
							
						case "Blue":
							g.setColor(Color.blue);
							break;
							
						case "Red":
							g.setColor(Color.red);
							break;
					}
					// These are the left and right columns
					for (int i=0; i<screenHeight/unitSize; i++) {
						g.fillRect(0, i*unitSize, unitSize, unitSize);
						g.fillRect(screenWidth-unitSize, i*unitSize, unitSize, unitSize);
					}
					// These are the top and bottom rows
					for (int i=0; i<screenWidth/unitSize; i++) {
						g.fillRect(i*unitSize, 0, unitSize, unitSize);
						g.fillRect(i*unitSize, screenHeight-unitSize, unitSize, unitSize);
					}
					
					// Deals with all the background colours
					// Sets the primary colour
					switch (backgroundColour1) {
						case "Black":
							g.setColor(Color.black);
							break;
						
						case "Gray":
							g.setColor(Color.gray);
							break;
							
						case "White":
							g.setColor(Color.white);
							break;
					}
					
					// Deals with all the rows/columns which contain the odd pairs and even pairs
					for (int row = 1; row<screenWidth/unitSize - 1; row = row + 2) {
						for (int col = 1; col < screenHeight/unitSize - 1; col = col + 2) {
							g.fillRect(col*unitSize, row*unitSize, unitSize, unitSize);
						}
					}
					for (int row = 2; row<screenWidth/unitSize - 1; row = row + 2) {
						for (int col = 2; col < screenHeight/unitSize - 1; col = col + 2) {
							g.fillRect(col*unitSize, row*unitSize, unitSize, unitSize);
						}
					}
					
					// Sets the secondary colour
					switch (backgroundColour2) {
						case "Black":
							g.setColor(Color.black);
							break;
						
						case "Gray":
							g.setColor(Color.gray);
							break;
							
						case "White":
							g.setColor(Color.white);
							break;
					}
					
					// Deals with all the rows/columns which contain both an even and odd number
					for (int row = 1; row < screenWidth/unitSize -1; row = row + 2) {
						for (int col = 2; col < screenHeight/unitSize - 1; col = col + 2) {
							g.fillRect(col*unitSize, row*unitSize, unitSize, unitSize);
						}
					}
					for (int row = 2; row < screenWidth/unitSize -1; row = row + 2) {
						for (int col = 1; col < screenHeight/unitSize - 1; col = col + 2) {
							g.fillRect(col*unitSize, row*unitSize, unitSize, unitSize);
						}
					}

					// Graphically creates fruit at the current fruit coordinates
					g.drawImage(currentFruit.getSprite(), currentFruit.getFruitX(), currentFruit.getFruitY(), null);
					
					// Graphically creates the starting Ducks
					// Switch-case used to determine if the user has selected a skin or not
					switch (currentDuckImage) {
						// If not then the duck is created using rectangles
						case "None":
							for (int i=0; i<duckSize; i++) {
								if (i != 0) {
									g.setColor(new Color(45,180,0));
								}
								else {
									g.setColor(Color.red);
								}
								g.fillRect(x_Coord[i], y_Coord[i], unitSize, unitSize);
							}
							break;
						
						// If a skin has been enabled then the image will have been loaded previously
						default:
							for (int i=0; i<duckSize; i++) {
								if (i == 0) {
									g.drawImage(head, x_Coord[i], y_Coord[i], this);
								}
								else {
									g.drawImage(body, x_Coord[i], y_Coord[i], this);
								}
							}
							break;
					}
					
					
					// Creates text at the top of the screen representing the score
					switch (wallColour) {
						case "Blue":
							g.setColor(Color.red);
							break;
							
						default:
							g.setColor(Color.black);
							break;
					}
					g.setFont(new Font("Oxygen", Font.BOLD, 20));
					FontMetrics metrics = getFontMetrics(g.getFont());
					g.drawString("Score: " + fruitEaten, (screenWidth - metrics.stringWidth("Score: " + fruitEaten))/2, g.getFont().getSize());
				}
				// If the user has come from the menu then the running state will be false but they haven't lost the game yet and so the statGame() method must be called
				else if (fromMenu == true) {
					startGame();
				}
				// Else go to the gameOver method
				else {
					gameOver(g);
				}
				break;
			
			// All the cases below will call the relevant methods that are in charge of creating the specific menu for the state
			// Menu state
			case MENU:
				MainMenu.createMenu(g);
				break;
			
			// Leaderboard state
			case LEADERBOARD:
				LeaderboardMenu.createMenu(g);
				break;
				
			// Instructions state
			case INSTRUCTIONS:
				InstructionsMenu.createMenu(g);
				break;
				
			// Settings state
			case SETTINGS:
				SettingsMenu.createMenu(g);
				break;
				
			// Difficulty state
			case DIFFICULTY:
				// fromMenu set to true so that startGame() will be called correctly
				fromMenu = true;
				DifficultyMenu.createMenu(g);
				break;
			
			// End screen state
			case END:
				EndMenu.createMenu(g);
				break;
		}
	}
	
	// Used to set the coordinates of the next fruit to be spawned
	public void fruitSpawn() {
		
		// Sets the fruit type by choosing a random number from 0, 1 or 2
		switch (random.nextInt(3)) {
			case 0:
				currentFruit = new Bread();
				break;
			case 1:
				currentFruit = new Apple();
				break;
			case 2:
				currentFruit = new Pellet();
				break;
		}
		
		// Determines the x coordinates of the fruit to be spawned between 0 and 1 tile away from the right border
		tempfruitX = random.nextInt((int) (screenWidth/unitSize)-1) * unitSize;
		
		// Validates the x coordinates making sure it isn't being spawned on the perimeter or inside the Duck
		boolean valid = false;
		while (valid == false) {
			// If fruit X is being spawned in the left border then re-determine the x coordinate
			if (tempfruitX == 0) {
				tempfruitX = random.nextInt((int) (screenWidth/unitSize)-1) * unitSize;
			}
			// Else loop and check if the x coordinate is in the same x position as any of the body or head ducks
			else {
				boolean insideDuck = false;
				for (int i=0; i<duckSize; i++) {
					if (tempfruitX == x_Coord[i]) {
						insideDuck = true;
					}
				}
				// If true then re-determine the x coordinates
				if (insideDuck == true) {
					tempfruitX = random.nextInt((int) (screenWidth/unitSize)-1) * unitSize;
				}
				// Else x coordinates are valid
				else {
					valid = true;
				}
			}
		}
		
		// Sets the fruit's x coordinates to the current temp value
		currentFruit.setFruitX(tempfruitX);
		
		// Determines the y coordinates of the fruit to be spawned
		tempfruitY = random.nextInt((int) (screenHeight/unitSize)-1) * unitSize;
		
		// Validates the y coordinates using the same approach as before
		valid = false;
		while (valid == false) {
			if (tempfruitY == 0) {
				tempfruitY = random.nextInt((int) (screenHeight/unitSize)-1) * unitSize;
			}
			else {
				boolean insideDuck = false;
				for (int i=0; i<duckSize; i++) {
					if (tempfruitY == y_Coord[i]) {
						insideDuck = true;
					}
				}
				if (insideDuck == true) {
					tempfruitY = random.nextInt((int) (screenHeight/unitSize)-1) * unitSize;
				}
				else {
					valid = true;
				}
			}
		}
		
		// Sets the fruit's y coordinates to the current temporary value
		currentFruit.setFruitY(tempfruitY);
	}
	
	// Used to control the Duck
	public void move() {
		// Moves the ducks that are not at the head to the position of the next duck along
		for (int i=duckSize; i>0; i--) {
			x_Coord[i] = x_Coord[i-1];
			y_Coord[i] = y_Coord[i-1];
		}
		
		// Switch case that makes the Duck move along a single tile in the direction that it is facing
		switch (direction) {
			// E.g if it is facing up then the y_Coordinate will decrease by a single tile which moves it up by 1 unitSize
			case 'U':
				y_Coord[0] = y_Coord[0] - unitSize;
				break;
			
			// Facing down
			case 'D':
				y_Coord[0] = y_Coord[0] + unitSize;
				break;
			
			// E.g if it is facing right then the x_Coordinate will increase by a single tile which moves it right by 1 unitSize
			case 'R':
				x_Coord[0] = x_Coord[0] + unitSize;
				break;
			
			// Facing left
			case 'L':
				x_Coord[0] = x_Coord[0] - unitSize;
				break;
			
		}
		// Used to make sure the user is able to move again on the next timer tick
		hasChangedDirection = false;
	}
	
	// Checks to see if the head Duck is currently on the same coordinates as the Fruit item hence the Duck grows, the Fruit item is eaten and another is spawned
	public void checkFruit() {
		if ((x_Coord[0] == currentFruit.getFruitX()) && (y_Coord[0] == currentFruit.getFruitY())) {
			duckSize++;
			fruitEaten = fruitEaten + currentFruit.getScoreValue();
			fruitSpawn();
		}
	}
	
	// Checks for any collisions between the front Duck and any object other than a Fruit
	public void checkCollisions() {
		// Checks if front Duck collides with other Ducks	
		for (int i=duckSize; i>0; i--) {
			if((x_Coord[0] == x_Coord[i]) && (y_Coord[0] == y_Coord[i])) {
				running = false;
			}
		}
		
		// Checks if front Duck collides with left border
		if (x_Coord[0] == 0) {
			running = false;
		}
		
		// Checks if front Duck collides with right border
		if (x_Coord[0] == screenWidth - unitSize) {
			running = false;
		}
		
		// Checks if front Duck collides with top border
		if (y_Coord[0] == 0) {
			running = false;
		}
		
		// Checks if front Duck collides with bottom border
		if (y_Coord[0] == screenHeight - unitSize) {
			running = false;
		}
		
		// Game over
		if (!running) {
			// Used to turn off the timer that is relevant for the current level size
			switch (delay) {
			case 50:
				timerFast.stop();
				break;
			
			case 75:
				timerMed.stop();
				break;
			
			case 150:
				timerSlow.stop();
				break;
			}
		}
	}
	
	// Sends the user to the end menu
	public void gameOver(Graphics g)  {		
		// Used to reset the current name being entered into the leader board
		nameStored = false;
		currentName = "";
		
		// Adds a multiplier to the final score depending on the game speed
		switch (delay) {
			case 50:
				fruitEaten = fruitEaten * 3;
				break;
			
			case 75:
				fruitEaten = fruitEaten * 2;
				break;
		}
		
		// State is set to the end screen so the user is sent there
		State = STATE.END;
	}

	// Main Game Loop -> runs as long as the panel is open 
	public void actionPerformed(ActionEvent event) {
		if (State == STATE.GAME) {
			if (running) {
				move();
				checkFruit();
				checkCollisions();
			}
		}
		
		// Updates the game space to reflect the current conditions within the game
		repaint();
		
	}
	
	// Used to accept keyboard inputs
	public class MyKeyAdapter extends KeyAdapter {
		// Override
		public void keyPressed(KeyEvent userKeyInput) {
			// If the user is playing the game then used to check what arrow key the user enters into the game and assigns the appropriate direction to the Duck
			if (State == STATE.GAME) { 
				switch (userKeyInput.getKeyCode()) {
					
					// Left arrow
					case KeyEvent.VK_LEFT:
						// Checks it the duck is facing right and has already changed directions in the current timer tick
						if (direction != 'R' && hasChangedDirection == false) {
							// Sets the direction to left and the duck has now changed directions for the current timer tick
							direction = 'L';
							hasChangedDirection = true;
						}
						break;
				
					// Right arrow
					case KeyEvent.VK_RIGHT:
						if (direction != 'L' && hasChangedDirection == false) {
							direction = 'R';
							hasChangedDirection = true;
						}
						break;
					
					// Up arrow
					case KeyEvent.VK_UP:
						if (direction != 'D' && hasChangedDirection == false) {
							direction = 'U';
							hasChangedDirection = true;
						}
						break;
					
					// Down arrow
					case KeyEvent.VK_DOWN:
						if (direction != 'U' && hasChangedDirection == false) {
							direction = 'D';
							hasChangedDirection = true;
						}
						break;
						
					// The WASD equivalent of above
					// A key
					case KeyEvent.VK_A:
						if (direction != 'R' && hasChangedDirection == false) {
							direction = 'L';
							hasChangedDirection = true;
						}
						break;
						
					// D key
					case KeyEvent.VK_D:
						if (direction != 'L' && hasChangedDirection == false) {
							direction = 'R';
							hasChangedDirection = true;
						}
						break;
						
					// W key
					case KeyEvent.VK_W:
						if (direction != 'D' && hasChangedDirection == false) {
							direction = 'U';
							hasChangedDirection = true;
						}
						break;
						
					// S key
					case KeyEvent.VK_S:
						if (direction != 'U' && hasChangedDirection == false) {
							direction = 'D';
							hasChangedDirection = true;
						}
						break;
				}
			}
			// Else if the user is in the end state then the keyboard will be used to enter text into the name box
			else if (State == STATE.END) {
				// Only works if a name isn't already stored
				if (nameStored == false) {
					// Only accepts alphanumeric characters and not including any spaces
					if (userKeyInput.getKeyChar() != '\uFFFF' && currentName.length() <= 12 && (userKeyInput.getKeyCode() != KeyEvent.VK_BACK_SPACE && userKeyInput.getKeyCode() != KeyEvent.VK_SPACE)) {
						currentName = currentName + userKeyInput.getKeyChar();
					}
					// If a backspace is used then it deletes the last character input
					else if(userKeyInput.getKeyCode() == KeyEvent.VK_BACK_SPACE && currentName.length() > 0) {
						currentName = currentName.substring(0, currentName.length()-1);
						
					}
				}
			}
		}
	}
}

