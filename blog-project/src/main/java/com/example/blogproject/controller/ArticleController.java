package com.example.blogproject.controller;

import com.example.blogproject.dto.ArticleForm;
import com.example.blogproject.entity.Article;
import com.example.blogproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        return "redirect:/articles/" + saved.getId();
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

    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        //데이터 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);
        //모델에 데이터 등록
        model.addAttribute("article", articleEntity);
        //뷰페이지 설정
        return "articles/edit";
    }

    @PostMapping("articles/update")
    public String update(ArticleForm form){
        log.info(form.toString());
        // 1. DTO Entity 변환
        Article articleEntity = form.toEntity();
        log.info(articleEntity.toString());
        //2. Entity DB 저장

        //2-1 DB에서 기존 데이터 가져오기
        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);

        //2-2 기존 데이터가 있다면 값을 갱신
        if(target != null){
            articleRepository.save(articleEntity);
        }

        //3.수정 결과 페이지로 리다이렉트
        return "redirect:/articles/" + target.getId();
    }

    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr){
        log.info("Delete request");
        // 삭제 대상 가져오기
        Article target = articleRepository.findById(id).orElse(null);
        log.info(target.toString());
        // 삭제 대상 삭제하기
        if(target != null){
            articleRepository.delete(target);
            rttr.addFlashAttribute("msg","Deleted Complete");
        }
        // 결과 페이지 리다이렉트
        return "redirect:/articles";
    }

}
