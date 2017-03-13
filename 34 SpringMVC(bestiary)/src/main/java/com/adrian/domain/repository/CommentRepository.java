package com.adrian.domain.repository;

import com.adrian.domain.objects.Comment;

import java.util.List;

public interface CommentRepository {
    Comment create(Comment comment);
    List readById(int beastId);
    Comment readSingleCommentById(int commentId);
    Comment update(Comment comment);
    void delete(int commentId);
}
