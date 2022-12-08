package tests; // The package where this test class is located at

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exceptions.IDNotFoundException;
import exceptions.StackEmptyException;
import models.User;
import structures.Stack;

class StackTests {

	// Declaring the global objects that will be used throughout this testing class
	Stack stack;
	User userOne;
	User userTwo;
	User userThree;

	@BeforeEach // Indicates that it is run 'before' each independent unit test
	public void setUp() {
		stack = new Stack();
		userOne = new User("Ilia", "Bravard", "igbravard@dmacc.edu");
		userTwo = new User("Jaiden", "Rodgers", "jrodgers@gmail.com");
		userThree = new User("Bob", "Barlin", "bbarlin@outlook.com");
	}

	/**
	 * Testing whether the isEmpty() method returns true and whether the default
	 * constructor creates an empty stack.
	 */
	@Test
	void isEmptyTrue() {
		assertTrue(stack.isEmpty());
	}

	/**
	 * Testing whether the isEmpty() method returns false.
	 */
	@Test
	void isEmptyFalse() {
		stack.push(userOne);
		assertFalse(stack.isEmpty());
	}

	/**
	 * Testing whether the size() method returns 0 when a new stack structure is
	 * being created.
	 */
	@Test
	void sizeZero() {
		assertTrue(stack.size() == 0);
	}

	/**
	 * Testing whether the size() method returns a nonzero size when a new User
	 * object has been pushed on to the stack.
	 */
	@Test
	void sizeNonzero() {
		stack.push(userOne);
		assertFalse(stack.size() == 0);
	}

	/**
	 * Testing when trying to pop a User/Nonexistent object from an empty stack.
	 */
	@Test
	void popThrowsEmptyException() {
		assertThrows(StackEmptyException.class, () -> stack.pop());
	}

	/**
	 * Testing when trying to pop a User object from a populated stack structure.
	 */
	@Test
	void popUser() throws StackEmptyException {
		stack.push(userThree);
		stack.pop(); // No User objects anymore

		assertTrue(stack.size() == 0);
	}

	/**
	 * Testing when addin a new User object on to the stack structure.
	 */
	@Test
	void pushUser() {
		stack.push(userTwo);
		assertTrue(stack.size() == 1);
	}

	/**
	 * Testing when trying to show all User/Nonexistent objects from an empty stack.
	 */
	@Test
	void showAllThrowsEmptyException() {
		assertThrows(StackEmptyException.class, () -> stack.showAll());
	}

	/**
	 * Testing when trying to display all User objects from a populated stack
	 * structure.
	 */
	@Test
	void showAllUsers() throws StackEmptyException {

		// ARRANGE
		List<User> expected = new LinkedList<>();

		// ACT
		expected.add(userTwo);
		expected.add(userOne);
		stack.push(userOne);
		stack.push(userTwo);

		// ASSERT
		assertEquals(expected.toString(), stack.showAll().toString());
	}

	/**
	 * Testing when trying to sort the User/Nonexistent objects of an empty stack.
	 */
	@Test
	void insertionSortThrowsEmptyException() {
		assertThrows(StackEmptyException.class, () -> stack.insertionSort());
	}

	/**
	 * Testing when trying to sort the User object of a populated stack structure.
	 */
	@Test
	void sortUsers() throws StackEmptyException {

		// ARRANGE
		List<User> expected = new LinkedList<>();
		int in, out;

		// ACT
		stack.push(userOne);
		stack.push(userTwo);
		stack.push(userThree);
		expected.add(userOne);
		expected.add(userTwo);
		expected.add(userThree);

		for (out = 1; out < expected.size(); out++) {
			User temporary = expected.get(out);
			in = out;
			while (in > 0 && (expected.get(in - 1).getUserID() > temporary.getUserID())) {
				expected.set(in, expected.get(in - 1));
				--in;
			}
			expected.set(in, temporary);
		}

		// ASSERT
		assertEquals(expected.toString(), stack.insertionSort().toString());
	}

	/**
	 * Testing when trying to find a User/Nonexistend object of an empty stack.
	 */
	@Test
	void findUserThrowsEmptyException() {
		assertThrows(StackEmptyException.class, () -> stack.findUser(0));
	}

	/**
	 * Testing when trying to find a particular User object within the priority
	 * queue with ID number 0. As it can be noticed from the User class itself, the
	 * range for its ID numbers is [1,1000].
	 */
	@Test
	void findUserIdZero() {
		stack.push(userOne);
		assertThrows(IDNotFoundException.class, () -> stack.findUser(0));
	}

	/**
	 * Testing when trying to find a particular User object within the stack with a
	 * negative ID number 0.
	 */
	@Test
	void findUserIdNegative() {
		stack.push(userThree);
		assertThrows(IDNotFoundException.class, () -> stack.findUser(-9));
	}

	/**
	 * Teting when trying to find a User object by using its ID number as the search
	 * parameter.
	 */
	@Test
	void findUser() throws StackEmptyException, IDNotFoundException {
		stack.push(userTwo);
		assertEquals(userTwo.toString(), stack.findUser(userTwo.getUserID()).toString());
	}
}