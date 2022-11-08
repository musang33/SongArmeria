package com.example.songarmeria.grpc;

import com.example.songarmeria.blog.grpc.BlogPostM;
import com.example.songarmeria.blog.grpc.BlogServiceGrpc;
import com.example.songarmeria.blog.grpc.CreateBlogPostRequest;
import com.example.songarmeria.service.PostsService;
import com.example.songarmeria.web.dto.PostsRequestDto;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GrpcBlogService extends BlogServiceGrpc.BlogServiceImplBase{

    final PostsService postsService;

    @Override
    public void createBlogPost(CreateBlogPostRequest request,
                               StreamObserver<BlogPostM> responseObserver) {
        postsService.save(new PostsRequestDto(request.getTitle(), request.getContent()));
    }

}
