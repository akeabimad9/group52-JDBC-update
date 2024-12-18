package se.lexicon;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
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
