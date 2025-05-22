package org.example.controller;

import org.example.model.User;
import org.example.service.AuthService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class AuthServlet extends HttpServlet {
    private final AuthService authService = new AuthService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/auth/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        User user = authService.authenticate(email, password);
        if (user != null) {
            req.getSession().setAttribute("user", user);
            resp.sendRedirect(req.getContextPath() + "/contacts");
        } else {
            req.setAttribute("error", "Invalid credentials or not confirmed.");
            req.getRequestDispatcher("/jsp/auth/login.jsp").forward(req, resp);
        }
    }
}