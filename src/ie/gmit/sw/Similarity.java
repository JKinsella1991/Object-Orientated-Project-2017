//James Kinsella - G00282261
//Object Oriented Programming Project 2017

package ie.gmit.sw;

/**
 * 
 * @author James Kinsella
 *
 */

public abstract class Similarity {
	private final int intersection;
	private final int itemASize;
	private final int itemBSize;

	/**
	 * 
	 * @param intersection
	 * @param itemASize
	 * @param itemBSize
	 */
	public Similarity(int intersection, int itemASize, int itemBSize) {
		this.intersection = intersection;
		this.itemASize = itemASize;
		this.itemBSize = itemBSize;
	}

	public abstract float calculateIndex();

	/**
	 * 
	 * @return for all below
	 */
	public int getIntersection() {
		return intersection;
	}

	public int getFileOneSize() {
		return itemASize;
	}

	public int getFileTwoSize() {
		return itemBSize;
	}

}
