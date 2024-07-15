import java.awt.Image;

import javax.swing.ImageIcon;

// Parent class for all fruit types
public class Fruit {
	
	// Coordinates of the fruit being spawned
	private int fruitX;
	private int fruitY;
	
	// How much score the fruit gives
	private int scoreValue;
	
	// Images for the fruit
	private ImageIcon iiSprite;
	private Image sprite;
	
	// Setter and Getter methods for each attribute
	public int getFruitX() {
		return fruitX;
	}
	public void setFruitX(int fruitX) {
		this.fruitX = fruitX;
	}
	public int getFruitY() {
		return fruitY;
	}
	public void setFruitY(int fruitY) {
		this.fruitY = fruitY;
	}
	public int getScoreValue() {
		return scoreValue;
	}
	public void setScoreValue(int scoreValue) {
		this.scoreValue = scoreValue;
	}
	public ImageIcon getIiSprite() {
		return iiSprite;
	}
	public void setIiSprite(ImageIcon iiSprite) {
		this.iiSprite = iiSprite;
	}
	public Image getSprite() {
		return sprite;
	}
	public void setSprite(Image sprite) {
		this.sprite = sprite;
	}
}