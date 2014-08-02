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
 





import travel.website.model.FindFlights;
import travel.website.model.Flight;
 
 
public class RoundTripController extends HttpServlet {
    private static final long serialVersionUID = 1L;
	
    String source;
	String destination;
	String departingstr;
	String returningstr;
	int adults;
	int children;
 
    public RoundTripController() {
        super();
    }
 
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
 
         
        RequestDispatcher rd = null;
 
        FindFlights findFlights = new FindFlights();
        List<Flight> departingFlights = new ArrayList<Flight>();
        List<Flight> returningFlights = new ArrayList<Flight>();
        Map<String,List<Flight>> result = new HashMap<String, List<Flight>>();
        
        setAttributesFromRequest(request);
        
        findFlights.setSession(request.getSession());
        try {
			departingFlights = findFlights.listAvailableFlights(source,destination,departingstr,adults,children);
			returningFlights = findFlights.listAvailableFlights(destination, source, returningstr, adults, children);
	        
			if(departingFlights!=null) {
	        result.put("DepartingFlightList", departingFlights);
			}
			if(returningFlights!=null) {
	        result.put("ReturningFlightList", returningFlights);
			}
	        
			if(departingFlights!=null && returningFlights!=null){
	                rd = request.getRequestDispatcher("/flightsAvailableRoundTrip.jsp");
	                request.setAttribute("flightsAvailable", result);
	            } else {
	                rd = request.getRequestDispatcher("/flightsNotFound.jsp");
	                request.setAttribute("message", "No matching flights. Please try seperately");
	            }
	            rd.forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     
    }

	private void setAttributesFromRequest(HttpServletRequest request) {
		
		source = request.getParameter("source");
		destination = request.getParameter("destination");
		departingstr = request.getParameter("departing");
		returningstr = request.getParameter("returning");
		adults = Integer.parseInt(request.getParameter("adults"));
		children = Integer.parseInt(request.getParameter("children"));
		
	}
 
}