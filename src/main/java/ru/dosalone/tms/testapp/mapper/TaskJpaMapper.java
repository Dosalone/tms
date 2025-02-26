package ru.dosalone.tms.testapp.mapper;

import ru.dosalone.tms.testapp.entity.TaskEntity;
import ru.dosalone.tms.testapp.entity.UserEntity;
import ru.dosalone.tms.testapp.model.TaskModel;

public class TaskJpaMapper {
    public static TaskEntity toJpa(TaskModel taskModel,
                                   UserEntity reporter,
                                   UserEntity assignee) {
        return new TaskEntity(null,
                taskModel.getName(),
                taskModel.getDescription(),
                taskModel.getStatus(),
                taskModel.getPriority(),
                reporter,
                assignee);
    }

    public static TaskModel toDomain(TaskEntity taskEntity) {
        return new TaskModel(taskEntity.getId(),
                taskEntity.getName(),
                taskEntity.getDescription(),
                taskEntity.getStatus(),
                taskEntity.getPriority(),
                taskEntity.getReporter().getId(),
                taskEntity.getAssignee().getId());
    }
}
