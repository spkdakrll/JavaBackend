package ru.tinkoff.edu.java.scrapper.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.tinkoff.edu.java.scrapper.configuration.dto.AddLinkRequest;
import ru.tinkoff.edu.java.scrapper.configuration.dto.RemoveLinkRequest;
import ru.tinkoff.edu.java.scrapper.configuration.dto.LinkResponse;
import ru.tinkoff.edu.java.scrapper.configuration.dto.ListLinksResponse;
import ru.tinkoff.edu.java.scrapper.controllers.handler.exceptions.BadEntityException;
import ru.tinkoff.edu.java.scrapper.controllers.handler.exceptions.DuplicateUniqueFieldException;
import ru.tinkoff.edu.java.scrapper.controllers.handler.exceptions.EmptyResultException;
import ru.tinkoff.edu.java.scrapper.controllers.handler.exceptions.ForeignKeyNotExistsException;
import ru.tinkoff.edu.java.scrapper.domain.entities.LinkData;
import ru.tinkoff.edu.java.scrapper.service.LinkService;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/links")
@RequiredArgsConstructor
public class LinkController {
    private final LinkService linkService;
    @GetMapping
    public ListLinksResponse getLinks(@RequestHeader("Tg-Chat-Id") Long tgChatId) {
        Collection<LinkData> links = linkService.listAll(tgChatId);
        if (links.isEmpty()) {
            return new ListLinksResponse(null, 0);
        }
        List<LinkResponse> listLinks = links.stream().map(
                (value) -> (new LinkResponse(value.getId(), value.getLink()))
        ).collect(Collectors.toList());

        return new ListLinksResponse(listLinks, listLinks.size());
    }

    @PostMapping
    public LinkResponse postLink(
            @RequestHeader("Tg-Chat-Id") Long tgChatId,
            @RequestBody AddLinkRequest request
    ) throws DuplicateUniqueFieldException, EmptyResultException, ForeignKeyNotExistsException, BadEntityException {
//        LinkManager.add(request.url());
        LinkData linkData = linkService.add(tgChatId, request.url());
        return new LinkResponse(linkData.getId(), linkData.getLink());
    }

    @DeleteMapping
    public LinkResponse deleteLink(
            @RequestHeader("Tg-Chat-Id") Long tgChatId,
            @RequestBody RemoveLinkRequest request
    ) throws EmptyResultException {
        LinkData linkData = linkService.remove(tgChatId, request.url());
        return new LinkResponse(tgChatId, linkData.getLink());
    }
}
