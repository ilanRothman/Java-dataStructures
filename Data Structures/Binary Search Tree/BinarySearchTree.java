package il.ac.telhai.ds.trees;

public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {
	int size;
	BinarySearchTree<T> left;
	BinarySearchTree<T> right;

	public BinarySearchTree(T value, BinarySearchTree<T> left, BinarySearchTree<T> right) {
		super(value);
		int size_l = 0;
		int size_r = 0;
		if(left!= null){
			if(left.findGE(value) != null){
				throw new IllegalArgumentException();
			}
		}
		if(right != null){
			if(right.findLE(value) != null){
				throw new IllegalArgumentException();
			}
		}
		if(right != null) {
			this.right = new BinarySearchTree<T>(right.getValue(), right.left, right.right);
			size_r = right.size();
		}else{
			this.right = null;

		}
		if( left != null) {
			this.left = new BinarySearchTree<T>(left.getValue(), left.left, left.right);
			size_l = left.size();
		}else{
			this.left = null;
		}
		size =size_l + size_r + 1;
	}

	@Override
	public int size(){
		return size;
	}

	public BinarySearchTree(T value) {
		super(value);
		size = 1;
	};

	/**
	 * Adds the object value to the tree as a leaf according to the parameter. If
	 * the object was in the tree before, then a RuntimeException is thrown.
	 * 
	 * @param val
	 */
	public void add(T val) {
		if(val.compareTo(getValue()) == 0){
			throw new IllegalArgumentException();
		}
		if (val.compareTo(getValue()) > 0){
			if(isLeaf()){
				right = new BinarySearchTree<T>(val);
				updateSize();
				return;
			}
			if(right != null) {
				right.add(val);
				updateSize();
				return;
			}
			right = new BinarySearchTree<T>(val);
			updateSize();
			return;
		}
		if(isLeaf()){
			left = new BinarySearchTree<T>(val);
			updateSize();
			return;
		}
		if(left != null){
			left.add(val);
			updateSize();
			return;
		}
		left = new BinarySearchTree<T>(val);
		updateSize();
	}
	@Override
	public void clear() {
		left = right = null;
		size = 1;
	}
	@Override
	public boolean isLeaf(){
		return left==null && right ==null;
	}

	/**
	 * Looks for an object which is equal to the parameter.
	 * 
	 * @param value: the object to be looked for in the tree
	 * @return true if the tree contains this object. Otherwise, return false.
	 */
	public boolean contains(T value) {
		if(value.equals(getValue())){
			return true;
		}else if(isLeaf()){
			return false;
		}
		int cmp = value.compareTo(getValue());
		if(cmp < 0){
			if(left != null) {
				return left.contains(value);
			}
			return false;
		}
		if(right != null) {
			return right.contains(value);
		}
		return false;
	}

	/**
	 * Looks for the minimal object in the tree, which is greater than or equal to
	 * the parameter.
	 * 
	 * @param val: the object to be looked for in the tree
	 * @return a reference to the found object.
	 */
	public T findGE(T val) {
		int cmp = val.compareTo(getValue());
		T tempGE = null;
		if(cmp > 0){
			if(isLeaf()) {
				return null;
			}
			else if(right != null){
				return right.findGE(val);
			}
		}if(cmp == 0 ){
			return getValue();
		}if(cmp < 0 && left != null){
			tempGE = left.findGE(val);
		}
		if(tempGE == null){
			if(cmp < 0)
				return getValue();
		}
		return tempGE;
	}

	/**
	 * Looks for the minimal object in the tree, which is smaller than or equal to
	 * the parameter.
	 * 
	 * @param val: the object to be looked for in the tree
	 * @return a reference to the found object.
	 */
	public T findLE(T val) {
		int cmp = val.compareTo(getValue());
		T tempLE = null;
		if(cmp < 0){
			if(isLeaf()) {
				return null;
			}
			else if(left != null){
				return left.findLE(val);
			}
		}if(cmp == 0 ){
			return getValue();
		}if(cmp > 0 && right != null){
			tempLE = right.findLE(val);
		}
		if(tempLE == null){
			if(cmp > 0)
				return getValue();
		}
		return tempLE;
	}
	private void setValue(T val, int x){
		super.setValue(val);
	}





	@Override
	public void setValue(T val){
		if(left.findGE(val) != null){
			throw new IllegalArgumentException();
		}if(right.findLE(val) != null){
			throw new IllegalArgumentException();
		}
		super.setValue(val);
	}

	private BinarySearchTree<T> remove_helper(BinarySearchTree<T> root, T val){
		if(root == null){
			return root;
		}
		int cmp = val.compareTo(root.getValue());
		if(cmp < 0){
			root.left = remove_helper(root.left,val);
			root.updateSize();
		}else if(cmp > 0){
			root.right = remove_helper(root.right,val);
			root.updateSize();
		}else{
			if (root.isLeaf()){
				return null;
			}
			else if(root.left == null){
				return root.right;
			}else if(root.right == null){
				return root.left;
			}else{
				T successor = root.right.findGE(val);
				root.setValue(successor,1);
				root.right =  remove_helper(root.right,successor);
				root.updateSize();
			}
		}
		return root;
	}
	private int updateSize(){
		int size_l = 0, size_r = 0;
		if(isLeaf()){
			size = 1;
		}
		if(left != null){
			size_l = left.size;
		}if(right != null){
			size_r = right.size;
		}
		size = size_l + size_r + 1;
		return size;
	}
	/**
	 * Removes the object in the tree which is equal to the parameter. If the object
	 * was found but the tree contains only one element, it won't be removed and a
	 * run-time exception will be thrown. Nothing is done if node found
	 * 
	 * @param val: the object to be looked for in the tree
	 * @return True, if the element was removed. Otherwise false.
	 */
	public boolean remove(T val) {
		int prev_size = size;
		remove_helper(this,val);
		return prev_size > size;
	}
	@Override
	public void setLeft(BinaryTreeI<T> left){
		if(left == null){
			if(super.getRight()!= null) {
				size = size - getRight().size();
			}
			this.left = null;
			return;
		}
		int cmp = left.getValue().compareTo(getValue());
		if(cmp >= 0 || !(left instanceof BinarySearchTree)){
			throw new IllegalArgumentException();
		}
		BinarySearchTree<T> tree = (BinarySearchTree<T>)left;
		if(tree.findGE(getValue()) != null){
			throw new IllegalArgumentException();
		}
		if(super.getLeft()!= null) {
			size = size - super.getLeft().size();
		}
		size += left.size();
		this.left = (BinarySearchTree<T>) left;

	}
	@Override
	public void setRight(BinaryTreeI<T> right){
		if(right == null){
			if(super.getRight()!= null) {
				size = size - super.getRight().size();
			}
			this.right = null;
			return;
		}
		int cmp = right.getValue().compareTo(getValue());
		if(cmp <= 0 || !(right instanceof BinarySearchTree)){
			throw new IllegalArgumentException();
		}
		BinarySearchTree<T> tree = (BinarySearchTree<T>)right;
		if(tree.findLE(getValue()) != null){
			throw new IllegalArgumentException();
		}
		if(super.getRight()!= null) {
			size = size - super.getRight().size();
		}
		size += right.size();
		this.right = (BinarySearchTree<T>) right;
	}
	@Override
	public BinarySearchTree<T> getLeft() {
		if(left == null){
			return null;
		}
		BinarySearchTree<T> ret = new BinarySearchTree<T>(left.getValue(),left.getLeft(),left.getRight());
		return ret;

	}

	@Override
	public BinarySearchTree<T> getRight() {
		if(right == null){
			return null;
		}
		BinarySearchTree<T> ret = new BinarySearchTree<T>(right.getValue(),right.getLeft(),right.getRight());
		return ret;
	}
	@Override
	public int height() {
		if(isLeaf())
			return 0;
		int h_left = 0, h_right = 0;
		if(left != null) {
			h_left = left.height();
		}
		if(right != null) {
			h_right = right.height();
		}
		if(h_left > h_right)
			return h_left+1;
		return h_right+1;
	}

}
