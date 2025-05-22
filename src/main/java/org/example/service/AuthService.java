package org.example.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.dao.UserDao;
import org.example.model.User;
import org.mindrot.jbcrypt.BCrypt;

public class AuthService {
    private static final Logger logger = LogManager.getLogger(AuthService.class);
    private final UserDao userDao = new UserDao();

    public User authenticate(String email, String password) {
        try {
            User user = userDao.findByEmail(email);
            if (user != null && user.isEnabled() && BCrypt.checkpw(password, user.getPasswordHash())) {
                return user;
            }
        } catch (Exception e) {
            logger.error("Authentication failed", e);
        }
        return null;
    }
}
