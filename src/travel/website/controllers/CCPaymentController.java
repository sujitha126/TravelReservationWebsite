package travel.website.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import travel.website.model.CCValidator;

public class CCPaymentController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CCPaymentController() {
		super();
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher rd = null;
		HttpSession session = request.getSession();
		CCValidator ccvalidator = new CCValidator();
		String result;
		try {
			result = ccvalidator.validate(request);
			if (result.equals("valid")) {
				rd = request.getRequestDispatcher("/FlightConfirmation.jsp");
				session.setAttribute("numberOfInvalidAttempts", 0);

			} else {
				if (session.getAttribute("numberOfInvalidAttemps") != null) {
					int value = (Integer) session
							.getAttribute("numberOfInvalidAttemps");
					if (value < 3) {
						session.setAttribute("numberOfInvalidAttemps",
								value + 1);
						rd = request
								.getRequestDispatcher("/payUsingCreditCard.jsp");
						request.setAttribute("errormessage",
								"Invalid Credit Card details. Please try again:");
						setRequestAttributes(request);

					} else {
						rd = request
								.getRequestDispatcher("/invalidTransaction.jsp");
					}
				} else {
					session.setAttribute("numberOfInvalidAttemps", 1);
					rd = request
							.getRequestDispatcher("/payUsingCreditCard.jsp");
					request.setAttribute("errormessage",
							"Invalid Credit Card details. Please try again:");
					setRequestAttributes(request);
				}
			}

			rd.forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void setRequestAttributes(HttpServletRequest request) {
		@SuppressWarnings("unchecked")
		Enumeration<String> enumKeys = request.getParameterNames();
		while (enumKeys.hasMoreElements()) {
			String key = enumKeys.nextElement();
			request.setAttribute(key, request.getParameter(key));
		}
	}

}
