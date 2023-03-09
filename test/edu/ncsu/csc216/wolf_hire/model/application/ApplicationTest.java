package edu.ncsu.csc216.wolf_hire.model.application;

import static org.junit.Assert.assertEquals; 
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.wolf_hire.model.command.Command;



/**
 * Tests the Application class
 * @author semcconn
 *
 */
class ApplicationTest {
	
	/**
	 * Tests the Application constructors
	 */
	@Test 
	void testApplication() {
		Application.setCounter(0);
		Application ap = new Application("Sam", "McConnell", "semcconn");
		assertEquals("Sam", ap.getFirstName());
		assertEquals("McConnell", ap.getSurname());
		assertEquals("semcconn", ap.getUnityId());
		assertEquals("Submitted", ap.getState());
		assertEquals("", ap.getNote());
		assertEquals("", ap.getReviewer());
		assertEquals(0, ap.getId());
		
		
		Application ap2 = new Application(2, "Rejected", "Delaney", "Paquette", "drpaquet", "", "Qualifications");
		assertEquals("Delaney", ap2.getFirstName());
		assertEquals("Paquette", ap2.getSurname());
		assertEquals("drpaquet", ap2.getUnityId());
		assertEquals("Rejected", ap2.getState());
		assertEquals("Qualifications", ap2.getNote());
		assertEquals("", ap2.getReviewer());
		assertEquals(2, ap2.getId());
		
		Exception e1 = assertThrows(IllegalArgumentException.class,  
				() -> new Application(0, "Submitted", "Delaney", "Paquette", "drpaquet", 
						"", ""));
		assertEquals("Application cannot be created.", e1.getMessage()); 
		
	}
	
	@Test
	void testSetState() {
		Application ap = new Application(2, "Submitted", "Delaney", "Paquette", "drpaquet", 
				"", "");
		assertEquals("Submitted", ap.getState());
		
		
		Application ap1 = new Application(2, "Rejected", "Delaney", "Paquette", "drpaquet", 
				"", "Qualifications");
		assertEquals("Rejected", ap1.getState());
		
		Application ap2 = new Application(2, "Reviewing", "Delaney", "Paquette", "drpaquet", 
				"semcconn", "");
		assertEquals("Reviewing", ap2.getState());
		
		Application ap3 = new Application(2, "Interviewing", "Delaney", "Paquette", "drpaquet", 
				"semcconn", "");
		assertEquals("Interviewing", ap3.getState());
		
		Application ap4 = new Application(2, "Processing", "Delaney", "Paquette", "drpaquet", 
				"semcconn", "");
		assertEquals("Processing", ap4.getState());
		
		Application ap5 = new Application(2, "Hired", "Delaney", "Paquette", "drpaquet", 
				"semcconn", null);
		assertEquals("Hired", ap5.getState());
		
		Application ap6 = new Application(2, "Inactive", "Delaney", "Paquette", "drpaquet", 
				"semcconn", "Completed");
		assertEquals("Inactive", ap6.getState());
		
		
		Exception e1 = assertThrows(IllegalArgumentException.class, 
				() -> new Application(2, null, "Delaney", "Paquette", "drpaquet", 
						"semcconn", ""));
		assertEquals("Application cannot be created.", e1.getMessage()); 
		
		
	} 
	
	@Test 
	void testSetFirstName() {
		Exception e1 = assertThrows(IllegalArgumentException.class, 
				() -> new Application(2, "Submitted", "", "Paquette", "drpaquet", 
						"semcconn", ""));
		assertEquals("Application cannot be created.", e1.getMessage());
		
		Exception e2 = assertThrows(IllegalArgumentException.class, 
				() -> new Application(2, "Submitted", null, "Paquette", "drpaquet", 
						"semcconn", ""));
		assertEquals("Application cannot be created.", e2.getMessage());
		
	} 
	
	@Test
	void testSetSurname() {
		Exception e1 = assertThrows(IllegalArgumentException.class, 
				() -> new Application(2, "Submitted", "Delaney", "", "drpaquet", 
						"semcconn", ""));
		assertEquals("Application cannot be created.", e1.getMessage());
		
		Exception e2 = assertThrows(IllegalArgumentException.class, 
				() -> new Application(2, "Submitted", "Delaney", null, "drpaquet", 
						"semcconn", ""));
		assertEquals("Application cannot be created.", e2.getMessage());
	}
	
	@Test
	void testSetUnityId() {
		Exception e1 = assertThrows(IllegalArgumentException.class, 
				() -> new Application(2, "Submitted", "Delaney", "Paquette", "", 
						"semcconn", ""));
		assertEquals("Application cannot be created.", e1.getMessage());
		
		Exception e2 = assertThrows(IllegalArgumentException.class, 
				() -> new Application(2, "Submitted", "Delaney", "Paquette", null, 
						"semcconn", ""));
		assertEquals("Application cannot be created.", e2.getMessage());
		
	}
	
	@Test 
	void testSetReviewer() {
		Application ap = new Application(2, "Inactive", "Delaney", "Paquette", "drpaquet", 
				"semcconn", "Completed");
		assertEquals("semcconn", ap.getReviewer());
		
		Exception e1 = assertThrows(IllegalArgumentException.class, 
				() -> new Application(2, "Submitted", "Delaney", "Paquette", "drpaquet", 
						"semcconn", ""));
		assertEquals("Application cannot be created.", e1.getMessage());
		
		Exception e2 = assertThrows(IllegalArgumentException.class, 
				() -> new Application(2, "Rejected", "Delaney", "Paquette", "drpaquet", 
						"semcconn", "Qualifications"));
		assertEquals("Application cannot be created.", e2.getMessage());
		
		Exception e3 = assertThrows(IllegalArgumentException.class, 
				() -> new Application(2, "Reviewing", "Delaney", "Paquette", "drpaquet", 
						"", ""));
		assertEquals("Application cannot be created.", e3.getMessage());
		
		Exception e4 = assertThrows(IllegalArgumentException.class, 
				() -> new Application(2, "Reviewing", "Delaney", "Paquette", "drpaquet", 
						null, ""));
		assertEquals("Application cannot be created.", e4.getMessage());
		
		
		Exception e5 = assertThrows(IllegalArgumentException.class, 
				() -> new Application(2, "Interviewing", "Delaney", "Paquette", "drpaquet", 
						"", ""));
		assertEquals("Application cannot be created.", e5.getMessage());
		
		Exception e6 = assertThrows(IllegalArgumentException.class, 
				() -> new Application(2, "Interviewing", "Delaney", "Paquette", "drpaquet", 
						null, ""));
		assertEquals("Application cannot be created.", e6.getMessage());
		
		Exception e7 = assertThrows(IllegalArgumentException.class, 
				() -> new Application(2, "Processing", "Delaney", "Paquette", "drpaquet", 
						"", ""));
		assertEquals("Application cannot be created.", e7.getMessage());
		
		Exception e8 = assertThrows(IllegalArgumentException.class, 
				() -> new Application(2, "Processing", "Delaney", "Paquette", "drpaquet", 
						null, ""));
		assertEquals("Application cannot be created.", e8.getMessage());
		
		Exception e9 = assertThrows(IllegalArgumentException.class, 
				() -> new Application(2, "Hired", "Delaney", "Paquette", "drpaquet", 
						"", ""));
		assertEquals("Application cannot be created.", e9.getMessage());
		
		Exception e10 = assertThrows(IllegalArgumentException.class, 
				() -> new Application(2, "Hired", "Delaney", "Paquette", "drpaquet", 
						null, ""));
		assertEquals("Application cannot be created.", e10.getMessage());
		
		
		Exception e11 = assertThrows(IllegalArgumentException.class, 
				() -> new Application(2, "Inactive", "Delaney", "Paquette", "drpaquet", 
						"", "Completed"));
		assertEquals("Application cannot be created.", e11.getMessage());
		
		Exception e12 = assertThrows(IllegalArgumentException.class, 
				() -> new Application(2, "Inactive", "Delaney", "Paquette", "drpaquet", 
						null, "Completed"));
		assertEquals("Application cannot be created.", e12.getMessage());
	}
	 
	@Test 
	void testSetNote() {
		Application ap = new Application(2, "Submitted", "Delaney", "Paquette", "drpaquet", 
				"", "");
		assertEquals("", ap.getNote());
		
		
		Application ap1 = new Application(2, "Rejected", "Delaney", "Paquette", "drpaquet", 
				"", "Qualifications");
		assertEquals("Qualifications", ap1.getNote());
		
		Application ap2 = new Application(2, "Reviewing", "Delaney", "Paquette", "drpaquet", 
				"semcconn", "");
		assertEquals("", ap2.getNote());
		
		Application ap3 = new Application(2, "Interviewing", "Delaney", "Paquette", "drpaquet", 
				"semcconn", "");
		assertEquals("", ap3.getNote());
		
		Application ap4 = new Application(2, "Processing", "Delaney", "Paquette", "drpaquet", 
				"semcconn", "");
		assertEquals("", ap4.getNote());
		
		Application ap5 = new Application(2, "Hired", "Delaney", "Paquette", "drpaquet", 
				"semcconn", null);
		assertEquals("", ap5.getNote());
		
		Application ap6 = new Application(2, "Inactive", "Delaney", "Paquette", "drpaquet", 
				"semcconn", "Completed");
		assertEquals("Completed", ap6.getNote());
		
		
		Exception e1 = assertThrows(IllegalArgumentException.class, 
				() -> new Application(2, "Submitted", "Delaney", "Paquette", "drpaquet", 
						"semcconn", "Completed"));
		assertEquals("Application cannot be created.", e1.getMessage());
		
		Exception e2 = assertThrows(IllegalArgumentException.class, 
				() -> new Application(2, "Inactive", "Delaney", "Paquette", "drpaquet", 
						"semcconn", ""));
		assertEquals("Application cannot be created.", e2.getMessage());
		
		Exception e3 = assertThrows(IllegalArgumentException.class, 
				() -> new Application(2, "Rejected", "Delaney", "Paquette", "drpaquet", 
						"", ""));
		assertEquals("Application cannot be created.", e3.getMessage());
		
		Exception e4 = assertThrows(IllegalArgumentException.class, 
				() -> new Application(2, "Rejected", "Delaney", "Paquette", "drpaquet", 
						"", "NotAReason"));
		assertEquals("Application cannot be created.", e4.getMessage());
	}
	
	@Test
	void testCounter() {
		Application.setCounter(0);
		Application ap = new Application("Matt", "McConnell", "mnmcconn");
		assertEquals(0, ap.getId());
		Application ap2 = new Application("Sam", "McConnell", "semcconn");
		assertEquals(1, ap2.getId());
		Application ap3 = new Application("Dealney", "Paquette", "drpaquet");
		assertEquals(2, ap3.getId());
		
		Application ap4 = new Application(7, "Rejected", "Delaney", "Paquette", "drpaquet", "", "Qualifications");
		assertEquals(7, ap4.getId());
		
		Application ap5 = new Application("Billy", "Bob", "pdbob33");
		assertEquals(8, ap5.getId());
	}
	
	@Test
	void testToString() {
		Application.setCounter(0); 
		Application ap = new Application("Matt", "McConnell", "mnmcconn");
		String s = "* 0,Submitted,Matt,McConnell,mnmcconn,,";
		assertEquals(s, ap.toString());
		
		Application ap2 = new Application(7, "Rejected", "Delaney", "Paquette", "drpaquet", "", "Qualifications");
		String s1 = "* 7,Rejected,Delaney,Paquette,drpaquet,,Qualifications";
		assertEquals(s1, ap2.toString());
		
		
		Application ap3 = new Application(7, "Rejected", "Delaney", "Paquette", "drpaquet", null, "Qualifications");
		String s2 = "* 7,Rejected,Delaney,Paquette,drpaquet,,Qualifications";
		assertEquals(s2, ap3.toString());
		
		Application ap4 = new Application(7, "Submitted", "Delaney", "Paquette", "drpaquet", "", null);
		String s3 = "* 7,Submitted,Delaney,Paquette,drpaquet,,";
		assertEquals(s3, ap4.toString());
	}
	
	@Test 
	void testSubmittedState() {
		Application.setCounter(0);
		Application ap = new Application("Matt", "McConnell", "mnmcconn");
		Command c = new Command(Command.CommandValue.REJECT, "Qualifications");
		ap.update(c);
		assertEquals("Rejected", ap.getState());
		
		Application ap2 = new Application("Matt", "McConnell", "mnmcconn");
		Command c2 = new Command(Command.CommandValue.ASSIGN, "semcconn");
		ap2.update(c2);
		assertEquals("Reviewing", ap2.getState());
		
		Application ap3 = new Application("Matt", "McConnell", "mnmcconn");
		Command c3 = new Command(Command.CommandValue.HIRE, null);
		
		
		
		Exception e1 = assertThrows(UnsupportedOperationException.class, 
				() -> ap3.update(new Command(Command.CommandValue.REJECT, "No")));
		assertEquals("Invalid command.", e1.getMessage()); 
		
		Exception e3 = assertThrows(UnsupportedOperationException.class, 
				() -> ap3.update(c3));
		assertEquals("Invalid command.", e3.getMessage()); 
		
	}
	
	@Test
	void testRejectedState() {
		
		Application ap = new Application(7, "Rejected", "Delaney", "Paquette", "drpaquet", null, "Qualifications");
		Command c = new Command(Command.CommandValue.RESUBMIT, null);
		ap.update(c);
		assertEquals("Submitted", ap.getState()); 
		
		Application ap2 = new Application(7, "Rejected", "Delaney", "Paquette", "drpaquet", null, "Qualifications");
		Command c2 = new Command(Command.CommandValue.HIRE, null);
		
		Exception e3 = assertThrows(UnsupportedOperationException.class, 
				() -> ap2.update(c2));
		assertEquals("Invalid command.", e3.getMessage()); 
	}
	
	@Test
	void testReviewingState() {
		Application ap = new Application(7, "Reviewing", "Delaney", "Paquette", "drpaquet", "semcconn", "");
		Command c = new Command(Command.CommandValue.SCHEDULE, null);
		ap.update(c);
		assertEquals("Interviewing", ap.getState()); 
		
		Application ap2 = new Application(7, "Reviewing", "Delaney", "Paquette", "drpaquet", "semcconn", "");
		Command c2 = new Command(Command.CommandValue.RETURN, null);
		ap2.update(c2);
		assertEquals("Submitted", ap2.getState()); 
		
		Application ap3 = new Application(7, "Reviewing", "Delaney", "Paquette", "drpaquet", "semcconn", "");
		Command c3 = new Command(Command.CommandValue.REJECT, "Qualifications");
		ap3.update(c3);
		assertEquals("Rejected", ap3.getState()); 
		
		Application ap4 = new Application(7, "Reviewing", "Delaney", "Paquette", "drpaquet", "semcconn", "");
		Command c4 = new Command(Command.CommandValue.HIRE, null);
		
		Exception e3 = assertThrows(UnsupportedOperationException.class, 
				() -> ap4.update(c4));
		assertEquals("Invalid command.", e3.getMessage()); 
		 
		Exception e1 = assertThrows(UnsupportedOperationException.class, 
				() -> ap3.update(new Command(Command.CommandValue.REJECT, "No")));
		assertEquals("Invalid command.", e1.getMessage()); 
		
		
	}
	
	@Test
	void testInterviewingState() {
		Application ap = new Application(7, "Interviewing", "Delaney", "Paquette", "drpaquet", "semcconn", "");
		Command c = new Command(Command.CommandValue.PROCESS, null);
		ap.update(c);
		assertEquals("Processing", ap.getState()); 
		
		Application ap2 = new Application(7, "Interviewing", "Delaney", "Paquette", "drpaquet", "semcconn", "");
		Command c2 = new Command(Command.CommandValue.ASSIGN, "semcconn");
		ap2.update(c2);
		assertEquals("Reviewing", ap2.getState()); 
		
		Application ap3 = new Application(7, "Interviewing", "Delaney", "Paquette", "drpaquet", "semcconn", "");
		Command c3 = new Command(Command.CommandValue.SCHEDULE, null);
		ap3.update(c3);
		assertEquals("Interviewing", ap3.getState()); 
		
		Application ap4 = new Application(7, "Interviewing", "Delaney", "Paquette", "drpaquet", "semcconn", "");
		Command c4 = new Command(Command.CommandValue.REJECT, "Qualifications");
		ap4.update(c4);
		assertEquals("Rejected", ap4.getState()); 
		
		Application ap5 = new Application(7, "Interviewing", "Delaney", "Paquette", "drpaquet", "semcconn", "");
		Command c5 = new Command(Command.CommandValue.HIRE, null);
		
		Exception e3 = assertThrows(UnsupportedOperationException.class, 
				() -> ap5.update(c5));
		assertEquals("Invalid command.", e3.getMessage());
		
		Exception e1 = assertThrows(UnsupportedOperationException.class, 
				() -> ap3.update(new Command(Command.CommandValue.REJECT, "No")));
		assertEquals("Invalid command.", e1.getMessage()); 
	}
	 
	@Test
	void testProcessingState() {
		Application ap = new Application(7, "Processing", "Delaney", "Paquette", "drpaquet", "semcconn", "");
		Command c = new Command(Command.CommandValue.REJECT, "Qualifications");
		ap.update(c);
		assertEquals("Rejected", ap.getState()); 
		
		Application ap2 = new Application(7, "Processing", "Delaney", "Paquette", "drpaquet", "semcconn", "");
		Command c2 = new Command(Command.CommandValue.HIRE, null);
		ap2.update(c2);
		assertEquals("Hired", ap2.getState()); 
		
		Application ap3 = new Application(7, "Processing", "Delaney", "Paquette", "drpaquet", "semcconn", "");
		Command c3 = new Command(Command.CommandValue.SCHEDULE, null);
		
		Exception e3 = assertThrows(UnsupportedOperationException.class, 
				() -> ap3.update(c3));
		assertEquals("Invalid command.", e3.getMessage());
		
		Exception e1 = assertThrows(UnsupportedOperationException.class, 
				() -> ap3.update(new Command(Command.CommandValue.REJECT, "No")));
		assertEquals("Invalid command.", e1.getMessage()); 
	}
	
	@Test 
	void testHiredState() {
		Application ap = new Application(7, "Hired", "Delaney", "Paquette", "drpaquet", "semcconn", "");
		Command c = new Command(Command.CommandValue.TERMINATE, "Completed");
		ap.update(c);
		assertEquals("Inactive", ap.getState()); 
		
		Application ap2 = new Application(7, "Hired", "Delaney", "Paquette", "drpaquet", "semcconn", "");
		Command c2 = new Command(Command.CommandValue.SCHEDULE, null);
		Exception e3 = assertThrows(UnsupportedOperationException.class, 
				() -> ap2.update(c2));
		assertEquals("Invalid command.", e3.getMessage());
		
		Exception e1 = assertThrows(UnsupportedOperationException.class, 
				() -> ap2.update(new Command(Command.CommandValue.TERMINATE, "No")));
		assertEquals("Invalid command.", e1.getMessage()); 
	}
	
	@Test 
	void testInactiveState() {
		Application ap2 = new Application(7, "Inactive", "Delaney", "Paquette", "drpaquet", "semcconn", "Completed");
		Command c2 = new Command(Command.CommandValue.SCHEDULE, null);
		Exception e3 = assertThrows(UnsupportedOperationException.class, 
				() -> ap2.update(c2));
		assertEquals("Invalid command.", e3.getMessage());
	}
	

}
