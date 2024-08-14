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
