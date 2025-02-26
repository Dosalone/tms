package ru.dosalone.tms.testapp.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.dosalone.tms.testapp.enums.Priority;
import ru.dosalone.tms.testapp.enums.Status;
import ru.dosalone.tms.testapp.model.CommentModel;
import ru.dosalone.tms.testapp.model.TaskModel;
import ru.dosalone.tms.testapp.service.CommentService;
import ru.dosalone.tms.testapp.service.TaskService;

import java.util.List;

@RestController
@RequestMapping("/task")
@Tag(name = "Операции с задачами")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @Autowired
    private CommentService commentService;

    //создать задачу
    @PostMapping(path = "/create", consumes = "application/json")
    public Long createNewTask(@RequestBody TaskModel taskModel) {
        return taskService.createNewTask(taskModel);
    }

    //удалить задачу
    @GetMapping(value = "/delete/{taskId}")
    public String deleteTaskFromId(@PathVariable Long taskId) {
        return taskService.deleteTaskFromId(taskId);
    }

    //получить данные задачи (просмотреть задачу) - тут нужны комментарии
    @GetMapping(value = "/get/{taskId}")
    public TaskModel getTaskFromId(@PathVariable Long taskId) {
        return taskService.getTaskFromId(taskId);
    }

    //получить список комментариев к задаче по айди задачи
    @GetMapping(value = "get/comments/{taskId}")
    public List<CommentModel> getListTaskCommentsFromId(@PathVariable Long taskId) {
        return commentService.getListTaskCommentsFromId(taskId);
    }

    //получить список задач с комментариями по:
    //автору
    //исполнителю

    //изменить название задачи
    @PostMapping(path = "/change/name", consumes = "application/json")
    public String renameTaskFromId(@RequestBody Long taskId, String name) {
        return taskService.renameTaskFromId(taskId, name);
    }


    //изменить описание задачи
    @PostMapping(path = "/change/description", consumes = "application/json")
    public String changeDescriptionTaskFromId(@RequestBody Long taskId, String description) {
        return taskService.changeDescriptionTaskFromId(taskId, description);
    }

    //изменить статус задачи
    @PostMapping(path = "/change/status", consumes = "application/json")
    public String changeStatusTaskFromId(@RequestBody Long taskId, Status status) {
        return taskService.changeStatusTaskFromId(taskId, status);
    }

    //изменить приоритет задачи
    @PostMapping(path = "/change/priority", consumes = "application/json")
    public String changePriorityTaskFromId(@RequestBody Long taskId, Priority priority) {
        return taskService.changePriorityTaskFromId(taskId, priority);
    }

    //назначить или изменить исполнителя задачи
    @PostMapping(path = "/change/assignee", consumes = "application/json")
    public String changeAssigneeTaskFromId(@RequestBody Long taskId, Long assigneeId) {
        return taskService.changeAssigneeTaskFromId(taskId, assigneeId);
    }

    //добавить комментарий к задаче
    @PostMapping(path = "/add/comment", consumes = "application/json")
    public String addCommentToTaskFromId(@RequestBody Long taskId, Long authorId, String description) {
        return commentService.addCommentToTaskFromId(taskId, authorId, description);
    }

}
