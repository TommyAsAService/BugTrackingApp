package nz.co.tommyngo.se325assignment1.mvc.controller;

/**
 * Utility class used by ContactController to create messages indicating that a Contact has been
 * successfully saved (following modification or creation) or that the Contact could not be saved
 * (because it has validation errors).
 * 
 * A Message has a type ("success" or "error") and a message string.
 */
public class Message {
	
	private static final String SUCCESS = "success";
	private static final String FAIL = "error";
	
	private String _type;
	private String _message;
	
	/**
	 * Factory method to create a Message whose type is "success".
	 */
	public static Message createSuccessfulSaveMessage() {
		Message message = new Message();
		message._type = SUCCESS;
		message._message = "Contact saved successfully";
		return message;
	}
	
	/**
	 * Factory method to create a Message whose type is "error".
	 */
	public static Message createFailureSaveMessage() {
		Message message = new Message();
		message._type = FAIL;
		message._message = "Failed to save contact";
		return message;
	}
	
	public String getType() {
		return _type;
	}
	
	public String getMessage() {
		return _message;
	}
}
