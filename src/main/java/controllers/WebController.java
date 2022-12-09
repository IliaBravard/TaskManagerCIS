package controllers; // The package where this controller class is located at

// Including the needed imports
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exceptions.IDNotFoundException;
import exceptions.PriorityQueueEmptyException;
import exceptions.PriorityQueueFullException;
import exceptions.StackEmptyException;
import models.Task;
import models.User;
import structures.PriorityQueue;
import structures.Stack;

/*
 * @author - Ilia G. Bravard
 * Servlet implementation class WebController. This servlet class performs all
 * transaction operations requested by the user.
 */
@WebServlet("/usersServlet")
public class WebController extends HttpServlet {

	private final int MAX_SIZE = 1000; // To avoid magic numbers
	private static final long serialVersionUID = 1L;

	private Stack dao = new Stack(); // The stack used to hold all users
	private PriorityQueue queue = new PriorityQueue(MAX_SIZE); // The PQ used to hold all tasks

	@SuppressWarnings("unchecked")
	private ArrayList<Task>[] allTasks = new ArrayList[MAX_SIZE];

	/**
	 * This is the default, no argument constructor.
	 * 
	 * @see HttpServlet#HttpServlet()
	 */
	public WebController() {
		super();

		// Initializing the helper structure
		for (int i = 0; i < MAX_SIZE; i++) {
			allTasks[i] = new ArrayList<Task>();
		}
	}

	/**
	 * This method handles the POST request made by the user of the application.
	 * 
	 * @param request  - the HTTP request
	 * @param response - the HTTP response
	 * @throws ServletException, IOException
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// If the person chose to add a new user to the application,
		if (request.getParameter("doThisToUser") == null) {

			// Store the user's details into variables
			String fName = request.getParameter("fName");
			String lName = request.getParameter("lName");
			String email = request.getParameter("email");

			// Instantiate a new user with the provided details
			User toAdd = new User(fName, lName, email);

			// Add the user to the stack
			dao.push(toAdd);

			// Send the person to the index page
			getServletContext().getRequestDispatcher("/index.html").forward(request, response);
		}

		// Else if the person chose to delete a user from the application
		else if (request.getParameter("doThisToUser").equals("delete")) {

			try {
				// Remove the top user from the stack
				dao.pop();
				request.setAttribute("numOfUsers", dao.size());
			} catch (StackEmptyException e) { // Or handle the appropriate exception
				e.printStackTrace();
			}

			try {
				// Add an attribute with all users to be handled by the appropriate JSP page
				request.setAttribute("allUsers", dao.showAll());
			} catch (StackEmptyException e) { // Or handle the appropriate exception
				e.printStackTrace();
			}

			// Send the person to view the current users left
			getServletContext().getRequestDispatcher("/usersList.jsp").forward(request, response);
		}

		// Else if the person chose to sort all users of the application,
		else if (request.getParameter("doThisToUser").equals("sort")) {

			try {
				// Sort them by the ID numbers using an insertion sort
				request.setAttribute("numOfUsers", dao.size());
				request.setAttribute("allUsers", dao.insertionSort());
			} catch (StackEmptyException e) { // Or handle the appropriate exception
				e.printStackTrace();
			}

			// Send the person to view the sorted users
			getServletContext().getRequestDispatcher("/usersList.jsp").forward(request, response);
		}

		// Else if the person chose to add a new task to the application,
		else if (request.getParameter("doThisToUser").equals("")) {

			try {
				// Get all users that are currently in the stack for the JSP page
				request.setAttribute("allUsers", dao.showAll());
			} catch (StackEmptyException e) { // Or handle the appropriate exception
				e.printStackTrace();
			}

			// Send the person to the task page
			getServletContext().getRequestDispatcher("/addTask.jsp").forward(request, response);
		}

		// Else if the person schedules a new task to a user of the application
		else if (request.getParameter("doThisToUser").equals("Schedule")) {

			// Store the task details into variables
			String details = request.getParameter("details");
			String dateString = request.getParameter("date");
			LocalDate dateDue = LocalDate.parse(dateString);
			String user = request.getParameter("userSelected");
			User toAssignTo = null;

			try {
				// Find the user to whom a task will be assigned
				try {
					toAssignTo = dao.findUser(Integer.parseInt(user));
				} catch (NumberFormatException e) { // Or handle the appropriate exceptions
					e.printStackTrace();
				} catch (IDNotFoundException e) {
					e.printStackTrace();
				}
			} catch (StackEmptyException e1) { // Or handle the appropriate exception
				e1.printStackTrace();
			}

			// Instantiate a new task object with the provided details
			Task toAdd = new Task(dateDue, details);

			allTasks[toAssignTo.getUserID() - 1].add(toAdd);

			String taskIDs = "| ";

			// Display the tasks that each user has
			for (Task t : allTasks[toAssignTo.getUserID() - 1]) {
				taskIDs += t.getTaskID() + " | ";
				toAssignTo.setTaskID(taskIDs);
			}

			try {
				// Add the task object to the prirority queue
				queue.enqueue(toAdd);
			} catch (PriorityQueueFullException e) { // Or handle the appropriate exception
				e.printStackTrace();
			}

			// Send the user back to the index page
			getServletContext().getRequestDispatcher("/index.html").forward(request, response);
		}

		// Else if the person chose to view all tasks in the application
		else if (request.getParameter("doThisToUser").equals(" ")) {

			try {
				// Display all tasks that are in the priority queue
				request.setAttribute("allTasks", queue.displayElements());
				request.setAttribute("numOfTasks", queue.size());
			} catch (PriorityQueueEmptyException e) { // Or handle the appropriate exception
				e.printStackTrace();
			}

			// Send the user to the page to view all tasks
			getServletContext().getRequestDispatcher("/tasksList.jsp").forward(request, response);
		}

		// Else if the person chose to complete a task for a particular user,
		else if (request.getParameter("doThisToUser").equals("complete")) {

			// Get the ID of the completed task
			String stringId = request.getParameter("id");
			int id = Integer.parseInt(stringId);
			Task taskToComplete = null;

			try {
				// Find it from the priority queue
				try {
					taskToComplete = queue.findTaskById(id);
				} catch (IDNotFoundException e) { // Or handle the appropriate exception
					e.printStackTrace();
				}
			} catch (PriorityQueueEmptyException e1) { // Or handle the appropriate exception
				e1.printStackTrace();
			}

			try {
				// Remove the completed task from the priority queue
				queue.dequeue(taskToComplete);
				request.setAttribute("numOfTasks", queue.size());
			} catch (PriorityQueueEmptyException e) { // Or handle the appropriate exception
				e.printStackTrace();
			}
			try {
				// Set an attribute that holds all currently incompleted tasks
				request.setAttribute("allTasks", queue.displayElements());
			} catch (PriorityQueueEmptyException e) { // Or handle the appropriate exeption
				e.printStackTrace();
			}

			int i;

			// Handle the relationship between the user and his/her tasks
			for (i = 0; i < allTasks.length; i++) {
				boolean stop = false;

				for (int j = 0; j < allTasks[i].size(); j++) {
					if (allTasks[i].get(j) == taskToComplete) {
						allTasks[i].remove(allTasks[i].get(j));
						stop = true;
						break;
					}
				}
				if (stop) {
					break;
				}
			}

			User found = null;
			try {
				try {
					found = dao.findUser(i + 1);
				} catch (IDNotFoundException e) { // Or handle the appropriate exception
					e.printStackTrace();
				}
			} catch (StackEmptyException e) {
				e.printStackTrace();
			}

			if (allTasks[found.getUserID() - 1].isEmpty()) {
				found.setTaskID("");
				getServletContext().getRequestDispatcher("/tasksList.jsp").forward(request, response);
			} else {
				String taskIDs = "| ";
				for (Task t : allTasks[found.getUserID() - 1]) {
					taskIDs += t.getTaskID() + " | ";
					found.setTaskID(taskIDs);
				}

				// Send the user to view the currently incomplete tasks
				getServletContext().getRequestDispatcher("/tasksList.jsp").forward(request, response);
			}
		}
	}

	/**
	 * This method handles the GET request made by the user.
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			// Get all users that are in the application
			request.setAttribute("allUsers", dao.showAll());
			request.setAttribute("numOfUsers", dao.size());
		} catch (StackEmptyException e) { // Or handle the appropriate exception
			e.printStackTrace();
		}

		// Send the person to view all users
		getServletContext().getRequestDispatcher("/usersList.jsp").forward(request, response);
	}
}