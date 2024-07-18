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

    public Status setStreetName(String streetName) {
        this.streetName = streetName;
        return Status.SUCCESS;
    }
    public int getBuildingNumber() {
        return buildingNumber;
    }

    public Status setBuildingNumber(int buildingNumber) {
        this.buildingNumber = buildingNumber;
        return Status.SUCCESS;
    }

    public String getCity() {
        return city;
    }

    public Status setCity(String city) {
        this.city = city;
        return Status.SUCCESS;
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
