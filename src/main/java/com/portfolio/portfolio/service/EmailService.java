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
            helper.setTo("kumarsuraj7462998828@gmail.com");
            helper.setSubject("📨 New Portfolio Contact from " + name);

            String content = """
            <html>
              <head>
                <style>
                  @media (max-width: 600px) {
                    .container { padding: 15px !important; }
                    .content { padding: 15px !important; }
                  }
                </style>
              </head>
              <body style="font-family: 'Segoe UI', sans-serif; background-color: #f1f5f9; margin: 0; padding: 20px;">
                <div class="container" style="max-width: 600px; margin: auto; background: #ffffff; border-radius: 10px; box-shadow: 0 4px 10px rgba(0,0,0,0.1); overflow: hidden;">
                  <div class="content" style="padding: 25px;">
                    <h2 style="color: #3b82f6;">📬 You've got a new message!</h2>
                    <p><strong>Name:</strong> %s</p>
                    <p><strong>Email:</strong> %s</p>
                    <p><strong>Message:</strong></p>
                    <div style="background-color: #f3f4f6; padding: 12px; border-radius: 5px; color: #111827;">%s</div>
                    <hr style="margin: 20px 0; border: none; border-top: 1px solid #e5e7eb;">
                    <p style="font-size: 13px; color: #6b7280;">📌 This message was sent from your portfolio contact form.</p>
                  </div>
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
              <head>
                <style>
                  @media (max-width: 600px) {
                    .container { padding: 15px !important; }
                    .content { padding: 15px !important; }
                  }
                </style>
              </head>
              <body style="font-family: 'Segoe UI', sans-serif; background-color: #ecfdf5; margin: 0; padding: 20px;">
                <div class="container" style="max-width: 600px; margin: auto; background: #ffffff; border-radius: 10px; box-shadow: 0 4px 10px rgba(0,0,0,0.1); overflow: hidden;">
                  <div class="content" style="padding: 25px;">
                    <h2 style="color: #22c55e;">👋 Hello %s,</h2>
                    <p>Thank you for getting in touch with me!</p>
                    <p>✅ I have received your message and will respond to you as soon as possible.</p>
                    <p>If it's urgent, feel free to reach out via LinkedIn or GitHub (links on my portfolio).</p>
                    <hr style="margin: 20px 0; border: none; border-top: 1px solid #d1d5db;">
                    <p style="font-size: 14px; color: #6b7280;">This is an automated confirmation email from <strong>Suraj Kumar’s Portfolio</strong>.</p>
                    <p style="font-size: 13px; color: #9ca3af;">Stay connected. 🚀</p>
                  </div>
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
