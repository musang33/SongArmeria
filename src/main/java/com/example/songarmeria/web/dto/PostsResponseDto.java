package com.example.songarmeria.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class PostsResponseDto {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime lastModifiedAt;


    @Builder
    public PostsResponseDto(final Long id, final String title, final String content, final LocalDateTime lastModifiedAt)
    {
        this.id = id;
        this.title = title;
        this.content = content;
        this.lastModifiedAt = lastModifiedAt;
    }
}
