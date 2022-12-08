package structures; // The package where this structure class is located at

// Including the needed imports
import java.util.LinkedList;
import java.util.List;

import exceptions.IDNotFoundException;
import exceptions.PriorityQueueEmptyException;
import exceptions.PriorityQueueFullException;
import models.Task;

/*
 * @author - Ilia G. Bravard
 * This class represents the priority queue structure that is used to manage all tasks.
 */
public class PriorityQueue {

	private int pQueueSize; // The queue's max size
	private Task[] pQueueArray; // The array that is used as the underlying structure
	private int numOfTasks; // Keeps track of the number of jobs held inside the queue

	/**
	 * This is the default, no argument constructor.
	 */
	public PriorityQueue() {
	}

	/**
	 * This is the nondefault constructor that initializes a new Task array and sets
	 * its size.
	 * 
	 * @param maxSize - the maximum size of the array
	 */
	public PriorityQueue(int maxSize) {
		pQueueSize = maxSize;
		this.pQueueArray = new Task[pQueueSize];
		this.numOfTasks = 0; // The initial constructor size is 0
	}

	/**
	 * This method checks whether the priority queue is empty.
	 * 
	 * @return true - the priority queue is empty; false otherwise
	 */
	public boolean isEmpty() {
		return numOfTasks == 0;
	}

	/**
	 * This method checks whether the priority queue is full.
	 * 
	 * @return true - the priority queue is full; false otherwise
	 */
	public boolean isFull() {
		return numOfTasks == pQueueSize;
	}

	/**
	 * This method inserts a Task element to the priority queue in descending order
	 * (i.e. the highest priority element is in the head).
	 * 
	 * @param prioritizedTask - the Task element to be added to the priority queue
	 * @throws PriorityQueueFullException - when the priority queue becomes full
	 */
	public void enqueue(Task prioritizedTask) throws PriorityQueueFullException {
		if (isFull()) { // If the priority queue is full,
			throw new PriorityQueueFullException(); // Let the user know that we cannot add to it
		}

		// Otherwise,
		int i;

		/*
		 * This is the more general statement (i.e. it is more likely for the priority
		 * queue to be populated than not).
		 */
		if (numOfTasks != 0) { // If the priority queue has >= 1 elements,
			for (i = numOfTasks - 1; i >= 0; i--) {

				// If the task's priority (due date) is closer to today's date
				if (prioritizedTask.getDueDate().compareTo(pQueueArray[i].getDueDate()) < 0) {
					pQueueArray[i + 1] = pQueueArray[i]; // Make space for that particular task
				} else { // Otherwise,
					break; // Exit the loop for jobs that have a higher due date
				}
			}
			pQueueArray[i + 1] = prioritizedTask; // Add the task to the priority queue
			numOfTasks++; // Increase the number of elements
		}

		else { // If the queue is empty,
			pQueueArray[numOfTasks++] = prioritizedTask; // Just add the task to the priority queue
		}
	}

	/**
	 * This method removes a particular task from the priority queue.
	 * 
	 * @param toRemove - the task to be removed from the queue
	 * @throws PriorityQueueEmptyException - when the priority queue becomes empty
	 */
	public void dequeue(Task toRemove) throws PriorityQueueEmptyException {
		if (isEmpty()) { // If the priority queue is empty,
			throw new PriorityQueueEmptyException(); // Let the user know that we cannot remove from it
		}

		// Otherwise,
		for (int i = 0; i < numOfTasks; i++) { // For each element in the queue,
			if (pQueueArray[i] == toRemove) { // If the current element is the one to be removed,
				pQueueArray[i] = pQueueArray[i + 1]; // Shift all elements to the left
				pQueueArray[numOfTasks] = null; // Remove the copied element
				numOfTasks--; // Decrease the number of tasks that are held in the queue
				break; // Done looping
			}
		}
	}

	/**
	 * This method determines the size of the priority queue.
	 * 
	 * @return the size of the priority queue
	 */
	public int size() {
		return numOfTasks;
	}

	/**
	 * This method displays the elements held inside the priority queue. The task
	 * with the closest due date should be displayed first (at the top) and then the
	 * remaining ones.
	 *
	 * @return each element held in the priority queue
	 * @throws PriorityQueueEmptyException - when the priority queue becomes empty
	 */
	public List<Task> displayElements() throws PriorityQueueEmptyException {

		// Using an iterable structure for the .jsp page
		List<Task> allTasks = new LinkedList<>();

		if (isEmpty()) { // If the queue is empty,
			throw new PriorityQueueEmptyException(); // Let the user know that there is nothing to display
		} else { // Otherwise
			for (int i = 0; i < numOfTasks; i++) { // Traverse each element in the queue
				allTasks.add(pQueueArray[i]); // And add it to the list
			}
		}
		return allTasks;
	}

	/**
	 * This method finds a particular task from the priority queue by using the
	 * task's ID number as the search parameter.
	 * 
	 * @param id - the id number of the task to be found
	 * @return the found task element
	 * @throws PriorityQueueEmptyException - when the priority queue becomes empty
	 * @throws IDNotFoundException         - when a given task ID number cannot be
	 *                                     found
	 */
	public Task findTaskById(int id) throws PriorityQueueEmptyException, IDNotFoundException {

		final int ZERO = 0; // To avoid magic numbers

		if (isEmpty()) { // If the priority queue is empty,
			throw new PriorityQueueEmptyException(); // Let the user know that there are no tasks to be found
		}

		else if (id <= ZERO) { // Else if the task ID is not in the valid range [1, 1000],
			throw new IDNotFoundException(); // Let the user know
		}

		// To hold the found task object
		Task found = new Task();

		// For each element in the priority queue
		for (Task t : pQueueArray) {

			// If the elements ID number matches with the one specified
			if (t.getTaskID() == id) {
				found = t; // Get it
				break; // Stop iterating
			}
		}
		return found;
	}

	/**
	 * This is a helper method that prints the values of all fields of this class on
	 * a single line.
	 * 
	 * @return a string line showcasing all field values of this class
	 */
	@Override
	public String toString() {
		String taskIDs = "";

		// For each nonnull task object in the array,
		for (int i = 0; i < numOfTasks; i++) {
			taskIDs += pQueueArray[i].getTaskID() + " "; // Record the task's ID number
		}

		return taskIDs;
	}
}