package org.example.controller;

import org.example.model.Order;
import org.example.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/orders")
public class OrderServlet extends HttpServlet {
    private final OrderService service = new OrderService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Order> orders = service.listAll();
        req.setAttribute("orders", orders);

        getServletContext().setAttribute("orders", orders);

        req.getRequestDispatcher("/jsp/orders/list.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String action = req.getParameter("action");
        if ("add".equals(action)) {
            String name = req.getParameter("name");
            String image = req.getParameter("image");
            double price = Double.parseDouble(req.getParameter("price"));
            service.add(name, image, price);
        }
        resp.sendRedirect(req.getContextPath() + "/orders");
    }

}
