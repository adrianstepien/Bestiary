package com.adrian.service.impl;

import com.adrian.domain.objects.Comment;
import com.adrian.domain.repository.CommentRepository;
import com.adrian.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{
    @Autowired
    private CommentRepository commentRepository;

    @Override
    public Comment create(Comment comment){
        return commentRepository.create(comment);
    }

    @Override
    public List readById(int beastId){
        return commentRepository.readById(beastId);
    }

    @Override
    public Comment readSingleCommentById(int commentId){
        return commentRepository.readSingleCommentById(commentId);
    }

    @Override
    public Comment update(Comment comment){
        return commentRepository.update(comment);
    }

    @Override
    public void delete(int commentId){
        commentRepository.delete(commentId);
    }
}
