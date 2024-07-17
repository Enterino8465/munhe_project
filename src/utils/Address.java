package src.utils;

public class Address {
    private String streetName;
    private int buildingNumber;
    private String city;
    private String country;

    public Address(String streetName, int buildingNumber, String city, String country) {
        this.streetName = streetName;
        this.buildingNumber = buildingNumber;
        this.city = city;
        this.country = country;
    }

    public String getStreetName() {
        return streetName;
    }

    public boolean setStreetName(String streetName) {
        this.streetName = streetName;
        return true;
    }

    public int getBuildingNumber() {
        return buildingNumber;
    }

    public boolean setBuildingNumber(int buildingNumber) {
        this.buildingNumber = buildingNumber;
        return true;
    }

    public String getCity() {
        return city;
    }

    public boolean setCity(String city) {
        this.city = city;
        return true;
    }

    public String getCountry() {
        return country;
    }

    public boolean setCountry(String country) {
        this.country = country;
        return true;
    }

    @Override
    public String toString() {
        return "- Country: " + country +
                "\n- City: " + city +
                "\n- Street: " + streetName +
                "\n- Building Number: " + buildingNumber;
    }
}
