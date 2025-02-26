package ru.dosalone.tms.testapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.dosalone.tms.testapp.enums.Role;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {
    private Long id;

    private String username;

    private String email;

    private Role role;

}
