package com.example.songarmeria.armeria.blog;

import com.example.songarmeria.service.PostsService;
import com.example.songarmeria.web.dto.PostsRequestDto;
import com.linecorp.armeria.common.HttpResponse;
import com.linecorp.armeria.common.HttpStatus;
import com.linecorp.armeria.server.annotation.Get;
import com.linecorp.armeria.server.annotation.Param;
import com.linecorp.armeria.server.annotation.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Component
public class BlogService {

    private final PostsService postsService;

    @Post("/blog")
    public HttpResponse post(final PostsRequestDto requestDto){

        postsService.save(requestDto);

        return HttpResponse.of(HttpStatus.OK);
    }

    @Get("/blog")
    public HttpResponse get(@Param Long id ){
        final var temp = postsService.findById(id);
        return HttpResponse.ofJson(temp);
    }
}
