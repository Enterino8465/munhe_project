package src.components.product;

import src.utils.Category;

public class Product {
    private static int serialCounter;
    private final int serialNumber;
    private String name;
    private double price;
    private Category category;
    private double packagingPrice;
    private boolean specialPackaging;

    public Product(String name, double price, Category category) {
        this.serialNumber = ++serialCounter;
        this.name = name;
        this.price = price;
        this.category = category;
        this.specialPackaging = false;
        this.packagingPrice = 0;
    }
    //Copy constructor
    public Product(Product p) {
        this.name = p.getName();
        this.price = p.getPrice();
        this.category = p.getCategory();
        this.specialPackaging = p.isSpecialPackaging();
        this.packagingPrice = p.getPackagingPrice();
        this.serialNumber = p.getSerialNumber();
    }
    public int getSerialNumber() {
        return serialNumber;
    }

    public String getName() {
        return name;
    }

    public boolean setName(String name) {
        this.name = name;
        return true;
    }

    public double getPrice() {
        return price;
    }

    public boolean setPrice(double price) {
        if (price > 0) {
            this.price = price;
            return true;
        }
        return false;
    }

    public Category getCategory() {
        return category;
    }

    public boolean setCategory(Category category) {
        this.category = category;
        return true;
    }

    public boolean isSpecialPackaging() {
        return specialPackaging;
    }

    public boolean setSpecialPackaging(boolean specialPackaging, double packagingPrice) {
        this.specialPackaging = specialPackaging;
        if (specialPackaging) {
            this.packagingPrice = packagingPrice;
        } else {
            this.packagingPrice = 0;
        }
        return true;
    }

    public double getPackagingPrice() {
        return packagingPrice;
    }
    public boolean hasSpecialPackaging() {
        return specialPackaging;
    }
    public String getSummary() {
        return "Product Name: " + name + ", Price: " + price + ", Category: " + category;
    }
    @Override
    public String toString() {
        return "Product [serialNumber=" + serialNumber
                + ", name=" + name + ", price=" + price + ", category=" + category
                + ", packagingPrice=" + packagingPrice + ", specialPackaging="
                + specialPackaging + "]";
    }
}
