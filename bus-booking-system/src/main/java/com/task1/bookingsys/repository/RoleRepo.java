package com.task1.bookingsys.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.task1.bookingsys.model.Role;



@Repository
public interface RoleRepo extends CrudRepository<Role, String> {

}
