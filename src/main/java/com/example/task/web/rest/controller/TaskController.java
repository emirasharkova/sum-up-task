package com.example.task.web.rest.controller;

import com.example.task.web.rest.ressource.TaskRequestResource;
import com.example.task.web.rest.ressource.TaskResponseResource;
import com.example.task.web.rest.service.TaskService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks/v1/process")
public class TaskController {
    private TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TaskResponseResource> process(@RequestBody @Validated TaskRequestResource request) {
        return ResponseEntity.ok(service.process(request));
    }

    @PostMapping("/bash")
    public ResponseEntity<String> processBash(@RequestBody @Validated TaskRequestResource request) {
        return ResponseEntity.ok(service.processBash(request));
    }
}
