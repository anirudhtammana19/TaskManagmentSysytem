package com.TaskManagement.Exception;

public class TaskNotFoundException extends Exception {
	
	public TaskNotFoundException(String msg) {
		super(msg);
	}
	
	public String getmessage(String msg) {
		return("TaskNotFoundException:"+super.getMessage());
	}

}
