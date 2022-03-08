public class SparseMatrix <T> implements Matrix<T>{
    private int size;
    private DLinkedList<SparseMatrixEntry> matrix;
    private T defaultVal;
    private boolean transpose = false;
    public SparseMatrix(T defaultValue){
        this(MAX_SIZE,defaultValue);

    }
    public SparseMatrix(int size, T defaultValue){
        defaultVal = defaultValue;
        this.size = size;
        matrix = new DLinkedList<SparseMatrixEntry>();


    }
    /**
     * @param row - number of row
     * @param col - number of column.
     * @return the value in (row,col) entry.
     * @throws IllegalArgumentException if row<1 or col<1 or row>size or col>size.
     */
    @Override
    public T get(int row, int col) {
        if( row > size || col > size || row < 1 || col <1){
            throw new IllegalArgumentException();
        }
        if(matrix.isEmpty()){
            return defaultVal;
        }
        SparseMatrixEntry tmp;
        if (transpose){
            int transRow = row;
            row = col;
            col = transRow;
        }
        matrix.goToBeginning();
        tmp = matrix.getCursor();
        if(!matrix.hasNext()){
            if (tmp.getRow() == row && tmp.getCol() == col)
                return tmp.getValue();
        }

        while(matrix.hasNext()) {
            if (tmp.getRow() == row && tmp.getCol() == col)
                return tmp.getValue();
            tmp = matrix.getNext();
        }
        if (tmp.getRow() == row && tmp.getCol() == col)
            return tmp.getValue();
        return defaultVal;
    }

    /**
     * @param row     - number of row
     * @param col     - number of column.
     * @param element - element to update
     *                Update the value in (row,col) entry.
     * @throws IllegalArgumentException if row<1 or col<1 or row>size or col>size.
     */
    @Override
    public void set(int row, int col, T element) {
        if( row > size || col > size || row < 1 || col <1){
            throw new IllegalArgumentException();
        }

        if(matrix.isEmpty()){
            matrix.insert(new SparseMatrixEntry(row,col,element));
            return;
        }
        SparseMatrixEntry tmp;
        matrix.goToBeginning();
        tmp = matrix.getCursor();
        if(!matrix.hasNext()){
            if (tmp.getRow() == row && tmp.getCol() == col) {
                tmp.setValue(element);
                return;
            }
        }
        while(matrix.hasNext()) {
            if (tmp.getRow() == row && tmp.getCol() == col) {
                tmp.setValue(element);
                return;
            }
            tmp = matrix.getNext();
        }
        if (tmp.getRow() == row && tmp.getCol() == col) {
            tmp.setValue(element);
            return;
        }
        matrix.insert(new SparseMatrixEntry(row,col,element));
    }

    /**
     * Transpose the matrix.
     * Bonus of 5 points will be given for implementation in O(1).
     */
    @Override
    public void transpose() {
        if(transpose){
            this.transpose = false;
            return;
        }
        this.transpose = true;
    }

    /**
     * @return the a new matrix which is a transpose of the original.
     */
    @Override
    public Matrix<T> getTranspose() {
        SparseMatrix<T> res = new SparseMatrix<T>(size,defaultVal);
        SparseMatrixEntry tmp;
        if(matrix.isEmpty()){
            return res;
        }
        matrix.goToBeginning();
        tmp = matrix.getCursor();
        if(!matrix.hasNext()){
            res.set(tmp.getCol(),tmp.getRow(),tmp.getValue());
            return res;
        }
        while(matrix.hasNext()){
            res.set(tmp.getCol(),tmp.getRow(),tmp.getValue());
            tmp = matrix.getNext();
        }
        res.set(tmp.getCol(),tmp.getRow(),tmp.getValue());
        return res;
    }
    private class SparseMatrixEntry {
        private T value;
        private int row;
        private int col;

        public SparseMatrixEntry(int row, int col, T val) {
            this.row = row;
            this.col = col;
            this.value = val;
        }
        public int getRow() { return row; }
        public void setRow(int row) { this.row = row; }
        public int getCol() { return this.col; }
        public void setCol(int col) {this.col = col;}
        public T getValue() {return this.value; }
        public void setValue(T newVal) { this.value = newVal;}
    }

}
