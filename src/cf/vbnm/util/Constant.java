package cf.vbnm.util;

import java.awt.*;

/**
 * 这个类存储程序可以更改的常量
 */
public final class Constant {
	/**
	 * 不需要new这个类
	 */
	private Constant() {
	}

	/**
	 * 开始页面的字体格式
	 */
	public static final Font BUTTON_FONT = new Font("宋体", Font.BOLD | Font.ITALIC, 25);
	/**
	 * 网格地图的横纵坐标的偏移量
	 */
	public static final int DRAW_X_OFFSET = 10;
	public static final int DRAW_Y_OFFSET = 7;
	/**
	 * 一个地图格子的长宽
	 */
	public static final int GRID_WIDTH = 10;
	public static final int GRID_HEIGHT = 10;
	/**
	 * 贪吃蛇一节body的长宽
	 */
	public static final int SNAKE_BODY_WIDTH = 9;
	public static final int SNAKE_BODY_HEIGHT = 9;
	/**
	 * 游戏时底部的状态栏
	 */
	public static final int BOTTOM_DOCK_HEIGHT = 60;
	/**
	 * 蛇的初始长度
	 */
	public static final int INIT_SNAKE_LENGTH = 5;
	/**
	 * 地图网格的数量
	 */
	public static final int GRID_X_NUM = 40;
	public static final int GRID_Y_NUM = 30;
	/**
	 * 食物数量
	 */
	public static final int FOODS_NUM = 50;
	/**
	 * 随机数种子最小更新时间
	 */
	public static final int RANDOM_SEED_UPDATE_TIME = 2000;
	/**
	 * 蛇的速度
	 */
	public static final int SNAKE_SPEED = 150;
	/**
	 * 主窗口大小
	 */
	public static final int MAIN_WINDOW_WIDTH = 300;
	public static final int MAIN_WINDOW_HEIGHT = 200;
	/**
	 * 两个窗口的偏移
	 */
	public static final int GAME_WINDOW_X_OFFSET =
			(1920 - (GRID_X_NUM + 5) * GRID_WIDTH) / 2;
	public static final int GAME_WINDOW_Y_OFFSET =
			(1080 - (GRID_Y_NUM + 7) * GRID_HEIGHT - BOTTOM_DOCK_HEIGHT) / 2;
	public static final int MAIN_WINDOW_X_OFFSET = (1920 - MAIN_WINDOW_WIDTH) / 2;
	public static final int MAIN_WINDOW_Y_OFFSET = (1080 - MAIN_WINDOW_HEIGHT) / 2;
	/**
	 * 程序启动时间, 记录日志用
	 */
	public static final long TIME_STAMP = System.currentTimeMillis();
}
