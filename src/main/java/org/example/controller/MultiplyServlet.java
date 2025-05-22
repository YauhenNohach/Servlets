package org.example.controller;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "MultiplyServlet", urlPatterns = "/multiply")
public class MultiplyServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(MultiplyServlet.class);
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sNumber = req.getParameter("number");
        int number = 0;
        try {
            number = Integer.parseInt(sNumber);
        } catch (NumberFormatException e) {
            logger.error("Invalid number format: {}", sNumber);
            req.setAttribute("error", "Please enter a valid integer.");
            req.getRequestDispatcher("/jsp/multiply.jsp").forward(req, resp);
            return;
        }
        int result = number * 2;
        req.setAttribute("number", number);
        req.setAttribute("result", result);
        req.getRequestDispatcher("/jsp/multiply.jsp").forward(req, resp);
    }
}
