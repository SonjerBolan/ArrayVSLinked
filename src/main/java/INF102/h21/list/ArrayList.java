package INF102.h21.list;

import java.util.Arrays;

public class ArrayList<T> implements List<T> {

	
	public static final int DEFAULT_CAPACITY = 10;
	
	private int n;
	
	private Object elements[];
	
	public ArrayList() {
		elements = new Object[DEFAULT_CAPACITY];
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public T get(int index) {
		if (index < 0 || index >= n)
			throw new IndexOutOfBoundsException("Index:" + index + " is out of bounds. The list has " + n + " n elements.");
		return (T) elements[index];
	}
	
	@Override
	public void add(int index, T element) {
		if (index < 0 || index > n)
			throw new IndexOutOfBoundsException("Index:" + index + " is out of bounds. The list has " + n + " n elements.");
		if (n+2 > elements.length)
			ensureCapacity();

		while (index < size()) {
			T tempElement = get(index);
			elements[index] = element;
			element = tempElement;
			index++;
		}
		elements[index] = element;
		n++;
	}
	
	public void ensureCapacity() {
		int newSize = elements.length * 2;
        elements = Arrays.copyOf(elements, newSize);
	}
	
	@Override
	public int size() {
		return n;
	}

	@Override
	public boolean isEmpty() {
		return n == 0;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder(n*3 + 2);
		str.append("[");
		for (int i = 0; i < n; i++) {
			str.append((T) elements[i]);
			if (i != n-1)
				str.append(", ");
		}
		str.append("]");
		return str.toString();
	}

}
