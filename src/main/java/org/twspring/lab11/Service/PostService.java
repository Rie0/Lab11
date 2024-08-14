package org.twspring.lab11.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.twspring.lab11.Api.ApiException;
import org.twspring.lab11.Model.Category;
import org.twspring.lab11.Model.Post;
import org.twspring.lab11.Repository.CategoryRepository;
import org.twspring.lab11.Repository.PostRepository;
import org.twspring.lab11.Repository.UserRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    //Basic GET
    public List<Post> getAllPosts   () {
        List<Post> posts = postRepository.findAll();
        if (posts.isEmpty()) {
            throw new ApiException("No posts found");
        }
        return posts;
    }
    //ADDITIONAL
    public List<Post> getPostsByContentContaining(String content) {
        List<Post> posts = postRepository.findPostByContentContaining(content);
        if (posts.isEmpty()) {
            throw new ApiException("No posts found");
        }
        return posts;
    }
    public List<Post> getPostsByPublish_dateDescOrder() {
        List<Post> posts=postRepository.findPostsByPublish_dateDescOrder();
        if (posts.isEmpty()) {
            throw new ApiException("No posts found");
        }
        return posts;
    }
    public List<Post> getPostsByPublish_dateAfter(LocalDate publish_date) {
        List<Post> posts=postRepository.findPostByPublish_dateAfter(publish_date);
        if (posts.isEmpty()) {
            throw new ApiException("No posts found");
        }
        return posts;
    }
    public List<Post> getPostsByCategory_id(Integer category_id) {
        List<Post> posts = postRepository.findPostByCategory_id(category_id);
        if (posts.isEmpty()) {
            throw new ApiException("No posts found");
        }
        return posts;
    }

    //Basic POST
    public void addPost(Post post) {
        if (userRepository.findUserByUser_id(post.getUser_id())==null){
            throw new ApiException("User not found");
        }
        if (categoryRepository.findByCategory_id(post.getCategory_id()) == null) {
            throw new ApiException("Category not found");
        }
        postRepository.save(post);
    }

    //Basic PUT
    public void updatePost(Integer post_id, Post post) {
        Post p = postRepository.findPostByPost_Id(post_id);
        if (p == null) {
            throw new ApiException("Post not found");
        }
        if (p.getUser_id()!=post.getUser_id()) {
            throw new ApiException("Illegal action: you cannot change the ID of the user");
        }
        if (categoryRepository.findByCategory_id(post.getCategory_id()) == null) {
            throw new ApiException("Category not found");
        }
        //can change category but not user
        p.setTitle(post.getTitle());
        p.setContent(post.getContent());
        p.setCategory_id(post.getCategory_id());
        postRepository.save(p);
    }

    //Basic Delete
    public void deletePost(Integer post_id) {
        Post p = postRepository.findPostByPost_Id(post_id);
        if (p == null) {
            throw new ApiException("Post not found");
        }
       postRepository.delete(p);
    }
}
