package org.twspring.lab11.Repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.twspring.lab11.Model.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Integer> {
}
