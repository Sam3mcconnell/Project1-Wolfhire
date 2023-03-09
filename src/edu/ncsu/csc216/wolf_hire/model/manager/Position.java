package edu.ncsu.csc216.wolf_hire.model.manager;

import java.util.ArrayList;

import edu.ncsu.csc216.wolf_hire.model.application.Application;
import edu.ncsu.csc216.wolf_hire.model.command.Command;

/**
 * Creates a Position object with the positionName, 
 * hoursPerWeek, and PayRate.
 * Also keeps a list of applications for the position
 * @author semcconn
 *
 */
public class Position {
	
	/** List of Application */
	private ArrayList<Application> applications;
	/** The name of the position */
	private String positionName;
	/** Hours per week of the Position */
	private int hoursPerWeek;
	/** Pay rate of the Position */
	private int payRate;
	
	/**
	 * Constructs the position object with the given parameters.
	 * Also constructs a new empty list.
	 * @param positionName as the name of the position.
	 * @param hoursPerWeek as the hours per week of the position.
	 * @param payRate as the pay rate of the position.
	 */
	public Position(String positionName, int hoursPerWeek, int payRate) {
		 setPositionName(positionName);
		 setHoursPerWeek(hoursPerWeek);
		 setPayRate(payRate);
		 applications = new ArrayList<Application>();
		 
	}
	
	/**
	 * Sets the counter for the Application instances to the value of 
	 * the maximum id on the list of Application for the position +1.
	 */
	public void setApplicationId() {
		int count = 0;
		for (int i = 0; i < applications.size(); i++) {
			if (applications.get(i).getId() > count) { 
				count = applications.get(i).getId();
			}
		}
		Application.setCounter(count); 
	}
	
	/**
	 * Sets the position name.
	 * @param positionName as the position name.
	 * @throws IllegalArgumentException if the positionName is invalid.
	 */
	private void setPositionName(String positionName) {
		if (positionName == null || "".equals(positionName)) {
			throw new IllegalArgumentException("Position cannot be created.");
		}
		this.positionName = positionName;
	}
	
	/**
	 * Gets the name of the position.
	 * @return the position name.
	 */
	public String getPositionName() {
		return positionName;
	}
	
	/**
	 * Sets the hours per week of the position.
	 * @param hoursPerWeek as the hours per week.
	 * @throws IllegalArgumentException if the hours per week is invalid.
	 */
	private void setHoursPerWeek(int hoursPerWeek) {
		if (hoursPerWeek < 5 || hoursPerWeek > 20) {
			throw new IllegalArgumentException("Position cannot be created.");
		}
		this.hoursPerWeek = hoursPerWeek; 
	}
	
	/**
	 * Gets the hours per week of the position.
	 * @return the hours per week.
	 */
	public int getHoursPerWeek() {
		return hoursPerWeek;
	}
	
	/**
	 * Sets the pay rate of the position.
	 * @param payRate as the pay rate.
	 * @throws IllegalArgumentException if the pay rate is invalid.
	 */
	private void setPayRate(int payRate) {
		if (payRate < 7 || payRate > 35) {
			throw new IllegalArgumentException("Position cannot be created.");
		}
		this.payRate = payRate;
		
	}
	
	/**
	 * Gets the pay rate.
	 * @return the pay rate.
	 */
	public int getPayRate() {
		return payRate;
	}
	
	/**
	 * Creates a new Application in the submitted state, 
	 * adds it to the list in sorted order, returns the id.
	 * @param firstName as the first name.
	 * @param surname as the surname.
	 * @param unityId as the unity id.
	 * @return the id of the application.
	 */
	public int addApplication(String firstName, String surname, String unityId) {
		if (applications.size() == 0) {
			Application.setCounter(1);
		}
		Application ap = new Application(firstName, surname, unityId);
		applications.add(ap);
		return ap.getId();
	}
	
	/**
	 * Adds the application to the list in sorted order by id.
	 * @param application as the application being added.
	 * @return the id of the application.
	 * @throws IllegalArgumentException if an application exist with the given id.
	 */
	public int addApplication(Application application) {
		
		for (int i = 0; i < applications.size(); i++) { 
			
			if (applications.get(i).getId() == application.getId()) {
				throw new IllegalArgumentException("Application cannot be created.");
			}
			if (applications.get(i).getId() > application.getId()) {
				applications.add(i, application);
				break;
			} 
		}
		if (applications.size() == 0) {
			applications.add(application);
		}
		if (applications.get(applications.size() - 1).getId() < application.getId()) {
			applications.add(application);
		}
		
		return application.getId();
	}
	
	/**
	 * Returns the list of Applications.
	 * @return the list of applications.
	 */
	public ArrayList<Application> getApplications() {
		return applications;
	}
	
	/**
	 * Returns the Application in the list with the given id.
	 * If there are no applications with the given id, method returns null.
	 * @param id as the application id.
	 * @return the application or null.
	 */
	public Application getApplicationById(int id) {
		
		for (int i = 0; i < applications.size(); i++) {
			if (applications.get(i).getId() == id) {
				return applications.get(i);
			}
		}
		return null;
	}
	
	/**
	 * Find the Application in the list with the given id 
	 * and update it by passing in the given Command.
	 * to the 
	 * @param id as the Application id
	 * @param c as the Command
	 */
	public void executeCommand(int id, Command c) {
		
		for (int i = 0; i < applications.size(); i++) {
			if (applications.get(i).getId() == id) {
				applications.get(i).update(c);
			}
		}
	}
	
	/**
	 * Removes the application with the given id from the list.
	 * @param id as the application id.
	 */
	public void deleteApplicationById(int id) {
		for (int i = 0; i < applications.size(); i++) {
			if (applications.get(i).getId() == id) {
				applications.remove(i);
			}
		}
	}
	
	/**
	 * Returns the string representation of the Position for a file.
	 * @return the string of the Position object.
	 */
	public String toString() {
		return "# " + getPositionName() + "," + getHoursPerWeek() + "," + getPayRate();
	}
	

}
