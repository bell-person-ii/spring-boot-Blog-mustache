package com.example.blogproject.dto;

import com.example.blogproject.entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;
@AllArgsConstructor //생성자
@ToString // toString

public class ArticleForm {
    private String title;
    private String content;


    public Article toEntity() {
        return  new Article(null,title,content);
    }
}
