package edu.ncsu.csc216.wolf_hire.model.application;

import edu.ncsu.csc216.wolf_hire.model.command.Command;

/**
 * Is the class for the Application object.
 * Holds the inner interface ApllicationState.
 * Holds all the concrete state classes
 * Runs the FTM of the program
 * @author semcconn
 *
 */
public class Application {
	
	/** Constant string for the Submitted state's name */
	public static final String SUBMITTED_NAME = "Submitted";
	/** Constant string for the Rejected state's name */
	public static final String REJECTED_NAME = "Rejected";
	/** Constant string for the Reviewing state's name */
	public static final String REVIEWING_NAME = "Reviewing";
	/** Constant string for the Interviewing state's name */
	public static final String INTERVIEWING_NAME = "Interviewing";
	/** Constant string for the Processing state's name */
	public final static String PROCESSING_NAME = "Processing";
	/** Constant string for the Hired state's name */
	public final static String HIRED_NAME = "Hired";
	/** Constant string for the Inactive state's name */
	public final static String INACTIVE_NAME = "Inactive";
	/** Constant string for the rejection reason Qualifications */
	public final static String QUALIFICATIONS_REJECTION = "Qualifications";
	/** Constant string for the rejection reason Incomplete */
	public final static String INCOMPLETE_REJECTION = "Incomplete";
	/** Constant string for the rejection reason Positions */
	public final static String POSITIONS_REJECTION = "Positions";
	/** Constant string for the rejection reason Duplicate */
	public final static String DUPLICATE_REJECTION = "Duplicate";
	/** Constant string for the priority of Completed */
	public final static String COMPLETED_TERMINATION = "Completed";
	/** Constant string for the priority of Resigned */
	public final static String RESIGNED_TERMINATION = "Resigned";
	/** Constant string for the priority of Fired */
	public final static String FIRED_TERMINATION = "Fired";
	
	/** Unique id for an application. */
	private int applicationId;
	/** First name of the applicant. */
	private String firstName;
	/** Surname of the applicant. */
	private String surname;
	/** Unity id of the applicant. */
	private String unityId;
	/** Reviewer assigned to review the application. */
	private String reviewer;
	/** Contains the rejection reason or termination reason for the application */
	private String note;
	/** Keeps track of home many instances of application there are */
	private static int counter;
	
	/** Represents the current state of the Application */
	private ApplicationState currentState;
	/** Instance of the concrete state SubmittedState */
	private final ApplicationState submittedState = new SubmittedState();
	/** Instance of the concrete state RejectedState */
	private final ApplicationState rejectedState = new RejectedState();
	/** Instance of the concrete state ReviewingState */
	private final ApplicationState reviewingState = new ReviewingState();
	/** Instance of the concrete state InterviewingState */
	private final ApplicationState interviewingState = new InterviewingState();
	/** Instance of the concrete state ProcessingState */
	private final ApplicationState processingState = new ProcessingState();
	/** Instance of the concrete state HiredState */
	private final ApplicationState hiredState = new HiredState();
	/** Instance of the concrete state InactiveState */
	private final ApplicationState inactiveState = new InactiveState();
	
	
	/**
	 * Interface for states in the Application State Pattern.  All 
	 * concrete Application states must implement the ApplicationState interface.
	 * The ApplicationState interface should be a private interface of the 
	 * Application class.
	 * 
	 * @author Dr. Sarah Heckman (sarah_heckman@ncsu.edu) 
	 */
	private interface ApplicationState {
		
		/**
		 * Update the Application from the given Command.
		 * An UnsupportedOperationException is thrown if the Command
		 * is not a valid action for the given state.  
		 * @param command Command describing the action that will update the Application's
		 * state.
		 * @throws UnsupportedOperationException if the Command is not a valid action
		 * for the given state.
		 */
		void updateState(Command command);
		
		/**
		 * Returns the name of the current state as a String.
		 * @return the name of the current state as a String.
		 */
		String getStateName();

	}
	
	/**
	 * The public class for the SubmittedState.
	 * Implements ApplicatioState interface.
	 * @author semcconn
	 *
	 */
	public class SubmittedState implements ApplicationState {

		/**
		 * Update the Application from the given Command.
		 * An UnsupportedOperationException is thrown if the Command
		 * is not a valid action for the given state.  
		 * @param command Command describing the action that will update the Application's
		 * state.
		 * @throws UnsupportedOperationException if the Command is not a valid action
		 * for the given state.
		 */
		@Override
		public void updateState(Command command) {
			switch (command.getCommand()) {
				case ASSIGN :
					currentState = reviewingState;
					if (command.getCommandInformation() == null) {
						setReviewer("");
					} else {
						setReviewer(command.getCommandInformation());
					}
					setNote(""); 
					break;
				case REJECT :
					if ("Qualifications".equals(command.getCommandInformation()) || "Incomplete".equals(command.getCommandInformation()) 
							|| "Positions".equals(command.getCommandInformation()) || "Duplicate".equals(command.getCommandInformation())) {
						currentState = rejectedState;
						setNote(command.getCommandInformation());
						setReviewer("");
					} else {
						throw new UnsupportedOperationException("Invalid command.");
					}
					break;
				default: 
					throw new UnsupportedOperationException("Invalid command.");
			}
			
		} 

		/**
		 * Returns the name of the SubmittedState state as a String.
		 * @return the name of the SubmittedState state as a String.
		 */
		@Override
		public String getStateName() {
			return SUBMITTED_NAME;
		}
		
	}
	
	/**
	 * The public class for the RejectedState.
	 * Implements ApplicatioState interface.
	 * @author semcconn
	 *
	 */
	public class RejectedState implements ApplicationState {

		/**
		 * Update the Application from the given Command.
		 * An UnsupportedOperationException is thrown if the Command
		 * is not a valid action for the given state.  
		 * @param command Command describing the action that will update the Application's
		 * state.
		 * @throws UnsupportedOperationException if the Command is not a valid action
		 * for the given state.
		 */
		@Override
		public void updateState(Command command) {
			switch(command.getCommand()) {
				case RESUBMIT: 
					currentState = submittedState;
					setReviewer("");
					setNote("");
					break;
				default: 
					throw new UnsupportedOperationException("Invalid command.");
			}
			
		}
 
		/**
		 * Returns the name of the RejectedState state as a String.
		 * @return the name of the RejectedState state as a String.
		 */
		@Override
		public String getStateName() {
			return REJECTED_NAME;
		}
		
	}
	
	/**
	 * The public class for the ReviewingState.
	 * Implements ApplicatioState interface.
	 * @author semcconn
	 *
	 */
	public class ReviewingState implements ApplicationState {

		/**
		 * Update the Application from the given Command.
		 * An UnsupportedOperationException is thrown if the Command
		 * is not a valid action for the given state.  
		 * @param command Command describing the action that will update the Application's
		 * state.
		 * @throws UnsupportedOperationException if the Command is not a valid action
		 * for the given state.
		 */
		@Override
		public void updateState(Command command) {
			switch(command.getCommand()) {
				case SCHEDULE:  
					currentState = interviewingState; 
					break;
				case RETURN:
					currentState = submittedState;
					setReviewer("");
					setNote("");
					break;
				case REJECT:
					if ("Qualifications".equals(command.getCommandInformation()) || "Incomplete".equals(command.getCommandInformation()) 
							|| "Positions".equals(command.getCommandInformation()) || "Duplicate".equals(command.getCommandInformation())) {
						currentState = rejectedState;
						setNote(command.getCommandInformation());
						setReviewer("");
					} else {
						throw new UnsupportedOperationException("Invalid command.");
					}
					break; 
				default :
					throw new UnsupportedOperationException("Invalid command.");
			}
		}

		/**
		 * Returns the name of the ReviewingState state as a String.
		 * @return the name of the ReviewingState state as a String.
		 */
		@Override
		public String getStateName() {
			return REVIEWING_NAME;
		}
		
	}
	
	
	/**
	 * The public class for the InterviewingState.
	 * Implements ApplicatioState interface.
	 * @author semcconn
	 *
	 */
	public class InterviewingState implements ApplicationState {

		/**
		 * Update the Application from the given Command.
		 * An UnsupportedOperationException is thrown if the Command
		 * is not a valid action for the given state.  
		 * @param command Command describing the action that will update the Application's
		 * state.
		 * @throws UnsupportedOperationException if the Command is not a valid action
		 * for the given state.
		 */
		@Override
		public void updateState(Command command) {
			switch(command.getCommand()) {
				case PROCESS: 
					currentState = processingState;
					break;
				case ASSIGN: 
					currentState = reviewingState;
					setReviewer(command.getCommandInformation());
					break;
				case SCHEDULE: 
					currentState = interviewingState;
					break;
				case REJECT:
					if ("Qualifications".equals(command.getCommandInformation()) || "Incomplete".equals(command.getCommandInformation()) 
							|| "Positions".equals(command.getCommandInformation()) || "Duplicate".equals(command.getCommandInformation())) {
						currentState = rejectedState;
						setNote(command.getCommandInformation());
						setReviewer("");
					} else {
						throw new UnsupportedOperationException("Invalid command.");
					}
					break;
				default: 
					throw new UnsupportedOperationException("Invalid command.");
			
			}
			
		}

		/**
		 * Returns the name of the InterviewingState state as a String.
		 * @return the name of the InterviewingState state as a String.
		 */
		@Override
		public String getStateName() {
			return INTERVIEWING_NAME;
		}
		
	}
	
	/**
	 * The public class for the ProcessingState.
	 * Implements ApplicatioState interface.
	 * @author semcconn
	 *
	 */
	public class ProcessingState implements ApplicationState {

		/**
		 * Update the Application from the given Command.
		 * An UnsupportedOperationException is thrown if the Command
		 * is not a valid action for the given state.  
		 * @param command Command describing the action that will update the Application's
		 * state.
		 * @throws UnsupportedOperationException if the Command is not a valid action
		 * for the given state.
		 */
		@Override
		public void updateState(Command command) {
			switch(command.getCommand()) {
				case HIRE: 
					currentState = hiredState;
					break;
				case REJECT: 
					if ("Qualifications".equals(command.getCommandInformation()) || "Incomplete".equals(command.getCommandInformation()) 
							|| "Positions".equals(command.getCommandInformation()) || "Duplicate".equals(command.getCommandInformation())) {
						currentState = rejectedState;
						setNote(command.getCommandInformation());
						setReviewer("");
					} else {
						throw new UnsupportedOperationException("Invalid command.");
					}
					break;
				default: 
					throw new UnsupportedOperationException("Invalid command.");
			}
			
		}

		/**
		 * Returns the name of the ProcessingState state as a String.
		 * @return the name of the ProcessingState state as a String.
		 */
		@Override
		public String getStateName() {
			return PROCESSING_NAME;
		}
		
	}
	
	/**
	 * The public class for the HiredState.
	 * Implements ApplicatioState interface.
	 * @author semcconn
	 *
	 */
	public class HiredState implements ApplicationState {
		
		/**
		 * Update the Application from the given Command.
		 * An UnsupportedOperationException is thrown if the Command
		 * is not a valid action for the given state.  
		 * @param command Command describing the action that will update the Application's
		 * state.
		 * @throws UnsupportedOperationException if the Command is not a valid action
		 * for the given state.
		 */
		@Override
		public void updateState(Command command) {
			switch (command.getCommand()) {
				case TERMINATE:
					if ("Completed".equals(command.getCommandInformation()) || "Resigned".equals(command.getCommandInformation()) 
							|| "Fired".equals(command.getCommandInformation())) {
						currentState = inactiveState;
						setNote(command.getCommandInformation());
					} else {
						throw new UnsupportedOperationException("Invalid command.");
					}
					
					break;
				default: 
					throw new UnsupportedOperationException("Invalid command.");
			}	
			
		}

		/**
		 * Returns the name of the HiredState state as a String.
		 * @return the name of the HiredState state as a String.
		 */
		@Override
		public String getStateName() {
			return HIRED_NAME;
		}
	}
	
	/**
	 * The public class for the InactiveState.
	 * Implements ApplicatioState interface.
	 * @author semcconn
	 *
	 */
	public class InactiveState implements ApplicationState {

		/**
		 * Update the Application from the given Command.
		 * An UnsupportedOperationException is thrown if the Command
		 * is not a valid action for the given state.  
		 * @param command Command describing the action that will update the Application's
		 * state.
		 * @throws UnsupportedOperationException if the Command is not a valid action
		 * for the given state.
		 */
		@Override
		public void updateState(Command command) {
			switch(command.getCommand()) {	
				default: 
					throw new UnsupportedOperationException("Invalid command.");
			}
			
		}

		/**
		 * Returns the name of the InactiveState state as a String.
		 * @return the name of the InactiveState state as a String.
		 */
		@Override
		public String getStateName() {
			return INACTIVE_NAME;
		}
		
	}
	
	/**
	 * Constructor for the Application object.
	 * Creates a new application with the following parameters.
	 * @param firstName is the first name of the applicant
	 * @param surname is the surname of the applicant
	 * @param unityId is the unity id of the applicant
	 */
	public Application(String firstName, String surname, String unityId) {
		setFirstName(firstName);
		setSurname(surname);
		setUnityId(unityId);
		currentState = submittedState;
		reviewer = "";
		note = "";
		applicationId = Application.counter;
		Application.incrementCounter();
	}
	
	/**
	 * Constructor for the Application object.
	 * Creates a new application with the information from a file.
	 * @param id as the application id.
	 * @param state as the application state.
	 * @param firstName as the application first name.
	 * @param surname as the application surname.
	 * @param unityId as the application unity id.
	 * @param reviewer as the application reviewer.
	 * @param note as the application note.
	 */
	public Application(int id, String state, String firstName, String surname, String unityId, String reviewer, String note) {
		setFirstName(firstName);
		setSurname(surname);
		setUnityId(unityId);
		setState(state);
		setReviewer(reviewer);
		setNote(note);
		if (id == 0) {
			throw new IllegalArgumentException("Application cannot be created.");
		} else {
			setId(id);
		}
	}
	
	/**
	 * Sets the id of the application.
	 * @param id as the id of the application.
	 */
	private void setId(int id) {
		if (id > Application.counter) {
			setCounter(id + 1);
		}
		
		this.applicationId = id; 
	}
	
	/**
	 * Sets the state of the application.
	 * @param stateValue as the value of the state.
	 * @throws IllegalArgumentException if the state is invalid.
	 */
	private void setState(String stateValue) {
		if ("Submitted".equals(stateValue)) {
			currentState = submittedState;
		} else if ("Rejected".equals(stateValue)) {
			currentState = rejectedState;
		} else if ("Reviewing".equals(stateValue)) {
			currentState = reviewingState;
		} else if ("Interviewing".equals(stateValue)) {
			currentState = interviewingState;
		} else if ("Processing".equals(stateValue)) {
			currentState = processingState;
		} else if ("Hired".equals(stateValue)) {
			currentState = hiredState;
		} else if ("Inactive".equals(stateValue)) {
			currentState = inactiveState;
		} else {
			throw new IllegalArgumentException("Application cannot be created.");
		}
	}
	
	/**
	 * Gets the application id of the application.
	 * @return the application id.
	 */
	public int getId() {
		return applicationId;
	}
	
	/**
	 * Gets the state of the application.
	 * @return the state of the application.
	 */
	public String getState() {
		return currentState.getStateName();
	}
	
	/**
	 * Gets the first name of the application.
	 * @return the first name.
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * Sets the first name of the application.
	 * @param firstName as the first name.
	 * @throws IllegalArgumentException if the firstName is invalid.
	 */
	private void setFirstName(String firstName) {
		if ("".equals(firstName) || null == firstName) {
			throw new IllegalArgumentException("Application cannot be created.");
		}
		this.firstName = firstName;
	}
	
	/**
	 * Gets the surname of the application.
	 * @return the surname of the application.
	 */
	public String getSurname() {
		return surname;
	}
	
	/**
	 * Sets the surname of the application.
	 * @param surname as the surname.
	 * @throws IllegalArgumentException if the surname is invalid.
	 */
	private void setSurname(String surname) {
		if ("".equals(surname) || null == surname) {
			throw new IllegalArgumentException("Application cannot be created.");
		}
		this.surname = surname;
	}
	
	/**
	 * Gets the unity id of the application.
	 * @return the unity id.
	 */
	public String getUnityId() { 
		return unityId;
	}
	
	/**
	 * Sets the unity id of the application.
	 * @param unityId as the unity id.
	 * @throws IllegalArgumentException if the unity id is invalid.
	 */
	private void setUnityId(String unityId) {
		if ("".equals(unityId) || null == unityId) {
			throw new IllegalArgumentException("Application cannot be created.");
		}
		this.unityId = unityId;
	}
	
	/**
	 * Gets the reviewer of the application.
	 * @return the reviewer.
	 */
	public String getReviewer() {
		return reviewer;
	}
	
	/**
	 * Sets the reviewer for the application.
	 * @param reviewer as the reviewer.
	 * @throws IllegalArgumentException if the reviewer is invalid.
	 */
	private void setReviewer(String reviewer) {
		if (currentState == submittedState && !(reviewer == null || "".equals(reviewer))) {
			throw new IllegalArgumentException("Application cannot be created.");
		}
		if (currentState == rejectedState && !(reviewer == null || "".equals(reviewer))) {
			throw new IllegalArgumentException("Application cannot be created.");
		}
		if (currentState == reviewingState && (reviewer == null || "".equals(reviewer))) {
			throw new IllegalArgumentException("Application cannot be created.");
		}
		if (currentState == interviewingState && (reviewer == null || "".equals(reviewer))) {
			throw new IllegalArgumentException("Application cannot be created.");
		}
		if (currentState == processingState && (reviewer == null || "".equals(reviewer))) {
			throw new IllegalArgumentException("Application cannot be created.");
		}
		if (currentState == hiredState && (reviewer == null || "".equals(reviewer))) {
			throw new IllegalArgumentException("Application cannot be created.");
		}
		if (currentState == inactiveState && (reviewer == null || "".equals(reviewer))) {
			throw new IllegalArgumentException("Application cannot be created.");
		}
		if (reviewer == null) {
			this.reviewer = "";
		} else {
			this.reviewer = reviewer;
		}
		
		
	}
	
	/**
	 * Gets the note of the application.
	 * @return the note.
	 */
	public String getNote() {
		return note;
	}
	
	/**
	 * Sets the note of the application.
	 * @param note as the note of the application.
	 * @throws IllegalArgumentException if the note is invalid.
	 */
	private void setNote(String note) {
		
		if (currentState == rejectedState && ("".equals(note) || note == null)) {
			throw new IllegalArgumentException("Application cannot be created.");
		} 
		
		if (currentState == rejectedState) {
			if ("Qualifications".equals(note) || "Incomplete".equals(note) 
					|| "Positions".equals(note) || "Duplicate".equals(note)) {
				this.note = note;
			} else {
				throw new IllegalArgumentException("Application cannot be created.");
			}
			
		} else if (currentState == inactiveState) {
			if ("Completed".equals(note) || "Resigned".equals(note) || "Fired".equals(note)) {
				this.note = note;
			} else {
				throw new IllegalArgumentException("Application cannot be created.");
			}
		
		} else  {
			if ("".equals(note) || null == note) {
				this.note = "";
			} else {
				throw new IllegalArgumentException("Application cannot be created.");
			}
		}
	}
	
	 
	/**
	 * Increments the counter 1 time every time a new instance of 
	 * Application is created.
	 */
	public static void incrementCounter() {
		counter += 1;
	}
	
	/**
	 * Sets the value of the counter.
	 * @param newCount as the new value of the counter.
	 */
	public static void setCounter(int newCount) {
		counter = newCount;
	}
	
	/**
	 * Returns a string of the Application object to be used in files.
	 * @return a string of the application object.
	 */ 
	public String toString() {
		if (getReviewer() == null && getNote() == null) {
			return "* " + Integer.toString(getId()) + "," + getState() + "," + getFirstName() + "," + getSurname() + "," 
					+ getUnityId() + "," + "" + "," + "";
		} else if (getReviewer() == null && getNote() != null) {
			return "* " + Integer.toString(getId()) + "," + getState() + "," + getFirstName() + "," + getSurname() + "," 
					+ getUnityId() + "," + "" + "," + getNote();
		} else if (getReviewer() != null && getNote() == null) {
			return "* " + Integer.toString(getId()) + "," + getState() + "," + getFirstName() + "," + getSurname() + "," 
					+ getUnityId() + "," + getReviewer() + "," + "";
		}
		else {
			return "* " + Integer.toString(getId()) + "," + getState() + "," + getFirstName() + "," + getSurname() + "," 
					+ getUnityId() + "," + getReviewer() + "," + getNote();
		} 
	} 
	
	/**
	 * Updates the state of the program with 
	 * a command that is passed through as the parameter.
	 * @param c as the command to update the state.
	 * @throws UnsupportedOperationException if the command is not appropriate 
	 * for the current state.
	 */
	public void update(Command c) {
		if (SUBMITTED_NAME.equals(currentState.getStateName())) {
			submittedState.updateState(c);
		} else if (REJECTED_NAME.equals(currentState.getStateName())) {
			rejectedState.updateState(c);
		} else if (REVIEWING_NAME.equals(currentState.getStateName())) {
			reviewingState.updateState(c);
		} else if (INTERVIEWING_NAME.equals(currentState.getStateName())) {
			interviewingState.updateState(c);
		} else if (PROCESSING_NAME.equals(currentState.getStateName())) {
			processingState.updateState(c);
		} else if (HIRED_NAME.equals(currentState.getStateName())) {
			hiredState.updateState(c);
		} else if (INACTIVE_NAME.equals(currentState.getStateName())) {
			inactiveState.updateState(c);
		} 
	}
}
