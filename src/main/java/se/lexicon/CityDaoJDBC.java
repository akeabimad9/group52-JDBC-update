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
    public List<City> findByName(String name) {
        List<City> cities = new ArrayList<>();
        String query = "SELECT * FROM city WHERE name = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                cities.add(new City(rs.getInt("id"), rs.getString("name"),
                        rs.getString("countrycode"), rs.getInt("population")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cities;
    }

    @Override
    public City findById(int id) {
        try (PreparedStatement stmt = connection.prepareStatement("SELECT * FROM city WHERE id = ?")) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new City(rs.getInt("id"), rs.getString("name"), rs.getString("countrycode"), rs.getInt("population"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<City> findByCode(String code) {
        List<City> cities = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement("SELECT * FROM city WHERE countrycode = ?")) {
            stmt.setString(1, code);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                cities.add(new City(rs.getInt("id"), rs.getString("name"), rs.getString("countrycode"), rs.getInt("population")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cities;
    }

    @Override
    public List<City> findAll() {
        List<City> cities = new ArrayList<>();
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM city");
            while (rs.next()) {
                cities.add(new City(rs.getInt("id"), rs.getString("name"), rs.getString("countrycode"), rs.getInt("population")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cities;
    }

    @Override
    public City add(City city) {
        try (PreparedStatement stmt = connection.prepareStatement(
                "INSERT INTO city (name, countrycode, population) VALUES (?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, city.getName());
            stmt.setString(2, city.getCountryCode());
            stmt.setInt(3, city.getPopulation());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                city.setId(rs.getInt(1));
                return city;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public City update(City city) {
        try (PreparedStatement stmt = connection.prepareStatement(
                "UPDATE city SET name = ?, countrycode = ?, population = ? WHERE id = ?")) {
            stmt.setString(1, city.getName());
            stmt.setString(2, city.getCountryCode());
            stmt.setInt(3, city.getPopulation());
            stmt.setInt(4, city.getId());
            if (stmt.executeUpdate() > 0) {
                return city;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int delete(City city) {
        try (PreparedStatement stmt = connection.prepareStatement("DELETE FROM city WHERE id = ?")) {
            stmt.setInt(1, city.getId());
            return stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
