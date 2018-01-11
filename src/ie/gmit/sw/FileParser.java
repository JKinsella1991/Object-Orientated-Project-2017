//James Kinsella - G00282261
//Object Oriented Programming Project 2017

package ie.gmit.sw;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;

/**
 * 
 * @author James Kinsella
 *
 */

public class FileParser implements Runnable {
	private BlockingQueue<Shingle> queue;
	private Deque<String> buffer = new LinkedList<>();
	private String fileName;
	private int shingleSize;
	private int docId;

	/**
	 * 
	 * @param queue
	 * @param fileName
	 * @param shingleSize
	 * @param docId
	 */
	public FileParser(BlockingQueue<Shingle> queue, String fileName, int shingleSize, int docId) {
		super();
		this.queue = queue;
		this.fileName = fileName;
		this.shingleSize = shingleSize;
		this.docId = docId;
	}

	@Override
	public void run() {

		BufferedReader br = null;

		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
		} catch (FileNotFoundException e1) {
			System.out.println("File not found! :/ Try again?");
		}
		String line = "";

		try {
			while ((line = br.readLine()) != null) {
				if (line.length() > 0) { 
					String uLine = line.toUpperCase();
					String[] words = uLine.split("\\s+");
					addWordsToBuffer(words);
				}
			}

			while (buffer.size() != 0) {
				Shingle s = getNextShingle();
				if (!(s == null)) {
					queue.put(s);
				}
			}//End While

		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		try {
			flushBuffer();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}// run

	/**
	 * 
	 * @param words
	 */
	private void addWordsToBuffer(String[] words) {
		for (String s : words) {
			buffer.add(s);
		}
	}

	/**
	 * 
	 * @return
	 */
	private Shingle getNextShingle() {
		StringBuffer sb = new StringBuffer();
		int counter = 0;

		while (counter < shingleSize) {
			if (buffer.peek() != null) {
				sb.append(buffer.poll());
				counter++;
			} else {
				counter = shingleSize;
			}
		}

		if (sb.length() > 0) {
			return (new Shingle(docId, sb.toString().hashCode()));
		} else {
			return (null);
		}
	}
	/**
	 * 
	 * @throws InterruptedException
	 */
	private void flushBuffer() throws InterruptedException {
		while (buffer.size() > 0) {
			Shingle s = getNextShingle();
			if (s != null) {
				queue.put(s);
			}
		}
		queue.put(new Poisin(0, 0));
	}

}