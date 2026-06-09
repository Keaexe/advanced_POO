package DataAccess;

import Utils.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingletonConnection {

    private static Connection connection;

    public static Connection getInstance() throws SQLException {
        if (connection == null) {
            if(Config.DATABASE_TYPE == "mysql"){
                connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/POO_ADVANCED",
                        "root",
                        "root"
                );
            } else {
                connection = DriverManager.getConnection(
                        "jdbc:sqlserver://localhost;databaseName=exam_db;encrypt=true;trustServerCertificate=true;",
                        "dev",
                        "Tigrou07"
                );
            }
        }
        return connection;
    }
}
