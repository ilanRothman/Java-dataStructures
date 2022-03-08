package il.ac.telhai.ds.hash;

import il.ac.telhai.ds.linkedlist.DLinkedList;

public class HashTable<V> {
	private DLinkedList<V>[] table;
	private int elementCount;
	private int size;
	public static final int DEF_MAX_HASH_SIZE = 10;

	/**
	 * c'tor
	 * construct a hash-table of max-size "DEF_MAX_HASH_SIZE".
	 */
	public HashTable() {
		this(DEF_MAX_HASH_SIZE);
	}

	/**
	 * construct a hash-table of size 'hashSize'.
	 * @param hashSize, the size of the constructed hash-table.
	 */
	@SuppressWarnings({"unchecked","rawtypes"})
	public HashTable (int hashSize) {
		table = new DLinkedList[hashSize];
		for(int i= 0; i<hashSize;i++){
			table[i] = null;
		}
		size  = hashSize;
	}
	
	/**
	 * @param val
	 * @return true if the hash-table contains val, otherwise return false
	 */
	public boolean contains(V val) {
		int where = val.hashCode();
		where = Math.abs(where%size);
		if (table[where] == null){
			return false;}
		table[where].goToBeginning();
		if(!table[where].hasNext()) {
			if (table[where].getCursor().equals(val)) {
				return true;
			}
		}
		while (table[where].hasNext()){
			if (table[where].getNext().equals(val)){
				return true;
			}
		}
		return false;
	}

	/**
	 * Add val to the hash-table.
	 * 
	 * @param val
	 * @return If the val has already exist in the the hash-table, it will not be
	 *         added again. Return true if the val was added successfully. Otherwise
	 *         return false.
	 */
	public boolean add(V val) {
		int where = val.hashCode();
		where = Math.abs(where%size);
		if (table[where] != null){
			table[where].goToBeginning();
			if(!table[where].hasNext()){
				if (table[where].getCursor().equals(val)){
					return false;}
			}
			while(table[where].hasNext()){
				if (table[where].getNext().equals(val)){
					return false;
				}
			}
			table[where].insert(val);
			elementCount++;
			return true;

		}

		table[where] = new DLinkedList<V>();
		table[where].insert(val);
		elementCount++;
		return true;
	}

	/**
	 * Remove val from the hash-table.
	 * 
	 * @param val
	 * @return Return true if the val was removed successfully. Otherwise return
	 *         false.
	 */
	public boolean remove(V val) {
		int where = val.hashCode();
		where = Math.abs(where%size);
		if(table[where] == null){
			return false;
		}
		if(table[where].remove(val) != null){
			elementCount--;
			return true;
		}
		return false;

	}

	/**
	 * clear al the data in the hash-table
	 */
	public void clear() {
		for (int i = 0 ;i<size; i++){
			if(table[i] != null){
				table[i].clear();
			}
		}
		elementCount = 0;
	}

	/**
	 * @return true if the hase-table is empty, otherwise return false.
	 */
	public boolean isEmpty() {
		return elementCount == 0;

	}
}