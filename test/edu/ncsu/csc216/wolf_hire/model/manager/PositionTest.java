package edu.ncsu.csc216.wolf_hire.model.manager;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.wolf_hire.model.application.Application;
import edu.ncsu.csc216.wolf_hire.model.command.Command;

class PositionTest {

	@Test
	void testPosition() {
		Position p = new Position("Software Engineer", 10, 20);
		assertEquals("Software Engineer", p.getPositionName());
		assertEquals(10, p.getHoursPerWeek());
		assertEquals(20, p.getPayRate());
	}
	
	@Test
	void testSetPositionName() {
		Exception e1 = assertThrows(IllegalArgumentException.class, 
				() -> new Position("", 10, 20));
		assertEquals("Position cannot be created.", e1.getMessage()); 
		
		Exception e2 = assertThrows(IllegalArgumentException.class, 
				() -> new Position(null, 10, 20));
		assertEquals("Position cannot be created.", e2.getMessage()); 
	}
	
	@Test 
	void testSetHoursPerWeek() {
		Exception e1 = assertThrows(IllegalArgumentException.class, 
				() -> new Position("Software Engineer", 2, 20));
		assertEquals("Position cannot be created.", e1.getMessage()); 
		
		Exception e2 = assertThrows(IllegalArgumentException.class, 
				() -> new Position("Software Engineer", 22, 20));
		assertEquals("Position cannot be created.", e2.getMessage());
		
	}
	
	@Test
	void testSetPayRate() {
		
		Exception e1 = assertThrows(IllegalArgumentException.class, 
				() -> new Position("Software Engineer", 10, 2));
		assertEquals("Position cannot be created.", e1.getMessage()); 
		
		Exception e2 = assertThrows(IllegalArgumentException.class, 
				() -> new Position("Software Engineer", 10, 40));
		assertEquals("Position cannot be created.", e2.getMessage());
		
	}
	
	@Test
	void testSetApplicationId() {
		Application.setCounter(0);
		Position p = new Position("Software Engineer", 10, 20);
		p.addApplication("Sam", "McConnell", "semcconn");
		p.addApplication("Matt", "McConnell", "mnmcconn");
		p.addApplication("Joe", "McConnell", "jamcconn");
		
		p.setApplicationId();
		assertEquals(3, p.getApplications().get(2).getId());
	}
	
	@Test
	void testAddApplication() {
		Application.setCounter(0);
		Position p = new Position("Software Engineer", 10, 20);
		assertEquals(1, p.addApplication("Sam", "McConnell", "semcconn"));
		
		Application.setCounter(0);
		Position p2 = new Position("Software Engineer", 10, 20);
		Application a = new Application(1, "Submitted", "Sam", "McConnell", "semcconn", "", "");
		Application a2 = new Application(2, "Submitted", "Joe", "McConnell", "jamcconn", "", "");
		Application a3 = new Application(3, "Submitted", "Matt", "McConnell", "mnmcconn", "", "");
		Application a4 = new Application(4, "Submitted", "Mark", "McConnell", "memcconn", "", "");
		Application a5 = new Application(2, "Submitted", "Sarah", "McConnell", "slmcconn", "", "");
		
		assertEquals(1, p2.addApplication(a));
		assertEquals(2, p2.addApplication(a2));
		assertEquals(3, p2.addApplication(a3)); 
		assertEquals(4, p2.addApplication(a4));
		
		assertEquals("Sam", p2.getApplications().get(0).getFirstName());
		assertEquals("Joe", p2.getApplications().get(1).getFirstName());
		assertEquals("Matt", p2.getApplications().get(2).getFirstName());
		assertEquals("Mark", p2.getApplications().get(3).getFirstName());
		
		Exception e1 = assertThrows(IllegalArgumentException.class, 
				() -> p2.addApplication(a5));
		assertEquals("Application cannot be created.", e1.getMessage()); 
		
	}
	
	@Test
	void testGetApplicationById() {
		Application.setCounter(0);
		Position p2 = new Position("Software Engineer", 10, 20);
		Application a = new Application(1, "Submitted", "Sam", "McConnell", "semcconn", "", "");
		Application a2 = new Application(2, "Submitted", "Joe", "McConnell", "jamcconn", "", "");
		Application a3 = new Application(3, "Submitted", "Matt", "McConnell", "mnmcconn", "", "");
		Application a4 = new Application(4, "Submitted", "Mark", "McConnell", "memcconn", "", "");
		
		assertEquals(1, p2.addApplication(a));
		assertEquals(2, p2.addApplication(a2));
		assertEquals(3, p2.addApplication(a3)); 
		assertEquals(4, p2.addApplication(a4));
	
		assertEquals("Sam", p2.getApplicationById(1).getFirstName());
		assertEquals("Joe", p2.getApplicationById(2).getFirstName());
		assertEquals("Matt", p2.getApplicationById(3).getFirstName());
		assertEquals("Mark", p2.getApplicationById(4).getFirstName());
		assertEquals(null, p2.getApplicationById(5));
	}
	
	@Test
	void testDeleteApplicationById() {
		Application.setCounter(0);
		Position p2 = new Position("Software Engineer", 10, 20);
		Application a = new Application(1, "Submitted", "Sam", "McConnell", "semcconn", "", "");
		Application a2 = new Application(2, "Submitted", "Joe", "McConnell", "jamcconn", "", "");
		Application a3 = new Application(3, "Submitted", "Matt", "McConnell", "mnmcconn", "", "");
		Application a4 = new Application(4, "Submitted", "Mark", "McConnell", "memcconn", "", "");
		
		assertEquals(1, p2.addApplication(a));
		assertEquals(2, p2.addApplication(a2));
		assertEquals(3, p2.addApplication(a3)); 
		assertEquals(4, p2.addApplication(a4));
		
		assertEquals("Sam", p2.getApplications().get(0).getFirstName());
		assertEquals("Joe", p2.getApplications().get(1).getFirstName());
		assertEquals("Matt", p2.getApplications().get(2).getFirstName());
		assertEquals("Mark", p2.getApplications().get(3).getFirstName());
		assertEquals(4, p2.getApplications().size());
		
		p2.deleteApplicationById(2);
		assertEquals("Sam", p2.getApplications().get(0).getFirstName());
		assertEquals("Matt", p2.getApplications().get(1).getFirstName());
		assertEquals("Mark", p2.getApplications().get(2).getFirstName());
		assertEquals(3, p2.getApplications().size());
	} 
	
	@Test
	void testToString() {
		Application.setCounter(0);
		Position p2 = new Position("Software Engineer", 10, 20);
		String s = "# Software Engineer,10,20";
		assertEquals(s, p2.toString());
	}
	
	@Test
	void testExecuteCommand() {
		Application.setCounter(0);
		Position p2 = new Position("Software Engineer", 10, 20);
		Application a = new Application(1, "Submitted", "Sam", "McConnell", "semcconn", "", "");
		assertEquals(1, p2.addApplication(a));
		Command c = new Command(Command.CommandValue.ASSIGN, "semcconn");
		p2.executeCommand(1, c);
		assertEquals("Reviewing", a.getState());
	}

}
