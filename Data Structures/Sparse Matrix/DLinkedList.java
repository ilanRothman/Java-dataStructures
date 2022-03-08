
public class DLinkedList <T> implements List<T> {
    private DNode<T> cursor;


    public DLinkedList() {
        this.cursor = null;
    }

    @Override
    public void insert(T newElement) {
        if (newElement == null){
            throw new NullPointerException();
        }
        if (isEmpty()){
            cursor = new DNode<T>(newElement);
            cursor.setNext(null);
            cursor.setPrev(null);
        }
        else{
            DNode<T> temp = new DNode<T>(newElement);
            temp.setNext(cursor.getNext());
            temp.setPrev(cursor);
            if(hasNext()){
                temp.getNext().setPrev(temp);
            }
            cursor.setNext(temp);
            cursor = temp;
        }



    }

    @Override
    public T remove() {
        if (isEmpty() ){
            return null;
        }
        DNode<T> temp = cursor;
        if (hasNext()){
            if (hasPrev()){
                (cursor.getPrev()).setNext(cursor.getNext());
                cursor = cursor.getNext();
            }else {
                cursor.getNext().setPrev(null);
                cursor = cursor.getNext();
            }
        }else{
            if (hasPrev()){
                cursor.getPrev().setNext(null);
                goToBeginning();
            }else{
                cursor = null;
            }

        }
        return temp.getElement();
    }

    @Override
    public T remove(T element) {
        if (isEmpty() ){
            return null;
        }
        goToBeginning();
        while(hasNext()){
            if(cursor.getElement().equals(element)){
                return remove();
            }
            getNext();
        }
        if(cursor.getElement().equals(element)){
            return remove();
        }

        return null;
    }

    @Override
    public void clear() {
        cursor = null;
    }

    @Override
    public void replace(T newElement) {
        if (isEmpty() || newElement == null){
            throw new IllegalArgumentException();
        }
        cursor.setElement(newElement);

    }

    @Override
    public boolean isEmpty() {
        return cursor == null;
    }

    @Override
    public boolean goToBeginning() {
        if (isEmpty()){
            return false;
        }
        while (hasPrev()){
            cursor = cursor.getPrev();
        }
        return true;
    }

    @Override
    public boolean goToEnd() {
        if (isEmpty() ){
            return false;
        }
        while(hasNext()){
            cursor = cursor.getNext();
        }
        return true;
    }

    @Override
    public T getNext() {
        if (hasNext() ){
            cursor = cursor.getNext();
            return cursor.getElement();
        }
        return null;
    }

    @Override
    public T getPrev() {
        if (hasPrev()){
            cursor = cursor.getPrev();
            return cursor.getElement();
        }
        return null;
    }

    @Override
    public T getCursor() {
        if(isEmpty()){
            return null;
        }
        return cursor.getElement();
    }

    @Override
    public boolean hasNext() {
        if (isEmpty() ){
            return false;
        }
        if(cursor.getNext() != null){
            return true;
        }
        return false;
    }

    @Override
    public boolean hasPrev() {
        if (isEmpty()){
            return false;
        }
        return cursor.getPrev() !=null;
    }
    @Override
    public String toString(){
        String str = "";
        if(isEmpty() ){
            return "";
        }
        goToBeginning();
        while (hasNext()){
            str += cursor.getElement().toString() + " ";

        }
        return str;
    }

    private class DNode <T> {
        private T element;
        private DNode<T> next;
        private DNode<T> prev;

        public DNode(T element) {
            this.element = element;
        }

        public T getElement() {
            return element;
        }

        public void setElement(T element) {
            this.element = element;
        }

        public DNode<T> getNext() {
            return next;
        }

        public void setNext(DNode<T> next) {
            this.next = next;
        }

        public DNode<T> getPrev() {
            return prev;
        }

        public void setPrev(DNode<T> prev) {
            this.prev = prev;
        }
    }

}

