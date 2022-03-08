public class MatrixTestInteger extends MatrixTest<Integer>{
    static int amount = 0;
    /**
     * @return a new instance of parameter T. Two instances which are created by
     * this method should be different according to "equals" method. For
     * example for T=Object, the implementation can be "return new
     * Object();".
     */
    @Override
    public Integer getParameterInstance() {
        return amount++;
    }
}
