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
//            hibProps.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
            config.setJdbcUrl(props.getProperty("jdbc.url"));
            config.setUsername(props.getProperty("jdbc.username"));
            config.setPassword(props.getProperty("jdbc.password"));
            config.setDriverClassName(props.getProperty("jdbc.driverClassName"));
//            config.setMaximumPoolSize(Integer.parseInt(props.getProperty("jdbc.maximumPoolSize")));
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