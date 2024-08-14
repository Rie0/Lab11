package org.twspring.lab11.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.twspring.lab11.Model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT u from User u where u.user_id=?1")
    User findUserByUser_id(Integer id);
}
