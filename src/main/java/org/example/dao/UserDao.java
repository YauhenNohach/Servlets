package org.example.dao;

import org.example.model.User;
import org.example.util.DataSourceUtil;

import javax.sql.DataSource;
import java.sql.*;

public class UserDao {
    private final DataSource ds = DataSourceUtil.getDataSource();

    public void save(User user) throws SQLException {
        String sql = "INSERT INTO users(email, password_hash, enabled, confirmation_token) VALUES(?,?,?,?)";
        try (Connection conn = ds.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPasswordHash());
            ps.setBoolean(3, user.isEnabled());
            ps.setString(4, user.getConfirmationToken());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) user.setId(rs.getInt(1));
            }
        }
    }

    public User findByEmail(String email) throws SQLException {
        String sql = "SELECT id, email, password_hash, enabled, confirmation_token FROM users WHERE email = ?";
        try (Connection conn = ds.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    User u = new User();
                    u.setId(rs.getInt("id"));
                    u.setEmail(rs.getString("email"));
                    u.setPasswordHash(rs.getString("password_hash"));
                    u.setEnabled(rs.getBoolean("enabled"));
                    u.setConfirmationToken(rs.getString("confirmation_token"));
                    return u;
                }
                return null;
            }
        }
    }

    public User findByToken(String token) throws SQLException {
        String sql = "SELECT id, email, password_hash, enabled, confirmation_token FROM users WHERE confirmation_token = ?";
        try (Connection conn = ds.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, token);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    User u = new User();
                    u.setId(rs.getInt("id"));
                    u.setEmail(rs.getString("email"));
                    u.setPasswordHash(rs.getString("password_hash"));
                    u.setEnabled(rs.getBoolean("enabled"));
                    u.setConfirmationToken(rs.getString("confirmation_token"));
                    return u;
                }
                return null;
            }
        }
    }

    public void enable(int id) throws SQLException {
        String sql = "UPDATE users SET enabled = TRUE, confirmation_token = NULL WHERE id = ?";
        try (Connection conn = ds.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
