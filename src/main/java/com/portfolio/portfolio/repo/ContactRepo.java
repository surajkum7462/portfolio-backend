// repository/ContactRepository.java
package com.portfolio.portfolio.repo;


import com.portfolio.portfolio.entity.ContactMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepo extends JpaRepository<ContactMessage, Long> {
}
