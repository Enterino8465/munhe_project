package src.components.product;

import src.utils.Category;
import src.utils.Status;

public class Product {
    private static int serialCounter;
    private final int serialNumber;
    private String name;
    private double price;
    private Category category;

    public Product(String name, double price, Category category) {
        this.serialNumber = ++serialCounter;
        this.name = name;
        this.price = price;
        this.category = category;
    }
    //Copy constructor
    public Product(Product p) {
        this.name = p.getName();
        this.price = p.getPrice();
        this.category = p.getCategory();
        this.serialNumber = p.getSerialNumber();
    }
    public int getSerialNumber() {
        return serialNumber;
    }

    public String getName() {
        return name;
    }

    public Status setName(String name) {
        this.name = name;
        return Status.SUCCESS;
    }

    public double getPrice() {
        return price;
    }

    public Status setPrice(double price) {
        if (price > 0) {
            this.price = price;
            return Status.SUCCESS;
        }
        return Status.NEGATIVE_PRICE;
    }

    public Category getCategory() {
        return category;
    }

    public boolean setCategory(Category category) {
        this.category = category;
        return true;
    }



    public String getSummary() {
        return "Product Name: " + name + ", Price: " + price + ", Category: " + category;
    }
    @Override
    public String toString() {
        return "Product [serialNumber=" + serialNumber
                + ", name=" + name + ", price=" + price + ", category=" + category +"]";
    }
}
