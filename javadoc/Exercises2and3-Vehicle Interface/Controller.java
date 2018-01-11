/**
	 * Controller class  for vehicle 
	 * documentation here
	 * @author Alan Crispin
	 */

public class Controller {
	
	public static void main(String[] args) {
		
		Vehicle v1= new MotorBike(5);
		Vehicle v2 = new Car(6);
		
		System.out.println("Vehicle 1 = "+v1.VehicleType()+" Tax ="+ v1.TaxValue());
		System.out.println("Vehicle 2 = "+v2.VehicleType()+" Tax ="+ v2.TaxValue());
	}

}
