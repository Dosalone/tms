package ru.dosalone.tms.testapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dosalone.tms.testapp.entity.CommentEntity;
import ru.dosalone.tms.testapp.entity.TaskEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    List<Optional<CommentEntity>> findByTaskEntity(TaskEntity taskEntity);
}
