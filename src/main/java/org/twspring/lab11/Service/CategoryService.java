package org.twspring.lab11.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.twspring.lab11.Api.ApiException;
import org.twspring.lab11.Model.Category;
import org.twspring.lab11.Repository.CategoryRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    //Basic GET
    public List<Category> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        if (categories.isEmpty()) {
            throw new ApiException("No categories found");
        }
        return categories;
    }

    //Basic POST
    public void addCategory(Category category) {
         categoryRepository.save(category);
    }

    //Basic PUT
    public void updateCategory(Integer category_id, Category category) {
        Category c = categoryRepository.findByCategory_id(category_id);
        if (c == null) {
            throw new ApiException("Category not found");
        }
        c.setName(category.getName());
        categoryRepository.save(c);
    }
    //Basic DELETE
    public void deleteCategory(Integer category_id) {
        Category c = categoryRepository.findByCategory_id(category_id);
        if (c == null) {
            throw new ApiException("Category not found");
        }
        categoryRepository.delete(c);
    }

}
