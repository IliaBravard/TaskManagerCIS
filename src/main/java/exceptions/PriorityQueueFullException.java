package exceptions; // The package where this exception class is located at

/*
 * @author - Ilia G. Bravard
 * This class represents a user-defined exception for when the priority queue
 * becomes full.
 */
public class PriorityQueueFullException extends Exception {
	private static final long serialVersionUID = 1L;

	public PriorityQueueFullException() {

		// Displaying the message that accompanies the exception
		System.out.println("The priority queue is full!");
	}
}