package com.example.songarmeria.web.controller;

import com.example.songarmeria.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class PostsController {

    final PostsService postsService;

    @GetMapping("/posts/save")
    public String save() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String update(Model model, @PathVariable Long id) {
        final var post = postsService.findById(id);
        model.addAttribute("post", post);
        return "posts-update";
    }
}
