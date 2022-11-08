package com.example.songarmeria.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class PostsEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    @Builder
    public PostsEntity(final String title, final String content)
    {
        this.title = title;
        this.content = content;
    }

    public void update(final String title, final String content)
    {
        this.title = title;
        this.content = content;
    }
}
