package models;

public class OscarData {
    private int year;
    private int age;
    private String name;
    private String movie;

    public OscarData(int year, int age, String name, String movie) {
        this.year = year;
        this.age = age;
        this.name = name;
        this.movie = movie;
    }

    public int getYear() {
        return year;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public String getMovie() {
        return movie;
    }
}
