package com.portfolio.portfolio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.MimeMessageHelper;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendContactMail(String name, String email, String message) {
        sendMailToOwner(name, email, message);     // Send to you
        sendAcknowledgmentToUser(name, email);     // Send to user
    }
    private void sendMailToOwner(String name, String email, String message) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setTo("your_email@gmail.com");
            helper.setSubject("📬 New Contact Message from Portfolio");

            String content = """
            <html>
              <body style="font-family: Arial, sans-serif; background-color: #f8f8f8; padding: 20px;">
                <div style="max-width: 600px; margin: auto; background-color: white; padding: 20px; border-radius: 8px;">
                  <h2 style="color: #4f46e5;">New Contact Message</h2>
                  <p><strong>Name:</strong> %s</p>
                  <p><strong>Email:</strong> %s</p>
                  <p><strong>Message:</strong></p>
                  <p style="background-color: #f0f0f0; padding: 10px; border-radius: 4px;">%s</p>
                </div>
              </body>
            </html>
        """.formatted(name, email, message.replace("\n", "<br/>"));

            helper.setText(content, true);
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send contact email", e);
        }
    }

    private void sendAcknowledgmentToUser(String name, String email) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setTo(email);
            helper.setSubject("✅ Thanks for contacting Suraj");

            String content = """
            <html>
              <body style="font-family: Arial, sans-serif; background-color: #f3f4f6; padding: 20px;">
                <div style="max-width: 600px; margin: auto; background-color: white; padding: 20px; border-radius: 8px;">
                  <h2 style="color: #22c55e;">Hi %s,</h2>
                  <p>Thank you for reaching out! I’ve received your message and will get back to you as soon as possible.</p>
                  <p>Regards,<br><strong>Suraj Kumar</strong></p>
                  <hr/>
                  <p style="font-size: 12px; color: #999;">This is an automated message from your portfolio site.</p>
                </div>
              </body>
            </html>
        """.formatted(name);

            helper.setText(content, true);
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send acknowledgment email", e);
        }
    }


}
