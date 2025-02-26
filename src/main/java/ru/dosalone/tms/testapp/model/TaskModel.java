package ru.dosalone.tms.testapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.dosalone.tms.testapp.enums.Priority;
import ru.dosalone.tms.testapp.enums.Status;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskModel {
    private Long id;

    private String name;

    private String description;

    private Status status;

    private Priority priority;

    private Long reporter_id;

    private Long assignee_id;
}
