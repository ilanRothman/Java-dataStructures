package il.ac.telhai.ds.heap;

public class MinHeap<T extends Comparable<T>> {

	private T heap[];
	private int size;
	private int elemCount;

	@SuppressWarnings({"unchecked","rawtypes"})
	public MinHeap(int length) {
		heap = (T[]) new Comparable[length];
		size = length;
		elemCount = 0;
	}

	public MinHeap(T[] arr) {
		heap = arr;
		size = heap.length;
		elemCount = size;
		for(int i = size - 1; i >= 0; i-- ){
			heapify(i);
		}
	}

	private int left(int i){
		return (i+1)*2 - 1;
	}

	private int right(int i){
		return (i+1)*2;
	}

	private int parent(int i){
		int res = (i + 1)/2;
		return res - 1;
	}

	private void siftUp(int i){
		if(i <= 0)
			return;
		int smallest = 0;
		T temp;
		if(parent(i) >= 0 && heap[parent(i)].compareTo(heap[i]) > 0){
			smallest = i;
		}else {
			smallest = parent(i);
		}
		if(smallest == i){
			temp = heap[parent(i)];
			heap[parent(i)] = heap[i];
			heap[i] = temp;
			siftUp(parent(i));
		}
	}

	private void heapify(int i){
		int smallest = 0;
		T temp;
		if(left(i) < elemCount && heap[left(i)].compareTo(heap[i]) < 0){
			smallest = left(i);
		}else{
			smallest = i;
		}if(right(i) < elemCount && heap[smallest].compareTo(heap[right(i)]) > 0){
			smallest = right(i);
		}
		if(smallest != i){
			temp = heap[i];
			heap[i] = heap[smallest];
			heap[smallest] = temp;
			heapify(smallest);
		}
	}
	public boolean isFull() {
		return elemCount == size;
	}

	public boolean isEmpty() {
		return elemCount == 0;
	}

	public void insert(T element) {
		if(isFull()){
			throw new RuntimeException();
		}
		heap[elemCount] = element;
		elemCount++;
		siftUp(elemCount - 1 );
	}

	public T getMin() {
		if(isEmpty()){
			throw new RuntimeException();
		}
		return heap[0];
	}

	public T deleteMin() {
		if(isEmpty())
			return null;
		T min = heap[0];
		heap[0] = heap[--elemCount];
		heapify(0);
		return min;
	}

	/**
	 * return a string represents the heap. The order of the elements are according
	 * to The string starts with "[", ends with "]", and the values are seperated
	 * with a comma
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for(int i = 0; i < elemCount; i++){
			if(i < elemCount - 1)
				sb.append(heap[i].toString() + ",");
			else
				sb.append(heap[i].toString());
		}
		sb.append("]");
		return sb.toString();
	}
}
