package ru.tinkoff.edu.java.bot.controllers;


import org.springframework.web.bind.annotation.*;
import ru.tinkoff.edu.java.bot.configuration.dto.request.LinkUpdateRequest;

@RestController
@RequestMapping("/updates")
public class LinksUpdateController {
    @PostMapping
    public void updateLink(@RequestBody LinkUpdateRequest request) {
    }

}
