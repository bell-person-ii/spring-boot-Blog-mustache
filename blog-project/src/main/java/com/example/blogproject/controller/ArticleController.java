package com.example.blogproject.controller;

import com.example.blogproject.dto.ArticleForm;
import com.example.blogproject.entity.Article;
import com.example.blogproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@Slf4j // 로깅 어노테이션
public class ArticleController {

    @Autowired // DI, 스프링 부트가 만들어둔 객체를 가져와 연결
    private ArticleRepository articleRepository;

    @GetMapping("articles/new")
    public String newArticleForm(){

        return "articles/new";
    }

    @PostMapping("article/create")
    public String createArticle(ArticleForm form){
        /*
        System.out.println(form.toString()); -> 로깅 대체
        */
        log.info(form.toString());
        //1. DTO -> Entity 변환
        Article article = form.toEntity();
        log.info(article.toString());
        //2. Repository를 통해 Entity를 DB안에 저장
        Article saved = articleRepository.save(article);
        log.info(saved.toString());
        return "";
    }

    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model){
        log.info("id : " + id);
        //1. id로 데이터 가져오기
        Article articleEntity =articleRepository.findById(id).orElse(null);
        //2. 가져온 데이터 모델에 등록
        model.addAttribute("article", articleEntity);
        //3. 보여줄 페이지 설정
        return "articles/show";
    }

    @GetMapping("/articles")
    public String index(Model model){

        //1. 모든 article 가져오기
        List<Article> articleEntityList=articleRepository.findAll();
        //2. 가져온 article 묶음 뷰로 전달
        model.addAttribute("articleList",articleEntityList);
        //3. 뷰 페이지 설정

        return "articles/index";
    }

}
