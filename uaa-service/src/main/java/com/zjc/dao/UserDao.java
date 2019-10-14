package com.zjc.dao;


import com.zjc.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;



public interface UserDao extends JpaRepository<User, Long> {

	User findByUsername(String username);
}
