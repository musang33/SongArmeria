package com.example.songarmeria.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PostsRequestDto {
    private final String title;
    private final String content;
}
