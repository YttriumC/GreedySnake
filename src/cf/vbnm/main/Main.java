package cf.vbnm.main;

import cf.vbnm.gui.Constant;

import javax.swing.*;

/**
 * 主类,启动类
 */
public class Main {
	/**
	 * 游戏入口
	 */
	private JFrame entry;
	/**
	 * 选择
	 */
	private JPanel select;
	/**
	 * 开始游戏按键
	 */
	private JButton startGame;
	/**
	 * 退出按键
	 */
	private JButton exit;
	/**
	 * 本类
	 */
	private final GameMain gameMain = new GameMain();

	/**
	 * 程序注册
	 *
	 * @param args 这个参数没啥用
	 */
	public static void main(String[] args) {
		System.err.printf("%.3f [WARNING] 初始化\n", 0.0f);
		Main main = new Main();
		main.entry = new JFrame("彩色贪吃蛇小游戏");
		main.entry.setBounds(Constant.MAIN_WINDOW_X_OFFSET, Constant.MAIN_WINDOW_Y_OFFSET,
				Constant.MAIN_WINDOW_WIDTH, Constant.MAIN_WINDOW_HEIGHT);
		/*不允许重设窗体大小*/
		main.entry.setResizable(false);
		main.entry.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		main.select = new JPanel();
		main.startGame = new JButton("Start Game!");
		main.exit = new JButton("EXIT");
		/*设置字体*/
		main.startGame.setFont(Constant.BUTTON_FONT);
		main.exit.setFont(Constant.BUTTON_FONT);
		/*添加组件*/
		main.select.add(main.startGame);
		main.select.add(main.exit);
		main.entry.add(main.select);
		/*注册事件*/
		main.eventReg(main);
		main.entry.setVisible(true);
		main.entry.validate();

	}

	/**
	 * 第一个窗口的事件注册
	 *
	 * @param main 给主窗口注册事件的,主窗口的对象在里面
	 */
	public void eventReg(Main main) {
		System.err.printf("%.3f [WARNING] 主窗口事件注册\n",
				(float) (System.currentTimeMillis() - Constant.TIME_STAMP) / 1000);
		/*开始游戏的事件注册*/
		main.startGame.addActionListener((e) -> {
			gameMain.init(Constant.INIT_SNAKE_LENGTH, Constant.FOODS_NUM,
					Constant.GRID_X_NUM, Constant.GRID_Y_NUM);
			gameMain.regEvent();
			gameMain.setSuperFrame(entry);
			entry.setVisible(false);
			gameMain.startGame();
		});
		/*退出的事件注册*/
		main.exit.addActionListener((e -> {
			System.exit(0);
		}));
	}

}
