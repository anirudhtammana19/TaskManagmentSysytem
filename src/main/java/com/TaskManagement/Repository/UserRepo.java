package com.TaskManagement.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.TaskManagement.Model.Users;


public interface UserRepo extends JpaRepository<Users, Long> {

	public Optional<Users> findByUsername(String username);
    // JpaRepository provides CRUD operations for User entity
}
