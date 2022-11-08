package com.example.songarmeria.web.controller;

import com.example.songarmeria.service.PostsService;
import com.example.songarmeria.web.dto.PostsRequestDto;
import com.example.songarmeria.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    final PostsService service;

    @PostMapping("/api/posts/v1")
    public void save(@RequestBody PostsRequestDto requestDto){
        service.save(requestDto);
    }

    @PutMapping("/api/posts/v1")
    public void update(@RequestBody PostsUpdateRequestDto requestDto) {
        service.update(requestDto);
    }

    @DeleteMapping("/api/posts/v1/{id}")
    public void update(@PathVariable Long id) {
        service.delete(id);
    }
}
