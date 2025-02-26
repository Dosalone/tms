package ru.dosalone.tms.server.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.dosalone.tms.testapp.service.UserService;


@RestController
@RequestMapping("/example")
@RequiredArgsConstructor
@Tag(name = "ѕримеры", description = "ѕримеры запросов с разными правами доступа")
public class ExampleController {
    private final UserService service;

    @GetMapping
    @Operation(summary = "ƒоступен только авторизованным пользовател€м")
    public String example() {
        return "Hello, world!";
    }

    @GetMapping("/admin")
    @Operation(summary = "ƒоступен только авторизованным пользовател€м с ролью ADMIN")
    @PreAuthorize("hasRole('ADMIN')")
    public String exampleAdmin() {
        return "Hello, admin!";
    }

}