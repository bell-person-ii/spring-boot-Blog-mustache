package com.example.blogproject.service;

import com.example.blogproject.dto.ArticleForm;
import com.example.blogproject.entity.Article;
import com.example.blogproject.repository.ArticleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest// 해당 클래스는 스프링부트와 연동되어 테스팅 된다
class ArticleServiceTest {
    @Autowired ArticleService articleService;
    @Autowired ArticleRepository articleRepository;
    @Test
    void index() {
        //예상
        Article a =new Article(1L,"가가가가","111");
        Article b =new Article(2L,"가가가가","222");
        Article c =new Article(3L,"가가가가","333");
        List<Article> expected =new ArrayList<Article>(Arrays.asList(a,b,c));
        //실제
        List<Article> articles =articleService.index();
        //비교
        assertEquals(expected.toString(),articles.toString());
    }

    @Test
    void show_success() {
        // 예상
        Long id = 1L;
        Article expected =new Article(id,"가가가가","111");
        //실제
        Article article=articleService.show(id);
        //비교
        assertEquals(expected.toString(),article.toString());
    }

    @Test
    void show_fail_id() {
        Long id = -1L;
        Article expected =null;
        //실제
        Article article=articleService.show(id);
        //비교
        assertEquals(expected,article);
    }

    @Test
    @Transactional
    void create_success_title_content_exist() {
        //예상
        String title= "title";
        String content = "content";
        ArticleForm dto = new ArticleForm(null,title,content);
        Article expected = new Article(4L,"title","content");
        //실제
        Article article = articleService.create(dto);
        // 비교
        assertEquals(expected.toString(),article.toString());
    }

    @Test
    @Transactional
    void create_fail1_id_exist() {
        Long id = 4L;
        String title= "title";
        String content = "content";
        ArticleForm dto = new ArticleForm(id,title,content);
        Article expected = null;
        //실제
        Article article = articleService.create(dto);
        // 비교
        assertEquals(expected,article);
    }

    //update 성공 (존재하는 id title content)가 있는 경우
    @Test
    @Transactional
    void update_success_id_title_content_exist() {
        Long id = 1L;
        String title = "edited title";
        String content = "edited content";
        ArticleForm dto = new ArticleForm(id,title,content);
        Article article = dto.toEntity();
        Article res = articleService.update(id,dto);
        assertEquals(article.toString(),res.toString());
    }


    //update 성공 (존재하는 id title ) 만 있는 경우
    @Test
    @Transactional
    void update_success_id_title_exist(){
        Long id = 1L;
        String title = "edited title";
        ArticleForm dto = new ArticleForm(id,title,null);
        Article article = new Article(id,title,"111");
        Article res = articleService.update(id,dto);
        assertEquals(article.toString(),res.toString());

    }

    //update 실패 (존재하지 않는 id의 dto 입력 )
    @Test
    @Transactional
    void update_fail_non_exist_id(){
        Long id = 4L;
        String title = "edited title";
        String content = "edited content";
        ArticleForm dto = new ArticleForm(id,title,content);
        Article article = null;
        Article res = articleService.update(id,dto);
        assertEquals(article,res);

    }

    //update 실패 (id만 있는 dto 입력 )
    @Test
    @Transactional
    void update_fail_id_exist(){
        Long id = 4L;
        String title = null;
        String content = null;
        ArticleForm dto = new ArticleForm(id,title,content);
        Article article = null;
        Article res = articleService.update(id,dto);
        assertEquals(article,res);

    }

    //delete 성공 (존재하는 id 입력)

    @Test
    @Transactional
    void delete_success_id_exist(){
        Long id = 1L;
        Article article = articleRepository.findById(id).orElse(null);
        Article res = articleService.delete(id);

        assertEquals(article.toString(),res.toString());

    }

    //delete 실패 (존재하지 않는 id 입력)
    @Test
    @Transactional
    void delete_success_id_non_exist(){
        Long id = 4L;
        Article article = articleRepository.findById(id).orElse(null);
        Article res = articleService.delete(id);

        assertEquals(article,res);

    }
}