package travel.website.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import travel.website.model.TrackFlight;
import travel.website.model.Flight;


public class TrackFlightController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public TrackFlightController() {
		super();
	}
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	
		RequestDispatcher rd = null;
		System.out.println("HEre point 1");
		TrackFlight tFlight_obj = new TrackFlight();
		List<Flight> flightInfo = new ArrayList<Flight>();
		
		rd = request.getRequestDispatcher("/success.jsp");
		rd.forward(request, response);
		/*
		try {
			flightInfo = tFlight_obj.flightTracker(request);
			System.out.println("HEre point 2");
			if (flightInfo.size()!=0) {
				rd = request.getRequestDispatcher("/TrackInfo.jsp");
				request.setAttribute("flightInfo", flightInfo);
			} else {
				rd = request.getRequestDispatcher("/flightsNotFound.jsp");
			}
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}*/
	
	}

}
