package com.example.task.web.rest.ressource;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.lang.NonNull;

import java.util.List;

public class TaskResource {

    @NonNull
    private String name;

    @NonNull
    private String command;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<String> requires;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public List<String> getRequires() {
        return requires;
    }

    public void setRequires(List<String> requires) {
        this.requires = requires;
    }
}
