package org.example.dao;

import org.example.model.Order;
import org.example.util.DataSourceUtil;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDao {
    private final DataSource ds = DataSourceUtil.getDataSource();

    public List<Order> findAll() throws SQLException {
        String sql = "SELECT id, name, image_url, price FROM orders";
        try (Connection conn = ds.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            List<Order> list = new ArrayList<>();
            while (rs.next()) {
                Order o = new Order();
                o.setId(rs.getInt("id"));
                o.setName(rs.getString("name"));
                o.setImageUrl(rs.getString("image_url"));
                o.setPrice(rs.getDouble("price"));
                list.add(o);
            }
            return list;
        }
    }

    public void save(Order order) throws SQLException {
        String sql = "INSERT INTO orders(name, image_url, price) VALUES (?, ?, ?)";
        try (Connection conn = ds.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, order.getName());
            ps.setString(2, order.getImageUrl());
            ps.setDouble(3, order.getPrice());
            ps.executeUpdate();
        }
    }
}
