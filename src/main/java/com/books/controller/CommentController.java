package com.books.controller;

import com.books.dto.CommentDTO;
import com.books.model.Comment;
import com.books.service.CommentService;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Comment>> getCommentsByBookId(@PathVariable Long bookId) {
        return ResponseEntity.ok(commentService.getCommentsByBookId(bookId));
    }

    @PostMapping
    public ResponseEntity<Comment> addComment(@RequestBody CommentDTO commentDTO) {
        Comment comment = commentService.addComment(commentDTO);
        return ResponseEntity.ok(comment);
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<Comment>> addComments(@RequestBody List<CommentDTO> commentDTOs) {
        List<Comment> comments = commentService.addComments(commentDTOs);
        return ResponseEntity.ok(comments);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return ResponseEntity.noContent().build();
    }
}