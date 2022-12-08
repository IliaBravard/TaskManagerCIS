package structures; // The package where this structure class is located at

// Including the needed imports
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import exceptions.IDNotFoundException;
import exceptions.StackEmptyException;
import models.User;

/*
 * @author - Ilia G. Bravard
 * This class represents the stack structure that is used to manage all users.
 */
public class Stack {

	// This is the linked list used as the underlying data structure
	private LinkedList<User> sList;

	/**
	 * This constructor creates a new linked list to be used for the implementation
	 * of the stack structure.
	 */
	public Stack() {
		sList = new LinkedList<>();
	}

	/**
	 * This method checks whether the stack is empty.
	 * 
	 * @return true - the stack is empty; false otherwise
	 */
	public boolean isEmpty() {
		return sList.isEmpty();
	}

	/**
	 * This method checks how many elements are held in the stack.
	 * 
	 * @return the total number of elements in the stack
	 */
	public int size() {
		return sList.size();
	}

	/**
	 * This method removes ("pops") and returns the top element of the stack.
	 * 
	 * @return the top element of the stack
	 * @throws StackEmptyException - when the stack becomes empty
	 */
	public User pop() throws StackEmptyException {
		if (this.isEmpty()) { // If the stack is empty,
			throw new StackEmptyException(); // Let the user know that there is nothing to remove
		}
		return sList.removeFirst();
	}

	/**
	 * This method adds ("pushes") an element to the top of the stack list.
	 * 
	 * @param user - the user element to be pushed on to the stack list
	 */
	public void push(User user) {
		sList.addFirst(user);
	}

	/**
	 * This method traverses all elements of the stack and adds them to an iterable
	 * list to later be used by the corresponding .jsp page.
	 * 
	 * @throws StackEmptyException - when the stack becomes empty
	 */
	public List<User> showAll() throws StackEmptyException {

		// Using an iterable structure for the .jsp page
		List<User> allUsers = new LinkedList<>();

		if (isEmpty()) { // If the stack is empty,
			throw new StackEmptyException(); // Let the user know that there is nothing to show
		}

		// Otherwise, traverse each element from the stack
		for (User user : sList) {
			allUsers.add(user); // And add it to the list
		}
		return allUsers;
	}

	/**
	 * This method performs an insertion sort for all users added to the stack. The
	 * users are sorted by their ID numbers in an ascending order.
	 * 
	 * @return an array list with the sorted users
	 * @throws StackEmptyException - when a given user ID number cannot be found
	 */
	public List<User> insertionSort() throws StackEmptyException {

		// Using an iterable structure for the .jsp page
		List<User> sortedUsers = new ArrayList<>();

		if (isEmpty()) { // If the stack is empty,
			throw new StackEmptyException(); // Let the user know that there is nothing to sort
		}
		int in;
		int out;

		// Otherwise, traverse the stack elements
		for (User user : sList) {
			sortedUsers.add(user); // And add each one to the array list
		}

		// For each element in the array list
		for (out = 1; out < sortedUsers.size(); out++) {
			User temporary = sortedUsers.get(out);
			in = out;

			// Sort them based on their ID numbers, such that the IDs placed in an ascending
			// order
			while (in > 0 && (sortedUsers.get(in - 1).getUserID() > temporary.getUserID())) {
				sortedUsers.set(in, sortedUsers.get(in - 1));
				--in;
			}

			// Set the new sorted users at the appropriate index
			sortedUsers.set(in, temporary);
		}
		return sortedUsers;
	}

	/**
	 * This method finds a user by using his/her ID number as the search parameter.
	 * 
	 * @param id - the id of the user to be found
	 * @return the found user object
	 * @throws StackEmptyException - when the stack becomes empty
	 * @throws IDNotFoundException - when the user ID number could not be found
	 */
	public User findUser(int id) throws StackEmptyException, IDNotFoundException {

		// Holds the found user object
		User found = new User();

		if (isEmpty()) { // If the stack is empty,
			throw new StackEmptyException(); // Let the user know that there are no elements to be searched for
		}

		else if (id <= 0) {
			throw new IDNotFoundException();
		}
		// For each user element in the stack,
		for (User u : sList) {

			// If the current user's ID number matches the one specified
			if (u.getUserID() == id) {
				found = u; // Get that user
				break; // And stop iterating
			}
		}
		return found;
	}
}