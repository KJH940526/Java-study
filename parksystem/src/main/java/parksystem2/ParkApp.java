package parksystem2;

public class ParkApp {
	public static void main(String[] args) {
		Car c = new Bus();  //일반적으로 이렇게 표현함
//		Bus d = new Bus();
		
		ParkSystem2.park(c);
		
		c = new SportsCar();
		ParkSystem2.park(c);
		
		
		c = new Taxi();
		c.brake();
		
		ParkSystem2.park(c);
		
		
	}

}
