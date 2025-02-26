package ru.dosalone.tms.testapp.mapper;

import ru.dosalone.tms.testapp.entity.UserEntity;
import ru.dosalone.tms.testapp.model.UserModel;

public class UserJpaMapper {
    public static UserModel toDomain(UserEntity userEntity) {
        return new UserModel(userEntity.getId(),
                userEntity.getUsername(),
                userEntity.getEmail(),
                userEntity.getRole());
    }

    public static UserEntity toJpa(UserModel userModel) {
        return new UserEntity(null,
                userModel.getUsername(),
                userModel.getEmail(),
                userModel.getRole());
    }

}
