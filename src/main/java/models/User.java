package models; // The package where this model class is located at

/*
 * @author - Ilia G. Bravard
 * This class represents the user to which a particular task(s) will be assigned to.
 */
public class User {

	private int userID; // The user's ID number
	private String firstName; // The user's first name
	private String lastName; // The user's last name
	private String emailAddress; // The user's email address
	private String taskID; // The user's assigned task(s)

	/**
	 * This is the default, no argument constructor.
	 */
	public User() {
	}

	/**
	 * This is the nondefault constructor that sets some fields of this class.
	 * 
	 * @param fName - the user's first name
	 * @param lName - the user's last name
	 * @param email - the user's email address
	 */
	public User(String fName, String lName, String email) {
		setUserID(); // Setting a random user ID number
		setFirstName(fName);
		setLastName(lName);
		setEmailAddress(email);
	}

	// Declaring the needed accessors and mutators
	public int getUserID() {
		return userID;
	}

	/**
	 * This setter method assigns a random ID number to each user of the
	 * application.
	 */
	public void setUserID() {

		// To avoid magic numbers
		final int LOWER_LIMIT = 1;
		final int HIGHER_LIMIT = 1000;

		// The user's ID is randomly generated between 1 and 1000
		this.userID = LOWER_LIMIT + (int) (Math.random() * HIGHER_LIMIT);
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getTaskID() {
		return taskID;
	}

	public void setTaskID(String taskID) {
		this.taskID = taskID;
	}

	/**
	 * This is a helper method that prints the values of all fields of this class on
	 * a single line.
	 * 
	 * @return a string line showcasing all field values of this class
	 */
	@Override
	public String toString() {
		return "User [ID = " + userID + ", First = " + firstName + ", Last = " + lastName + ", Email = " + emailAddress
				+ ", Assigned Task = " + taskID + "]";
	}
}