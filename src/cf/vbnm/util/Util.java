package cf.vbnm.util;

import cf.vbnm.gui.Constant;

import java.awt.*;
import java.util.Random;

/**
 * 工具类
 */
public class Util {
	private static Random random;
	private static long randomSeed;

	static {
		random = new Random();
		random.setSeed((randomSeed = System.currentTimeMillis()));
	}

	/**
	 * 返回一个伪随机数，它是取自此随机数生成器序列的、在 0（包括）和指定值（不包括）之间均匀分布的 int 值。
	 */
	public static int random(int bounds) {
//		random.setSeed(System.currentTimeMillis());
		long l = System.currentTimeMillis();
		if (l - randomSeed > Constant.RANDOM_SEED_UPDATE_TIME)
			random.setSeed((randomSeed = l));
		return random.nextInt(bounds);
	}

	public static int random(int start, int bounds) {
		return random(bounds - start) + start;
	}


	public static Point randomPoint(int XBounds, int YBounds) {
		return new Point(random(XBounds), random(YBounds));
	}
	/*
	public static void main(String[] args) {
		int i = 100;
		while (i-- > 0) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(random(9));
		}
	}*/
}
