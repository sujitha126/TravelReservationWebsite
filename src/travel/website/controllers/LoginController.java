package travel.website.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import travel.website.model.Authenticator;
import travel.website.model.User;


 public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginController() {
		super();
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		RequestDispatcher rd = null;

		Authenticator authenticator = new Authenticator();
		String result;
		try {
			result = authenticator.authenticate(username, password);
			if (result.equals("success")) {
				rd = request.getRequestDispatcher("/success.jsp");
				User user = new User(username, password);
				request.setAttribute("user", user);
				HttpSession session = request.getSession();
				session.setAttribute("userid", username);
			} else {
				rd = request.getRequestDispatcher("/error.jsp");
			}
			rd.forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

}
