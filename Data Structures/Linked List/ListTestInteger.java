public class ListTestInteger extends ListTest<Integer>{
	static int numInstances = 0;
	@Override
	public Integer getParameterInstance() {
		return ++numInstances;
	}

}
