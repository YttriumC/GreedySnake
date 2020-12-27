package cf.vbnm.gui;

import java.awt.*;

/**
 * 绘画接口,实现此接口可以在屏幕上绘制图像
 */
public interface Drawable {
	int XOffset = Constant.DRAW_X_OFFSET, YOffset = Constant.DRAW_Y_OFFSET;


	void paint(Graphics g);
}
