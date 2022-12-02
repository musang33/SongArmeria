package com.example.songarmeria.grpc;

import com.example.blog.grpc.BlogPostM;
import com.example.blog.grpc.BlogServiceGrpc;
import com.example.blog.grpc.CreateBlogPostRequest;
import com.example.blog.grpc.ListBlogPostsResponse;
import com.example.songarmeria.service.PostsService;
import com.example.songarmeria.web.dto.PostsRequestDto;
import com.example.songarmeria.web.dto.PostsUpdateRequestDto;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
@Slf4j
@RequiredArgsConstructor
@Service
public class GrpcBlogService extends BlogServiceGrpc.BlogServiceImplBase{

    final PostsService postsService;

    @Override
    public void createBlogPost(CreateBlogPostRequest request,
                               StreamObserver<BlogPostM> responseObserver) {
        final var id = postsService.save(new PostsRequestDto(request.getTitle(), request.getContent()));
        final var postsResponseDto = postsService.findById(id);

        log.info("createBlogPost");
        try{
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("createBlogPost end");

        responseObserver.onNext(BlogPostM.newBuilder()
                .setId(postsResponseDto.getId())
                .setTitle(postsResponseDto.getTitle())
                .setContent(postsResponseDto.getContent())
                .setModifiedAt(postsResponseDto.getLastModifiedAt().getSecond())
                .build());


        responseObserver.onCompleted();
    }

    @Override
    public void getBlogPost(com.example.blog.grpc.GetBlogPostRequest request,
                            io.grpc.stub.StreamObserver<com.example.blog.grpc.BlogPostM> responseObserver) {
        final var postsResponseDto = postsService.findById(request.getId());

        responseObserver.onNext( BlogPostM.newBuilder()
                .setId(postsResponseDto.getId())
                .setTitle(postsResponseDto.getTitle())
                .setContent(postsResponseDto.getContent())
                .setModifiedAt(postsResponseDto.getLastModifiedAt().getSecond())
                .build());
        responseObserver.onCompleted();
    }

    @Override
    public void getAllBlogPosts(com.google.protobuf.Empty request,
                                io.grpc.stub.StreamObserver<com.example.blog.grpc.BlogPostM> responseObserver) {
        final var blogPostMList = postsService.findAll().stream().map(post -> BlogPostM.newBuilder()
                .setId(post.getId())
                .setTitle(post.getTitle())
                .setContent(post.getContent())
                .setModifiedAt(post.getLastModifiedAt().getSecond())
                .build()).toList();

        blogPostMList.forEach( blogPostM -> responseObserver.onNext(blogPostM) );

        responseObserver.onCompleted();


    }

    @Override
    public void listBlogPosts(com.example.blog.grpc.ListBlogPostsRequest request,
                              io.grpc.stub.StreamObserver<com.example.blog.grpc.ListBlogPostsResponse> responseObserver) {

        final var blogPostMList = postsService.findAll().stream().map(post -> BlogPostM.newBuilder()
                .setId(post.getId())
                .setTitle(post.getTitle())
                .setContent(post.getContent())
                .setModifiedAt(post.getLastModifiedAt().getSecond())
                .build()).toList();
        responseObserver.onNext(ListBlogPostsResponse.newBuilder().addAllBlogs(blogPostMList).build());
        responseObserver.onCompleted();
    }

    @Override
    public void updateBlogPost(com.example.blog.grpc.UpdateBlogPostRequest request,
                               io.grpc.stub.StreamObserver<com.example.blog.grpc.BlogPostM> responseObserver) {
        final var postsUpdateRequestDto = new PostsUpdateRequestDto(request.getId(), request.getTitle(), request.getContent());
        postsService.update(postsUpdateRequestDto);

        final var postsResponseDto = postsService.findById(request.getId());

        responseObserver.onNext( BlogPostM.newBuilder()
                .setId(postsResponseDto.getId())
                .setTitle(postsResponseDto.getTitle())
                .setContent(postsResponseDto.getContent())
                .setModifiedAt(postsResponseDto.getLastModifiedAt().getSecond())
                .build());
        responseObserver.onCompleted();
    }

    @Override
    public void deleteBlogPost(com.example.blog.grpc.DeleteBlogPostRequest request,
                               io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
        postsService.delete(request.getId());
        responseObserver.onCompleted();
    }
}
