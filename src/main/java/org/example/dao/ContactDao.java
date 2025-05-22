package org.example.dao;

import org.example.model.Contact;
import org.example.util.DataSourceUtil;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactDao {
    private final DataSource ds = DataSourceUtil.getDataSource();

    public List<Contact> findAll() throws SQLException {
        String sql = "SELECT id, name, phone FROM contacts";
        try (Connection conn = ds.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            List<Contact> list = new ArrayList<>();
            while (rs.next()) {
                Contact c = new Contact();
                c.setId(rs.getInt("id"));
                c.setName(rs.getString("name"));
                c.setPhone(rs.getString("phone"));
                list.add(c);
            }
            return list;
        }
    }
    public void save(Contact contact) throws SQLException {
        String sql = "INSERT INTO contacts(name, phone) VALUES (?, ?)";
        try (Connection conn = ds.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, contact.getName());
            ps.setString(2, contact.getPhone());
            ps.executeUpdate();
        }
    }
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM contacts WHERE id = ?";
        try (Connection conn = ds.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}