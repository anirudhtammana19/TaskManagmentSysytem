package com.TaskManagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.TaskManagement.Model.Tasks;
import com.TaskManagement.Model.Users;

public interface TaskRepo extends JpaRepository<Tasks,Long>{

	

}
