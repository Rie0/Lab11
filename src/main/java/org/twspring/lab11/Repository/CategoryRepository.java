package org.twspring.lab11.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.twspring.lab11.Model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    @Query("SELECT c from Category c where c.category_id=?1")
    Category findByCategory_id(Integer Category_id);
}
