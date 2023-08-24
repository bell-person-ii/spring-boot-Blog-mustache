package com.example.blogproject.api;

import com.example.blogproject.dto.ArticleForm;
import com.example.blogproject.entity.Article;
import com.example.blogproject.service.ArticleService;
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
    private ArticleService articleService;

    //Get
    @GetMapping("/api/articles")
    public List<Article> index(){
        return articleService.index();
    }

    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable long id){
       return articleService.show(id);
    }

    //Post
    @PostMapping("/api/articles")
    public ResponseEntity<Article> create(@RequestBody ArticleForm dto){
        Article created = articleService.create(dto);

        if(created != null){
            return ResponseEntity.status(HttpStatus.OK).body(created);
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    //Patch
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleForm dto){

        Article updated = articleService.update(id,dto);

        if(updated != null){
            return ResponseEntity.status(HttpStatus.OK).body(updated);
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    //Delete
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){

        Article deleted = articleService.delete(id);

        if(deleted != null){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/api/transaction-test")
    public ResponseEntity<List<Article>>transactionTest(@RequestBody List<ArticleForm> dtos){
        List<Article> createdList = articleService.createArticles(dtos);

        if(createdList != null){
            return ResponseEntity.status(HttpStatus.OK).body(createdList);
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
