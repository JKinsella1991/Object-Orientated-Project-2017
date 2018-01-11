//James Kinsella - G00282261
//Object Oriented Programming Project 2017

package ie.gmit.sw;

/**
 * 
 * @author James Kinsella
 *
 */

public class Jaccard extends Similarity {
	private final int intersection;
	private final int fileOneSize;
	private final int fileTwoSize;
	private float jaccard;

	/**
	 * 
	 * @param intersection
	 * @param fileOneSize
	 * @param fileTwoSize
	 */
	public Jaccard(int intersection, int fileOneSize, int fileTwoSize) {
		super(intersection, fileOneSize, fileTwoSize);
		this.intersection = intersection;
		this.fileOneSize = fileOneSize;
		this.fileTwoSize = fileTwoSize;
	}

	@Override
	public float calculateIndex() {
		jaccard = (float) intersection / (((float) fileOneSize + (float) fileTwoSize) - (float) intersection);
		return jaccard;
	}
	
	//Generated Getters and Setters
	/**
	 * @return for all below
	 */
	public int getIntersection() {
		return intersection;
	}

	public int getFileOneSize() {
		return fileOneSize;
	}

	public int getFileTwoSize() {
		return fileTwoSize;
	}

}