package connection;

import java.sql.Connection;

public interface getConnection {

    Connection getConection();

    void closeConnection();

}
