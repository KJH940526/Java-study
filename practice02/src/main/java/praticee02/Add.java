package praticee02;

public class Add {
	private int lValue;
	private int rValue;

	public Add() {};
	
	public void setValue(int lValue, int rValue) {
		this.lValue=lValue;
		this.rValue=rValue;
		
	}

	public int calculate() {
		// TODO Auto-generated method stub
		return rValue+lValue;
	}

}
