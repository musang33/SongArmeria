package com.example.songarmeria.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PostsRequestDto {
    private String title;
    private String content;
}
