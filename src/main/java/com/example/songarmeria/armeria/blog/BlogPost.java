package com.example.songarmeria.armeria.blog;

import lombok.Getter;

@Getter
public class BlogPost {
    private final int id;           // The post ID
    private final String title;     // The post title
    private final String content;   // The post content
    private final long createdAt;   // The time post is created at
    private final long modifiedAt;  // The time post is modified at

    public BlogPost(int id, String title, String content) {
        this(id, title, content, System.currentTimeMillis());
    }

    public BlogPost(int id, String title, String content, long createdAt) {
        this(id, title, content, createdAt, createdAt);
    }

    public BlogPost(int id, String title, String content, long createdAt, long modifiedAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
