package com.task1.bookingsys.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.task1.bookingsys.model.User;

@Repository
public interface UserRepo extends CrudRepository<User, String> {
	    User findByUsername(String username);

	    @Query("select u from User u where u.usermail = ?1")
		User findByUsermail(String usermail);
}


