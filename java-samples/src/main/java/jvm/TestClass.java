package jvm;

public class TestClass implements Cloneable{
	private int m;

	public int inc() {
		return m + 1;
	}
	
	@Override
    protected Object clone() throws CloneNotSupportedException {

        return super.clone();

    }
}