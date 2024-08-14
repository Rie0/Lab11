package org.twspring.lab11.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.twspring.lab11.Api.ApiException;
import org.twspring.lab11.Model.Comment;
import org.twspring.lab11.Repository.CommentRepository;
import org.twspring.lab11.Repository.PostRepository;
import org.twspring.lab11.Repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    //Basic GET
    public List<Comment> getAllComments() {
        List<Comment> comments = commentRepository.findAll();
        if (comments.isEmpty()) {
            throw new ApiException("No comments found");
        }
        return comments;
    }
    //ADDITIONAL GET
    public List<Comment> getCommentByPostId(Integer post_id) {
        List<Comment> comments = commentRepository.findCommentByPost_id(post_id);
        if (comments.isEmpty()) {
            throw new ApiException("No comments found");
        }
        return comments;
    }
    public List<Comment> getCommentByUserId(Integer user_id) {
        List<Comment> comments = commentRepository.findCommentByUser_id(user_id);
        if (comments.isEmpty()) {
            throw new ApiException("No comments found");
        }
        return comments;
    }

    //Basic POST
    public void addComment(Comment comment) {
        if (userRepository.findUserByUser_id(comment.getUser_id())==null){
            throw new ApiException("User not found");
        }
        if (postRepository.findPostByPost_Id(comment.getPost_id())==null){
            throw new ApiException("Post not found");
        }
        commentRepository.save(comment);
    }

    public void updateComment(Integer comment_id, Comment comment) {
       Comment c = commentRepository.findCommentByComment_id(comment_id);
       if (c==null){
           throw new ApiException("Comment not found");
       }
       if (commentRepository.findCommentByComment_id(comment_id).getUser_id()!=comment.getUser_id()){
           throw new ApiException("Illegal action: you cannot change the ID of the user");
       }
       if (commentRepository.findCommentByComment_id(comment_id).getPost_id()!=comment.getPost_id()){
           throw new ApiException("Illegal action: you cannot change the ID of the post");
       }
       c.setContent(comment.getContent());
       commentRepository.save(c);
    }

    public void deleteComment(Integer comment_id) {
        Comment c = commentRepository.findCommentByComment_id(comment_id);
        if (c==null){
            throw new ApiException("Comment not found");
        }
        commentRepository.delete(c);
    }
}
