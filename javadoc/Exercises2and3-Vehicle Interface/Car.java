/**
	 * Document Carclass
	 * @author alan
	 *
	 */


public class Car implements Vehicle{
	
	private int age;
	
	public Car(int theage)
	{
		
		age=theage;
	}
	
	public String VehicleType()
	{
		return "Car";
	}
	
	
	public int TaxValue()
	{
		return 25*age;		
	}

}
