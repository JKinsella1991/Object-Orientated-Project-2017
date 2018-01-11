//James Kinsella - G00282261
//Object Oriented Programming Project 2017


package ie.gmit.sw;
/**
 * 
 * @author James Kinsella
 * Main interface for application
 */
//Provides the user a way to interface with the application
	public class Menu{
		private String fileOne;
		private String fileTwo;
		private int shingleSize;
		private int option;
	
	public Menu() {
		super();
	}
	
	//Primary Menu method
	/**
	 * 
	 * @return
	 */
	public Menu show() {
			//Refers to the Addition.java class for added functionality
			option = Addition.getInputInt(Addition.showMainMenu);
			if (option==1){
				this.fileOne=Addition.getInputString("\nEnter first file name:");
				this.fileTwo=Addition.getInputString("\nEnter second file name:");
				this.shingleSize = Addition.getInputInt("\nEnter Shingle Size:");
				System.out.println("First file:" + this.fileOne + "\nSecond file:" + fileTwo + "\nShingle size:" + shingleSize + "\n");
				System.out.println("Starting...");
			}	
		return null;	
	}
	
	//Generated Getters and Setters from Source
	/**
	 * 
	 * @return for all below
	 */
	public String getFileNameA() {
		return fileOne;
	}
	protected void setFileNameA(String fileOne) {
		this.fileOne = fileOne;
	}
	public String getFileNameB() {
		return fileTwo;
	}
	protected void setFileNameB(String fileTwo) {
		this.fileTwo = fileTwo;
	}
	public int getShingleSize() {
		return shingleSize;
	}
	protected void setShingleSize(int shingleSize) {
		this.shingleSize = shingleSize;
	}

}