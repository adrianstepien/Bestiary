package com.adrian.service;

import com.adrian.domain.objects.Comment;
import org.omg.CORBA.COMM_FAILURE;

import java.util.List;

public interface CommentService {
    Comment create(Comment comment);
    List readById(int beastId);
    Comment readSingleCommentById(int commentId);
    Comment update(Comment comment);
    void delete(int commentId);
}
