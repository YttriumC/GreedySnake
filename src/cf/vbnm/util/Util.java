package cf.vbnm.util;

import cf.vbnm.gui.Constant;

import java.awt.*;
import java.util.Random;

/**
 * 工具类
 * 之所以不使用 (int)(Math.random()*bounds)是因为在某些情况中
 * 会有一些值难以取到,所以使用Random类直接生成线性同余算法的伪随机数
 */
public class Util {
	private static Random random;
	/**
	 * 当前随机数的种子
	 */
	private static long randomSeed;

	static {
		random = new Random();
		random.setSeed((randomSeed = System.currentTimeMillis()));
	}

	/**
	 * 返回一个伪随机数，它是取自此随机数生成器序列的、在 0（包括）
	 * 和指定值（不包括）之间均匀分布的 int 值。
	 * 此方法至少2秒更新一次随机数发生器的种子
	 *
	 * @param bounds 范围
	 * @return 返回[0, bounds)的伪随机整数
	 */
	public static int random(int bounds) {
//		random.setSeed(System.currentTimeMillis());
		long l = System.currentTimeMillis();
		if (l - randomSeed > Constant.RANDOM_SEED_UPDATE_TIME)
			random.setSeed((randomSeed = l));
		return random.nextInt(bounds);
	}

	/**
	 * 返回一个范围的随机数
	 *
	 * @param start  从start开始
	 * @param bounds 范围
	 * @return 返回[start, bounds)范围内的伪随机整数
	 */
	public static int random(int start, int bounds) {
		return random(bounds - start) + start;
	}


	/**
	 * 此方法直接随机生成一个Point对象,其中Point对象的X,Y取值为[0,XBounds),[0,YBounds)
	 *
	 * @param XBounds X的取值范围,从0开始
	 * @param YBounds Y的取值范围,从0开始
	 * @return 返回一个随机生成的Point对象, 其中Point对象的X, Y取值为[0, XBounds),[0,YBounds)
	 */
	public static Point randomPoint(int XBounds, int YBounds) {
		return new Point(random(XBounds), random(YBounds));
	}
}
