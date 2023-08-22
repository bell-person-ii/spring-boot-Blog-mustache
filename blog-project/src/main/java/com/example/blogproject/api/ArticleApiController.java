package com.example.blogproject.api;

import com.example.blogproject.dto.ArticleForm;
import com.example.blogproject.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import java.util.List;
import com.example.blogproject.repository.ArticleRepository;

@RestController
public class ArticleApiController {

    @Autowired //DI
    private ArticleRepository articleRepository;
    //Get
    @GetMapping("/api/articles")
    public List<Article> index(){
        return articleRepository.findAll();
    }

    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable long id){
       return articleRepository.findById(id).orElse(null);
    }


    //Post
    @PostMapping("/api/articles")
    public Article create(@RequestBody ArticleForm dto){
        Article article = dto.toEntity();
        return articleRepository.save(article);


    }

    //Patch
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleForm dto){
        // 수정용 엔티티 생성
        Article article = dto.toEntity();
        // 대상 엔티티 가져오기
        Article target = articleRepository.findById(id).orElse(null);

        // 예외처리 (대상이 없거나 id가 없는 경우)
        if(target == null || id != article.getId()){
            // 400 응답
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        // 업데이트 및 정상처리 응답
        target.patch(article);
        Article updated = articleRepository.save(target);
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }

    //Delete
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id){
        //대상 찾기
        Article target = articleRepository.findById(id).orElse(null);
        //대상이 없는 경우
        if(target == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        // 대상삭제
        articleRepository.delete(target);
        return ResponseEntity.status(HttpStatus.OK).build(); //build랑 body(null)로 처리해도 똑같음
    }

}
