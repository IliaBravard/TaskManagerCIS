package exceptions; // The package where this exception class is located at

/*
 * @author - Ilia G. Bravard
 * This class represents a user-defined exception for when the stack becomes
 * empty.
 */
public class StackEmptyException extends Exception {
	private static final long serialVersionUID = 1L;

	public StackEmptyException() {

		// Displaying the message that accompanies the exception
		System.out.println("The stack is empty!");
	}
}