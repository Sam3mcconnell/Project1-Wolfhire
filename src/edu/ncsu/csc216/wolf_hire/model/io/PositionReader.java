package edu.ncsu.csc216.wolf_hire.model.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import edu.ncsu.csc216.wolf_hire.model.application.Application;
import edu.ncsu.csc216.wolf_hire.model.manager.Position;

/**
 * Reads a file of Positions and returns the positions
 * in a list.
 * @author semcconn
 *
 */
public class PositionReader {
	
	
	/**
	 * Recieves a string with the file name to read from it.
	 * @param fileName the file being read.
	 * @return the ArrayList of positions.
	 * @throws IllegalArgumentException if there is a problem loading the file.
	 */
	public static ArrayList<Position> readPositionFile(String fileName) {
		try {
			Scanner fileReader = new Scanner(new FileInputStream(fileName));
			ArrayList<Position> positions = new ArrayList<Position>();
			String file = "";
			while (fileReader.hasNextLine()) {
				try {
					file += fileReader.nextLine() + "\n";
				} catch (IllegalArgumentException e) {
					//The line is invalid b/c we couldn't create a Position, skip it!
				}
			}
			
			if (file.charAt(0) != '#') { 
				return positions;
			}
			
			String[] p = file.split("\\r?\\n?[#]"); //Breaks up each position
			for (int i = 1; i < p.length; i++) { //Goes through each position
				if(processPosition(p[i]) != null) {
					positions.add(processPosition(p[i]));
				}
			}
			
			for (int i = 0; i < positions.size(); i++) {
				if(positions.get(i).getApplications().size() == 0) {
					positions.remove(i);
				}
			}
	
			fileReader.close();
			return positions;
			
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to load file.");
		}
		
	}
	
	/**
	 * Breaks apart each position.
	 * @param positionText as the string of positions.
	 * @return a position.
	 */
	private static Position processPosition(String positionText) {
		String[] p = positionText.split("\\r?\\n?[*]");
		Position positon = processPositionLine(p[0]);
		
		if (positon == null) {
			return null;
		}
		
		for (int i = 1; i < p.length; i++) {
			if (processApplication(p[i]) != null) {
				try {
					positon.addApplication(processApplication(p[i]));
				} catch (Exception e) {
					//I am going to do nothing in this block,
					//The position will just not be added.
				}
			}
		}
		
		return positon;
	}
	
	/**
	 * Reads each position line and returns it as a position.
	 * @param positionLine as the position line.
	 * @return the position line.
	 */
	private static Position processPositionLine(String positionLine) {
		
			String[] p = positionLine.split(",");
			
			if (p.length != 3) {
				return null;
			}
			
			String positionName = p[0].trim();
			String hoursPerWeek = p[1].trim();
			String payRate = p[2].trim();
			
			if ("".equals(positionName) || "".equals(hoursPerWeek) || "".equals(payRate) || 
					positionName == null || hoursPerWeek == null || payRate == null) {
				return null;
			}
			
			return new Position(positionName, Integer.parseInt(hoursPerWeek), Integer.parseInt(payRate));
		
		
		
	}
	
	/**
	 * Processes the applications of each position.
	 * @param applicationLine as a application of a position.
	 * @return an application.
	 */
	private static Application processApplication(String applicationLine) {
		String[] a = applicationLine.split(",");
		String id = a[0].trim();
		String state = a[1].trim();
		String firstName = a[2].trim();
		String surname = a[3].trim();
		String unityId = a[4].trim();
		String reviewer = "";
		String note = ""; 
		
		if (a.length == 5) {
			reviewer = "";
			note = "";
		} else if (a.length == 6) {
			reviewer = a[5].trim();
			note = "";
		} else if (a.length == 7) {
			reviewer = a[5].trim();
			note = a[6].trim();
		} else {
			return null; 
		}
		
		if ("".equals(firstName) || "".equals(surname) || "".equals(unityId) || 
				firstName == null || surname == null || unityId == null
				|| "".equals(id) || id == null || state == null || "".equals(state)) {
			return null;
		}
		
		try {
			return new Application(Integer.parseInt(id), state, firstName, surname, unityId, 
					reviewer, note);
		} catch(Exception e) {
			return null;
		}
		
		
	}
}
