package org.example.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.dao.UserDao;
import org.example.model.User;
import org.example.util.MailUtil;
import org.mindrot.jbcrypt.BCrypt;

import java.util.UUID;

public class RegistrationService {
    private static final Logger logger = LogManager.getLogger(RegistrationService.class);
    private final UserDao userDao = new UserDao();

    public void register(String email, String password) {
        String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
        String token = UUID.randomUUID().toString();
        User user = new User();
        user.setEmail(email);
        user.setPasswordHash(hashed);
        user.setEnabled(false);
        user.setConfirmationToken(token);
        try {
            userDao.save(user);
            String link = "http://localhost:8080/LabServlet_war/confirm?token=" + token;
            MailUtil.send(email, "Confirm your registration",
                    "Click <a href='" + link + "'>here</a> to confirm.");
        } catch (Exception e) {
            logger.error("Registration failed", e);
            throw new RuntimeException(e);
        }
    }

    public boolean confirm(String token) {
        try {
            User user = userDao.findByToken(token);
            if (user != null && !user.isEnabled()) {
                userDao.enable(user.getId());
                return true;
            }
        } catch (Exception e) {
            logger.error("Confirmation failed", e);
        }
        return false;
    }
}
