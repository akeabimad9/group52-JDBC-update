package se.lexicon;
import java.sql.Connection;
public class Main {
    public static void main(String[] args) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            CityDao cityDao = new CityDaoJDBC(connection);

            City city = cityDao.findById(1);
            System.out.println(city.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
