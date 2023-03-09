package edu.ncsu.csc216.wolf_hire.model.io;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.wolf_hire.model.application.Application;
import edu.ncsu.csc216.wolf_hire.model.manager.Position;

class PositionWriterTest {

	@Test
	void test() {
		ArrayList<Position> positions = new ArrayList<Position>();
		Position p = new Position("Position", 18, 20);
		Application a = new Application(4, "Hired", "Fiona", "Rosario", "frosari", "sesmith5", null);
		Position p2 = new Position("Position2", 18, 20);
		p.addApplication(a);
		positions.add(p);
		positions.add(p2);
		assertEquals(2, positions.size());
		PositionWriter.writePositionsToFile("test-files/position_writer_test.txt", positions);
		checkFiles("test-files/expected_hired.txt", "test-files/position_writer_test.txt");
		
		
		
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
