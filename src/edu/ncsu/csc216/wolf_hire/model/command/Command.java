package edu.ncsu.csc216.wolf_hire.model.command;

/**
 * Creates objects that encapsulate user actions that cause 
 * the state of a Application to update.
 * @author semcconn
 *
 */
public class Command {
	
	/** Enumeration of all the commands */
	public enum CommandValue { ASSIGN, REJECT, RESUBMIT, RETURN, SCHEDULE, PROCESS, HIRE, TERMINATE }
	
	/** The value of a command. */
	private CommandValue command;
	
	/** The information of a command. */
	private String commandInformation;
	
	/**
	 * Constructs the command object with the given parameters.
	 * @param command as the command.
	 * @param commandInformation as the command information.
	 * @throws IllegalArgumentException if a command has command information 
	 * when it is not supposed to or does not when it need it.
	 */
	public Command(CommandValue command, String commandInformation) {
		
		if (command == null) {
			throw new IllegalArgumentException("Invalid information.");
		}
		
		if ((command == CommandValue.ASSIGN || command == CommandValue.REJECT || command == CommandValue.TERMINATE)
				&& ("".equals(commandInformation) || commandInformation == null)) {
			throw new IllegalArgumentException("Invalid information.");
		}
		
		if ((command == CommandValue.RESUBMIT || command == CommandValue.RETURN || command == CommandValue.SCHEDULE
				|| command == CommandValue.PROCESS || command == CommandValue.HIRE)
			//	&& !("".equals(commandInformation) || commandInformation == null)) {
			&&  commandInformation != null) {
			throw new IllegalArgumentException("Invalid information.");
		}
		
		this.command = command;
		this.commandInformation = commandInformation;
		
	}
	
	/**
	 * Gets the command.
	 * @return the command.
	 */
	public CommandValue getCommand() {
		return command;
	}
	
	/**
	 * Gets the command information.
	 * @return the command information.
	 */
	public String getCommandInformation() {
		return commandInformation;
	}


}
