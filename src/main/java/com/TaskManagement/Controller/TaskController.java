package com.TaskManagement.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TaskManagement.DTO.TasksDTO;
import com.TaskManagement.DTO.UserDTO;
import com.TaskManagement.Exception.TaskNotFoundException;
import com.TaskManagement.Service.TaskService;

@RestController
@RequestMapping("/api")
public class TaskController {
	
	@Autowired
	TaskService ts;
	
	@PostMapping("/register")
	public ResponseEntity<UserDTO> register(@RequestBody UserDTO d)throws Exception{
		
		UserDTO saveddata = ts.savedata(d);
		if (saveddata==null){
			throw new Exception();
		}
		else {
			return new ResponseEntity<>(saveddata,HttpStatus.CREATED);
		}
	}
	
	@GetMapping("/getalltasks")
	public ResponseEntity<List<TasksDTO>> getall() throws TaskNotFoundException{
		List<TasksDTO> task = ts.getalltasks();
		if (task==null) {
			throw new TaskNotFoundException("There are no tasks");
		}
		else {
			return new ResponseEntity<>(task,HttpStatus.OK);
		}
	}
	
	@PostMapping("/addtask")
	public ResponseEntity<String> addtask(@RequestBody TasksDTO d){
		
		String task = ts.addAtask(d);
		return new ResponseEntity<>(task,HttpStatus.CREATED);
		
		}
	
	@GetMapping("/gettaskbyid/{taskid}")
	public ResponseEntity<TasksDTO>gettaskbyid(@PathVariable Long taskid) throws TaskNotFoundException{
		
		TasksDTO task = ts.findbyid(taskid);
		
		return new ResponseEntity<>(task,HttpStatus.OK);
		
	}
	
	@PutMapping("updatetask/{taskid}")
	public ResponseEntity<String> updatetask(@PathVariable Long taskid,@RequestBody TasksDTO d ){
		String task = ts.updateAtask(taskid,d);
		return new ResponseEntity<>(task,HttpStatus.OK);
	}
	
	@DeleteMapping("deletetask/{taskid}")
	public ResponseEntity<String> deletetask(@PathVariable Long taskid){
		String task = ts.deleteAtask(taskid);
		return new ResponseEntity<>(task,HttpStatus.OK);
	}
		
}
	


