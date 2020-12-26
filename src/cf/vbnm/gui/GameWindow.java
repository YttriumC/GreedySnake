package cf.vbnm.gui;

import cf.vbnm.gui.components.AbstractComponent;
import cf.vbnm.gui.components.Food;
import cf.vbnm.gui.components.Snake;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * @apiNote 游戏窗口, 进行蛇, 食物等的设置, 封装了游戏内容的对象
 */
public class GameWindow extends AbstractWindow implements Drawable {

	private int width, height;
	private int score;
	private int snakeLength;
	private JPanel gamePanel;
	private Snake snake;
	private ArrayList<Food> foods;
	private boolean gameContinue;


	public void pauseGame() {
		this.gameContinue = false;
	}

	public void resumeGame() {
		this.gameContinue = true;
	}

	public boolean getGameContinue() {
		return gameContinue;
	}


	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Snake getSnake() {
		return snake;
	}

	public void setSnake(Snake snake) {
		this.snake = snake;
	}

	public ArrayList<Food> getFoods() {
		return foods;
	}

	public void setFoods(ArrayList<Food> foods) {
		this.foods = foods;
	}


	public int getScore() {
		return score;
	}

	public void addScore(int score) {
		this.score += score;
	}

	public int getSnakeLength() {
		return snakeLength;
	}

	public void setSnakeLength(int snakeLength) {
		this.snakeLength = snakeLength;
	}

	public JPanel getGamePanel() {
		return gamePanel;
	}

	public void setGamePanel(JPanel gamePanel) {
		this.gamePanel = gamePanel;
	}


	public GameWindow(Snake snake) {
		this.snake = snake;
		foods = new ArrayList<>();
	}


	@Override
	void addComponent(AbstractComponent component) {
		if (component instanceof Food)
			foods.add((Food) component);
	}

	@Override
	public void paint(Graphics g) {
		gamePanel.repaint();
	}
}
