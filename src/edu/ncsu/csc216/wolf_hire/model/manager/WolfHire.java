package edu.ncsu.csc216.wolf_hire.model.manager;

import java.util.ArrayList;

import edu.ncsu.csc216.wolf_hire.model.application.Application;
import edu.ncsu.csc216.wolf_hire.model.command.Command;
import edu.ncsu.csc216.wolf_hire.model.io.PositionReader;
import edu.ncsu.csc216.wolf_hire.model.io.PositionWriter;

/**
 * Controls the creation and modification of many Positions
 * @author semcconn
 *
 */
public class WolfHire {
	/** List of Applications */
	private ArrayList<Position> positions = new ArrayList<Position>();
	/** The active position of the list */
	private Position activePosition = null;
	/** Single instance of WolfHire */ 
	private static WolfHire singleton = null;
	
	/**
	 * Constructor for WolfHire.
	 * Creates the WolfHire Object.
	 */
	private WolfHire() {

	}
	
	/**
	 * Checks if the singleton is null.
	 * If singleton is null, a call is made to the constructor 
	 * to make a new instance of WolfHire.
	 * @return singleton as the single instance of WolfHire.
	 */
	public static WolfHire getInstance() {
		if (singleton == null) {
			singleton = new WolfHire();
		}
		return singleton;
	}
	
	/**
	 * Reads the given fileName by calling PositionReader.
	 * The returned list is added to the positions field.
	 * The first Position in the list is made the activePosition.
	 * @param fileName as the file being read.
	 * @throws IllegalArgumentException if the file can not load.
	 */
	public void loadPositionsFromFile(String fileName) {
		try {
			positions = PositionReader.readPositionFile(fileName);
			activePosition = positions.get(0);
		} catch (Exception e) {
			throw new IllegalArgumentException("Unable to load file.");
		}
	}
	
	/**
	 * Writes the list of positions to a file using PositionWriter class.
	 * @param fileName as the file being writen too.
	 * @throws IllegalArgumentException if the activePosition is null.
	 */
	public void savePositionsToFile(String fileName) {
		if (activePosition == null) {
			throw new IllegalArgumentException("Unable to save file.");
		} else {
			PositionWriter.writePositionsToFile(fileName, positions);
		}
		
	}
	
	/**
	 * Creates a new Position with the given info and adds it to the end
	 * of the positions list.
	 * The position is then loaded as the activePosition.
	 * @param positionName as the position name.
	 * @param hoursPerWeek as the hours per week of the position.
	 * @param payRate as the pay rate of the position.
	 * @throws IllegalArgumentException if the positionName parameter 
	 * gives invalid information.
	 */
	public void addNewPosition(String positionName, int hoursPerWeek, int payRate) {
		Position p = new Position(positionName, hoursPerWeek, payRate);
		
		for(int i = 0; i < positions.size(); i++) {
			if (positions.get(i).getPositionName().toLowerCase().equals(p.getPositionName().toLowerCase())) {
				throw new IllegalArgumentException("Position cannot be created.");
			}
		}
		positions.add(p);
		loadPosition(p.getPositionName());
	}
	
	/**
	 * Finds the position with the given name in the list and 
	 * makes it the activePosition.
	 * Sets the application id for that position.
	 * @param positionName as the name of the position.
	 * @throws IllegalArgumentException if there is no position with
	 * the given name.
	 */
	public void loadPosition(String positionName) {
		
		boolean check = false;
		
		for (int i = 0; i < positions.size(); i++) {
			if (positions.get(i).getPositionName().toLowerCase().equals(positionName.toLowerCase())) {
				activePosition = positions.get(i);
				activePosition.setApplicationId();
				check = true;
				break;
			}
		}
		if (!check) {
			throw new IllegalArgumentException("Position not available.");
		}
		
		
	}
	
	/**
	 * Returns the activePosition or null if there is no activePosition.
	 * @return activePosition or null.
	 */
	public Position getActivePosition() {
		if (activePosition != null) {
			return activePosition;
		} else {
			return null;
		}
		
	}
	
	/**
	 * Returns the name for activePosition.
	 * If the activePosition is null, null is returned.
	 * @return the activePosition name or null.
	 */
	public String getActivePositionName() {
		if (activePosition != null) {
			return activePosition.getPositionName();
		} else {
			return null;
		}
	}
	
	/**
	 * Returns the String array of position names in the 
	 * order they are listed in the positions list.
	 * @return the String array of position names.
	 */
	public String[] getPositionList() {
		String[] pList = new String[positions.size()];
		
		for (int i = 0; i < positions.size(); i++) {
			pList[i] = positions.get(i).getPositionName();
		}
		
		return pList; 
	}
	
	/**
	 * Creates a new application in submitted state and adds it to the activePosition.
	 * @param firstName as the applications firstName.
	 * @param surname as the applications surname.
	 * @param unityId as the applications unity id.
	 */
	public void addApplicationToPosition(String firstName, String surname, String unityId) {
		if (activePosition != null) {
			
			if(activePosition.getApplications().size() == 0) {
				Application.setCounter(1);
			}
			
			activePosition.getApplications().add(new Application(firstName, surname, unityId));
		}
		
	}
	
	/**
	 * Find the Application in the list with the given id 
	 * and update it by passing in the given Command.
	 * to the 
	 * @param id as the Application id
	 * @param c as the Command
	 */
	public void executeCommand(int id, Command c) {
		if (activePosition != null) {
			for (int i = 0; i < activePosition.getApplications().size(); i++) {
				if (activePosition.getApplications().get(i).getId() == id) {
					activePosition.getApplications().get(i).update(c);
				}
			}
		}
	}
	
	/**
	 * Removes the Application with the given id from the list.
	 * @param id as the application id
	 */
	public void deleteApplicationById(int id) {
		if (activePosition != null) {
			for (int i = 0; i < activePosition.getApplications().size(); i++) {
				if (activePosition.getApplications().get(i).getId() == id) {
					activePosition.getApplications().remove(i);
				}
			}
		}
	}
	
	/**
	 * Creates a 2D Object array that is used to populate the ApplicationTableModel.
	 * @param filter provides a string to filter on.
	 * @return the 2D array.
	 */
	public String[][] getApplicationsAsArray(String filter) {
		if (activePosition != null) {
			ArrayList<Application> a = new ArrayList<Application>();
			for (int i = 0; i < activePosition.getApplications().size(); i++) {
				if (filter.equals(activePosition.getApplications().get(i).getState())) {
					a.add(activePosition.getApplications().get(i));
				}
			}
			
			if ("All".equals(filter)) {
				
				String[][] aList = new String[activePosition.getApplications().size()][4];
				for (int i = 0; i < activePosition.getApplications().size(); i++) {
					aList[i][0] = Integer.toString(activePosition.getApplications().get(i).getId());
					aList[i][1] = activePosition.getApplications().get(i).getState();
					aList[i][2] = activePosition.getApplications().get(i).getUnityId();
					aList[i][3] = activePosition.getApplications().get(i).getReviewer();
				}
				return aList;
			} else {
				String[][] aList = new String[a.size()][4];
				for (int i = 0; i < a.size(); i++) { 
					aList[i][0] = Integer.toString(a.get(i).getId());
					aList[i][1] = a.get(i).getState();
					aList[i][2] = a.get(i).getUnityId();
					aList[i][3] = a.get(i).getReviewer();
				}
				return aList;
			}
		}
		return null;
		
	}
	
	/**
	 * Returns the given application or null if there is no 
	 * application with the given id.
	 * @param id as the given id.
	 * @return the application or null.
	 */
	public Application getApplicationById(int id) {
		if (activePosition != null) {
			for (int i = 0; i < activePosition.getApplications().size(); i++) {
				if (activePosition.getApplications().get(i).getId() == id) {
					return activePosition.getApplications().get(i);
				}
			}
		}
		
		return null;
	}
	
	/**
	 * Resets the WolfHire singleton
	 * Protected because we only want the GUI to access this method.
	 */
	protected void resetManager() {
		singleton = null;
	}
	
}
