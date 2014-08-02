package travel.website.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import travel.website.model.FindFlights;
import travel.website.model.Flight;

public class MultiWayController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	class RequestDetails {
		String source;
		String destination;
		String departingstr;

		public RequestDetails(String source, String destination,
				String departingStr) {
			this.source = source;
			this.destination = destination;
			this.departingstr = departingStr;
		}
	}

	int adults;
	int children;
	List<RequestDetails> listOfRequests;
	int numberOfFlights;
	List<List<Flight>> flightsAvailable;

	public MultiWayController() {
		super();
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher rd = null;

		FindFlights findFlights = new FindFlights();
		listOfRequests = new ArrayList<RequestDetails>();
		flightsAvailable = new ArrayList<List<Flight>>();
		HttpSession session = request.getSession();

		processRequest(request);

		session.setAttribute("numberofFlights",numberOfFlights);
		//session.setAttribute("adults",adults);
		//session.setAttribute("children",children);
		
		findFlights.setSession(session);

		for (int i = 0; i < numberOfFlights; i++) {

			RequestDetails req = listOfRequests.get(i);
			try {
				flightsAvailable.add(findFlights.listAvailableFlights(
						req.source, req.destination, req.departingstr, adults,
						children));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
				if (flightsAvailable != null) {
					rd = request.getRequestDispatcher("/flightsAvailableMultiWay.jsp");
					request.setAttribute("flightsAvailable", flightsAvailable);
				} else {
					rd = request.getRequestDispatcher("/flightsNotFound.jsp");
					request.setAttribute("message",
							"No matching flights. Please try seperately");
				}
				rd.forward(request, response);

	}

	private void processRequest(HttpServletRequest request) {
		String name = "sourceFlight";
		String source, destination, departingStr;
		RequestDetails flightRequest;
		int count = 1;
		source = request.getParameter("source");
		destination = request.getParameter("destination");
		departingStr = request.getParameter("departing");
		adults = Integer.parseInt(request.getParameter("adults"));
		children = Integer.parseInt(request.getParameter("children"));
		

		flightRequest = new RequestDetails(source, destination, departingStr);
		listOfRequests.add(flightRequest);

		for (int i = 2; i <= 6; i++) {
			String flightname = name + i;
			if (request.getParameter(flightname) != null
					&& request.getParameter(flightname) != "") {
				count++;
				source = request.getParameter(flightname);
				destination = request.getParameter("destinationFlight" + i);
				departingStr = request.getParameter("departingDate" + i);
				flightRequest = new RequestDetails(source, destination,
						departingStr);
				listOfRequests.add(flightRequest);
			} else {
				break;
			}
		}
		numberOfFlights = count;
	}

}