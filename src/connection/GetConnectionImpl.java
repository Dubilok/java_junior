package connection;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class GetConnectionImpl implements getConnection {

    public static GetConnectionImpl instance;

    private Connection connection;

    private GetConnectionImpl() {}

    public static GetConnectionImpl getInstance() {
        if (instance == null) {
            instance = new GetConnectionImpl();
        }
        return instance;
    }

    @Override
    public Connection getConection() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("C:\\Java\\Learning\\Java_Junior\\src\\resources\\db.properties"));
            String dbUrl = properties.getProperty("db.url");
            String dbUsername = properties.getProperty("db.username");
            String dbPassword = properties.getProperty("db.password");
            String drivelClassName = properties.getProperty("db.driverClassName");
            Class.forName(drivelClassName);
            this.connection = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        } catch (IOException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return this.connection;
    }

    @Override
    public void closeConnection() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            //NOP
        }
    }
}
