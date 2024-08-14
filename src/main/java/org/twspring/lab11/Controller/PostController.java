package org.twspring.lab11.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.twspring.lab11.Api.ApiResponse;
import org.twspring.lab11.Model.Post;
import org.twspring.lab11.Service.PostService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/post")
public class PostController {
    private final PostService postService;
    //===========================GET===========================
    @GetMapping("/get/all")
    public ResponseEntity getAllPosts() {
        return ResponseEntity.status(200).body(postService.getAllPosts());
    }
    //===========================POST===========================
    @PostMapping("/add")
    public ResponseEntity addPost(@Valid @RequestBody Post post, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(404).body(message);
        }
        postService.addPost(post);
        return ResponseEntity.status(201).body(new ApiResponse("Post added successfully"));
    }
    //===========================PUT===========================
    @PutMapping("/update/{post_id}")
    public ResponseEntity updatePost(@PathVariable Integer post_id, @Valid @RequestBody Post post, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(404).body(message);
        }
        postService.updatePost(post_id, post);
        return ResponseEntity.status(200).body(new ApiResponse("Post updated successfully"));
    }
    //===========================DELETE===========================
    @DeleteMapping("/delete/{post_id}")
    public ResponseEntity deletePost(@PathVariable Integer post_id) {
        postService.deletePost(post_id);
        return ResponseEntity.status(200).body(new ApiResponse("Post deleted successfully"));
    }
}
