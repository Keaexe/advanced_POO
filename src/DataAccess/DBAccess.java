package DataAccess;

import Exceptions.DataAccessException;
import Interfaces.IDataAccess;
import Models.Referent;
import Models.SchoolOfThought;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class DBAccess implements IDataAccess {

    public DBAccess() throws DataAccessException {}

    public Referent getReferentById(int id) throws DataAccessException {
        try (
            PreparedStatement sqlStat =
                SingletonConnection.getInstance().prepareStatement(
                    "SELECT * FROM referent WHERE id = ?"
                )
        ) {
            sqlStat.setInt(1, id);
            ResultSet data = sqlStat.executeQuery();
            if (!data.next()) throw new DataAccessException(
                "Referent not found (wrong ID)"
            );
            return new Referent(
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
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    public ArrayList<Referent> getReferentsByDesignation(String search)
        throws DataAccessException {
        try (
            PreparedStatement sqlStat =
                SingletonConnection.getInstance().prepareStatement(
                    "SELECT * FROM referent WHERE designation = ?"
                )
        ) {
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
        try (
            PreparedStatement sqlStat =
                SingletonConnection.getInstance().prepareStatement(
                    "SELECT * FROM referent;"
                )
        ) {
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
        String sqlString =
            "INSERT INTO referent (designation, first_name, last_name, birth_date, is_alive," +
            " school_of_thought_id, website, nickname) VALUES (?,?,?,?,?,?,?,?)";
        // Pas d'id car AUTO_INCREMENT
        try (
            PreparedStatement sqlStat =
                SingletonConnection.getInstance().prepareStatement(sqlString)
        ) {
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

    public void updateReferent(Referent referent) throws DataAccessException {
        String sqlString =
            "UPDATE referent SET designation = ?, first_name = ?, last_name = ?, birth_date = ?, is_alive = ?," +
            " school_of_thought_id = ?, website = ?, nickname = ? WHERE id = ?";
        try (
            PreparedStatement sqlStat =
                SingletonConnection.getInstance().prepareStatement(sqlString)
        ) {
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
            sqlStat.setInt(9, referent.getId());

            sqlStat.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    public void deleteReferent(int id) throws DataAccessException {
        try (
            PreparedStatement sqlStat =
                SingletonConnection.getInstance().prepareStatement(
                    "DELETE FROM referent WHERE id=?;"
                )
        ) {
            sqlStat.setInt(1, id);
            sqlStat.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    @Override
    public ArrayList<SchoolOfThought> getAllSchools()
        throws DataAccessException {
        try (
            PreparedStatement sqlStat =
                SingletonConnection.getInstance().prepareStatement(
                    "SELECT * FROM school_of_thought;"
                )
        ) {
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
        try (
            PreparedStatement sqlStat =
                SingletonConnection.getInstance().prepareStatement(
                    "SELECT * FROM school_of_thought WHERE id=?;"
                )
        ) {
            sqlStat.setInt(1, id);
            ResultSet data = sqlStat.executeQuery();

            data.next();
            return new SchoolOfThought(
                data.getInt("id"),
                data.getString("name"),
                data.getString("description")
            );
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    @Override
    public ArrayList<Object[]> getItemSearchResultsByReferentId(int referentId)
        throws DataAccessException {
        StringBuilder sqlStringB = new StringBuilder();
        sqlStringB.append("SELECT ");
        sqlStringB.append("i.name AS item_name, ");
        sqlStringB.append("i.price_ex_vat AS item_price_ex_vat, ");
        sqlStringB.append("ol.quantity AS order_quantity, ");
        sqlStringB.append("ol.price_at_the_time AS order_price_at_the_time, ");
        sqlStringB.append("c.name AS category_name ");
        sqlStringB.append("FROM item i ");
        sqlStringB.append("JOIN item_referent ir ON i.id = ir.item_id ");
        sqlStringB.append("JOIN order_line ol ON i.id = ol.item_id ");
        sqlStringB.append("JOIN category c ON i.category_name = c.name ");
        sqlStringB.append("WHERE ir.referent_id = ? ");
        sqlStringB.append("ORDER BY i.name");
        try (
            PreparedStatement sqlStat =
                SingletonConnection.getInstance().prepareStatement(
                    sqlStringB.toString()
                )
        ) {
            sqlStat.setInt(1, referentId);

            ResultSet data = sqlStat.executeQuery();

            ArrayList<Object[]> results = new ArrayList<>();

            while (data.next()) {
                results.add(new Object[] {
                    data.getString("item_name"),
                    data.getDouble("item_price_ex_vat"),
                    data.getInt("order_quantity"),
                    data.getDouble("order_price_at_the_time"),
                    data.getString("category_name"),
                });
            }

            return results;
        } catch (SQLException exception) {
            throw new DataAccessException(exception.getMessage());
        }
    }

    @Override
    public ArrayList<Object[]> getOrderLinesByClientId(int clientId)
        throws DataAccessException {
        StringBuilder sqlStringB = new StringBuilder();
        sqlStringB.append("SELECT ");
        sqlStringB.append("ot.id AS order_id, ");
        sqlStringB.append("ot.creation_time AS creation_time, ");
        sqlStringB.append("i.name AS item_name, ");
        sqlStringB.append("ol.quantity AS quantity, ");
        sqlStringB.append("ol.price_at_the_time AS price_at_the_time ");
        sqlStringB.append("FROM order_table ot ");
        sqlStringB.append("JOIN order_line ol ON ot.id = ol.order_id ");
        sqlStringB.append("JOIN item i ON ol.item_id = i.id ");
        sqlStringB.append("WHERE ot.client_id = ? ");
        sqlStringB.append("ORDER BY ot.creation_time DESC, ot.id");
        try (
            PreparedStatement sqlStat =
                SingletonConnection.getInstance().prepareStatement(
                    sqlStringB.toString()
                )
        ) {
            sqlStat.setInt(1, clientId);

            ResultSet data = sqlStat.executeQuery();

            ArrayList<Object[]> rows = new ArrayList<>();

            while (data.next()) {
                rows.add(new Object[] {
                    data.getInt("order_id"),
                    data.getTimestamp("creation_time"),
                    data.getString("item_name"),
                    data.getInt("quantity"),
                    data.getDouble("price_at_the_time"),
                });
            }

            return rows;
        } catch (SQLException exception) {
            throw new DataAccessException(exception.getMessage());
        }
    }

    @Override
    public ArrayList<Object[]> getAllClientsForCombo()
        throws DataAccessException {
        String sqlString =
            "SELECT id, first_name, last_name FROM client ORDER BY last_name, first_name";
        try (
            PreparedStatement sqlStat =
                SingletonConnection.getInstance().prepareStatement(sqlString)
        ) {
            ResultSet data = sqlStat.executeQuery();

            ArrayList<Object[]> clients = new ArrayList<>();

            while (data.next()) {
                clients.add(new Object[] {
                    data.getInt("id"),
                    data.getString("first_name"),
                    data.getString("last_name"),
                });
            }

            return clients;
        } catch (SQLException exception) {
            throw new DataAccessException(exception.getMessage());
        }
    }

    @Override
    public ArrayList<String> getAllCountryNames() throws DataAccessException {
        try (
            PreparedStatement sqlStat =
                SingletonConnection.getInstance().prepareStatement(
                    "SELECT name FROM country ORDER BY name"
                )
        ) {
            ResultSet data = sqlStat.executeQuery();

            ArrayList<String> countries = new ArrayList<>();

            while (data.next()) {
                countries.add(data.getString("name"));
            }

            return countries;
        } catch (SQLException exception) {
            throw new DataAccessException(exception.getMessage());
        }
    }

    @Override
    public ArrayList<Object[]> getOrdersByCountryAndDates(
        String countryName,
        LocalDate startDate,
        LocalDate endDate
    ) throws DataAccessException {
        StringBuilder sqlStringB = new StringBuilder();
        sqlStringB.append("SELECT ");
        sqlStringB.append("ot.id AS order_id, ");
        sqlStringB.append("ot.creation_time AS creation_time, ");
        sqlStringB.append("c.first_name AS client_first_name, ");
        sqlStringB.append("c.last_name AS client_last_name, ");
        sqlStringB.append("da.num_in_street AS address_number, ");
        sqlStringB.append("da.street_name AS street, ");
        sqlStringB.append("l.name AS locality_name, ");
        sqlStringB.append("l.zipCode AS zip_code ");
        sqlStringB.append("FROM order_table ot ");
        sqlStringB.append("JOIN client c ON ot.client_id = c.id ");
        sqlStringB.append(
            "JOIN delivery_address da ON c.delivery_address_id = da.id "
        );
        sqlStringB.append("JOIN locality l ON da.locality_id = l.id ");
        sqlStringB.append("JOIN country co ON l.country_name = co.name ");
        sqlStringB.append("WHERE co.name = ? ");
        sqlStringB.append("AND DATE(ot.creation_time) BETWEEN ? AND ? ");
        sqlStringB.append("ORDER BY ot.creation_time");
        try (
            PreparedStatement sqlStat =
                SingletonConnection.getInstance().prepareStatement(
                    sqlStringB.toString()
                )
        ) {
            sqlStat.setString(1, countryName);
            sqlStat.setDate(2, java.sql.Date.valueOf(startDate));
            sqlStat.setDate(3, java.sql.Date.valueOf(endDate));

            ResultSet data = sqlStat.executeQuery();

            ArrayList<Object[]> rows = new ArrayList<>();

            while (data.next()) {
                rows.add(new Object[] {
                    data.getInt("order_id"),
                    data.getTimestamp("creation_time"),
                    data.getString("client_first_name"),
                    data.getString("client_last_name"),
                    data.getString("address_number"),
                    data.getString("street"),
                    data.getString("locality_name"),
                    data.getString("zip_code"),
                });
            }

            return rows;
        } catch (SQLException exception) {
            throw new DataAccessException(exception.getMessage());
        }
    }
}
