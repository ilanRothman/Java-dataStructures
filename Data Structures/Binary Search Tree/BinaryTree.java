package il.ac.telhai.ds.trees;
public class BinaryTree<T> implements BinaryTreeI<T> {

     private BinaryTreeI<T> left;
     private BinaryTreeI<T> right;
     private T key;
     private int size;

     public BinaryTree(T element) {
         key = element;
         left = right = null;
         size = 1;
    }

    public BinaryTree(T element, BinaryTreeI<T> leftSon, BinaryTreeI<T> rightSon ){
         key = element;
         left = leftSon;
         right  = rightSon;
         int size_r = 0, size_l = 0;
         if(rightSon!=null){
             size_r = rightSon.size();
         }
         if(leftSon!=null){
             size_l = leftSon.size();
         }
         size = size_l + size_r + 1;
    }


    /**
     * @return the left subtree
     */


    @Override
    public BinaryTreeI<T> getLeft() {
        return left;
    }

    /**
     * @return the right subtree
     */
    @Override
    public BinaryTreeI<T> getRight() {
        return right;
    }

    /**
     * @return the value in the root
     */
    @Override
    public T getValue() {
        return key;
    }

    /**
     * @param value set the value in the root
     */
    @Override
    public void setValue(T value) {
        key = value;

    }

    /**
     * @param left set the left subtree
     */
    @Override
    public void setLeft(BinaryTreeI<T> left) {
        this.left = left;
    }

    /**
     * @param right set the right subtree
     */
    @Override
    public void setRight(BinaryTreeI<T> right) {
        this.right = right;
    }

    /**
     * @return if it is a leaf or not.
     */
    @Override
    public boolean isLeaf() {
        return left == null && right == null;

    }

    /**
     * @return the height of the tree, i.e. the length of a longest path starting
     * from the root.
     */
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

    /**
     * @return the number of nodes in the tree
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * clears all the tree except its root.
     */
    @Override
    public void clear() {
        left = right = null;
        size = 1;
    }

    /**
     * @return the concatenation of the string representations of the data values in
     * the tree traversed in preorder fashion, where adding a " " before and
     * a " " after each value in the tree.
     */
    @Override
    public String preOrder() {
        return preOrder(" "," ");
    }

    /**
     * @param separationBeforeVal
     * @param separationAfterVal
     * @return the concatenation of the string representations of the data values in
     * the tree traversed in preorder fashion, where adding a
     * "separationBeforeVal" before each value and a
     * "separationAfterVal" after each value in the tree.
     */
    @Override
    public String preOrder(String separationBeforeVal, String separationAfterVal) {
        StringBuilder str = new StringBuilder();
        str.append(separationBeforeVal+key.toString()+separationAfterVal);
        if(isLeaf())
            return separationBeforeVal+key.toString()+separationAfterVal;
        if(left != null) {
            str.append(left.preOrder(separationBeforeVal,separationAfterVal));
        }
        if(right != null) {
            str.append(right.preOrder(separationBeforeVal,separationAfterVal));
        }
        return str.toString();
    }

    /**
     * @return the concatenation of the string representations of the data values in
     * the tree traversed in inorder fashion, where adding a " " before and
     * a " " after each value in the tree.
     */
    @Override
    public String inOrder() {
        StringBuilder str = new StringBuilder();
        if(isLeaf())
            return " "+key.toString() + " ";
        if( left != null) {
            str.append(left.inOrder());
        }
        str.append(" "+key.toString()+" ");
        if( right != null) {
            str.append(right.inOrder());
        }
        return str.toString();
    }

    /**
     * @param separationBeforeVal
     * @param separationAfterVal
     * @return the concatenation of the string representations of the data values in
     * the tree traversed in inorder fashion, where adding a
     * "separationBeforeVal" before each value and a
     * "separationAfterVal" after each value in the tree.
     */
    @Override
    public String inOrder(String separationBeforeVal, String separationAfterVal) {
        StringBuilder str = new StringBuilder();
        if(isLeaf())
            return separationBeforeVal +key.toString() + separationAfterVal;
        if( left != null) {
            str.append(left.inOrder(separationBeforeVal,separationAfterVal));
        }
        str.append(separationBeforeVal+key.toString()+separationAfterVal);
        if(right != null) {
            str.append(right.inOrder(separationBeforeVal,separationAfterVal));
        }
        return str.toString();
    }

    /**
     * @return the concatenation of the string representations of the data values in
     * the tree traversed in postorder fashion, where adding a " " before
     * and a " " after each value in the tree.
     */
    @Override
    public String postOrder() {
        StringBuilder str = new StringBuilder();
        if(isLeaf())
            return " "+key.toString()+" ";
        if(left != null) {
            str.append(left.postOrder());
        }
        if(right != null) {
            str.append(right.postOrder());
        }
        str.append(" "+key.toString()+" ");
        return str.toString();
    }

    /**
     * @param separationBeforeVal
     * @param separationAfterVal
     * @return the concatenation of the string representations of the data values in
     * the tree traversed in postorder fashion, where adding a
     * "separationBeforeVal" before each value and a
     * "separationAfterVal" after each value in the tree.
     */
    @Override
    public String postOrder(String separationBeforeVal, String separationAfterVal) {
        StringBuilder str = new StringBuilder();
        if(isLeaf())
            return separationBeforeVal+key.toString()+separationAfterVal;
        if(left != null) {
            str.append(left.postOrder(separationBeforeVal,separationAfterVal));
        }
        if( right != null) {
            str.append(right.postOrder(separationBeforeVal,separationAfterVal));
        }
        str.append(separationBeforeVal+key.toString()+separationAfterVal);
        return str.toString();
    }
}
