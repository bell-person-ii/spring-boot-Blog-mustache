package com.example.blogproject.service;

import com.example.blogproject.dto.ArticleForm;
import com.example.blogproject.entity.Article;
import com.example.blogproject.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service //서비스 선언 (서비스 객체 스프링 부트에 생성)
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    public List<Article> index(){
        return articleRepository.findAll();
    }

    public Article show(Long id){
        return articleRepository.findById(id).orElse(null);
    }

    public Article create(ArticleForm dto){
        Article article = dto.toEntity();
        if (article.getId() != null){
            return null;
        }
        return articleRepository.save(article);
    }

    public Article update(Long id, ArticleForm dto){
        Article article = dto.toEntity();
        Article target = articleRepository.findById(id).orElse(null);

        if(target == null || id != article.getId()){
            return null;
        }

        target.patch(article);
        Article updated = articleRepository.save(target);
        return updated;

    }

    public Article delete(Long id){

        Article target = articleRepository.findById(id).orElse(null);

        if(target == null){
            return null;
        }
        else{
            articleRepository.delete(target);
            return target;

        }
    }
    @Transactional
    public List<Article> createArticles(List<ArticleForm> dtos){
        //dto 묶음 -> 엔티티 묶음 전환
        List<Article> articleList=dtos.stream().map(dto->dto.toEntity()).collect(Collectors.toList());
        // 엔티티 묶음 DB 저장
        articleList.stream().forEach(article ->articleRepository.save(article));
        //강제 예외방생
        articleRepository.findById(-1L).orElseThrow(
                ()-> new IllegalArgumentException("결재 실패")
        );
        return articleList;
    }
}
