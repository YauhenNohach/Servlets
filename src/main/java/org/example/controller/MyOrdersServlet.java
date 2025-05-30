package org.example.controller;

import org.example.model.PurchaseOrder;
import org.example.model.User;
import org.example.service.PurchaseService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/myorders")
public class MyOrdersServlet extends HttpServlet {
    private final PurchaseService purchaseService = new PurchaseService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        List<PurchaseOrder> orders = purchaseService.listUserOrders(user.getId());
        req.setAttribute("purchaseOrders", orders);
        req.getRequestDispatcher("/jsp/checkout/myorders.jsp").forward(req, resp);
    }
}