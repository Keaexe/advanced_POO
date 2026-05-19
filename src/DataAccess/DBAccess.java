package DataAccess;
import Interfaces.IDataAccess;
import Model.Item;
import Model.Referent;

import java.sql.*;
import java.util.Dictionary;

public class DBAccess implements IDataAccess {
    Connection connection;

    public DBAccess() throws SQLException{
        Connection connection =
                DriverManager.getConnection("jdbc:mysql://localhost:3306/POO_ADVANCED","root", "root");
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

    public Referent getReferentById() throws SQLException {
        return null;
    }

    public Dictionary<Integer, Referent> getAllReferent() {
        return null;
    }

    public void addReferent(Referent referent) throws SQLException {
        String sqlString = "insert into referent (id, designation, first_name, last_name, birth_date, is_alive, school_thought_id, website, nickname) values (?,?,?,?,?,?,?,?,?)";

        PreparedStatement sqlStat = connection.prepareStatement(sqlString);
        sqlStat.setInt(1, referent.getId());
        sqlStat.setString(2, referent.getDesignation());
        sqlStat.setString(3, referent.getFirstName());
        sqlStat.setString(4, referent.getLastName());
        sqlStat.setDate(5, Date.valueOf(referent.getBirthDate()));
        sqlStat.setBoolean(6, referent.getIsAlive());
        sqlStat.setInt(7, referent.getIdSchoolOfThought());
        if (referent.getWebsite() != null){
            sqlStat.setString(8, referent.getWebsite());
        } else {
            sqlStat.setNull(8, Types.VARCHAR);
        }
        if (referent.getNickname() != null){
            sqlStat.setString(9, referent.getNickname());
        } else {
            sqlStat.setNull(9, Types.VARCHAR);
        }
        sqlStat.executeUpdate();
    }
}
