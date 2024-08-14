package org.twspring.lab11.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.twspring.lab11.Model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
}
