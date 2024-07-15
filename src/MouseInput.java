import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {

		public void mouseClicked(MouseEvent e) {
			
			
		}
	
		// If the user clicks in a certain area then this method is called
		public void mousePressed(MouseEvent e) {
			// Gets the x and y coordinates of where the user has clicked
			int mx = e.getX();
			int my = e.getY();
			
			// Switch statement to decide what state the game is in so it can get the correct button locations
			switch (GamePanel.State) {
				
				case MENU:
					// Since all buttons share the same width and starting x-coordinate the first if statement is the same for all
					if (mx >= 300 && mx <= 720) {
						// Multiple if statements have to be used to check y-coordinates because a switch-case can't use conditional logic
						// This way of deciding the boundaries of the button is replicated down below for every single statement and therefore won't be commented on repeatedly
						
						// Play button
						if (my >= 400 && my <= 480) {
							// Changes state to DIFFICULTY so that the user may change the difficulty settings before starting the game
							 GamePanel.State = GamePanel.STATE.DIFFICULTY;
						}
						
						// Leaderboard button
						else if (my >= 520 && my <= 600) {
							GamePanel.State = GamePanel.STATE.LEADERBOARD;
						}
						
						// Instructions button
						else if (my >= 640 && my <= 720) {
							GamePanel.State = GamePanel.STATE.INSTRUCTIONS;
						}
						
						// Settings Button
						else if (my >= 760 && my <= 840) {
							GamePanel.State = GamePanel.STATE.SETTINGS;
						}
						
						// Quit Button
						else if (my >= 880 && my <= 960) {
							// Closes the frame
							System.exit(0);
						}
					}
					break;
			
				case INSTRUCTIONS:
					// Menu button
					if ((mx >= 250 && mx <= 750) && (my >= 800 && my <= 900)) {
						GamePanel.State = GamePanel.STATE.MENU;
					}
					break;
			
				case SETTINGS:
					// Menu button
					if ((mx >= 250 && mx <= 750) && (my >= 920 && my <= 1000)) {
						GamePanel.State = GamePanel.STATE.MENU;
					}
					// Duck skin 
					else if (my >= 100 && my <= 180) {
						if (mx >= 75 && mx <= 225) {
							GamePanel.currentDuckImage = "TopHat";
						}
						else if (mx >= 360 && mx <= 635) {
							GamePanel.currentDuckImage = "DuckCap";
						}
						else if (mx >= 675 && mx <= 825) {
							GamePanel.currentDuckImage = "None";
						}
					}
					// Wall colour
					else if (my >= 300 && my <= 380) {
						if (mx >= 75 && mx <= 225) {
							GamePanel.wallColour = "Red";
						}
						else if(mx >= 375 && mx <= 625) {
							GamePanel.wallColour = "Blue";
						}
						else if (mx >= 675 && mx <= 825) {
							GamePanel.wallColour = "Green";
						}
					}
					// Primary background colour
					else if (my >= 500 && my <= 580) {
						if (mx >= 75 && mx <= 225) {
							GamePanel.backgroundColour1 = "Black";
						}
						else if(mx >= 375 && mx <= 625) {
							GamePanel.backgroundColour1 = "Gray";
						}
						else if (mx >= 675 && mx <= 825) {
							GamePanel.backgroundColour1 = "White";
						}
					}
					// Secondary background colour
					else if (my >= 700 && my <= 780) {
						if (mx >= 75 && mx <= 225) {
							GamePanel.backgroundColour2 = "Black";
						}
						else if(mx >= 375 && mx <= 625) {
							GamePanel.backgroundColour2 = "Gray";
						}
						else if (mx >= 675 && mx <= 825) {
							GamePanel.backgroundColour2 = "White";
						}
					}
					break;
				
				case DIFFICULTY:
					// Used to set the different game speeds by changing the timer delay
					if (my >= 300 && my <= 400) {
						// Slow game speed
						if (mx >= 250 && mx <= 450) {
							GamePanel.delay = 150;
						}
						// Medium game speed
						else if (mx >= 500 && mx <= 700) {
							GamePanel.delay = 75;
						}
						// Fast game speed
						else if (mx >= 750 && mx <= 950) {
							GamePanel.delay = 50;
						}
					}
					// Used to set the different level sizes by changing unitSize hence allowing more/less squares on the screen
					else if (my >= 450 && my <= 550) {
						// Small level size
						if (mx >= 250 && mx <= 450) {
							GamePanel.unitSize = 84;
						}
						// Medium level size
						else if (mx >= 500 && mx <= 700) {
							GamePanel.unitSize = 42;
						}
						// Large level size
						else if (mx >= 750 && mx <= 950) {
							GamePanel.unitSize = 21;
						}
					}
					// Menu and play buttons
					else if (my >= 750 && my <= 850) {
						if (mx >= 225 && mx <= 425) {
							GamePanel.State = GamePanel.STATE.MENU; 
						}
						else if (mx >= 625 && mx <= 825) {
							GamePanel.State = GamePanel.STATE.GAME;
						}
					}
					break;
				
				case END:
					// Menu and play again buttons
					if (my >= 750 && my <= 850) {
						if (mx >= 10 && mx <= 522) {
							GamePanel.State = GamePanel.STATE.MENU;
						}
						else if (mx >= 530 && mx <= 995) {
							GamePanel.State = GamePanel.STATE.DIFFICULTY;
						}
					}
					// Used to save the name
					else if (my >= 400 && my <= 500) {
						if (mx >= 740 && mx <= 990) {
							// Validates that the score hasn't already been saved and that a name has been entered
							if (GamePanel.nameStored == false && GamePanel.currentName.length() > 0) {
								// Switch case used to determine what level size the user is on and therefore what arrayList needs to be updated
								switch (GamePanel.unitSize) {
									// Big level size
									case 21:
										GamePanel.scoresBig.add(GamePanel.currentName + " " + GamePanel.fruitEaten);
										LeaderboardMenu.insertionSort(GamePanel.scoresBig);
										break;
										
									// Medium level size
									case 42:
										GamePanel.scoresMed.add(GamePanel.currentName + " " + GamePanel.fruitEaten);
										LeaderboardMenu.insertionSort(GamePanel.scoresMed);
										break;
									
									// Small level size
									case 84:
										GamePanel.scoresSmall.add(GamePanel.currentName + " " + GamePanel.fruitEaten);
										LeaderboardMenu.insertionSort(GamePanel.scoresSmall);
										break;
								}
								LeaderboardMenu.txtAddition();
								GamePanel.nameStored = true;
							}
						}
					}
					
					break;
				
				case LEADERBOARD:
					// Buttons used to change what leaderboard the user is on
					if (my >= 750 && my <= 850) {
						if (mx >= 50 && mx <= 250) {
							LeaderboardMenu.currentSize = "Small";
						}
						else if (mx >= 400 && mx <= 650) {
							LeaderboardMenu.currentSize = "Med";
						}
						else if (mx >= 750 && mx <= 950) {
							LeaderboardMenu.currentSize = "Big";
						}
					}
					// Menu button
					else if((my >= 900 && my <= 1000) && (mx >= 400 && mx <= 650)) {
						GamePanel.State = GamePanel.STATE.MENU;
					}
					
					break;
			
			}
			
		}
	
		public void mouseReleased(MouseEvent e) {
			
			
		}
	
		public void mouseEntered(MouseEvent e) {
			
			
		}
	
		public void mouseExited(MouseEvent e) {
			
			
		}
	}