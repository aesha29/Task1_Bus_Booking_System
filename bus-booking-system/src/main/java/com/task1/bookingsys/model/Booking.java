package com.task1.bookingsys.model;


import javax.persistence.Id;
import javax.validation.constraints.NotNull;


import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "booking")
public class Booking {
	
	@Id
    private String id;

	@NotNull(message = "User's first name must not be null")
    private String first_name;

	@NotNull(message = "User's first name must not be null")
    private String last_name;

	@NotNull(message = "Bus no must not be null")
    private String bus_no;

	@NotNull(message = "Seat no must not be null")
    private String seat_no;

	public Booking(String id, @NotNull(message = "User's first name must not be null") String first_name,
			@NotNull(message = "User's first name must not be null") String last_name,
			@NotNull(message = "Bus no must not be null") String bus_no,
			@NotNull(message = "Seat no must not be null") String seat_no) {
		super();
		this.id = id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.bus_no = bus_no;
		this.seat_no = seat_no;
	}

	public Booking() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getBus_no() {
		return bus_no;
	}

	public void setBus_id(String bus_no) {
		this.bus_no = bus_no;
	}

	public String getSeat_no() {
		return seat_no;
	}

	public void setSeat_no(String seat_no) {
		this.seat_no = seat_no;
	}
	
	
	

}
