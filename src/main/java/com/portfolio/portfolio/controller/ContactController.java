// controller/ContactController.java
package com.portfolio.portfolio.controller;


import com.portfolio.portfolio.entity.ContactMessage;
import com.portfolio.portfolio.repo.ContactRepo;
import com.portfolio.portfolio.service.EmailService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contact")
@CrossOrigin(origins = "http://localhost:3000")
public class ContactController {

    private final ContactRepo contactRepo;
    private final EmailService emailService;

    public ContactController(ContactRepo contactRepo, EmailService emailService) {
        this.contactRepo = contactRepo;
        this.emailService = emailService;
    }

    @PostMapping
    public ContactMessage submitContact(@RequestBody ContactMessage msg) {
        emailService.sendContactMail(msg.getName(), msg.getEmail(), msg.getMessage());
        return contactRepo.save(msg);
    }
}
