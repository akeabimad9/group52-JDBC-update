package se.lexicon;
public class City {
    private int id;
    private String name;
    private String countryCode;
    private int population;

    public City(int id, String name, String countryCode, int population) {
        this.id = id;
        this.name = name;
        this.countryCode = countryCode;
        this.population = population;
    }

    public City(String name, String countryCode, int population) {
        this.name = name;
        this.countryCode = countryCode;
        this.population = population;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public String getCountryCode() { return countryCode; }
    public int getPopulation() { return population; }
}
