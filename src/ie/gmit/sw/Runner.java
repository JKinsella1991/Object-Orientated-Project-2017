//James Kinsella - G00282261
//Object Oriented Programming Project 2017

package ie.gmit.sw;

import java.util.concurrent.*;


public class Runner {

	public static void main(String[] args) throws InterruptedException {
		
		
		BlockingQueue<Shingle> q = new LinkedBlockingQueue<>();
		Menu menu = new Menu();
		
		menu.show();

		Thread t1 = new Thread(new FileParser(q, menu.getFileNameA(), menu.getShingleSize(),1), "A");
		Thread t2 = new Thread(new FileParser(q, menu.getFileNameB(), menu.getShingleSize(),2), "B");
		
		t1.start();
		t2.start();
		
		// wait for t1 and t2 to die before t3.start()
		t1.join();
		t2.join();
		
		Thread t3 = new Thread(new MinHasher(q,menu.getShingleSize()),"C");
		t3.start();
		t3.join();
	}

}