package cf.vbnm.gui.components;

/**
 * 抽象食物
 * 可以扩展的食物类
 * 本来想设置加分的食物和减分的毒物而扩展出来的类
 */
public abstract class AbstractFood extends AbstractComponent {
	/**
	 * 设置食物类型
	 * 比如食物,毒物,etc.
	 */
	public abstract void setFoodType();
}
