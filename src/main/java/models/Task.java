package models; // The package where this model class is located at

// Including the needed import
import java.time.LocalDate;

/*
 * @author - Ilia G. Bravard
 * This class represents the task to be assigned for each user in the application.
 */
public class Task {

	private int taskID; // The task's ID number
	private LocalDate startDate; // The task's start date (today)
	private LocalDate dueDate; // The task's due date
	private String taskInfo; // The task's details

	/**
	 * This is the default, no argument constructor.
	 */
	public Task() {
	}

	/**
	 * This is the nondefault constructor that sets some fields of this class.
	 * 
	 * @param end         - the task's end date
	 * @param taskDetails - the task's details
	 * @param userID      - the user's ID number to which the task will be assigned
	 */
	public Task(LocalDate end, String taskDetails) {
		setTaskID(); // Setting a random task ID number
		setStartDate(); // The starting date is today's date
		setDueDate(end);
		setTaskInfo(taskDetails);
	}

	// Declaring the needed accessors and mutators
	public int getTaskID() {
		return taskID;
	}

	/**
	 * This setter method assigns a random ID number to each task assigned to a
	 * particular user.
	 */
	public void setTaskID() {

		// To avoid magic numbers
		final int LOWER_LIMIT = 1;
		final int HIGHER_LIMIT = 1000;

		// The task's ID is randomly generated between 1 and 1000
		this.taskID = LOWER_LIMIT + (int) (Math.random() * HIGHER_LIMIT);
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	/**
	 * This setter method assigns today's date as the starting date for a given
	 * task.
	 */
	public void setStartDate() {
		this.startDate = LocalDate.now();
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public String getTaskInfo() {
		return taskInfo;
	}

	public void setTaskInfo(String taskInfo) {
		this.taskInfo = taskInfo;
	}

	/**
	 * This is a helper method that prints the values of all fields of this class on
	 * a single line.
	 * 
	 * @return a string line showcasing all field values of this class
	 */
	@Override
	public String toString() {
		return "Task [ID = " + taskID + ", Start On = " + startDate + ", Due = " + dueDate + ", Details = " + taskInfo
				+ "]";
	}
}