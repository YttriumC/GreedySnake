package cf.vbnm.gui;

import cf.vbnm.gui.components.Food;
import cf.vbnm.gui.components.Snake;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * 戏窗口, 进行蛇, 食物等的设置, 封装了游戏内容的对象
 */
public class GameWindow implements Drawable {

	/**
	 * 所得分数
	 */
	private int score;
	/**
	 * 游戏panel,绘制游戏用
	 */
	private JPanel gamePanel;
	/**
	 * 贪吃蛇对象
	 */
	private Snake snake;
	/**
	 * 所有的食物对象
	 */
	private ArrayList<Food> foods;
	/**
	 * 标识游戏是否在运行(是否没暂停)
	 */
	private boolean gameContinue;

	/**
	 * 暂停游戏
	 */
	public void pauseGame() {
		this.gameContinue = false;
	}

	/**
	 * 继续游戏
	 */
	public void resumeGame() {
		this.gameContinue = true;
	}

	/**
	 * 获取游戏是否在运行
	 *
	 * @return 游戏是否在运行
	 */
	public boolean getGameContinue() {
		return gameContinue;
	}

	/**
	 * 获取这条贪吃蛇
	 *
	 * @return 返回贪吃蛇
	 */
	public Snake getSnake() {
		return snake;
	}

	/**
	 * 设置一条贪吃蛇
	 *
	 * @param snake 要添加的蛇
	 */
	public void setSnake(Snake snake) {
		this.snake = snake;
	}

	/**
	 * 获取食物
	 *
	 * @return 所有食物的链表
	 */
	public ArrayList<Food> getFoods() {
		return foods;
	}

	/**
	 * 设置所有食物
	 *
	 * @param foods 所有食物的链表
	 */
	public void setFoods(ArrayList<Food> foods) {
		this.foods = foods;
	}


	/**
	 * 获取当前分数
	 *
	 * @return 当前分数
	 */
	public int getScore() {
		return score;
	}

	/**
	 * 加分
	 *
	 * @param score 要加的分数
	 */
	public void addScore(int score) {
		this.score += score;
	}

	/**
	 * 获取游戏panel
	 *
	 * @return 游戏的panel
	 */
	public JPanel getGamePanel() {
		return gamePanel;
	}

	/**
	 * 设置游戏panel
	 *
	 * @param gamePanel 游戏的panel
	 */
	public void setGamePanel(JPanel gamePanel) {
		this.gamePanel = gamePanel;
	}

	/**
	 * 构造方法
	 *
	 * @param snake 需要一条贪吃蛇完成构造
	 */
	public GameWindow(Snake snake) {
		this.snake = snake;
		foods = new ArrayList<>();
	}

	/**
	 * Drawable接口实现
	 *
	 * @param g 图形对象,系统用来绘制图像
	 */
	@Override
	public void paint(Graphics g) {
		gamePanel.repaint();
	}
}
