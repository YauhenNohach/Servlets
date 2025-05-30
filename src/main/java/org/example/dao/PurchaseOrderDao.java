package org.example.dao;

import org.example.model.PurchaseItem;
import org.example.model.PurchaseOrder;
import org.example.util.DataSourceUtil;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PurchaseOrderDao {
    private final DataSource ds = DataSourceUtil.getDataSource();

    public int saveOrder(PurchaseOrder po) throws SQLException {
        String sql = "INSERT INTO purchase_order(user_id, address, payment_method) VALUES (?, ?, ?)";
        try (Connection c = ds.getConnection();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, po.getUserId());
            ps.setString(2, po.getAddress());
            ps.setString(3, po.getPaymentMethod());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                rs.next();
                return rs.getInt(1);
            }
        }
    }

    public void saveItems(List<PurchaseItem> items) throws SQLException {
        String sql = "INSERT INTO purchase_item(purchase_order_id, order_id, price, quantity) VALUES (?, ?, ?, ?)";
        try (Connection c = ds.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            for (PurchaseItem it : items) {
                ps.setInt(1, it.getPurchaseOrderId());
                ps.setInt(2, it.getOrderId());
                ps.setDouble(3, it.getPrice());
                ps.setInt(4, it.getQuantity());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    public List<PurchaseOrder> findByUser(int userId) throws SQLException {
        String sql = "SELECT id, address, payment_method, created_at FROM purchase_order WHERE user_id = ? ORDER BY created_at DESC";
        List<PurchaseOrder> list = new ArrayList<>();
        try (Connection c = ds.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    PurchaseOrder po = new PurchaseOrder();
                    po.setId(rs.getInt("id"));
                    po.setUserId(userId);
                    po.setAddress(rs.getString("address"));
                    po.setPaymentMethod(rs.getString("payment_method"));
                    po.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                    list.add(po);
                }
            }
        }

        for (PurchaseOrder po : list) {
            po.setItems(findItems(po.getId()));
        }
        return list;
    }

    private List<PurchaseItem> findItems(int purchaseOrderId) throws SQLException {
        String sql = "SELECT id, order_id, price, quantity FROM purchase_item WHERE purchase_order_id = ?";
        List<PurchaseItem> items = new ArrayList<>();
        try (Connection c = ds.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, purchaseOrderId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    PurchaseItem it = new PurchaseItem();
                    it.setId(rs.getInt("id"));
                    it.setPurchaseOrderId(purchaseOrderId);
                    it.setOrderId(rs.getInt("order_id"));
                    it.setPrice(rs.getDouble("price"));
                    it.setQuantity(rs.getInt("quantity"));
                    items.add(it);
                }
            }
        }
        return items;
    }
}
