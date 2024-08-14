package org.twspring.lab11.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.twspring.lab11.Api.ApiResponse;
import org.twspring.lab11.Model.Comment;
import org.twspring.lab11.Service.CommentService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/comment")
public class CommentController {
    private final CommentService commentService;
    //===========================GET===========================
    @GetMapping("/get/all")
    public ResponseEntity getAllComments() {
        return ResponseEntity.status(200).body(commentService.getAllComments());
    }
    //ADDITIONAL GET
    @GetMapping("/get/by_post_id/{post_id}")
    public ResponseEntity getCommentsByPostId(@PathVariable Integer post_id) {
            return ResponseEntity.status(200).body(commentService.getCommentByPostId(post_id));
    }
    @GetMapping("/get/by_user_id/{user_id}")
    public ResponseEntity getCommentsByUserId(@PathVariable Integer user_id) {
        return ResponseEntity.status(200).body(commentService.getCommentByUserId(user_id));
    }
    //===========================POST===========================
    @PostMapping("/add")
    public ResponseEntity addComment(@Valid @RequestBody Comment comment, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(404).body(message);
        }
        commentService.addComment(comment);
        return ResponseEntity.status(201).body(new ApiResponse("Comment added successfully"));
    }
    //===========================PUT===========================
    @PutMapping("/update/{comment_id}")
    public ResponseEntity updateComment(@PathVariable Integer comment_id, @Valid @RequestBody Comment comment, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(404).body(message);
        }
        commentService.updateComment(comment_id, comment);
        return ResponseEntity.status(200).body(new ApiResponse("Comment updated successfully"));
    }
    //===========================DELETE===========================

    @DeleteMapping("/delete/{comment_id}")
    public ResponseEntity deleteComment(@PathVariable Integer comment_id) {
        commentService.deleteComment(comment_id);
        return ResponseEntity.status(200).body(new ApiResponse("Comment deleted successfully"));
    }
}
