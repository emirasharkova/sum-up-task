package com.example.task.service;

import com.example.task.web.rest.ressource.TaskRequestResource;
import com.example.task.web.rest.ressource.TaskResource;
import com.example.task.web.rest.ressource.TaskResponseResource;
import com.example.task.web.rest.service.TaskService;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TaskServiceTest {

    private final TaskService service = new TaskService();

    @Test
    public void shouldProcessGivenTasks() {
        // GIVEN
        TaskRequestResource request = mockedTasks();

        // WHEN
        TaskResponseResource result = service.process(request);

        // THEN
        assertNotNull(result);
        List<TaskResource> tasks = result.getTasks();
        assertNotNull(tasks);
        assertEquals(tasks.get(0).getName(), "task-1");
        assertEquals(tasks.get(1).getName(), "task-3");
        assertEquals(tasks.get(2).getName(), "task-2");
        assertEquals(tasks.get(3).getName(), "task-4");
    }

    @Test
    public void shouldProcessGivenTasksAndReturnAsBashScript() {
        // GIVEN
        TaskRequestResource request = mockedTasks();

        // WHEN
        String result = service.processBash(request);

        // THEN
        String lineSeparator = System.lineSeparator();
        String expectedResult = "#!/usr/bin/env bash" +
                lineSeparator + lineSeparator +
                "touch /tmp/file1" + lineSeparator +
                "echo \"Hello World!\" > /tmp/file1" + lineSeparator +
                "cat /tmp/file1" + lineSeparator +
                "rm /tmp/file1";
        assertNotNull(result);
        assertEquals(expectedResult, result);
    }

    private TaskRequestResource mockedTasks() {
        TaskResource t1 = new TaskResource();
        t1.setName("task-1");
        t1.setCommand("touch /tmp/file1");

        TaskResource t2 = new TaskResource();
        t2.setName("task-2");
        t2.setCommand("cat /tmp/file1");
        t2.setRequires(Arrays.asList("task-3"));

        TaskResource t3 = new TaskResource();
        t3.setName("task-3");
        t3.setCommand("echo \"Hello World!\" > /tmp/file1");
        t3.setRequires(Arrays.asList("task-1"));

        TaskResource t4 = new TaskResource();
        t4.setName("task-4");
        t4.setCommand("rm /tmp/file1");
        t4.setRequires(Arrays.asList("task-2", "task-3"));

        List<TaskResource> taskList = Arrays.asList(t1, t2, t3, t4);

        return new TaskRequestResource(taskList);
    }

}
