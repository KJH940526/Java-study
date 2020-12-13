package parksystem3;

public class ParkApp {
	public static void main(String[] args) {
		Iparkable parkalbe = new Bus();  	
		ParkSystem3.park(parkalbe);
		
		parkalbe = new SportsCar();
		ParkSystem3.park(parkalbe);
		
		
		parkalbe = new Taxi();
		ParkSystem3.park(parkalbe);
		
		parkalbe = new Airplane();
		ParkSystem3.park(parkalbe);
		
		
	}

}
