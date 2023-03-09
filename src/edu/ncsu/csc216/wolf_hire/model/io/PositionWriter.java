package edu.ncsu.csc216.wolf_hire.model.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;

import edu.ncsu.csc216.wolf_hire.model.manager.Position;

/**
 * Writes a list of positions to a given file.
 * @author semcconn
 *
 */ 
public class PositionWriter { 
	
	/**
	 * Receives a String with the file name to write to and a list 
	 * of Positions to write to the file.
	 * @param fileName as the name of the file.
	 * @param positions as the list of positions
	 * @throws IllegalArgumentException if there are any errors
	 */
	public static void writePositionsToFile(String fileName, ArrayList<Position> positions) {
		try {
			PrintStream fileWriter = new PrintStream(new File(fileName));
			
			for (int i = 0; i < positions.size(); i++) {
				if (positions.get(i).getApplications().size() == 0) {
					positions.remove(i);
				}
			}
			
			for (int i = 0; i < positions.size(); i++) {
				fileWriter.println(positions.get(i).toString());
				for (int x = 0; x < positions.get(i).getApplications().size(); x++) {
					fileWriter.println(positions.get(i).getApplications().get(x).toString());
				}
			}
			fileWriter.close();
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to save file.");
		}
	}

}
