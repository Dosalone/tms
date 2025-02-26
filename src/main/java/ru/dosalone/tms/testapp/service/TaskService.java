package ru.dosalone.tms.testapp.service;

import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dosalone.tms.testapp.enums.Priority;
import ru.dosalone.tms.testapp.enums.Status;
import ru.dosalone.tms.testapp.entity.TaskEntity;
import ru.dosalone.tms.testapp.entity.UserEntity;
import ru.dosalone.tms.testapp.mapper.TaskJpaMapper;
import ru.dosalone.tms.testapp.model.TaskModel;
import ru.dosalone.tms.testapp.repository.TaskRepository;
import ru.dosalone.tms.testapp.repository.UserRepository;

import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    /** Создаёт новую задачу
     *
     * @param taskModel
     * @return id созданной задачи
     * РАЗОБРАТЬСЯ, КАКОЕ ИСКЛЮЧЕНИЕ ПРОБРОСИТЬ
     */
    public Long createNewTask(@NotNull TaskModel taskModel) {
        if (userRepository.findById(taskModel.getReporter_id()).isPresent()) {
            UserEntity reporter = userRepository.findById(taskModel.getReporter_id()).get();
            UserEntity assignee = userRepository.findById(taskModel.getAssignee_id()).orElse(null);
            TaskEntity taskEntity = TaskJpaMapper.toJpa(taskModel, reporter, assignee);
            taskRepository.save(taskEntity);
            return taskEntity.getId();
        } else throw new RuntimeException("404: Task not found");
    }

    /** Удаляет задачу по id
     *
     * @param taskId
     * @return String с результатом выполнения
     */
    public String deleteTaskFromId(@NotNull Long taskId) {
        if(taskRepository.findById(taskId).isPresent()) {
            taskRepository.deleteById(taskId);
            return "Задача удалена";
        } else return "Задача не найдена";
    }

    /** Переименовывает задачу
     *
     * @param taskId
     * @param name
     * @return String с результатом выполнения
     */
    public String renameTaskFromId(Long taskId, String name) {
        if(taskRepository.findById(taskId).isPresent()) {
            TaskEntity taskEntity = taskRepository.findById(taskId).get();
            taskEntity.setName(name);
            taskRepository.save(taskEntity);
            return "Название изменено";
        } else return "Задача не найдена";
    }

    /** Изменяет описание задачи
     *
     * @param taskId
     * @param description
     * @return String с результатом выполнения
     */
    public String changeDescriptionTaskFromId(Long taskId, String description) {
        if(taskRepository.findById(taskId).isPresent()) {
            TaskEntity taskEntity = taskRepository.findById(taskId).get();
            taskEntity.setDescription(description);
            taskRepository.save(taskEntity);
            return "Описание изменено";
        } else return "Задача не найдена";
    }

    /** Изменяет статус задачи
     *
     * @param taskId
     * @param status
     * @return String с результатом выполнения
     */
    public String changeStatusTaskFromId(Long taskId, Status status) {
        if(taskRepository.findById(taskId).isPresent()) {
            TaskEntity taskEntity = taskRepository.findById(taskId).get();
            taskEntity.setStatus(status);
            taskRepository.save(taskEntity);
            return "Статус изменён";
        } else return "Задача не найдена";
    }

    /** Изменяет приоритет задачи
     *
     * @param taskId
     * @param priority
     * @return String с результатом выполнения
     */
    public String changePriorityTaskFromId(Long taskId, Priority priority) {
        if(taskRepository.findById(taskId).isPresent()) {
            TaskEntity taskEntity = taskRepository.findById(taskId).get();
            taskEntity.setPriority(priority);
            taskRepository.save(taskEntity);
            return "Приоритет изменён";
        } else return "Задача не найдена";
    }

    /** Добавляет или изменяет исполнителя задачи
     *
     * @param taskId
     * @param assigneeId
     * @return String с результатом выполнения
     */
    public String changeAssigneeTaskFromId(Long taskId, Long assigneeId) {
        if(taskRepository.findById(taskId).isPresent()) {
            TaskEntity taskEntity = taskRepository.findById(taskId).get();
            if (userRepository.findById(assigneeId).isPresent()) {
                UserEntity assigneeEntity = userRepository.findById(assigneeId).get();
                taskEntity.setAssignee(assigneeEntity);
            } else {
                return "Исполнитель не найден";
            }
            taskRepository.save(taskEntity);
            return "Исполнитель изменён";
        } else return "Задача не найдена";
    }

    /** Возвращает задачу по id
     *
     * @param taskId
     * @return TaskModel
     * РАЗОБРАТЬСЯ, КАКОЕ ИСКЛЮЧЕНИЕ ПРОБРОСИТЬ
     */
    public TaskModel getTaskFromId(Long taskId) {
        if (taskRepository.findById(taskId).isPresent()) {
            TaskEntity taskEntity = taskRepository.findById(taskId).get();
            return TaskJpaMapper.toDomain(taskEntity);
        } else throw new RuntimeException("404: Task not found");
    }
}
