package tests; // The package where this test class is located at

// Including the needed imports
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exceptions.IDNotFoundException;
import exceptions.PriorityQueueEmptyException;
import exceptions.PriorityQueueFullException;
import models.Task;
import structures.PriorityQueue;

/*
 * @author Ilia G. Bravard
 * This class tests the implementation for the priority queue data structure.
 */
class PriorityQueueTests {

	// Declaring the global objects that will be used throughout this testing class
	PriorityQueue pQueue;
	Task firstTask;
	Task secondTask;
	Task thirdTask;

	@BeforeEach // Indicates that it is run 'before' each independent unit test
	public void setUp() {

		// Delcaring the date objects that will be used for instantiating Task objects
		LocalDate dateOne = LocalDate.parse("2022-12-24");
		LocalDate dateTwo = LocalDate.parse("2022-12-31");

		pQueue = new PriorityQueue(3);
		firstTask = new Task(LocalDate.now(), "First Task Details");
		secondTask = new Task(dateOne, "Second Task Details");
		thirdTask = new Task(dateTwo, "Third Task Details");
	}

	/**
	 * Testing the whether the isEmpty() method returns true and whether the
	 * nondefault constructor creates an empty priority queue.
	 */
	@Test
	void isEmptyTrue() {
		assertTrue(pQueue.isEmpty());
	}

	/**
	 * Testing whether the isEmpty() method returns false.
	 */
	@Test
	void isEmptyFalse() throws PriorityQueueFullException {
		pQueue.enqueue(firstTask);
		assertFalse(pQueue.isEmpty());
	}

	/**
	 * Testing whether the isFull() method returns false.
	 */
	@Test
	void isFullFalse() {
		assertFalse(pQueue.isFull());
	}

	/**
	 * Testinf whether the isFull() method returns false.
	 */
	@Test
	void isFullTrue() throws PriorityQueueFullException {
		pQueue.enqueue(firstTask);
		pQueue.enqueue(secondTask);
		pQueue.enqueue(thirdTask);

		assertTrue(pQueue.isFull());
	}

	/**
	 * Testing when trying to add a Task object to an already full priority queue
	 * structure.
	 */
	@Test
	void enqueueThrowsFullException() throws PriorityQueueFullException {
		pQueue.enqueue(firstTask);
		pQueue.enqueue(secondTask);
		pQueue.enqueue(thirdTask); // The pQueue is now full!

		assertThrows(PriorityQueueFullException.class, () -> pQueue.enqueue(firstTask));
	}

	/**
	 * Testing the enqueue() method for adding Task objects to the priority queue.
	 */
	@Test
	void enqueueEmptyQueue() throws PriorityQueueFullException {
		pQueue.enqueue(firstTask);
		assertTrue(pQueue.size() == 1);
	}

	/**
	 * Testing when adding a new Task object to the queue that has less priority
	 * than the previous one.
	 */
	@Test
	void enqueueFutureTask() throws PriorityQueueFullException {
		pQueue.enqueue(firstTask);
		pQueue.enqueue(secondTask); // This task has less pririty than the first one

		// Should be HEAD <-- firstTaskID secondTaskID --> REAR
		assertEquals(firstTask.getTaskID() + " " + secondTask.getTaskID() + " ", pQueue.toString());
	}

	/**
	 * Testing when adding a new Task object to the queue that has a higher priority
	 * than the previous one.
	 */
	@Test
	void enqueuePastTask() throws PriorityQueueFullException {
		pQueue.enqueue(secondTask);
		pQueue.enqueue(firstTask); // This task has a higher priority that the first one

		// Should be HEAD <-- firstTaskID secondTaskID --> REAR
		assertEquals(firstTask.getTaskID() + " " + secondTask.getTaskID() + " ", pQueue.toString());
	}

	/**
	 * Testing when adding a new Task object to the queue that has the same priority
	 * as the previous one.
	 */
	@Test
	void enqueueSamePriorityTasks() throws PriorityQueueFullException {
		Task fourthTask = new Task(LocalDate.now(), "Fourth Task Details");

		pQueue.enqueue(firstTask);
		pQueue.enqueue(fourthTask); // This task has the same priority as the first one

		// Should be HEAD <-- firstTaskID fourthTaskID --> REAR
		assertEquals(firstTask.getTaskID() + " " + fourthTask.getTaskID() + " ", pQueue.toString());
	}

	/**
	 * Testing when trying to remove a Task object from the queue that has not been
	 * populated yet.
	 */
	@Test
	void dequeueThrowsEmptyException() throws PriorityQueueEmptyException {

		// The task object does not even exist yet :)
		assertThrows(PriorityQueueEmptyException.class, () -> pQueue.dequeue(firstTask));
	}

	/**
	 * Testing when trying to remove a Task object from the queue.
	 */
	@Test
	void dequeueTask() throws PriorityQueueFullException, PriorityQueueEmptyException {
		pQueue.enqueue(firstTask);
		pQueue.dequeue(firstTask);

		assertTrue(pQueue.size() == 0);
	}

	/**
	 * Testing when the size() method returns zero.
	 */
	@Test
	void sizeIsZero() {
		assertTrue(pQueue.size() == 0);
	}

	/**
	 * Testing when the size() method returns a nonzero value.
	 */
	@Test
	void sizeIsNonZero() throws PriorityQueueFullException {
		pQueue.enqueue(firstTask);
		pQueue.enqueue(thirdTask);

		// Double negative makes a true statement
		assertFalse(!(pQueue.size() == 2));
	}

	/**
	 * Testing when trying to display all Task objects contained within an empty
	 * priority queue.
	 */
	@Test
	void displayElementsThrowsEmptyException() throws PriorityQueueEmptyException {
		assertThrows(PriorityQueueEmptyException.class, () -> pQueue.displayElements());
	}

	/**
	 * Testing when trying to display all Task objects contained within the priority
	 * queue.
	 */
	@Test
	void diplayElements() throws PriorityQueueFullException, PriorityQueueEmptyException {

		// ARRANGE
		List<Task> expected = new LinkedList<>();

		// ACT
		pQueue.enqueue(firstTask);
		pQueue.enqueue(thirdTask);
		expected.add(firstTask);
		expected.add(thirdTask);

		// ASSERT
		assertEquals(expected.toString(), pQueue.displayElements().toString());
	}

	/**
	 * Testing when trying to find a particular Task object within the prioirity
	 * queue by using its ID number as the search parameter.
	 */
	@Test
	void findTaskByIdThrowsEmptyException() throws PriorityQueueEmptyException {

		// The task ID number does not even exist yet :)
		assertThrows(PriorityQueueEmptyException.class, () -> pQueue.findTaskById(256));
	}

	/**
	 * Testing when trying to find a particular Task object within the priority
	 * queue with ID number 0. As it can be noticed from the Task class itself, the
	 * range for its ID numbers is [1,1000].
	 */
	@Test
	void findTaskByIdZero() throws IDNotFoundException, PriorityQueueFullException {
		pQueue.enqueue(firstTask);
		assertThrows(IDNotFoundException.class, () -> pQueue.findTaskById(0));
	}

	/**
	 * Testing when trying to find a particular Task object within the priority
	 * queue with a negative ID number 0.
	 */
	@Test
	void findTaskByIdNegative() throws PriorityQueueFullException {
		pQueue.enqueue(secondTask);
		assertThrows(IDNotFoundException.class, () -> pQueue.findTaskById(-9));
	}

	/**
	 * Teting when trying to find a Task object by using its ID number as the search
	 * parameter.
	 */
	@Test
	void findTaskById() throws PriorityQueueFullException, PriorityQueueEmptyException, IDNotFoundException {
		pQueue.enqueue(firstTask);
		assertEquals(firstTask.toString(), pQueue.findTaskById(firstTask.getTaskID()).toString());
	}
}