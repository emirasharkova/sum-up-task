package com.example.task.web.rest.ressource;

import java.util.ArrayList;
import java.util.List;

public class TaskResponseResource {

    private List<TaskResource> tasks;

    public TaskResponseResource() {
        this.tasks = new ArrayList<>();
    }

    public TaskResponseResource(List<TaskResource> tasks) {
        this.tasks = tasks;
    }

    public List<TaskResource> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskResource> tasks) {
        this.tasks = tasks;
    }
}
