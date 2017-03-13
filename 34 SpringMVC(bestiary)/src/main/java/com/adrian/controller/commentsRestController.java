package com.adrian.controller;

import com.adrian.domain.objects.Comment;
import com.adrian.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/comment")
public class commentsRestController {
    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Comment create(@RequestBody Comment comment){
        return commentService.create(comment);
    }

    @RequestMapping(value = "/showComment/{commentId}", method = RequestMethod.GET)
    public Comment readSingleComment(@PathVariable(value = "commentId") int commentId){
        return commentService.readSingleCommentById(commentId);
    }

    //odczytuje wszytskie komentarze dla danej bestii
    @RequestMapping(value = "/showAll/{beastId}", method = RequestMethod.GET)
    public List readById(@PathVariable(value = "beastId") int beastId){
        return commentService.readById(beastId);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.PUT)
    public Comment update(@RequestBody Comment comment){
       return commentService.update(comment);
    }

    @RequestMapping(value = "/delete/{commentId}", method = RequestMethod.DELETE)
    public void delete(@PathVariable(value = "commentId") int commentId){
        //przy usuwaniu uzytkownika dodac usuwanie tabel z k obc do niego
        commentService.delete(commentId);
    }
}
