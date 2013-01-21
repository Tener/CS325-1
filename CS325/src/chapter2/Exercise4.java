package chapter2;

import static org.junit.Assert.*;

import org.junit.Test;

public class Exercise4 {
	
	public EmailCreationCheck ecc = new EmailCreationCheck();

	@Test
	public void shouldBeABeginningIdentifier() {
		
		String testString = "@smumn.edu";
		assertFalse(ecc.CheckForValidEmail(testString));
	}
	
	@Test
	public void shouldBeABeginningAndEndingIdentifier() {
		
		String testString = "@smumn";
		assertFalse(ecc.CheckForValidEmail(testString));
	}
	
	@Test
	public void shouldBeABeginningAndMiddleIdentifier() {
		
		String testString = "@.edu";
		assertFalse(ecc.CheckForValidEmail(testString));
	}
	
	@Test
	public void shouldNotBeNumbersAtStartOfBeginningIdentifier() {
		
		String testString = "11jpbudi@smumn.edu";
		assertFalse(ecc.CheckForValidEmail(testString));
	}

	@Test
	public void shouldNotBeADotIdentifierAtBeginning() {
		
		String testString = "jpbudi.jpb11@smumn.edu";
		assertFalse(ecc.CheckForValidEmail(testString));
	}
	
	@Test
	public void shouldBeWordsInBeginningIdentifier() {
		
		String testString = "11@smumn.edu";
		assertFalse(ecc.CheckForValidEmail(testString));
	}
	
	@Test
	public void shouldBeAtSignInEmail() {
		
		String testString = "jpbudi11smumn.edu";
		assertFalse(ecc.CheckForValidEmail(testString));
	}
	
	@Test
	public void shouldBeAMiddleAndEndingIdentifier() {
		
		String testString = "jpbudi11@";
		assertFalse(ecc.CheckForValidEmail(testString));
	}
	
	@Test
	public void shouldNotBeAEndingIdentifierInTheBeginning() {
		
		String testString = ".edu@smumn.edu";
		assertFalse(ecc.CheckForValidEmail(testString));
	}
	
	@Test
	public void shouldBeAnEndingIdentifier() {
		
		String testString = "jpbudi11@smumn";
		assertFalse(ecc.CheckForValidEmail(testString));
	}
	
	@Test
	public void shouldBeAMiddleIdentifier() {
		
		String testString = "jpbudi11@.edu";
		assertFalse(ecc.CheckForValidEmail(testString));
	}
	
	@Test
	public void canBeOnlyOneCharacterIdentifier() {
		
		String testString = "j@s.e";
		assertTrue(ecc.CheckForValidEmail(testString));
	}
	
	@Test
	public void allComponentsPresent() {
		
		String testString = "jpbudi11@smumn.edu";
		assertTrue(ecc.CheckForValidEmail(testString));
	}
	
	@Test
	public void allComponentsPresentWithMultipleEndingIdentifiers() {
		
		String testString = "jpbudi11@smumn.edu.mn.us";
		assertTrue(ecc.CheckForValidEmail(testString));
	}
}

