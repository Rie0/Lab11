package org.twspring.lab11.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.twspring.lab11.Model.Comment;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    @Query("SELECT c FROM Comment c WHERE c.comment_id=?1")
    Comment findCommentByComment_id(Integer id);

    //ADDITIONAL ENDPOINT2
    @Query("SELECT c FROM Comment c WHERE c.post_id=?1")
    List<Comment> findCommentByPost_id(Integer post_id);

    //ADDITIONAL ENDPOINT3
    @Query("SELECT c FROM Comment c WHERE c.user_id=?1")
    List<Comment> findCommentByUser_id(Integer user_id);
}
