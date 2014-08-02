package travel.website.model;

import java.sql.Date;
import java.sql.Time;

public class Flight {
	
	String flight_num;
	int flight_id;
	String airways_id;
	Date departure_date;
	Time departure_time;
	Date arrival_date;
	Time arrival_time;
	String flying_from;
	String flying_to;
	double duration;
	double base_price;
	String status;
	int miles;
	int seats_occupied;
	int total_seats;
	
	public String getFlight_num() {
		return flight_num;
	}
	public void setFlight_num(String flight_num) {
		this.flight_num = flight_num;
	}
	public int getFlight_id() {
		return flight_id;
	}
	public void setFlight_id(int flight_id) {
		this.flight_id = flight_id;
	}
	public String getAirways_id() {
		return airways_id;
	}
	public void setAirways_id(String airways_id) {
		this.airways_id = airways_id;
	}
	public Date getDeparture_date() {
		return departure_date;
	}
	public void setDeparture_date(Date departure_date) {
		this.departure_date = departure_date;
	}
	public Time getDeparture_time() {
		return departure_time;
	}
	public void setDeparture_time(Time departure_time) {
		this.departure_time = departure_time;
	}
	public Date getArrival_date() {
		return arrival_date;
	}
	public void setArrival_date(Date arrival_date) {
		this.arrival_date = arrival_date;
	}
	public Time getArrival_time() {
		return arrival_time;
	}
	public void setArrival_time(Time arrival_time) {
		this.arrival_time = arrival_time;
	}
	public String getFlying_from() {
		return flying_from;
	}
	public void setFlying_from(String flying_from) {
		this.flying_from = flying_from;
	}
	public String getFlying_to() {
		return flying_to;
	}
	public void setFlying_to(String flying_to) {
		this.flying_to = flying_to;
	}
	public double getDuration() {
		return duration;
	}
	public void setDuration(double duration) {
		this.duration = duration;
	}

	public double getBase_price() {
		return base_price;
	}
	public void setBase_price(double base_price) {
		this.base_price = base_price;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getMiles() {
		return miles;
	}
	public void setMiles(int miles) {
		this.miles = miles;
	}
	
	public int getSeats_occupied() {
		return seats_occupied;
	}
	public void setSeats_occupied(int seats_occupied) {
		this.seats_occupied = seats_occupied;
	}
	public int getTotal_seats() {
		return total_seats;
	}
	public void setTotal_seats(int total_seats) {
		this.total_seats = total_seats;
	}
	
}
