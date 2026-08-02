package bank.management.system;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.Properties;

/**
 * Database Connection Manager.
 * Supports MySQL and embedded H2 database with automatic schema initialization.
 * Provides support for PreparedStatement to prevent SQL Injection vulnerabilities.
 */
public class Connn {
    public Connection connection;
    public Statement statement;

    public Connn() {
        try {
            Properties props = new Properties();
            InputStream is = getClass().getClassLoader().getResourceAsStream("database.properties");
            if (is != null) {
                props.load(is);
            }

            String dbMode = props.getProperty("db.mode", "h2");
            
            if ("mysql".equalsIgnoreCase(dbMode)) {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    String url = props.getProperty("mysql.url", "jdbc:mysql://localhost:3306/bank?createDatabaseIfNotExist=true");
                    String user = props.getProperty("mysql.user", "root");
                    String pass = props.getProperty("mysql.password", "");
                    connection = DriverManager.getConnection(url, user, pass);
                    System.out.println("[BMS DB] Connected to MySQL Database.");
                } catch (Exception e) {
                    System.err.println("[BMS DB Warning] MySQL Connection failed. Falling back to embedded H2 Database.");
                    connectH2(props);
                }
            } else {
                connectH2(props);
            }

            statement = connection.createStatement();
            initDatabaseSchema();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void connectH2(Properties props) throws Exception {
        Class.forName("org.h2.Driver");
        String url = props.getProperty("h2.url", "jdbc:h2:./bms_db;DB_CLOSE_DELAY=-1;MODE=MySQL");
        String user = props.getProperty("h2.user", "sa");
        String pass = props.getProperty("h2.password", "");
        connection = DriverManager.getConnection(url, user, pass);
        System.out.println("[BMS DB] Connected to Embedded H2 Database.");
    }

    private void initDatabaseSchema() {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("schema.sql")) {
            if (is != null) {
                String sqlScript = new String(is.readAllBytes(), StandardCharsets.UTF_8);
                String[] statements = sqlScript.split(";");
                for (String sql : statements) {
                    sql = sql.trim();
                    if (!sql.isEmpty()) {
                        statement.execute(sql);
                    }
                }
                System.out.println("[BMS DB] Schema initialized successfully.");
            }
        } catch (Exception e) {
            System.err.println("[BMS DB Warning] Failed to run schema.sql: " + e.getMessage());
        }
    }

    public PreparedStatement prepareStatement(String sql) throws SQLException {
        return connection.prepareStatement(sql);
    }
}
