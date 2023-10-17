package com.evmoon.blog.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Article {

    private int id;
    private Users author;
    private LocalDateTime createdAt;
    private String title;
    private String content;
    private List<Reply> replies;

}
