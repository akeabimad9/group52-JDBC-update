package se.lexicon;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CityDaoJDBC implements CityDao {
    private Connection connection;

    public CityDaoJDBC(Connection connection) {
        this.connection = connection;
    }

    @Override
    public City findById(int id) {
        String query = "SELECT * FROM city WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new City(rs.getInt("id"), rs.getString("name"),
                        rs.getString("countrycode"), rs.getInt("population"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Implement other methods similarly
}
