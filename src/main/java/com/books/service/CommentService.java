package com.books.service;

import com.books.dto.CommentDTO;
import com.books.model.Comment;
import jakarta.transaction.Transactional;

import java.util.List;

public interface CommentService {
    List<Comment> getCommentsByBookId(Long bookId);

    Comment addComment(CommentDTO commentDTO);

    List<Comment> addComments(List<CommentDTO> commentDTOs);
    void deleteComment(Long id);
}