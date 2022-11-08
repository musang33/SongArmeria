package com.example.songarmeria.web.controller;

import com.example.songarmeria.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class IndexController {

    final PostsService postsService;

    @GetMapping("/")
    public String index(Model model) {
        final var posts = postsService.findAll();
        model.addAttribute("posts", posts);
        return "index";
    }
}
