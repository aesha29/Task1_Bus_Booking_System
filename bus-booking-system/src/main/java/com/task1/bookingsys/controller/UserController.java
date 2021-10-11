package com.task1.bookingsys.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.task1.bookingsys.model.Booking;
import com.task1.bookingsys.model.User;
import com.task1.bookingsys.myexception.ResourceNotFoundException;
import com.task1.bookingsys.repository.BookingRepo;
import com.task1.bookingsys.repository.UserRepo;
import com.task1.bookingsys.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
    private UserService userService;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private BookingRepo bookingRepo;
	
	 

    @PostConstruct
    public void initRoleAndUser() {
        userService.initRoleAndUser();
    }


    @GetMapping({"/forAdmin"})
    @PreAuthorize("hasRole('Admin')")
    public String forAdmin(){
        return "This URL is only accessible to the admin";
    }

    @GetMapping({"/forUser"})
    @PreAuthorize("hasRole('User')")
    public String forUser(){
        return "This URL is only accessible to the user";
    }
	
	    
	    @GetMapping("/admin/viewusers")
	    @PreAuthorize("hasRole('Admin')")
	    public List<User> users(){
	        return (List<User>) this.userRepo.findAll();
	    }
	 
	    @GetMapping("/admin/viewbookings")
	    @PreAuthorize("hasRole('Admin')")
	    public List<Booking> showAllBookings(){
	        return bookingRepo.findAll();
	    }

	    
	    
	    @PostMapping("/user/booking")
	    @PreAuthorize("hasRole('User')")
	    public Booking makeBooking(@Valid @RequestBody Booking booking) {
	    	return bookingRepo.save(booking);
	    	
	    }
	    
	    @DeleteMapping("/user/cancel/booking/{id}")
	    @PreAuthorize("hasRole('User')")
	    public void deleteBookingById(@PathVariable(value = "id") String id) throws ResourceNotFoundException{
	    	Booking b = bookingRepo.findById(id)
	    			.orElseThrow(() -> new ResourceNotFoundException("Booking is not found for this id : " + id));
	    	bookingRepo.delete(b);
	    	System.out.println("Booking successfully canceled!");	    }
	    
	    
	    @PostMapping( "/register")
		public User saveUser(@RequestBody User user) throws Exception {
	    	System.out.println(user);
			return (userService.registerUser(user));
		}

	  
	
	

}
