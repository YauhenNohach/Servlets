package org.example.controller;


import org.example.model.Contact;
import org.example.service.ContactService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/contacts")
public class ContactServlet extends HttpServlet {
    private final ContactService service = new ContactService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Contact> list = service.listAll();
        req.setAttribute("contacts", list);
        req.getRequestDispatcher("/jsp/contacts/list.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("add".equals(action)) {
            service.add(req.getParameter("name"), req.getParameter("phone"));
        } else if ("delete".equals(action)) {
            service.remove(Integer.parseInt(req.getParameter("id")));
        }
        resp.sendRedirect(req.getContextPath() + "/contacts");
    }
}
