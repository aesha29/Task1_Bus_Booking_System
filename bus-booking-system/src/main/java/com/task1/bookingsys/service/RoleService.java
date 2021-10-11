package com.task1.bookingsys.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task1.bookingsys.model.Role;
import com.task1.bookingsys.repository.RoleRepo;

@Service
public class RoleService {

    @Autowired
    private RoleRepo repo;

    public Role createNewRole(Role role) {
        return repo.save(role);
    }
}
