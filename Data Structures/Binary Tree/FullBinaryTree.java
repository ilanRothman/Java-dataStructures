package il.ac.telhai.ds.trees;

public class FullBinaryTree<T> extends BinaryTree<T>{



    public FullBinaryTree(T element) {
        super(element);
    }

    public FullBinaryTree(T element, BinaryTreeI<T> leftSon, BinaryTreeI<T> rightSon) {
        super(element);
        if((leftSon != null && rightSon == null) || (leftSon == null && rightSon != null)){
            throw new IllegalArgumentException();
        }
        super.setLeft(leftSon);
        super.setRight(rightSon);

    }
    @Override
    public void setLeft(BinaryTreeI<T> left){
        if(super.getRight() == null && left == null)
            return;
        if( super.getRight() == null && left != null)
            throw new IllegalArgumentException();
        if(!(left instanceof FullBinaryTree))
            throw new IllegalArgumentException();
        super.setLeft(left);
    }
    @Override
    public void setRight(BinaryTreeI<T> right){
        if(super.getLeft() == null && right == null){
            return;
        }
        if(super.getLeft() == null  && right != null|| !(right instanceof FullBinaryTree))
            throw new IllegalArgumentException();
        super.setRight(right);
    }
}
