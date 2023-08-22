package com.example.blogproject.dto;

import com.example.blogproject.entity.Article;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED) // @RequestBody 사용시 기본 생성자 핈수
@AllArgsConstructor //생성자
@Getter // @RequestBody에서 json key 값 생성시 getter를 기준으로 생성하기 때문에 getter가 필수
@ToString // toString

public class ArticleForm {
    private Long id;
    private String title;
    private String content;


    public Article toEntity() {
        return  new Article(id,title,content);
    }
}
