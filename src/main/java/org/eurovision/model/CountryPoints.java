package org.eurovision.model;

import java.util.Objects;

/**
 * @author Kseniia Orlenko
 * @version 4/21/18
 */
public class CountryPoints {

    private String country;

    private int points;

    public CountryPoints(String country, int points) {
        this.country = country;
        this.points = points;
    }

    public CountryPoints() {
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        CountryPoints that = (CountryPoints) o;
        return points == that.points &&
                Objects.equals(country, that.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(country, points);
    }
}
