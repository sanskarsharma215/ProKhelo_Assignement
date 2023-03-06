package com.prokhelo.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import com.prokhelo.entities.User;

public interface UserRepository extends JpaRepository<User,Long>  {
    User findByEmail(String email);  
}
