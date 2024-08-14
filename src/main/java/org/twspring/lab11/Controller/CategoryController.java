package org.twspring.lab11.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.twspring.lab11.Api.ApiResponse;
import org.twspring.lab11.Model.Category;
import org.twspring.lab11.Service.CategoryService;
import org.twspring.lab11.Service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/category")
public class CategoryController {
    private final CategoryService categoryService;
    private final UserService userService;

    //===========================GET===========================
    @GetMapping("/get/all")
    public ResponseEntity getAllCategories() {
        return ResponseEntity.status(200).body(categoryService.getAllCategories());
    }
    //ADDITIONAL
    @GetMapping("get/by_name/{name}")
    public ResponseEntity getCategoryByName(@PathVariable String name) {
        return ResponseEntity.status(200).body(categoryService.getCategoryByName(name));
    }
    //===========================POST===========================
    @PostMapping("/add")
    public ResponseEntity addCategory(@Valid @RequestBody Category category, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(404).body(message);
        }
        categoryService.addCategory(category);
        return ResponseEntity.status(201).body(new ApiResponse("Category created successfully"));
    }
    //===========================PUT===========================
    @PutMapping("/update/{category_id}")
    public ResponseEntity updateCategory(@PathVariable Integer category_id, @Valid @RequestBody Category category, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(404).body(message);
        }
        categoryService.updateCategory(category_id, category);
        return ResponseEntity.status(200).body(new ApiResponse("Category updated successfully"));
    }
    //===========================DELETE===========================
    @DeleteMapping("/delete/{category_id}")
    public ResponseEntity deleteCategory(@PathVariable Integer category_id) {
        categoryService.deleteCategory(category_id);
        return ResponseEntity.status(200).body(new ApiResponse("Category deleted successfully"));
    }
}
