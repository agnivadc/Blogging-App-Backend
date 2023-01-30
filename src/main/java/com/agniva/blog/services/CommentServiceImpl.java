package com.agniva.blog.services;

import com.agniva.blog.entities.Comment;
import com.agniva.blog.entities.Post;
import com.agniva.blog.entities.User;
import com.agniva.blog.exceptions.ResourceNotFoundException;
import com.agniva.blog.payloads.CommentDto;
import com.agniva.blog.repositories.CommentRepo;
import com.agniva.blog.repositories.PostRepo;
import com.agniva.blog.repositories.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId, Integer userId) {

        Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));

        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "user id", userId));

        Comment comment = this.modelMapper.map(commentDto, Comment.class);

        comment.setPost(post);
        comment.setUser(user);

        Comment createdComment = this.commentRepo.save(comment);

        return this.modelMapper.map(createdComment, CommentDto.class);
    }

    @Override
    public void deleteComment(Integer commentId) {

        Comment comment = this.commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment", "comment id", commentId));

        this.commentRepo.delete(comment);

    }
}
