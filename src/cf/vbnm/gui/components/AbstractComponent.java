package cf.vbnm.gui.components;

/**
 * 抽象组件类
 * 贪吃蛇,食物都是游戏组件
 */
public abstract class AbstractComponent {
	/**
	 * 设置组件颜色
	 *
	 * @param color 要设置的颜色
	 */
	public abstract void setColor(ComponentColor color);
}
