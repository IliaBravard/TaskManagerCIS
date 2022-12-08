package controllers; // The package where this servlet class is located at

// Including the needed imports
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * @author - Ilia G. Bravard
 * Servlet implementation class LoginServlet. This servlet class checks the
 * user's login credentials and lets him/her in to the application.
 */
@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * This is the default, no argument constructor.
	 * 
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
	}

	/**
	 * This method checks the user's login credentials and redirects him/her to the
	 * appropriate page.
	 * 
	 * @param request  - the HTTP request
	 * @param response - the HTTP response
	 * @throws ServletException, IOException
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Assigning the user inputs to the appropriate variables
		String userName = request.getParameter("username");
		String password = request.getParameter("password");

		String path = ""; // The path the user is redirected to

		// Checking whether the login credentials are valid
		if (userName.equals("IliaBravard") && password.equals("AdminAccess@2022")) {
			path = "/index.html"; // If so, allow him/her in
		} else {
			path = "/login.html"; // Otherwise, try again
		}

		// Forwarding the request to the appropriate page
		getServletContext().getRequestDispatcher(path).forward(request, response);
	}
}