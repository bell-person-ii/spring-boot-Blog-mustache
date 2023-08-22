package com.example.blogproject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor // 디폴트 생성자 추가
@ToString
@Getter

@Entity //DB에서 해당 객체 인식 (해당 클래스로 DB가 테이블을 생성)
public class Article {

    //기본키
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //자동 생성
    private Long id;
    @Column // DB에 컬럼 추가
    private String title;
    private String content;

    public void patch(Article article){
        if(article.title != null){
            this.title = article.title;
        }
        if(article.content != null){
            this.content = article.content;
        }
    }
}
