package com.books.service.impl;

import com.books.dto.CommentDTO;
import com.books.model.Book;
import com.books.model.Comment;
import com.books.model.Users;
import com.books.repository.BookRepository;
import com.books.repository.CommentRepository;
import com.books.repository.UserRepository;
import com.books.service.CommentService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, BookRepository bookRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public List<Comment> getCommentsByBookId(Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        return commentRepository.findByBook(book);
    }

    @Override
    @Transactional
    public Comment addComment(CommentDTO commentDTO) {
        Long bookId = commentDTO.getBookId();
        Long userId = commentDTO.getUserId();

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Comment comment = Comment.builder()
                .content(commentDTO.getContent())
                .book(book)
                .user(user)
                .build();

        return commentRepository.save(comment);
    }


    @Override
    @Transactional
    public List<Comment> addComments(List<CommentDTO> commentDTOs) {
        Long bookId = commentDTOs.get(0).getBookId();
        Long userId = commentDTOs.get(0).getUserId();

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Comment> comments = commentDTOs.stream()
                .map(dto -> Comment.builder()
                        .content(dto.getContent())
                        .book(book)
                        .user(user)
                        .build())
                .collect(Collectors.toList());

        return commentRepository.saveAll(comments);
    }


    @Override
    @Transactional
    public void deleteComment(Long id) {
        if (!commentRepository.existsById(id)) {
            throw new RuntimeException("Comment not found");
        }

        commentRepository.deleteById(id);
    }
}

