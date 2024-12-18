package com.books.service;

import com.books.dto.CommentDTO;
import com.books.model.Comment;
import jakarta.transaction.Transactional;

import java.util.List;

public interface CommentService {
    List<Comment> getCommentsByBookId(Long bookId);
    List<Comment> addComments(List<CommentDTO> commentDTOs);
    Comment addComment(CommentDTO commentDTO);
    void deleteComment(Long id);
}