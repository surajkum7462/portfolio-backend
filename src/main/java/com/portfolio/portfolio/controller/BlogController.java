// controller/BlogController.java
package com.portfolio.portfolio.controller;


import com.portfolio.portfolio.entity.Blog;
import com.portfolio.portfolio.repo.BlogRepo;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/blogs")
@CrossOrigin(origins = "http://localhost:3000")
public class BlogController {

    private final BlogRepo blogRepo;

    public BlogController(BlogRepo blogRepo) {
        this.blogRepo = blogRepo;
    }

    @GetMapping
    public List<Blog> getAllBlogs() {
        return blogRepo.findAll();
    }

    @PostMapping
    public Blog createBlog(@RequestBody Blog blog) {
        if (blog.getCreatedAt() == null) {
            blog.setCreatedAt(LocalDate.now());
        }
        return blogRepo.save(blog);
    }
}
