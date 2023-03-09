package edu.ncsu.csc216.wolf_hire.model.io;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.wolf_hire.model.application.Application;
import edu.ncsu.csc216.wolf_hire.model.manager.Position;

class PositionReaderTest {

	@Test
	void testPositionReaderValid() {
		ArrayList<Position> positions = new ArrayList<Position>();
		Position p = new Position("Position", 18, 20);
		Application a = new Application(4, "Hired", "Fiona", "Rosario", "frosari", "sesmith5", null);
		p.addApplication(a);
		positions.add(p);
		
		ArrayList<Position> read = PositionReader.readPositionFile("test-files/expected_hired.txt");
		 
		
		assertEquals("Position", read.get(0).getPositionName());
		assertEquals(18, read.get(0).getHoursPerWeek());
		assertEquals(20, read.get(0).getPayRate());
		
		assertEquals(4, read.get(0).getApplications().get(0).getId());
		assertEquals("Hired", read.get(0).getApplications().get(0).getState());
		assertEquals("Fiona", read.get(0).getApplications().get(0).getFirstName());
		assertEquals("Rosario", read.get(0).getApplications().get(0).getSurname());
		assertEquals("frosari", read.get(0).getApplications().get(0).getUnityId());
		assertEquals("sesmith5", read.get(0).getApplications().get(0).getReviewer());
		assertEquals("", read.get(0).getApplications().get(0).getNote());
		
		Exception e1 = assertThrows(IllegalArgumentException.class, 
				() -> PositionReader.readPositionFile("test-files/expect"));
		assertEquals("Unable to load file.", e1.getMessage()); 
		
		ArrayList<Position> read7 = PositionReader.readPositionFile("test-files/positions1.txt");
		assertEquals(1, read7.size());
		
		ArrayList<Position> read8 = PositionReader.readPositionFile("test-files/positions2.txt");
		assertEquals(4, read8.size());
		
		ArrayList<Position> read9 = PositionReader.readPositionFile("test-files/positions3.txt");
		assertEquals(0, read9.size());
		
		ArrayList<Position> read10 = PositionReader.readPositionFile("test-files/positions4.txt");
		assertEquals(0, read10.size());
		
		ArrayList<Position> read11 = PositionReader.readPositionFile("test-files/positions5.txt");
		assertEquals(0, read11.size());
		
		
		
		ArrayList<Position> read2 = PositionReader.readPositionFile("test-files/positions6.txt");
		assertEquals(0, read2.size());
		
		ArrayList<Position> read3 = PositionReader.readPositionFile("test-files/positions7.txt");
		assertEquals(0, read3.size());
		
		ArrayList<Position> read4 = PositionReader.readPositionFile("test-files/positions8.txt");
		assertEquals(0, read4.size());
		
		ArrayList<Position> read5 = PositionReader.readPositionFile("test-files/positions9.txt");
		assertEquals(0, read5.size());
		
		ArrayList<Position> read6 = PositionReader.readPositionFile("test-files/positions10.txt");
		assertEquals(0, read6.size());
		
	}
 
}
