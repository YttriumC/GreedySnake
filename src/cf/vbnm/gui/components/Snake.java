package cf.vbnm.gui.components;

import cf.vbnm.gui.Constant;
import cf.vbnm.gui.Drawable;
import cf.vbnm.main.GameMain;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * 该类描述了一个贪吃蛇所具有的属性
 */
public class Snake implements Drawable, Movable, HitBorder {
	/**
	 * 蛇的身体
	 */
	Body snakeBody;
	/**
	 * 蛇走的方向
	 */
	Direction direction;
	/**
	 * 玩家输入的方向,以避免一个隐藏的bug
	 */
	Direction tempDirection;

	/**
	 * 蛇的身体的定义,成员内部类
	 */
	private static class Body {
		/**
		 * 蛇身体的各个颜色的链表
		 */
		private final ArrayList<ComponentColor> bodyColor;
		/**
		 * 蛇身体各个部分的坐标
		 */
		private LinkedList<Point> coordinates;

		/**
		 * 构造方法,生成一个蛇的身体
		 *
		 * @param snakeLength 蛇初始长度
		 */
		public Body(int snakeLength) {
			bodyColor = new ArrayList<>();
			coordinates = new LinkedList<>();
			for (int i = 0; i < snakeLength; i++) {
				bodyColor.add(ComponentColor.getRandomColor());
				coordinates.add(new Point(i, 0));
			}
		}

		/**
		 * 添加一个蛇的方块
		 *
		 * @param componentColor 蛇的颜色
		 * @param coordinate     蛇的位置
		 */
		public void addBody(ComponentColor componentColor, Point coordinate) {
			bodyColor.add(componentColor);
			coordinates.addLast(coordinate);
		}

		/**
		 * 得到蛇的身体颜色
		 *
		 * @return 蛇的身体组成颜色的数组
		 */
		public ArrayList<ComponentColor> getBodyColor() {
			return bodyColor;
		}

		/**
		 * 得到蛇的身体位置
		 *
		 * @return 蛇的位置的链表
		 */
		public LinkedList<Point> getCoordinates() {
			return coordinates;
		}
	}

	/**
	 * 得到蛇的各个身体部位的坐标
	 *
	 * @return 蛇的位置的坐标
	 */
	public LinkedList<Point> getSnakePosition() {
		return snakeBody.coordinates;
	}

	/**
	 * 获取贪吃蛇当前前进方向
	 *
	 * @return 前进方向
	 */
	public Direction getDirection() {
		return direction;
	}

	/**
	 * 设置贪吃蛇当前前进方向
	 *
	 * @param direction 方向
	 */
	public void setDirection(Direction direction) {
		this.tempDirection = direction;
	}

	/**
	 * 实现绘制蛇的方法,由电脑调用
	 *
	 * @param g 图像
	 */
	@Override
	public void paint(Graphics g) {
		Color c = g.getColor();
		Iterator<Point> coordinateIterator = snakeBody.getCoordinates().iterator();
		Iterator<ComponentColor> colorIterator = snakeBody.getBodyColor().iterator();
		while (coordinateIterator.hasNext() && colorIterator.hasNext()) {
			g.setColor(colorIterator.next().getColor());
			Point next = coordinateIterator.next();
			g.fillRect(next.x * Constant.GRID_WIDTH + Drawable.XOffset + 1,
					next.y * Constant.GRID_HEIGHT + Drawable.YOffset + 1,
					Constant.SNAKE_BODY_WIDTH, Constant.SNAKE_BODY_HEIGHT);
		}
		g.setColor(c);
	}

	/**
	 * 生成一个贪吃蛇
	 *
	 * @param initLength 蛇的初始长度
	 */
	public Snake(int initLength) {
		snakeBody = new Body(initLength);
		direction = Direction.RIGHT;
		tempDirection = Direction.RIGHT;
	}

	/**
	 * Movable接口回调扩展方法
	 *
	 * @param direction 移动方向
	 */
	public void move0(Direction direction) {
		LinkedList<Point> coordinates = snakeBody.getCoordinates();
		Point first = coordinates.removeFirst();
		Point last = coordinates.getLast();
		if (direction == Direction.UP) {
			first.x = last.x;
			first.y = last.y - 1;
		} else if (direction == Direction.DOWN) {
			first.x = last.x;
			first.y = last.y + 1;
		} else if (direction == Direction.LEFT) {
			first.x = last.x - 1;
			first.y = last.y;
		} else if (direction == Direction.RIGHT) {
			first.x = last.x + 1;
			first.y = last.y;
		}
		coordinates.addLast(first);
	}

	/**
	 * 撞墙后的回调,(终止游戏)
	 */
	@Override
	public void hitBorder() {
		GameMain.getGameWindow().pauseGame();
		JButton jButton = (JButton) GameMain.getGameWindow().getGamePanel().getComponent(1);
		jButton.setText("你失败了,得分是" + GameMain.getGameWindow().getScore());
		jButton.setVisible(true);
		try {
			Thread.sleep(Constant.SNAKE_SPEED);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		jButton.repaint();
	}

	/**
	 * 判断前面有没有食物,同时吃掉前面的食物并且身体长度+1
	 *
	 * @param p     当前所在的点
	 * @param d     方向
	 * @param foods 所有的食物
	 * @return 是否吃到了食物
	 */
	public boolean isFoodFront(Point p, Direction d, ArrayList<Food> foods) {
		p = (Point) p.clone();
		if (d == Direction.UP) {
			p.y -= 1;
			return eatFood(p, foods);
		} else if (d == Direction.DOWN) {
			p.y += 1;
			return eatFood(p, foods);
		} else if (d == Direction.RIGHT) {
			p.x += 1;
			return eatFood(p, foods);
		} else if (d == Direction.LEFT) {
			p.x -= 1;
			return eatFood(p, foods);
		}
		return false;
	}

	/**
	 * Movable接口的实现
	 */
	@Override
	public void move() {
		System.out.printf("%.3f [INFO] 1 step\n",
				(float) (System.currentTimeMillis() - Constant.TIME_STAMP) / 1000);
		direction = tempDirection;
		move1(GameMain.getGameWindow().getFoods());
	}

	/**
	 * 边界检测, 同时检测会不会碰到自己
	 *
	 * @param direction 需要检测的方向
	 * @return 是否碰到边界, 或者吃到自己
	 */
	public boolean hitDetect(Direction direction) {
		Point last = snakeBody.getCoordinates().getLast();
		last = (Point) last.clone();
		if (direction == Direction.UP) {
			if (last.y-- <= 0) {
				return true;
			}
		} else if (direction == Direction.DOWN) {
			if (last.y++ >= Constant.GRID_Y_NUM - 1) {
				return true;
			}
		} else if (direction == Direction.LEFT) {
			if (last.x-- <= 0) {
				return true;
			}
		} else if (direction == Direction.RIGHT) {
			if (last.x++ >= Constant.GRID_X_NUM - 1) {
				return true;
			}
		}
		for (Point point : snakeBody.getCoordinates()) {
			if (point.equals(last)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 吃掉一个方块,然后生成一个新方块
	 *
	 * @param p     检测的方块,如果P上面有食物,就吃掉
	 * @param foods 所有的食物
	 * @return 是否吃到食物
	 */
	private synchronized boolean eatFood(Point p, ArrayList<Food> foods) {
		Food food;
		for (int i = 0; i < foods.size(); i++) {
			food = foods.get(i);
			if (food.getCoordinate().equals(p)) {
				snakeBody.addBody(food.getColor(), food.getCoordinate());
				/*根据吃到的不同方块属性加不同的分数*/
				switch (food.getFoodType()) {
					case BAD:
						GameMain.getGameWindow().addScore(1);
					case MIDDLE:
						GameMain.getGameWindow().addScore(2);
					case GOOD:
						GameMain.getGameWindow().addScore(3);
				}
				System.out.printf("%.3f [INFO] 吃到食物\n", (float) (System.currentTimeMillis() - Constant.TIME_STAMP) / 1000);
				foods.set(i, food.generateFood(this));
				return true;
			}
		}
		return false;
	}

	/**
	 * 移动, 如果有食物,则吃掉它;同时碰撞检测
	 *
	 * @param foods 食物
	 */
	public void move1(ArrayList<Food> foods) {
		if (isFoodFront(snakeBody.coordinates.getLast(), direction, foods)) {
			return;
		}
		/*		if (direction == Direction.UP) {*/
		if (!hitDetect(direction)) {
			move0(direction);
		} else {
			hitBorder();
		}

	}

}
