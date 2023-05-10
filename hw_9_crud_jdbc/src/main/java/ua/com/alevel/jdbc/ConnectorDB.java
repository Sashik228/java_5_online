package ua.com.alevel.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectorDB {

    private static final ConnectorDB instance = new ConnectorDB();

    private Connection connection;
    private Statement statement;


    private static final String url = "jdbc:postgresql://localhost:5432/jdbc_crud";
    private static final String user = "postgres";
    private static final String password = "1111";

    private ConnectorDB() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static ConnectorDB getInstance() {
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    public Statement getStatement() {
        return statement;
    }
}