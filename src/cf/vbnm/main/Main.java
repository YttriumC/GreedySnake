package cf.vbnm.main;

import cf.vbnm.gui.Constant;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;

/**
 * 主类,启动类
 */
public class Main {
	private JFrame entry;
	private JPanel select;
	private JButton startGame;
	private JButton exit;
	private final GameMain gameMain = new GameMain();

	/**
	 * 程序注册
	 */
	public static void main(String[] args) {
		System.err.printf("%.3f [WARNING] 初始化\n", 0.0f);
		Main main = new Main();
		main.entry = new JFrame("彩色贪吃蛇小游戏");
		main.entry.setBounds(Constant.MAIN_WINDOW_X_OFFSET, Constant.MAIN_WINDOW_Y_OFFSET,
				Constant.MAIN_WINDOW_WIDTH, Constant.MAIN_WINDOW_HEIGHT);
		main.entry.setResizable(false);
		main.entry.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		main.select = new JPanel();
		main.startGame = new JButton("Start Game!");
		main.exit = new JButton("EXIT");
		main.startGame.setFont(Constant.BUTTON_FONT);
		main.exit.setFont(Constant.BUTTON_FONT);
		main.select.add(main.startGame);
		main.select.add(main.exit);
		main.entry.add(main.select);
		main.eventReg(main);
		main.entry.setVisible(true);
		main.entry.validate();

	}

	/**
	 * 第一个窗口的事件注册
	 */
	public void eventReg(Main main) {
		System.err.printf("%.3f [WARNING] 主窗口事件注册\n",
				(float) (System.currentTimeMillis() - Constant.TIME_STAMP) / 1000);
		main.startGame.addActionListener((e) -> {

			gameMain.init(Constant.INIT_SNAKE_LENGTH, Constant.FOODS_NUM,
					Constant.GRID_X_NUM, Constant.GRID_Y_NUM);
			gameMain.regEvent();
			gameMain.setSuperFrame(entry);
			entry.setVisible(false);
			gameMain.startGame();
		});
		main.exit.addActionListener((e -> {
			System.exit(0);
		}));
	}

}
