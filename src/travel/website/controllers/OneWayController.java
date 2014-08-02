package travel.website.controllers;
 
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
 
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 


import javax.servlet.http.HttpSession;

import travel.website.model.FindFlights;
import travel.website.model.Flight;
 
 
public class OneWayController extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    String source;
    String destination;
    String departingDate;
    int adults;
    int children;
    
    public OneWayController() {
        super();
    }
 
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
 
         
        RequestDispatcher rd = null;
 
        FindFlights findFlights = new FindFlights();
        List<Flight> departingFlights = new ArrayList<Flight>();
        Map<String,List<Flight>> result = new HashMap<String, List<Flight>>();
        HttpSession session = request.getSession();
        
        processRequest(request);
        findFlights.setSession(session);
        
        
        try {
            //departingFlights = findFlights.listAvailableFlights(request);
        	departingFlights = findFlights.listAvailableFlights(source, destination, departingDate, adults, children);
            result.put("DepartingFlightList", departingFlights);
             
            if (departingFlights.size()!=0) {
                rd = request.getRequestDispatcher("/flightsAvailableOneWay.jsp");
                request.setAttribute("flightsAvailable", result);
            } else {
                rd = request.getRequestDispatcher("/flightsNotFound.jsp");
                request.setAttribute("message","Flights not found for the given conditions");
            }
            rd.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
     
    }

	private void processRequest(HttpServletRequest request) {
		
		source = request.getParameter("source");
		destination = request.getParameter("destination");
		departingDate = request.getParameter("departing");
		adults = Integer.parseInt(request.getParameter("adults"));
		children = Integer.parseInt(request.getParameter("children"));
	}
 
}