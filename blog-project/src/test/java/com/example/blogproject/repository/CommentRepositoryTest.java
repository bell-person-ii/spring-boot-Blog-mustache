package com.example.blogproject.repository;

import com.example.blogproject.entity.Article;
import com.example.blogproject.entity.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CommentRepositoryTest {

    @Autowired CommentRepository commentRepository;
    @Test
    @DisplayName("특정 게시글의 모든 댓글 조회")
    void findByArticleId() {
        {
            // Case 1: 4번 게시글의 모든 댓글 조회

            //입력데이터 준비
            Long articleId = 4L;
            //실제 수행
            List<Comment> comments = commentRepository.findByArticleId(articleId);

            //예상
            Article article = new Article(4L,"당신의 인생 영화는?","댓글 ㄱ");
            Comment a= new Comment(1L, article,"Park","너의 결혼식");
            Comment b= new Comment(2L, article,"Kim","스타워즈");
            Comment c= new Comment(3L, article,"Choi","세 얼간이");
            List<Comment> expected = Arrays.asList(a,b,c);
            //검증
            assertEquals(expected.toString(),comments.toString());
        }

        {
            // Case 1: 4번 게시글의 모든 댓글 조회

            //입력데이터 준비
            Long articleId = 1L;
            //실제 수행
            List<Comment> comments = commentRepository.findByArticleId(articleId);

            //예상
            Article article = new Article(4L,"가가가가","111");
            List<Comment> expected = Arrays.asList();
            //검증
            assertEquals(expected.toString(),comments.toString(),"1번 글은 댓글이 없음");
        }

    }

    @Test
    @DisplayName("특정 닉네임의 모든 댓글 조회")
    void findByNickname() {
        {
            // park의 모든 댓글

            //입력 데이터
            String nickname = "Park";
            //수행
            List<Comment> comments=commentRepository.findByNickname(nickname);
            //예상하기
            Comment a = new Comment(1L,new Article(4L,"당신의 인생 영화는?","댓글 ㄱ"),"Park","너의 결혼식");
            Comment b = new Comment(4L,new Article(5L,"당신의 소울푸드는?","댓글 ㄱ"),"Park","치킨");
            Comment c = new Comment(7L,new Article(6L,"당신의 취미는?","댓글 ㄱ"),"Park","유튜브");
            List<Comment> expected = Arrays.asList(a,b,c);
            //검증
            assertEquals(expected.toString(),comments.toString(),"Park의 모든 댓글을 출력");
        }
    }
}