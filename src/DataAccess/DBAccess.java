package DataAccess;
import Interfaces.IDataAccess;
import Model.Item;

import java.sql.*;
import java.util.Dictionary;

public class DBAccess implements IDataAccess {
    public DBAccess() throws SQLException{
        Connection connection =
                DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root", "root");
    }

    public Dictionary<Integer, Item> getAllItems() {
        return null;
    }

    public Item getItemById(Integer id) throws SQLException {
        return null;
    }

    public Integer getItemCount() throws SQLException {
        return 0;
    }
}
