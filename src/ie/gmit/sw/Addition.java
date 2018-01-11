//James Kinsella - G00282261
//Object Oriented Programming Project 2017

package ie.gmit.sw;

import java.util.Scanner;

//Essentially a holding spot for additional functions for the application
public class Addition {

	static Scanner scanner = new Scanner(System.in);

	public static String getInputString(String input) {
		String output = "";

		System.out.println(input);
		output = setFileName(scanner.next());
		return output;
	}

	public static int getInputInt(String input) {
		int output;

		System.out.println(input);
		output = scanner.nextInt();
		return output;
	}

	public static String showMainMenu = "Welcome! Please select an option\n Main Menu\n1: Start Similarity Check\n2: Exit";


	public static void showJaccardResult(Similarity sim) {
		float jaccard = sim.calculateIndex();
		System.out.printf("Similarity Computed\n First file " + sim.getFileOneSize() + " shingles"
				+ "\nSecond file " + sim.getFileTwoSize() + " shingle" + sim.getIntersection()
				+ "\nJaccard Index: %.2f",  +jaccard);
	}

	//Appends a .txt extension if one doesn't exist
	public static String setFileName(String fileName) {
	
		String ext = ".txt";
		if (!fileName.contains(ext)) {
			return fileName + ext;
		}
		return fileName;
	}
}