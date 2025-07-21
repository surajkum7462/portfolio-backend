// repository/BlogRepository.java
package com.portfolio.portfolio.repo;


import com.portfolio.portfolio.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepo extends JpaRepository<Blog, Long> {
}
