package com.example.task.web.rest.ressource;

import org.springframework.lang.NonNull;

import java.util.List;

public class TaskRequestResource {

    @NonNull
    private List<TaskResource> tasks;

    public TaskRequestResource() {

    }

    public TaskRequestResource(List<TaskResource> tasks) {
        this.tasks = tasks;
    }

    public List<TaskResource> getTasks() {
        return tasks;
    }

    public void List(List<TaskResource> tasks) {
        this.tasks = tasks;
    }
}
