package com.agniva.blog.controllers;

import com.agniva.blog.payloads.ApiResponse;
import com.agniva.blog.payloads.CategoryDto;
import com.agniva.blog.payloads.UserDto;
import com.agniva.blog.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){

        CategoryDto createCategoryDto = this.categoryService.createCategory((categoryDto));
        return new ResponseEntity<CategoryDto>(createCategoryDto, HttpStatus.CREATED);

    }

    @PutMapping("/{catId}")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable Integer catId){

        CategoryDto updateCategoryDto = this.categoryService.updateCategory(categoryDto, catId);
        return new ResponseEntity<CategoryDto>(updateCategoryDto, HttpStatus.CREATED);

    }

    @DeleteMapping("/{catId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer catId){

        this.categoryService.deleteCategory(catId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Category deleted successfully", true), HttpStatus.OK);

    }

    @GetMapping("/{catId}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer catId){

        CategoryDto getCategoryDto = this.categoryService.getCategory(catId);
        return new ResponseEntity<CategoryDto>(getCategoryDto, HttpStatus.CREATED);

    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategories(){

        return ResponseEntity.ok(this.categoryService.getAllCategories());
    }

}
