package cim.tests;

import static org.junit.Assert.*;
import junit.extensions.jfcunit.JFCTestCase;
import org.junit.Test;

import cim.models.Account;




public class ModelTest extends JFCTestCase {
	
	
	public void AccountTest(){
		Account a = new Account("Petter","hedaf@dfa.com");
		assertEquals("Petter", a.getName());
	}

	
	public void CalendarTest() {
		Account a = new Account("Tormod", "hello@dff.com");
		Calendar cal = new Calendar(a);
		
		
	}
}
