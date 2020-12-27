package cf.vbnm.gui;

import java.awt.*;

/**
 * 绘画接口,实现此接口可以在屏幕上绘制图像
 */
public interface Drawable {
	/**
	 * X轴的绘制偏移
	 */
	int XOffset = Constant.DRAW_X_OFFSET;
	/**
	 * Y绘制偏移
	 */
	int YOffset = Constant.DRAW_Y_OFFSET;

	/**
	 * 绘制需要绘制的东西
	 *
	 * @param g 系统调用的,用来绘制图像
	 */
	void paint(Graphics g);
}
