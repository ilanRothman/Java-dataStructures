public class MatrixTestString extends MatrixTest<String>{
    static int count = 0;

    /**
     * @return a new instance of parameter T. Two instances which are created by
     * this method should be different according to "equals" method. For
     * example for T=Object, the implementation can be "return new
     * Object();".
     */
    @Override
    public String getParameterInstance() {
        return Integer.toString(count++);
    }
}
