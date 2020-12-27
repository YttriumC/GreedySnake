package cf.vbnm.gui.components;

import cf.vbnm.util.Util;

import java.awt.*;

/**
 * 组件颜色
 */
public class ComponentColor {
	/**
	 * 颜色
	 */
	private Color color;

	/**
	 * 构造方法
	 *
	 * @param color 颜色
	 */
	public ComponentColor(Color color) {
		this.color = color;
	}

	/**
	 * 获取颜色
	 *
	 * @return 返回颜色
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * 设置颜色
	 *
	 * @param color 颜色
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * 得到一个随机的组件颜色
	 *
	 * @return 组件颜色
	 */
	public static ComponentColor getRandomColor() {
		int random = Util.random(9);
		switch (random) {
			case 0:
				return new ComponentColor(Color.BLACK);
			case 1:
				return new ComponentColor(Color.BLUE);
			case 2:
				return new ComponentColor(Color.CYAN);
			case 3:
				return new ComponentColor(Color.GRAY);
			case 4:
				return new ComponentColor(Color.GREEN);
			case 5:
				return new ComponentColor(Color.MAGENTA);
			case 6:
				return new ComponentColor(Color.ORANGE);
			case 7:
				return new ComponentColor(Color.RED);
			case 8:
				return new ComponentColor(Color.YELLOW);
			default:
				return new ComponentColor(Color.WHITE);
		}
	}

}
