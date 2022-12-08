package exceptions; // The package where this exception class is located at

/*
 * @author - Ilia G. Bravard
 * This class represents a user-defined exception for when a given 
 * task/user ID number cannot be found.
 */
public class IDNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;

	public IDNotFoundException() {

		// Displaying the message that accompanies the exception
		System.out.println("The specified ID number could not be found!");
	}
}