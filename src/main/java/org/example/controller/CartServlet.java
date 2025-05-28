package org.example.controller;

import org.example.model.Order;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Order> cart = getCart(req.getSession());
        req.setAttribute("cart", cart);
        getServletContext().getRequestDispatcher("/jsp/cart/cart.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String action = req.getParameter("action");
        if ("add".equals(action)) {
            int orderId = Integer.parseInt(req.getParameter("orderId"));
            Order order = getOrderById(orderId, req); // достаём из атрибута context
            if (order != null) {
                getCart(req.getSession()).add(order);
            }
        } else if ("remove".equals(action)) {
            int index = Integer.parseInt(req.getParameter("index"));
            getCart(req.getSession()).remove(index);
        } else if ("clear".equals(action)) {
            getCart(req.getSession()).clear();
        }
        resp.sendRedirect(req.getContextPath() + "/cart");
    }

    private List<Order> getCart(HttpSession session) {
        List<Order> cart = (List<Order>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
            session.setAttribute("cart", cart);
        }
        return cart;
    }

    private Order getOrderById(int id, HttpServletRequest req) {
        List<Order> orders = (List<Order>) getServletContext().getAttribute("orders");
        if (orders != null) {
            for (Order o : orders) {
                if (o.getId() == id) return o;
            }
        }
        return null;
    }
}