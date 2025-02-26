package ru.dosalone.tms.testapp.mapper;

import ru.dosalone.tms.testapp.entity.CommentEntity;
import ru.dosalone.tms.testapp.model.CommentModel;

public class CommentJpaMapper {
    public static CommentModel toDomain(CommentEntity commentEntity) {
        return new CommentModel(commentEntity.getId(),
                commentEntity.getDescription(),
                commentEntity.getTaskEntity().getId(),
                commentEntity.getAuthor().getId());
    }
}
