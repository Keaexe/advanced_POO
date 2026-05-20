package DataAccess;

import Interfaces.IDataAccess;
import Model.Item;
import Model.Referent;
import java.sql.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Dictionary;

public class DBAccess implements IDataAccess {

    public DBAccess() throws SQLException {}

    public Dictionary<Integer, Item> getAllItems() {
        return null;
    }

    public Item getItemById(int id) throws SQLException {
        return null;
    }

    public Integer getItemCount() throws SQLException {
        return 0;
    }

    public Referent getReferentById(int id) throws SQLException {
        String sqlString = "select * from referent where id = ?";
        PreparedStatement sqlStat =
            SingletonConnection.getInstance().prepareStatement(sqlString);
        sqlStat.setInt(1, id);
        ResultSet data = sqlStat.executeQuery();
        data.next();
        var referent = new Referent(
            data.getInt("id"),
            data.getString("designation"),
            data.getString("first_name"),
            data.getString("last_name"),
            LocalDate.ofInstant(
                data.getDate("birth_date").toInstant(),
                ZoneId.systemDefault()
            ),
            data.getBoolean("is_alive"),
            data.getInt("school_of_thought"),
            data.getString("website"),
            data.getString("nickname")
        );
        return referent;
    }

    public Dictionary<Integer, Referent> getAllReferent() {
        return null;
    }

    public void addReferent(Referent referent) throws SQLException {
        String sqlString =
            "insert into referent (designation, first_name, last_name, birth_date, is_alive," +
            " school_of_thought_id, website, nickname) values (?,?,?,?,?,?,?,?)";
        // Pas d'id car AUTO_INCREMENT
        PreparedStatement sqlStat =
            SingletonConnection.getInstance().prepareStatement(sqlString);
        sqlStat.setString(1, referent.getDesignation());
        sqlStat.setString(2, referent.getFirstName());
        sqlStat.setString(3, referent.getLastName());
        sqlStat.setDate(4, Date.valueOf(referent.getBirthDate()));
        sqlStat.setBoolean(5, referent.getIsAlive());
        sqlStat.setInt(6, referent.getIdSchoolOfThought());
        if (referent.getWebsite() != null) {
            sqlStat.setString(7, referent.getWebsite());
        } else {
            sqlStat.setNull(7, Types.VARCHAR);
        }
        if (referent.getNickname() != null) {
            sqlStat.setString(8, referent.getNickname());
        } else {
            sqlStat.setNull(8, Types.VARCHAR);
        }

        sqlStat.executeUpdate();
    }
}
