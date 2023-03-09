package edu.ncsu.csc216.wolf_hire.model.manager;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.wolf_hire.model.application.Application;
import edu.ncsu.csc216.wolf_hire.model.command.Command;

class WolfHireTest {

	/** The instance of wolfhire */ 
	WolfHire wh = WolfHire.getInstance();
	
	
	/**
	 * Tests the loadPositionsFromFile() method
	 */
	@Test
	void testLoadPositionsFromFile() {
		wh.resetManager();
		wh.loadPositionsFromFile("test-files/expected_hired.txt");
		assertEquals("Position", wh.getActivePositionName());
		
		Exception e1 = assertThrows(IllegalArgumentException.class, 
				() -> wh.loadPositionsFromFile("test-files/expected_hired"));
		assertEquals("Unable to load file.", e1.getMessage()); 
		
	}
	
	
	/**
	 * Tests the savePositionsToFile() method.
	 */
	@Test
	void testSavePositionsToFile() {
		wh.resetManager();
		Exception e1 = assertThrows(IllegalArgumentException.class, 
				() -> wh.savePositionsToFile("test-files/wolfhire_saved.txt"));
		assertEquals("Unable to save file.", e1.getMessage()); 
		
		wh.loadPositionsFromFile("test-files/expected_hired.txt");
		assertEquals("Position", wh.getActivePositionName());
		wh.savePositionsToFile("test-files/wolfhire_saved.txt");
		
		checkFiles("test-files/expected_hired.txt", "test-files/wolfhire_saved.txt");
	}
	
	
	/**
	 * Tests the addNewPosition() method
	 */
	@Test
	void testAddNewPosition() {
		wh.resetManager();
		wh.addNewPosition("Position", 18, 20);
		assertEquals(wh.getActivePosition().getPositionName(), "Position");
		
		Exception e1 = assertThrows(IllegalArgumentException.class, 
				() -> wh.addNewPosition("Position", 18, 20));
		assertEquals("Position cannot be created.", e1.getMessage()); 
	}
	
	/**
	 * Tests the loadPosition() method.
	 */
	@Test
	void testLoadPosition() {
		wh.resetManager();
		wh.addNewPosition("Position", 18, 20);
		wh.addNewPosition("Position2", 18, 20);
		assertEquals(wh.getActivePosition().getPositionName(), "Position2");
		wh.loadPosition("Position");
		assertEquals(wh.getActivePosition().getPositionName(), "Position");
		
		Exception e1 = assertThrows(IllegalArgumentException.class, 
				() -> wh.loadPosition("Position3"));
		assertEquals("Position not available.", e1.getMessage()); 
		
		
	}
	
	/**
	 * Tests the getActivePosition() method
	 */
	@Test 
	void testGetActivePosition() {
		wh.resetManager();
		assertEquals(null, wh.getActivePosition());
		wh.addNewPosition("Position", 18, 20);
		assertEquals(wh.getActivePosition().getPositionName(), "Position");
	}
	
	/**
	 * Tests the getActivePostionName() method
	 */
	@Test
	void testGetActivePostionName() {
		wh.resetManager();
		assertEquals(null, wh.getActivePositionName());
		wh.addNewPosition("Position", 18, 20);
		assertEquals(wh.getActivePositionName(), "Position");
	}
	
	/**
	 * Tests the addApplicationToPosition() method
	 */
	@Test 
	void testAddApplicationToPosition() {
		wh.resetManager();
		wh.addNewPosition("Position", 18, 20);
		wh.addApplicationToPosition("Sam", "McConnell", "Semcconn");
		assertEquals("Sam", wh.getApplicationById(1).getFirstName());
	}
	
	
	/**
	 * Tests the executeCommand() method
	 */
	@Test
	void testExecuteCommand() {
		wh.resetManager();
		wh.addNewPosition("Position", 18, 20);
		wh.addApplicationToPosition("Sam", "McConnell", "Semcconn");
		Command c = new Command(Command.CommandValue.ASSIGN, "drpaquet");
		wh.executeCommand(1, c); 
		assertEquals("Reviewing", wh.getApplicationById(1).getState());
		
		
		
	}
	
	/**
	 * Tests the deleteApplicationById() method
	 */
	@Test
	void testDeleteApplicationById() {
		wh.resetManager();
		wh.addNewPosition("Position", 18, 20);
		wh.addApplicationToPosition("Sam", "McConnell", "Semcconn");
		assertEquals(1, wh.getActivePosition().getApplications().size());
		wh.deleteApplicationById(1);
		assertEquals(0, wh.getActivePosition().getApplications().size());
	} 
	
	/**
	 * Tests the getPositionList() method
	 */
	@Test
	void testGetPositionList() {
		wh.resetManager();
		wh.addNewPosition("Position", 18, 20);
		Application p = new Application(1, "Submitted", "Sam", "McConnell", "semcconn", null, null);
		Application p2 = new Application(2, "Submitted", "Joe", "McConnell", "jamcconn", null, null);
		Application p3 = new Application(3, "Hired", "Matt", "McConnell", "mnmcconn", "semcconn", null);
		wh.getActivePosition().addApplication(p);
		wh.getActivePosition().addApplication(p2);
		wh.getActivePosition().addApplication(p3);
		
		String[][] alist = wh.getApplicationsAsArray("All");
		assertEquals(3, alist.length);
		assertEquals("semcconn", alist[0][2]);
		assertEquals("jamcconn", alist[1][2]);
		assertEquals("mnmcconn", alist[2][2]);
		
		String[][] alist2 = wh.getApplicationsAsArray("Hired");
		assertEquals(1, alist2.length);
		assertEquals("mnmcconn", alist2[0][2]);
		
		
	}
	
	/**
	 * Tests the getApplicationById() method
	 */
	@Test
	void testGetApplicationById() {
		wh.resetManager();
		wh.addNewPosition("Position", 18, 20);
		assertEquals(null, wh.getApplicationById(0));
		wh.addApplicationToPosition("Sam", "McConnell", "Semcconn");
		assertEquals("Sam", wh.getApplicationById(1).getFirstName());
	}
 
	
	/**
	 * Helper method to compare two files for the same contents
	 * @param expFile expected output
	 * @param actFile actual output
	 */
	private void checkFiles(String expFile, String actFile) {
		try (Scanner expScanner = new Scanner(new File(expFile));
			 Scanner actScanner = new Scanner(new File(actFile));) {
			
			while (expScanner.hasNextLine()) {
				assertEquals(expScanner.nextLine(), actScanner.nextLine());
			}
			
			expScanner.close();
			actScanner.close();
		} catch (IOException e) {
			fail("Error reading files.");
		}
	}
}
