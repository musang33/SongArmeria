package com.example.songarmeria.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PostsUpdateRequestDto {
    private Long id;
    private String title;
    private String content;
}
