package travel.website.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;







import travel.website.model.FindFlights;
import travel.website.model.Flight;

public class TripSummaryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	HttpSession session;
	FindFlights findFlights;
	Map<String,Flight> flightselected;
	List<Flight> flightselectedlist;
	
	public TripSummaryController() {
		super();
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		
		RequestDispatcher rd = null;
		session = request.getSession();
		findFlights = new FindFlights();
		flightselected = new HashMap<String, Flight>();
		flightselectedlist = new ArrayList<Flight>();
		
		
		try {
			processRequest(request);
			double totalcost = calculateTotalCost(flightselected);
			rd = request.getRequestDispatcher("/TripSummary.jsp");
			request.setAttribute("flightselected", flightselectedlist);
			request.setAttribute("totalcost", totalcost);
			session.setAttribute("flightselected", flightselectedlist);
			session.setAttribute("totalcost", totalcost);
			rd.forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	
	}

	private void processRequest(HttpServletRequest request) throws Exception {
		
		if(session.getAttribute("triptype").equals("oneway")) {
			String flightstr = request.getParameter("flightfordeparture");
			int flightid = Integer.parseInt(flightstr);
			Flight departingFlight = findFlights.getFlight(flightid);
			flightselected.put("departingflight",departingFlight);
			flightselectedlist.add(departingFlight);
			
			
		} else if(session.getAttribute("triptype").equals("roundtrip")) {
			
			
			String departingflightstr = request.getParameter("flightfordeparture");
			int departingflightid = Integer.parseInt(departingflightstr);
			Flight departingFlight = findFlights.getFlight(departingflightid);
			flightselected.put("departingflight",departingFlight);
			flightselectedlist.add(departingFlight);
			
			String returningflightstr = request.getParameter("flightforreturning");
			int returningflight = Integer.parseInt(returningflightstr);
			Flight returningFlight = findFlights.getFlight(returningflight);
			flightselected.put("returningflight",returningFlight);
			flightselectedlist.add(returningFlight);
			
		} else if(session.getAttribute("triptype").equals("multiple")) {
			
			int numberOfFlights = (Integer) session.getAttribute("numberofFlights");

			for(int i=1;i<=numberOfFlights;i++) {
				String name = "flight"+i;
		
				String flightstr = request.getParameter(name);
				int flightid = Integer.parseInt(flightstr);
				Flight flight = findFlights.getFlight(flightid);
				flightselected.put(name, flight);
				flightselectedlist.add(flight);
			}
		}
		
	}

	private double calculateTotalCost(Map<String, Flight> flightselected) {
		
		double totalcost = 0;
		double costperperson = 0;
		
		Iterator<Entry<String, Flight>> iterator = flightselected.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<String,Flight> flightentry = (Entry<String, Flight>)iterator.next();
			Flight flight = (Flight) flightentry.getValue();
			costperperson = flight.getBase_price();
			
			totalcost+= (Integer)session.getAttribute("adults") *costperperson;
			totalcost+= (Integer)session.getAttribute("children")*costperperson;
			
		}
		
		//Adding taxes and fee
				totalcost+= totalcost*0.15;
				
				return totalcost;
	}
	

}
