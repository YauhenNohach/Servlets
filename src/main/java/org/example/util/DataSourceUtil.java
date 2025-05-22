package org.example.util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.util.Properties;

public class DataSourceUtil {
    private static HikariDataSource ds;
    static {
        try {
            Properties props = new Properties();
            props.load(DataSourceUtil.class.getClassLoader().getResourceAsStream("db.properties"));
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(props.getProperty("jdbc:postgresql://localhost:5432/postgres"));
            config.setUsername(props.getProperty("postgres"));
            config.setPassword(props.getProperty("1234"));
            ds = new HikariDataSource(config);
        } catch(Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }
    public static DataSource getDataSource() {
        return ds;
    }
    private DataSourceUtil() {}
}