package com.agniva.blog.controllers;

import com.agniva.blog.payloads.ApiResponse;
import com.agniva.blog.payloads.CommentDto;
import com.agniva.blog.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.desktop.SystemSleepEvent;

@RestController
@RequestMapping("/api")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto,
                                                    @PathVariable Integer postId,
                                                    @RequestParam Integer userId){

        CommentDto createdComment = this.commentService.createComment(commentDto, postId, userId);

        return new ResponseEntity<>(createdComment, HttpStatus.CREATED);

    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId){

        this.commentService.deleteComment(commentId);

        return new ResponseEntity<>(new ApiResponse("Comment deleted succesfully", true), HttpStatus.OK);

    }

}
