package com.ryan.centralsale.util;

import com.ryan.centralsale.model.User;
import com.ryan.centralsale.model.UserProduct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class Email {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String email;

    public void sendEmail(UserProduct userProduct, double percentageDrop) {
        try {
            User user = userProduct.getUser();

            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(email);
            message.setTo(user.getEmail());
            message.setSubject("!!! PRICE DROP ALERT: " + userProduct.getProduct().getTitle() + " !!!");
            message.setText("One of your items has dropped in price by " + percentageDrop + "%!\n\n"
                    + "Product: " + userProduct.getProduct().getTitle() + "\n"
                    + "New Price: $" + userProduct.getProduct().getCurrentPrice() + "\n"
                    + "Check it out here: " + userProduct.getProduct().getProductUrl() + "\n\n"
                    + "Thank you for using CentralSale!");
            mailSender.send(message);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
    }
}
