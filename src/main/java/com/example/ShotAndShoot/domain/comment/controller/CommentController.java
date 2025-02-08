package com.example.ShotAndShoot.domain.comment.controller;

import com.example.ShotAndShoot.domain.comment.dto.CommentRequestDTO;
import com.example.ShotAndShoot.domain.comment.dto.CommentResponseDTO;
import com.example.ShotAndShoot.domain.comment.dto.UseridFromCommentIdResponseDTO;
import com.example.ShotAndShoot.domain.comment.repository.CommentRepository;
import com.example.ShotAndShoot.domain.comment.service.CommentService;
import com.example.ShotAndShoot.global.dto.ResultMessageDTO;
import com.example.ShotAndShoot.global.entity.Comment;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/api/v1/comment")
@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{questionId}")
    public ResponseEntity<ResultMessageDTO> saveComment(@PathVariable("questionId") Long questionId,
                                                        @RequestBody @Valid CommentRequestDTO commentRequestDTO) {
        String message = commentService.saveComment(questionId, commentRequestDTO);
        return new ResponseEntity<>(new ResultMessageDTO(message), HttpStatus.OK);
    }

    /**
     * question에 해당하는 댓글 조회
     */
    @GetMapping("/{questionId}")
    public ResponseEntity<List<CommentResponseDTO>> getComments(@PathVariable Long questionId) {
        List<CommentResponseDTO> comments = commentService.getComments(questionId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<ResultMessageDTO> deleteComment(@PathVariable("commentId") Long commentId) {

        String message = commentService.deleteComment(commentId);
        return new ResponseEntity<>(new ResultMessageDTO(message), HttpStatus.OK);
    }

    /**
     * commentId로 userId 조회
     *
     * @param commentId
     * @return
     */
    @GetMapping("/user/{commentId}")
    public ResponseEntity<UseridFromCommentIdResponseDTO> getUserIdFromCommentId(
            @PathVariable("commentId") Long commentId) {
        String userId = commentService.getUserIdFromCommentId(commentId);
        return new ResponseEntity<>(new UseridFromCommentIdResponseDTO(userId), HttpStatus.OK);
    }

    /**
     * 마이페이지 사용자의 모든 댓글 조회
     *
     * @return
     */
    @GetMapping("/my")
    public ResponseEntity<List<CommentResponseDTO>> getMyAllComments() {
        List<CommentResponseDTO> myComments = commentService.getMyAllComments();
        return new ResponseEntity<>(myComments, HttpStatus.OK);
    }
}
