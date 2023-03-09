package edu.ncsu.csc216.wolf_hire.model.command;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.wolf_hire.model.command.Command.CommandValue;

/**
 * Test class for the Command class
 * @author semcconn
 *
 */
public class CommandTest {
	
	/**
	 * This test method will test the whole command class 
	 * because the command class only consist of a constructor 
	 * and two getters.
	 */
	@Test
	void testCommand() {
		//Test assign command.
		Command c = new Command(CommandValue.ASSIGN, "This is the assign command");
		assertEquals(Command.CommandValue.ASSIGN, c.getCommand());
		assertEquals("This is the assign command", c.getCommandInformation());
		
		//Test reject command.
		Command c1 = new Command(CommandValue.REJECT, "This is the reject command");
		assertEquals(Command.CommandValue.REJECT, c1.getCommand());
		assertEquals("This is the reject command", c1.getCommandInformation());
		
		//Test terminate command.
		Command c2 = new Command(CommandValue.TERMINATE, "This is the terminate command");
		assertEquals(Command.CommandValue.TERMINATE, c2.getCommand());
		assertEquals("This is the terminate command", c2.getCommandInformation());
		
		//Test resubmit command.
		Command c3 = new Command(CommandValue.RESUBMIT, null);
		assertEquals(Command.CommandValue.RESUBMIT, c3.getCommand());
		assertEquals(null, c3.getCommandInformation());
		
		//Test return command.
		Command c4 = new Command(CommandValue.RETURN, null);
		assertEquals(Command.CommandValue.RETURN, c4.getCommand());
		assertEquals(null, c4.getCommandInformation());
		
		//Test schedule command.
		Command c5 = new Command(CommandValue.SCHEDULE, null);
		assertEquals(Command.CommandValue.SCHEDULE, c5.getCommand());
		assertEquals(null, c5.getCommandInformation());
		
		//Test process command.
		Command c6 = new Command(CommandValue.PROCESS, null);
		assertEquals(Command.CommandValue.PROCESS, c6.getCommand());
		assertEquals(null, c6.getCommandInformation());
		
		//Test hire command.
		Command c7 = new Command(CommandValue.HIRE, null);
		assertEquals(Command.CommandValue.HIRE, c7.getCommand());
		assertEquals(null, c7.getCommandInformation());
		
		
		//Testing exceptions
		
	
		Exception e1 = assertThrows(IllegalArgumentException.class, 
				() -> new Command(CommandValue.ASSIGN, null));
		assertEquals("Invalid information.", e1.getMessage());
		
		Exception e11 = assertThrows(IllegalArgumentException.class, 
				() -> new Command(CommandValue.ASSIGN, ""));
		assertEquals("Invalid information.", e11.getMessage());
		
		Exception e2 = assertThrows(IllegalArgumentException.class, 
				() -> new Command(CommandValue.REJECT, null));
		assertEquals("Invalid information.", e2.getMessage());
		
		Exception e22 = assertThrows(IllegalArgumentException.class, 
				() -> new Command(CommandValue.ASSIGN, ""));
		assertEquals("Invalid information.", e22.getMessage());
		
		Exception e3 = assertThrows(IllegalArgumentException.class, 
				() -> new Command(CommandValue.TERMINATE, null));
		assertEquals("Invalid information.", e3.getMessage());
		
		Exception e33 = assertThrows(IllegalArgumentException.class, 
				() -> new Command(CommandValue.ASSIGN, ""));
		assertEquals("Invalid information.", e33.getMessage());
		
		Exception e4 = assertThrows(IllegalArgumentException.class, 
				() -> new Command(CommandValue.RESUBMIT, "Something"));
		assertEquals("Invalid information.", e4.getMessage());
		
		Exception e5 = assertThrows(IllegalArgumentException.class, 
				() -> new Command(CommandValue.RETURN, "Something"));
		assertEquals("Invalid information.", e5.getMessage());
		
		Exception e6 = assertThrows(IllegalArgumentException.class, 
				() -> new Command(CommandValue.SCHEDULE, "Something"));
		assertEquals("Invalid information.", e6.getMessage());
		
		Exception e7 = assertThrows(IllegalArgumentException.class, 
				() -> new Command(CommandValue.PROCESS, "Something"));
		assertEquals("Invalid information.", e7.getMessage());
		
		Exception e8 = assertThrows(IllegalArgumentException.class, 
				() -> new Command(CommandValue.HIRE, "Something"));
		assertEquals("Invalid information.", e8.getMessage());
		
		Exception e9 = assertThrows(IllegalArgumentException.class, 
				() -> new Command(null, null));
		assertEquals("Invalid information.", e9.getMessage());
		
	}

}
