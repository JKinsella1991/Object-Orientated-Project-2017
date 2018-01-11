//James Kinsella - G00282261
//Object Oriented Programming Project 2017

//Helped along with: https://gist.github.com/tpeng/3496464
//Plus additional class notes, etc

package ie.gmit.sw;
/**
 * @author James Kinsella
 */

import java.util.*;
import java.util.concurrent.*;


public class MinHasher implements Runnable {

	private BlockingQueue<Shingle> q;
	private Map<Integer, List<Integer>> map = new ConcurrentHashMap<Integer, List<Integer>>();
	private ExecutorService pool;
	private Set<Integer> minHashes;
	private int amtMinHashes;

	/**
	 * 
	 * @param q
	 * @param k
	 */
	public MinHasher(BlockingQueue<Shingle> q, int k) {
		this.q = q;
		this.amtMinHashes = k;
		pool = Executors.newFixedThreadPool(k);
		init();
	}

	
	public void init() {
		minHashes = new TreeSet<Integer>();

		// random number for the minhashing
		Random randInt = new Random();

		for (int i = 0; i < amtMinHashes; i++) {
			minHashes.add(randInt.nextInt());
		}
	}

	/**
	 * 
	 * @param s
	 * @return
	 */
	public synchronized int hasher(Shingle s) {
		int minValue = Integer.MAX_VALUE;
		for (Integer hash : minHashes) {

			int minHashed = s.getShingleHashCode() ^ hash;
			if (minHashed < minValue) {
				minValue = minHashed;
			}
		}
		return minValue;
	}

	@Override
	public void run() {
		List<Integer> list1 = new ArrayList<>();
		List<Integer> list2 = new ArrayList<>();
		List<Integer> voidList = new ArrayList<>();
		int docCount = 2;

		while (docCount > 0) {
			try {
				Shingle s = q.take();

				try {
					if (s instanceof Poisin == false) {
						pool.execute(new Runnable() {
							@Override
							public void run() {
								if (s.getDocumentId() == 1) 
									{
									list1.add(hasher(s));
									}
								else if (s.getDocumentId() == 2) 
									{
									list2.add(hasher(s));
									}
								else 
									{
									voidList.add(hasher(s));
									}
							}
						});
					} else {
						docCount--;
					}
				} catch (IndexOutOfBoundsException e) {
					// Getting weird IOoB Exception, try/catch to clear hopefully
					e.printStackTrace();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
				System.out.println("Interuppted exception " + e);
			}
		}

		shutdownAndAwaitTermination(pool);

		int k1 = list1.size();
		int k2 = list2.size();

		// put list1,list2 to map at specified index
		map.put(1, list1);
		map.put(2, list2);

		List<Integer> intersection = map.get(1);
		intersection.retainAll(map.get(2));

		// compute jaccard index
		Similarity sim = new Jaccard(intersection.size(), k1, k2);
		Addition.showJaccardResult(sim);
	}


	void shutdownAndAwaitTermination(ExecutorService pool) {
		pool.shutdown(); 
		try {
			// Wait a while for existing tasks to terminate
			if (!pool.awaitTermination(60, TimeUnit.SECONDS)) {
				pool.shutdownNow(); 
				
				if (!pool.awaitTermination(60, TimeUnit.SECONDS))
					System.err.println("Pool did not terminate");
			}
		} catch (InterruptedException ie) {
			// (Re-)Cancel if current thread also interrupted
			pool.shutdownNow();
			// Preserve interrupt status
			Thread.currentThread().interrupt();
		}
	}

}