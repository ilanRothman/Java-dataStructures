package il.ac.telhai.ds.stack;

public class DLinkedListStack<T> implements Stack<T> {
    DLinkedList<T> stack;
    T top;

    public DLinkedListStack(){
        stack = new DLinkedList<T>();
        top = null;


    }

    @Override
    public void push(T t) {
        top = t;
        stack.insert(t);

    }

    @Override
    public T pop() {
        if(isEmpty()){
            return null;
        }
        stack.goToEnd();
        T ret = stack.remove();
        stack.goToEnd();
        top = stack.getCursor();
        return ret;
    }

    @Override
    public T top() {
        return top;
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public String toString() {
        if (stack.isEmpty()){
            return "[]";
        }
        String retStr = "[";

        while( stack.hasPrev()){
            retStr += stack.getCursor().toString()+", ";
            stack.getPrev();
        }
        stack.getPrev();
        retStr += stack.getCursor().toString()+ "]";
        stack.goToEnd();
        return retStr;
    }
}
