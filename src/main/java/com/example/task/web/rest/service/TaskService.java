package com.example.task.web.rest.service;

import com.example.task.web.rest.ressource.TaskRequestResource;
import com.example.task.web.rest.ressource.TaskResource;
import com.example.task.web.rest.ressource.TaskResponseResource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    public TaskResponseResource process(TaskRequestResource request) {
        return new TaskResponseResource(sort(request.getTasks()));
    }

    public String processBash(TaskRequestResource request) {
        List<TaskResource> sortedTasks = sort(request.getTasks());

        StringBuilder bashScript = new StringBuilder("#!/usr/bin/env bash")
                .append(System.lineSeparator());

        sortedTasks.forEach(task -> {
            bashScript.append(System.lineSeparator());
            bashScript.append(task.getCommand());
        });

        return bashScript.toString();
    }

    private List<TaskResource> sort(List<TaskResource> tasks) {
        return tasks.stream().sorted((a, b) -> {
                    if (a.getRequires() == null || a.getRequires().isEmpty()) {
                        return -1;
                    }

                    if (b.getRequires() == null || b.getRequires().isEmpty()) {
                        return 1;
                    }

                    return a.getRequires().contains(b.getName()) ? 1 : -1;
                }
        ).collect(Collectors.toList());
    }
}
