package cf.vbnm.gui.components;

import cf.vbnm.util.Constant;
import cf.vbnm.gui.Drawable;
import cf.vbnm.util.Util;

import java.awt.*;

/**
 * 该类定义了食物的实现
 */
public class Food extends AbstractFood implements Drawable {
	/**
	 * 食物类型,可以加不同得分
	 */
	private EnumFood foodType;
	/**
	 * 食物颜色
	 */
	private ComponentColor color;
	/**
	 * 食物位置
	 */
	private Point coordinate;

	/**
	 * 构造方法, 生成一个食物
	 *
	 * @param color      食物颜色
	 * @param coordinate 食物位置
	 */
	public Food(Point coordinate, ComponentColor color) {
		this.coordinate = coordinate;
		setFoodType();
		this.color = color;
	}

	/**
	 * 获得食物的颜色
	 *
	 * @return 食物颜色
	 */
	public ComponentColor getColor() {
		return color;
	}

	/**
	 * 获得食物位置
	 *
	 * @return 食物位置
	 */
	public Point getCoordinate() {
		return coordinate;
	}

	/**
	 * 随机生成一个食物类型,
	 * 其中中等食物概率3/5,好食物概率1/5,差食物概率1/5
	 */
	public void setFoodType() {
		int random = Util.random(5);
		switch (random) {
			case 0:
				foodType = EnumFood.BAD;
			case 1:
			case 2:
			case 3:
				foodType = EnumFood.MIDDLE;
			default:
				foodType = EnumFood.GOOD;
		}
	}

	/**
	 * 获得食物类型,以便加相应的分
	 *
	 * @return 食物类型
	 */
	public EnumFood getFoodType() {
		return foodType;
	}

	/**
	 * 生成一个随机的食物,但是不能跟蛇的位置重叠
	 *
	 * @param snake 贪吃蛇
	 * @return 返回一个食物
	 */
	public Food generateFood(Snake snake) {
		Point randomPoint = Util.randomPoint(Constant.GRID_X_NUM, Constant.GRID_Y_NUM);
		while (true) {
			boolean innerFlag = true;
			for (Point point : snake.getSnakePosition()) {
				if (randomPoint.equals(point)) {
					randomPoint = Util.randomPoint(Constant.GRID_X_NUM, Constant.GRID_Y_NUM);
					innerFlag = false;
					break;
				}
			}
			if (innerFlag)
				return new Food(randomPoint, ComponentColor.getRandomColor());
		}
	}

	/**
	 * 绘制食物
	 *
	 * @param g 系统调用
	 */
	@Override
	public void paint(Graphics g) {
		Color c = g.getColor();
		g.setColor(color.getColor());
		g.fillRect(coordinate.x * Constant.GRID_WIDTH + Drawable.XOffset + 1,
				coordinate.y * Constant.GRID_HEIGHT + Drawable.YOffset + 1,
				Constant.SNAKE_BODY_WIDTH, Constant.SNAKE_BODY_HEIGHT
		);
		g.setColor(c);
	}

	/**
	 * 设置颜色
	 *
	 * @param color 颜色
	 */
	@Override
	public void setColor(ComponentColor color) {
		this.color = color;
	}
}
