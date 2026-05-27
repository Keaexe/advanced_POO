package DataAccess;

import Exceptions.DataAccessException;
import Interfaces.IDataAccess;
import Model.Item;
import Model.Referent;
import Model.SchoolOfThought;
import java.sql.*;
import java.util.ArrayList;

public class DBAccess implements IDataAccess {

    public DBAccess() throws DataAccessException {}

    public ArrayList<Item> getAllItems() {
        return null;
    }

    public Item getItemById(int id) throws DataAccessException {
        return null;
    }

    public Integer getItemCount() throws DataAccessException {
        return 0;
    }

    public Referent getReferentById(int id) throws DataAccessException {
        try {
            String sqlString = "SELECT * FROM referent WHERE id = ?";
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
                data.getDate("birth_date").toLocalDate(),
                data.getBoolean("is_alive"),
                data.getInt("school_of_thought_id"),
                data.getString("website"),
                data.getString("nickname")
            );
            return referent;
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    public ArrayList<Referent> getReferentsByDesignation(String search)
        throws DataAccessException {
        try {
            String sqlString = "SELECT * FROM referent WHERE designation = ?";
            PreparedStatement sqlStat =
                SingletonConnection.getInstance().prepareStatement(sqlString);
            sqlStat.setString(1, search);
            ResultSet data = sqlStat.executeQuery();
            var referents = new ArrayList<Referent>();
            while (data.next()) {
                referents.add(
                    new Referent(
                        data.getInt("id"),
                        data.getString("designation"),
                        data.getString("first_name"),
                        data.getString("last_name"),
                        data.getDate("birth_date").toLocalDate(),
                        data.getBoolean("is_alive"),
                        data.getInt("school_of_thought_id"),
                        data.getString("website"),
                        data.getString("nickname")
                    )
                );
            }
            return referents;
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    public ArrayList<Referent> getAllReferent() throws DataAccessException {
        try {
            PreparedStatement sqlStat =
                SingletonConnection.getInstance().prepareStatement(
                    "SELECT * FROM referent;"
                );
            ResultSet data = sqlStat.executeQuery();
            var referents = new ArrayList<Referent>();
            while (data.next()) {
                referents.add(
                    new Referent(
                        data.getInt("id"),
                        data.getString("designation"),
                        data.getString("first_name"),
                        data.getString("last_name"),
                        data.getDate("birth_date").toLocalDate(),
                        data.getBoolean("is_alive"),
                        data.getInt("school_of_thought_id"),
                        data.getString("website"),
                        data.getString("nickname")
                    )
                );
            }
            return referents;
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    public void addReferent(Referent referent) throws DataAccessException {
        try {
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
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    public void deleteReferent(int id) throws DataAccessException {
        try {
            PreparedStatement sqlStat =
                SingletonConnection.getInstance().prepareStatement(
                    "DELETE FROM referent WHERE id=?;"
                );
            sqlStat.setInt(1, id);
            sqlStat.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    @Override
    public ArrayList<SchoolOfThought> getAllSchools()
        throws DataAccessException {
        try {
            PreparedStatement sqlStat =
                SingletonConnection.getInstance().prepareStatement(
                    "SELECT * FROM school_of_thought;"
                );
            ResultSet data = sqlStat.executeQuery();
            var schools = new ArrayList<SchoolOfThought>();
            while (data.next()) {
                schools.add(
                    new SchoolOfThought(
                        data.getInt("id"),
                        data.getString("name"),
                        data.getString("description")
                    )
                );
            }
            return schools;
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    @Override
    public SchoolOfThought getSchoolsByID(int id) throws DataAccessException {
        try {
            PreparedStatement sqlStat =
                SingletonConnection.getInstance().prepareStatement(
                    "SELECT * FROM school_of_thought WHERE id=?;"
                );
            sqlStat.setInt(1, id);
            ResultSet data = sqlStat.executeQuery();

            data.next();
            var school = new SchoolOfThought(
                data.getInt("id"),
                data.getString("name"),
                data.getString("description")
            );
            return school;
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
    }
}
