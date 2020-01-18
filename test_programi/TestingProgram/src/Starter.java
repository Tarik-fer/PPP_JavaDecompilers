public class Starter {

	final static String salutation = "Hello! ";
	
	public static void main(String[] args) {
		System.out.println("test");
		
		String str1 = new String();
		
		
		Integer int1 = Integer.valueOf(5);
		
		boolean bo = true;
		
		str1 = str1 + int1;
		System.out.println(str1 + int1);
		
		
		GreetingService greetService1 = message -> 
	      System.out.println(salutation + message);
	      greetService1.sayMessage("Mahesh");
		
		   
		function();
		
		recursiveFunction();
		
		while(bo) {
		}
		
	}
	
	interface GreetingService {
	      void sayMessage(String message);
	   }
	
	public static void function(){
		String strF = new String("asdf");
		
		System.out.println(strF);
		}
	
	public static void recursiveFunction() {
		recursiveFunction();
	}
}
