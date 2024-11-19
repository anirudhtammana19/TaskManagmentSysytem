package com.TaskManagement.DTO;

import java.time.LocalDate;
import com.TaskManagement.Model.Tasks.Priority;
import com.TaskManagement.Model.Tasks.Status;

public class TasksDTO {

    private int taskid;
    private String title;
    private String description;
    private LocalDate due_date;
    private Priority priority;
    private Status status;

    // Default constructor
    public TasksDTO() {
    }

    // Parameterized constructor
    public TasksDTO(int taskid, String title, String description, 
                   LocalDate due_date, Priority priority, Status status) {
        this.taskid = taskid;
        this.title = title;
        this.description = description;
        this.due_date = due_date;
        this.priority = priority;
        this.status = status;
    }

    // Getters and Setters
    public int getTaskid() {
        return taskid;
    }

    public void setTaskid(int taskid) {
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
		return "TasksDTO [taskid=" + taskid + ", title=" + title + ", description=" + description + ", due_date="
				+ due_date + ", priority=" + priority + ", status=" + status + "]";
	}

}
