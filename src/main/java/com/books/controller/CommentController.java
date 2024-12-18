package com.books.controller;

import com.books.dto.CommentDTO;
import com.books.model.Comment;
import com.books.service.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/book/{bookId}")
    public List<Comment> getCommentsByBookId(@PathVariable Long bookId) {
        return commentService.getCommentsByBookId(bookId);
    }

    @PostMapping
    public Comment addComment(@RequestBody CommentDTO commentDTO) {
        return commentService.addComment(commentDTO);
    }

    @PostMapping("/bulk")
    public List<Comment> addComments(@RequestBody List<CommentDTO> commentDTOs) {
        return commentService.addComments(commentDTOs);
    }

    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
    }
}