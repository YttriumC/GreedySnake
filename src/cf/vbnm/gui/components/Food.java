package cf.vbnm.gui.components;

import cf.vbnm.gui.Constant;
import cf.vbnm.gui.Drawable;
import cf.vbnm.util.Util;

import java.awt.*;

public class Food extends AbstractFood implements Drawable {
	private EnumFood foodType;
	private ComponentColor color;
	private Point coordinate;
//	public static final Dimension FOOD_SIZE = new Dimension(5, 5);

	public Food(Point coordinate, ComponentColor color) {
		this.coordinate = coordinate;
		setFoodType();
		this.color = color;
	}

	public ComponentColor getColor() {
		return color;
	}

	public Point getCoordinate() {
		return coordinate;
	}

//	public static Dimension getFoodSize() {
//		return FOOD_SIZE;
//	}

	public Food(EnumFood food) {
		foodType = food;
	}

	public void setFoodType(EnumFood foodType) {
		this.foodType = foodType;
	}

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
		;
	}

	public EnumFood getFoodType() {
		return foodType;
	}

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

	@Override
	void setColor(ComponentColor color) {
		this.color = color;
	}
}
