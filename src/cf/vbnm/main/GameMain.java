package cf.vbnm.main;

import cf.vbnm.util.Constant;
import cf.vbnm.gui.GameWindow;
import cf.vbnm.gui.components.ComponentColor;
import cf.vbnm.gui.components.Direction;
import cf.vbnm.gui.components.Food;
import cf.vbnm.gui.components.Snake;
import cf.vbnm.util.Util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

/**
 * 主游戏界面元素类, 保存游戏窗口
 *
 * @version 1.0
 */
public class GameMain {
	private static GameWindow gameWindow;
	private JFrame thisFrame;
	private JFrame superFrame;
	private JPanel gamePanel;
	private boolean gameStarted;

	public JPanel getGamePanel() {
		return gamePanel;
	}

	public void setGamePanel(JPanel gamePanel) {
		this.gamePanel = gamePanel;
	}


	public void setSuperFrame(JFrame superFrame) {
		this.superFrame = superFrame;
	}

	public static GameWindow getGameWindow() {
		return gameWindow;
	}

	public GameMain() {
		gameWindow = null;
	}


	/**
	 * @param foodNums     食物数量
	 * @param initSnakeLen 初始蛇长度
	 * @param gridXNum     X方向格子数量
	 * @param gridYNum     Y方向格子数量
	 */
	public void init(int initSnakeLen, int foodNums, int gridXNum, int gridYNum) {
		System.err.printf("%.3f [WARNING] 游戏窗口初始化\n",
				(float) (System.currentTimeMillis() - Constant.TIME_STAMP) / 1000);
		Snake snake = new Snake(Constant.INIT_SNAKE_LENGTH);
		gameWindow = new GameWindow(snake);
		gamePanel = new JPanel(new FlowLayout()) {
			@Override
			public void paint(Graphics g) {
				super.paint(g);
				for (int i = 0; i < gridYNum; i++) {
					for (int j = 0; j < gridXNum; j++) {
						g.drawRect(Constant.GRID_WIDTH * j + Constant.DRAW_X_OFFSET,
								Constant.GRID_HEIGHT * i + Constant.DRAW_Y_OFFSET,
								Constant.GRID_WIDTH, Constant.GRID_HEIGHT);
					}
				}
				gameWindow.getSnake().paint(g);
				ArrayList<Food> food = gameWindow.getFoods();
				for (Food value : food) value.paint(g);
				g.setFont(Constant.BUTTON_FONT);
				//绘制得分信息
				g.drawString("当前得分:" + gameWindow.getScore(), Constant.DRAW_X_OFFSET,
						(gridYNum + 5) * Constant.GRID_HEIGHT);
			}
		};
		//绘制地图GamePanel
		gameWindow.setGamePanel(gamePanel);
		thisFrame = new JFrame("Greedy Snake");
		thisFrame.setBackground(Color.LIGHT_GRAY);
		thisFrame.add(gameWindow.getGamePanel());
		thisFrame.setBounds(Constant.GAME_WINDOW_X_OFFSET, Constant.GAME_WINDOW_Y_OFFSET,
				(gridXNum + 5) * Constant.GRID_WIDTH,
				(gridYNum + 5) * Constant.GRID_HEIGHT + Constant.BOTTOM_DOCK_HEIGHT);
		thisFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		thisFrame.setMinimumSize(new Dimension((gridXNum + 5) * Constant.GRID_WIDTH,
				(gridYNum + 5) * Constant.GRID_HEIGHT + Constant.BOTTOM_DOCK_HEIGHT));
		thisFrame.setVisible(true);
		gameWindow.setSnake(new Snake(initSnakeLen));
		ArrayList<Food> foods = new ArrayList<>();
		for (int i = 0; i < foodNums; i++) {
			foods.add(new Food(
					new Point((Util.random(Constant.GRID_X_NUM)),
							(Util.random(1, Constant.GRID_Y_NUM))),
					ComponentColor.getRandomColor()));
		}
		gameWindow.setFoods(foods);
		JPanel panel = gameWindow.getGamePanel();
		JButton button = new JButton("暂停...按Esc恢复");
		button.setFont(Constant.BUTTON_FONT);
		button.setVisible(false);
		button.setEnabled(false);
		panel.add(button);
		button = new JButton();
		button.setFont(Constant.BUTTON_FONT);
		button.setEnabled(false);
		button.setVisible(false);
		panel.add(button);
		thisFrame.validate();
		gameStarted = true;
	}

	/**
	 * 各种事件的注册
	 */
	public void regEvent() {
		thisFrame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				System.err.printf("%.3f [WARNING] 窗口关闭\n",
						(float) (System.currentTimeMillis() - Constant.TIME_STAMP) / 1000);
				gameStarted = false;
				superFrame.setVisible(true);
			}
		});
		System.err.printf("%.3f [WARNING] 按键监听注册\n",
				(float) (System.currentTimeMillis() - Constant.TIME_STAMP) / 1000);
		thisFrame.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				System.out.printf("%.3f [INFO] 监听到按键\n",
						(float) (System.currentTimeMillis() - Constant.TIME_STAMP) / 1000);
				if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
					Direction OriginDirection = gameWindow.getSnake().getDirection();
					if (OriginDirection == Direction.DOWN || OriginDirection == Direction.UP)
						return;
					gameWindow.getSnake().setDirection(Direction.UP);
				} else if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
					Direction OriginDirection = gameWindow.getSnake().getDirection();
					if (OriginDirection == Direction.DOWN || OriginDirection == Direction.UP)
						return;
					gameWindow.getSnake().setDirection(Direction.DOWN);
				} else if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
					Direction OriginDirection = gameWindow.getSnake().getDirection();
					if (OriginDirection == Direction.LEFT || OriginDirection == Direction.RIGHT)
						return;
					gameWindow.getSnake().setDirection(Direction.RIGHT);
				} else if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
					Direction OriginDirection = gameWindow.getSnake().getDirection();
					if (OriginDirection == Direction.LEFT || OriginDirection == Direction.RIGHT)
						return;
					gameWindow.getSnake().setDirection(Direction.LEFT);
				} else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					JButton jButton = (JButton) GameMain.getGameWindow().getGamePanel().getComponent(0);

					if (gameWindow.getGameContinue()) {
						gameWindow.pauseGame();
						jButton.setVisible(true);
					} else {
						gameWindow.resumeGame();
						jButton.setVisible(false);
					}
				}
			}
		});
	}

	/**
	 * 开始游戏,并新建一个线程
	 */
	public void startGame() {
		System.err.println((float) (System.currentTimeMillis() - Constant.TIME_STAMP) / 1000
				+ " [WARNING] 游戏开始");
		gameWindow.resumeGame();
		new Thread(() -> {
			try {
				while (gameStarted) {
					if (!gameWindow.getGameContinue()) {
						Thread.sleep(500);
						continue;
					}
					getGameWindow().getSnake().move();
					getGameWindow().getGamePanel().repaint();
					Thread.sleep(Constant.SNAKE_SPEED);
				}
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}

		}).start();
	}

}
