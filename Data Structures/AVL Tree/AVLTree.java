package il.ac.telhai.ds.trees;

public class AVLTree <T extends Comparable<T>>{
    AVLTree<T> left;
    AVLTree<T> right;
    int size;
    int height;
    T val;

    public AVLTree(T value){
        val = value;
        left = right = null;
        size = 1;
        height = 0;
    }

    public boolean isLeaf(){
        return left==null && right ==null;
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

    private  int updateHeight(){
        int L_height = -1;
        int R_height = -1;
        if( isLeaf()){
            this.height = 0;
            return 0;
        }
        if(left != null){
            L_height = left.height;

        }
        if(right != null){
            R_height = right.height;
        }
        if (L_height> R_height){
            height = L_height+1;
            return height;
        }
        height = R_height+1;
        return height;
    }


    public T getValue(){
        return val;
    }

    public AVLTree<T> getLeft(){
        return left;
    }

    public AVLTree<T> getRight() {
        return right;
    }
    public AVLTree<T> add(T value) {
        /**
         * Adds the object value to the tree as a leaf according to the parameter. If
         * the object was in the tree before, then a RuntimeException is thrown.
         *
         * @param val
         */
        if (value.compareTo(getValue()) == 0) {
            throw new IllegalArgumentException();
        }
        if (value.compareTo(getValue()) > 0) {
            if (right != null) {
                right = right.add(value);
                updateSize();
                updateHeight();
                if(checkBalance() == -2){
                    if(right.checkBalance() < 0 ){
                        rollRR();
                    }
                    else if(right.checkBalance() == 1){
                        rollRL();
                    }
                }
                return this;
            }
            right = new AVLTree<T>(value);
            updateSize();
            updateHeight();
            return this;
        }
        if (left != null) {
            left = left.add(value);
            updateSize();
            updateHeight();
            if(checkBalance() == 2){
                if(left.checkBalance() >= 0){
                    rollLL();
                }else if(left.checkBalance() == -1){
                    rollLR();
                }
            }
            return this;
        }
        left = new AVLTree<T>(value);
        updateSize();
        updateHeight();
        return this;
    }

    private void rollLR() {
        left.rollRR();
        rollLL();
    }

    private void rollRL() {
        right.rollLL();
        rollRR();
    }

    private void rollLL() {
        AVLTree<T> temp = right;
        T tempVal = val;
        val = left.val;
        left.val = tempVal;
        this.right = left;
        left = right.left;
        right.left = right.right;
        right.right = temp;
        right.updateHeight();
        updateHeight();
        right.updateSize();
        updateSize();

    }

    private void rollRR() {
        AVLTree<T> temp = left;
        T tempVal = val;
        val = right.val;
        right.val = tempVal;
        this.left = right;
        right = left.right;
        left.right = left.left;
        left.left = temp;
        left.updateHeight();
        updateHeight();
        left.updateSize();
        updateSize();
    }

    private int checkBalance() {
        int l_h = -1, r_h = -1;
        if(left != null){
            l_h = left.height;
        }
        if(right != null){
            r_h = right.height;
        }
        return l_h - r_h;
    }
}
