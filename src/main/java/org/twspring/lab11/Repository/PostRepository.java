package org.twspring.lab11.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.twspring.lab11.Model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    @Query("SELECT p from Post p WHERE p.post_id=?1")
    Post findPostByPost_Id(Integer post_id);
}
