package ru.dosalone.tms.testapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.dosalone.tms.testapp.entity.TaskEntity;
import ru.dosalone.tms.testapp.entity.UserEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentModel {
    private Long id;

    private String description;

    private Long task_id;

    private Long author_id;
}
