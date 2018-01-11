//James Kinsella - G00282261
//Object Oriented Programming Project 2017

package ie.gmit.sw;

/**
 * 
 * @author James Kinsella
 *
 */


public class Shingle {
	private int documentId;
	private int shingleHashCode;

	/**
	 * 
	 * @param documentId
	 * @param shingleHashCode
	 */
	public Shingle(int documentId, int shingleHashCode) {
		super();
		this.documentId = documentId;
		this.shingleHashCode = shingleHashCode;
	}

	/**
	 * 
	 * @return for all below
	 */
	public int getDocumentId() {
		return this.documentId;
	}

	protected void setDocumentId(int documentId) {
		this.documentId = documentId;
	}

	public int getShingleHashCode() {
		return shingleHashCode;
	}

	protected void setShingleHashCode(int shingleHashCode) {
		this.shingleHashCode = shingleHashCode;
	}

	@Override
	public boolean equals(Object arg0) {
		return super.equals(arg0);
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

}