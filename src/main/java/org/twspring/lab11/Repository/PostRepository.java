package org.twspring.lab11.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.twspring.lab11.Model.Post;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    @Query("SELECT p from Post p WHERE p.post_id=?1")
    Post findPostByPost_Id(Integer post_id);


    //ADDITIONAL ENDPOINT4
    List<Post> findPostByContentContaining(String content);
    //ADDITIONAL ENDPOINT5
    @Query("SELECT p FROM Post p ORDER BY p.publish_date DESC")
    List<Post> findPostsByPublish_dateDescOrder();
    //ADDITIONAL ENDPOINT6
    @Query("SELECT p FROM Post p WHERE p.publish_date>?1")
    List<Post> findPostByPublish_dateAfter(LocalDate publish_date);
    //ADDITIONAL ENDPOINT7
    @Query("SELECT p FROM Post p WHERE p.category_id=?1")
    List<Post> findPostByCategory_id(Integer category_id);
}
