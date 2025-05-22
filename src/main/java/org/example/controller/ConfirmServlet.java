package org.example.controller;
import org.example.dao.UserDao;
import org.example.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/confirm")
public class ConfirmServlet extends HttpServlet {

    private final UserDao userDao = new UserDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String token = req.getParameter("token");

        if (token == null || token.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing token");
            return;
        }

        try {
            User user = userDao.findByToken(token);
            if (user == null) {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Invalid token");
                return;
            }

            userDao.enable(user.getId());
            req.getRequestDispatcher("/jsp/auth/confirm.jsp").forward(req, resp);

        } catch (SQLException e) {
            throw new ServletException("Database error", e);
        }
    }
}
