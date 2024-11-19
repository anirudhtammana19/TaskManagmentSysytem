package com.TaskManagement.Service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.TaskManagement.DTO.TasksDTO;
import com.TaskManagement.DTO.UserDTO;
import com.TaskManagement.Exception.TaskNotFoundException;
import com.TaskManagement.Model.Tasks;
import com.TaskManagement.Model.Users;
import com.TaskManagement.Repository.TaskRepo;
import com.TaskManagement.Repository.UserRepo;

@Service
public class TaskService {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private UserRepo userrepo;
    
    @Autowired
    private TaskRepo taskrepo;
    
    @Autowired
    private PasswordEncoder encoder;

    public UserDTO savedata(UserDTO d) {
        Users user = mapper.map(d, Users.class);
        user.setPassword(encoder.encode(d.getPassword()));
        Users savedUser = userrepo.save(user);
        UserDTO savedUserDTO = mapper.map(savedUser, UserDTO.class);

        return savedUserDTO;
    }
    
    public List<TasksDTO> getalltasks() {
    	
    	List<Tasks> task = taskrepo.findAll();
    	
    	List<TasksDTO> taskdto = task.stream().map(i->{
    		TasksDTO j = mapper.map(i,TasksDTO.class);
    		return j;
    	}).toList();
    	
    	return taskdto;
    	
    }

	public String addAtask(TasksDTO d) {
		
		Tasks task = mapper.map(d,Tasks.class);
		
		task.setStatus(Tasks.Status.Pending);
		
		Tasks savedtask = taskrepo.save(task);
		
		TasksDTO taskdto = mapper.map(savedtask, TasksDTO.class);
		
		return "Task Saved Successfully";
	}

	public TasksDTO findbyid(Long taskid) throws TaskNotFoundException {
		Tasks task = taskrepo.findById(taskid).orElse(null);
		if (task==null) {
			throw new TaskNotFoundException("Task with that id is not found");
		}
		TasksDTO taskdto = mapper.map(task, TasksDTO.class);
		return taskdto;
	}

	public String updateAtask(Long taskid, TasksDTO d) {
		Tasks existingtask = taskrepo.findById(taskid).orElse(null);
		
		if (existingtask==null) {
			return "Task not found";
		}
		
		if(d.getTitle()!=null) {
			existingtask.setTitle(d.getTitle());
		}
		if(d.getDescription()!=null) {
			existingtask.setDescription(d.getDescription());
		}
		if(d.getDue_date()!=null) {
			existingtask.setDue_date(d.getDue_date());
			
		}
		if(d.getPriority()!=null) {
			existingtask.setPriority(d.getPriority());
		}
		if(d.getStatus()!=null) {
			existingtask.setStatus(d.getStatus());
		}
		
		Tasks updated = taskrepo.save(existingtask);
		
		TasksDTO taskdto = mapper.map(updated, TasksDTO.class);
		
		return "Task updated successfully";
	}

	public String deleteAtask(Long taskid) {
		Tasks task = taskrepo.findById(taskid).orElse(null);
		if (task == null) {
		    System.out.println("Task not found with ID: " + taskid); // Add logging here
		    return "Task not found";
		} else {
		    taskrepo.delete(task);
		    return "Task deleted successfully";
		}

	
	}
	
	
    
    
}
