import java.util.Arrays;
public class Controller {	
	
	public static void main(String[] args)
	  {
	    Person[] persons = new Person[4];
	    persons[0] = new Person("Jon","Good",56);     
	    persons[1] = new Person("Jack", "Hatter",12);    
	    persons[2] = new Person("Jenny", "Harris",16);    
	    persons[3] = new Person("Kate", "Morris",37);
	    
	    System.out.println("Natural Order");

	    for (int i=0; i<4; i++) {
	      Person person = persons[i];
	      String lastName = person.getLastName();
	      String firstName = person.getFirstName();
	      int age = person.getAge();
	      System.out.println(lastName + ", " + firstName + ". Age:" + age);
	    }
	    
	    Arrays.sort(persons);
	    
	    System.out.println();
	    System.out.println("Sorted by age");

	    for (int i=0; i<4; i++) {
	      Person person = persons[i];
	      String lastName = person.getLastName();
	      String firstName = person.getFirstName();
	      int age = person.getAge();
	      System.out.println(lastName + ", " + firstName + ". Age:" + age);
	    }
	  }
}
