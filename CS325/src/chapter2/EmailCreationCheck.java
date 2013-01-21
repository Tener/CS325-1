package chapter2;

public class EmailCreationCheck {
	
	public boolean isEmailValid = false;
	
	public boolean CheckForValidEmail(String email){
		
		isEmailValid  = email.matches("([A-Za-z_])(\\w+)*@(\\w+\\.)(\\w+)(\\.\\w+)*");
		
		return isEmailValid;
	}
	public static void main (String[] args){
		boolean istrue = false;
		
		// Testing Regular Expression
		istrue  = "i@s.e".matches("([A-Za-z_])(\\w+)*@(\\w+\\.)(\\w+)(\\.\\w+)*");
		System.out.println (istrue);
		
		// Check CheckForValidEmail method
		String emailentry = "jpbudi11@smumn.edu.edu";
		EmailCreationCheck ecc = new EmailCreationCheck();
		System.out.println( ecc.CheckForValidEmail(emailentry));
		
	}

}
