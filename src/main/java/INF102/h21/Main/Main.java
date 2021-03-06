package INF102.h21.Main;

import INF102.h21.list.List;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.function.Consumer;

import INF102.h21.list.ArrayList;
import INF102.h21.list.LinkedList;

public class Main {

	public static Random rand = new Random();

	public static DecimalFormat formatter = new DecimalFormat("#, ###");

	private static int nOperations;

	public static void main(String[] args) {
		List<Integer> arrayList = new ArrayList<>();
		List<Integer> linkedList = new LinkedList<>();

		int initalSize = 1000;
		for (int i = 0; i < initalSize; i++) {
			arrayList.addLast(i);
			linkedList.addLast(i);
		}

		// For each operation (insertion and access) time the 
		// process for both LinkedList and ArrayList
		long timeElapsedArray;
		long timeElapsedLinked;
		nOperations = 10000;

		// Random Insertion
		// ArrayList
		System.out.printf("----%sRandom Insertions----%n", formatter.format(nOperations));
		timeElapsedArray = timeListMethod(arrayList, Main::randomInsertion);
		printResult(arrayList, timeElapsedArray);
		// Linked List
		timeElapsedLinked = timeListMethod(linkedList, Main::randomInsertion);
		printResult(linkedList, timeElapsedLinked);
		printPercentage(timeElapsedArray, timeElapsedLinked);

		// Head Insertion
		// ArrayList
		System.out.printf("%n----%sHead Insertions----%n", formatter.format(nOperations));
		timeElapsedArray = timeListMethod(arrayList, Main::headInsertion);
		printResult(arrayList, timeElapsedArray);
		// Linked List
		timeElapsedLinked = timeListMethod(linkedList, Main::headInsertion);
		printResult(linkedList, timeElapsedLinked);
		printPercentage(timeElapsedArray, timeElapsedLinked);

		// Tail Insertion
		// ArrayList
		System.out.printf("%n----%sTail Insertions----%n", formatter.format(nOperations));
		timeElapsedArray = timeListMethod(arrayList, Main::tailInsertion);
		printResult(arrayList, timeElapsedArray);
		// Linked List
		timeElapsedLinked = timeListMethod(linkedList, Main::tailInsertion);
		printResult(linkedList, timeElapsedLinked);
		printPercentage(timeElapsedArray, timeElapsedLinked);

		// Random Access
		// ArrayList
		System.out.printf("%n----%sRandom Access----%n", formatter.format(nOperations));
		timeElapsedArray = timeListMethod(arrayList, Main::randomAccess);
		printResult(arrayList, timeElapsedArray);
		// Linked List
		timeElapsedLinked = timeListMethod(linkedList, Main::randomAccess);
		printResult(linkedList, timeElapsedLinked);
		printPercentage(timeElapsedArray, timeElapsedLinked);

		// Head Access
		// ArrayList
		System.out.printf("%n----%sHead Access----%n", formatter.format(nOperations));
		timeElapsedArray = timeListMethod(arrayList, Main::headAccess);
		printResult(arrayList, timeElapsedArray);
		// Linked List
		timeElapsedLinked = timeListMethod(linkedList, Main::headAccess);
		printResult(linkedList, timeElapsedLinked);
		printPercentage(timeElapsedArray, timeElapsedLinked);

		// Tail Access
		// ArrayList
		System.out.printf("%n----%sTail Access----%n", formatter.format(nOperations));
		timeElapsedArray = timeListMethod(arrayList, Main::tailAccess);
		printResult(arrayList, timeElapsedArray);
		// Linked List
		timeElapsedLinked = timeListMethod(linkedList, Main::tailAccess);
		printResult(linkedList, timeElapsedLinked);
		printPercentage(timeElapsedArray, timeElapsedLinked);
	}


	
	public static void printResult(List<Integer> list, long microSeconds) {
		String listType = list.getClass().getSimpleName();
		double seconds = microSeconds / 1000000.0;
		System.out.printf("%-15s| time elapsed: %-7d microseconds (%f seconds)%n", listType, microSeconds, seconds);
	}

	public static void printPercentage(double timeArray, double timeLinked) {
		if (timeArray > timeLinked) {
			double percentage = (timeLinked / timeArray) * 100.0;
			System.out.println("LINKEDLIST BEST");
			System.out.printf("LinkedList spent %.1f %% of the time ArrayList did.%n", percentage);
		} else {
			double percentage = (timeArray / timeLinked) * 100.0;
			System.out.println("ARRAYLIST BEST");
			System.out.printf("ArrayList spent %.1f %% of the time LinkedList did.%n", percentage);
		}
	}

	/**
	 * Get <code>n</code> elements from <code>list</code> at random indices
	 * 
	 * @param list of integers
	 */
	public static void randomAccess(List<Integer> list) {
		int listLength = list.size();
		for (int i = 0; i < nOperations; i++) {
			int randomIndex = rand.nextInt(listLength - 1);
			list.get(randomIndex);
		}
	}

	/**
	 * Get <code>n</code> elements from <code>list</code> at index 0
	 * 
	 * @param list of integers
	 */
	public static void headAccess(List<Integer> list) {
		for (int i = 0; i < nOperations; i++) {
			list.get(0);
		}
	}

	/**
	 * Get <code>n</code> elements from <code>list</code> at index 0
	 * 
	 * @param list of integers
	 */
	public static void tailAccess(List<Integer> list) {
		int listLength = list.size();
		for (int i = 0; i < nOperations; i++) {
			list.get(listLength - 1);
		}
	}

	/**
	 * Insert <code>n</code> elements in <code>list</code> at random indices
	 * 
	 * @param list of integers
	 */
	public static void randomInsertion(List<Integer> list) {
		int listLength = list.size();
		for (int i = 0; i < nOperations; i++) {
			int randomIndex = rand.nextInt(listLength - 1);
			list.add(randomIndex, 42);
		}
	}

	/**
	 * Insert <code>n</code> elements in <code>list</code>. Each new element at the
	 * end of the list.
	 * 
	 * @param list of integers
	 */
	public static void tailInsertion(List<Integer> list) {
		for (int i = 0; i < nOperations; i++) {
			list.addLast(42);
		}
	}

	/**
	 * Insert <code>n</code> elements in <code>list</code>. Each new element at the
	 * start of the list.
	 * 
	 * @param list of integers
	 */
	public static void headInsertion(List<Integer> list) {
		for (int i = 0; i < nOperations; i++) {
			list.add(0, 42);
		}
	}
	
	/**
	 * Time <code>method</code> with <code>list</code> as input
	 * @param list
	 * @param method
	 * @return nanoseconds spent in operation
	 */
	public static long timeListMethod(List<Integer> list, Consumer<List<Integer>> method) {
		long startTime = System.nanoTime();
		method.accept(list);
		long endTime = System.nanoTime();
		long timeElapsed = (endTime - startTime) / 1000;
		return timeElapsed;
	}
}
