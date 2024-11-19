package com.TaskManagement.Model;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
public class Tasks {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long taskid;
	private String title;
	private String description;
	private LocalDate due_date;
	@Enumerated(EnumType.STRING)
	private Priority priority;
	@Enumerated(EnumType.STRING)
	private Status status;
	
	public enum Priority{
		Low,Medium,High
	}
	
	public enum Status{
		Pending,Inprogress,Completed
	}
	
	public Tasks() {
		//default Constructor
	}
	
	public Tasks(Long taskid,String title,String description,LocalDate due_date,
			Priority priority,Status status) {
		
		this.taskid=taskid;
		this.title=title;
		this.description=description;
		this.due_date=due_date;
		this.priority=priority;
		this.status= status;
		
	}

	public Long getTaskid() {
		return taskid;
	}

	public void setTaskid(Long taskid) {
		this.taskid = taskid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getDue_date() {
		return due_date;
	}

	public void setDue_date(LocalDate due_date) {
		this.due_date = due_date;
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Tasks [taskid=" + taskid + ", title=" + title + ", description=" + description + ", due_date="
				+ due_date + ", priority=" + priority + ", status=" + status + "]";
	}
	




	
	
	
	

}
