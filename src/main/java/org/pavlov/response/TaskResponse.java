package org.pavlov.response;

import lombok.Getter;
import lombok.Setter;
import org.pavlov.model.Task;

@Getter
@Setter
public class TaskResponse {

    Long id;

    String name;

    public TaskResponse(Task task) {
        id = task.getId();
        name = task.getName();
    }
}