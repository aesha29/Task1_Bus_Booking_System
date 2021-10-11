package com.task1.bookingsys.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.task1.bookingsys.model.Booking;
@Repository
public interface BookingRepo extends MongoRepository<Booking,String>{

}
