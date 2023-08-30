package com.example.blogproject.service;

import com.example.blogproject.dto.CommentDto;
import com.example.blogproject.entity.Article;
import com.example.blogproject.entity.Comment;
import com.example.blogproject.repository.ArticleRepository;
import com.example.blogproject.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ArticleRepository articleRepository;

    public List<CommentDto>comments(Long id){
        //댓글 목록 조회
        List<Comment> comments =commentRepository.findByArticleId(id);
        // 엔티티를 dto로 변환

        /* 기본 방식
        List<CommentDto> dtos =new ArrayList<CommentDto>();
        for(int i = 0; i<comments.size();i++){
            Comment c =comments.get(i);
            CommentDto dto = CommentDto.createCommentDto(c);
            dtos.add(dto);
        }
        //dto 반환
        return dtos;
        */

        // 스트림 방식
        return commentRepository.findByArticleId(id).stream()
                .map(comment ->CommentDto.createCommentDto(comment))
                .collect(Collectors.toList());
    }

    @Transactional
    public CommentDto create(Long id, CommentDto dto){
        // 게시글 조회 및 예외 발생
        Article article =articleRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("댓글 생성 실패 대상 게시글이 없습니다."));
        // 댓글 엔티티 생성
        Comment comment = Comment.createComment(dto,article);
        //댓글 엔티티를 DB로 저장
        Comment created=commentRepository.save(comment);
        //엔티티를 dto로 변환하여 반환
        return CommentDto.createCommentDto(created);
    }

    @Transactional
    public CommentDto update(Long id, CommentDto dto) {
        //댓글 조회 및 예외 발생
        Comment target = commentRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("대상 댓글이 없습니다."));
        //댓글 수정
        target.patch(dto);
        //DB 갱신
        Comment updated =commentRepository.save(target);
        //댓글 엔티티를 DTO로 변환 및 반환
        return CommentDto.createCommentDto(updated);
    }

    @Transactional
    public CommentDto delete(Long id){
        //댓글 조회 및 예외 발생
       Comment target =commentRepository.findById(id).orElseThrow(()->new IllegalArgumentException("댓글 삭제 실패, 대상이 없습니다."));
        //DB 삭제
        commentRepository.delete(target);
        //삭제 댓글 Dto 반환
        return CommentDto.createCommentDto(target);
    }
}
