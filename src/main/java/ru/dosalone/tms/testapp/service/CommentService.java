package ru.dosalone.tms.testapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dosalone.tms.testapp.entity.CommentEntity;
import ru.dosalone.tms.testapp.entity.TaskEntity;
import ru.dosalone.tms.testapp.entity.UserEntity;
import ru.dosalone.tms.testapp.mapper.CommentJpaMapper;
import ru.dosalone.tms.testapp.model.CommentModel;
import ru.dosalone.tms.testapp.repository.CommentRepository;
import ru.dosalone.tms.testapp.repository.TaskRepository;
import ru.dosalone.tms.testapp.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    /** Добавляет комментарий к задаче
     *
     * @param taskId
     * @param authorId
     * @param description
     * @return String с результатом выполнения
     */
    public String addCommentToTaskFromId(Long taskId, Long authorId, String description) {
        if(taskRepository.findById(taskId).isPresent()) {
            TaskEntity taskEntity = taskRepository.findById(taskId).get();
            if(userRepository.existsById(authorId)) {
                UserEntity userEntity = userRepository.findById(authorId).get();
                CommentEntity commentEntity = new CommentEntity(null,
                        description,
                        taskEntity,
                        userEntity);
                return "Комментарий добавлен";
            } else return "Автор не найден";
        } else return "Задача не найдена";
    }

    /** Возвращает список комментариев к задаче по id задачи
     *
     * @param taskId
     * @return List комментариев или исключение
     */
    public List<CommentModel> getListTaskCommentsFromId(Long taskId) {
        if(taskRepository.findById(taskId).isPresent()) {
            TaskEntity taskEntity = taskRepository.findById(taskId).get();
            List<Optional<CommentEntity>> commentEntityList = commentRepository.findByTaskEntity(taskEntity);
            List<CommentModel> result = new ArrayList<>();
            for(Optional<CommentEntity> commentEntity : commentEntityList) {
                assert commentEntity.orElse(null) != null;
                result.add(CommentJpaMapper.toDomain(commentEntity.orElse(null)));
            }
            return result;
        } else throw new RuntimeException("404: Task not found");
    }

}
