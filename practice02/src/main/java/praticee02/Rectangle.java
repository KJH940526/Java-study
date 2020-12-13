package praticee02;

public class Rectangle {
	private int x1;
	private int y1;
	private int x2;
	private int y2;
	
	public Rectangle() {
		
	}

	public Rectangle(int x1,int y1,int x2,int y2) {
		// TODO Auto-generated constructor stub
		this.x1=x1;
		this.x2=x2;
		this.y1=y1;
		this.y2=y2;
	}

	public void set(int x1,int y1,int x2,int y2) {
		// TODO Auto-generated method stub
		this.x1=x1;
		this.x2=x2;
		this.y1=y1;
		this.y2=y2;
	}

	public int square() {
		// TODO Auto-generated method stub
		int x3 = x2-x1;
		int y3 = y2-y1;
		int s = x3*y3;
		return s;
	}
	
	public boolean equals(Rectangle r) {
		boolean result;
		if(x1==r.x1 && y1==r.y1 && x2==r.x2 && y2==r.y2) {
			result=true;
		}
		else {
			result=false;
		}
		return result;
	}

	
	public String toString() {
		return "Rectangle(x1=" + x1 + ", y1=" + y1 + ", x2=" + x2 + ", y2=" + y2 +")";
		
	}




}
