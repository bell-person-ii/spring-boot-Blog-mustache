package com.example.blogproject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor // 디폴트 생성자 추가
@ToString
@Getter

@Entity //DB에서 해당 객체 인식
public class Article {

    //기본키
    @Id
    @GeneratedValue //자동 생성
    private Long id;
    @Column // DB에 컬럼 추가
    private String title;
    private String content;

}
