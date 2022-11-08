package com.example.songarmeria.service;

import com.example.songarmeria.domain.PostsEntity;
import com.example.songarmeria.domain.PostsRepository;
import com.example.songarmeria.web.dto.PostsRequestDto;
import com.example.songarmeria.web.dto.PostsResponseDto;
import com.example.songarmeria.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class PostsService {

    final PostsRepository postsRepository;

    public List<PostsResponseDto> findAll()
    {
        return postsRepository.findAll().stream()
                .map(it-> PostsResponseDto.builder()
                        .id(it.getId())
                        .title(it.getTitle())
                        .content(it.getContent())
                        .lastModifiedAt(it.getLastModifiedAt())
                        .build())
                .collect(Collectors.toList());
    }

    public PostsResponseDto findById(final Long id)
    {
        final var post = postsRepository.findById(id).orElseThrow( () -> new IllegalArgumentException("cannot find post by id : " + id ));
        return PostsResponseDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .lastModifiedAt(post.getLastModifiedAt())
                .build();
    }

    public Long save(final PostsRequestDto requestDto)
    {
        PostsEntity entity = PostsEntity.builder().title(requestDto.getTitle()).content(requestDto.getContent()).build();
        return postsRepository.save(entity).getId();
    }

    @Transactional
    public void update(final PostsUpdateRequestDto requestDto) {
        final var post = postsRepository.findById(requestDto.getId()).orElseThrow( () -> new IllegalArgumentException("cannot find post by id : " + requestDto.getId() ));
        post.update(requestDto.getTitle(), requestDto.getContent());
    }

    @Transactional
    public void delete(final Long id) {
        final var post = postsRepository.findById(id).orElseThrow( () -> new IllegalArgumentException("cannot find post by id : " + id ));
        postsRepository.delete (post);
    }
}
