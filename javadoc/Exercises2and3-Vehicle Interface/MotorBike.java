/**
	 * Document Motorbike class
	 * @author alan
	 *
	 */

public class MotorBike implements Vehicle {
	
	private int age;
	
	public MotorBike(int theage)
	{
		age=theage;		
	}
	
	public String VehicleType()
	{
		return "MotorBike";
	}
	
	
	public int TaxValue()
	{
		return 15*age;
		
	}

}
