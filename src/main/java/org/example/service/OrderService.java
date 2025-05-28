package org.example.service;

import org.example.dao.OrderDao;
import org.example.model.Order;

import java.sql.SQLException;
import java.util.List;

public class OrderService {
    private final OrderDao dao = new OrderDao();

    public List<Order> listAll() {
        try {
            return dao.findAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void add(String name, String imageUrl, double price) {
        Order o = new Order();
        o.setName(name);
        o.setImageUrl(imageUrl);
        o.setPrice(price);
        try {
            dao.save(o);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
