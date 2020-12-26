package cf.vbnm.gui.components;

import cf.vbnm.util.Util;

import java.awt.*;

public class ComponentColor {
	private Color color;

	public ComponentColor(Color color) {
		this.color = color;
	}


	public Color getColor() {
		return color;
	}


	public void setColor(Color color) {
		this.color = color;
	}

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
