package src.components.product;

import src.utils.Category;
import src.utils.Status;

import java.util.Arrays;

public class ProductBox {
    private Product[] products;
    private int productCount;

    public ProductBox() {
        this.products = new Product[2];
    }

    public Status addProduct(String name, double price, Category category) {
        if (productCount == products.length) { // Double the size of the array
            extendProductsList();
        }
        products[productCount++] = new Product(name, price, category);
        return Status.SUCCESS;
    }
    public Status addProduct(Product p) {
        if (productCount == products.length) { // Double the size of the array
            extendProductsList();
        }
        products[productCount++] = new Product(p);
        return Status.SUCCESS;
    }
    public Status addProduct(SpeciallyPackedProduct p) {
        if (productCount == products.length) { // Double the size of the array
            extendProductsList();
        }
        products[productCount++] = new Product(p);
        return Status.SUCCESS;
    }

    //add with specialPackaging
    public Status addSpecialProduct(String name, double price, Category category){
        if (productCount == products.length) { // Double the size of the array
            extendProductsList();
        }
        products[productCount++] = new SpeciallyPackedProduct(name, price, category);
        return Status.SUCCESS;
    }
    public Status addSpecialProduct(SpeciallyPackedProduct sp){
        if (productCount == products.length) { // Double the size of the array
            extendProductsList();
        }
        products[productCount++] = new SpeciallyPackedProduct(sp);
        return Status.SUCCESS;
    }


    private Status extendProductsList(){
        Product[] newProducts = new Product[products.length * 2];
        for (int i = 0; i < products.length; i++) {
            newProducts[i] = products[i];
        }
        products = newProducts;
        return Status.SUCCESS;
    }

    public Product[] getProducts() {
        return products;
    }

    public int getProductCount() {
        return productCount;
    }

    public Product getProductByIndex(int index) {
        return products[index];
    }

    public Product getProductByName(String wantedName) {
        for (int i = 0; i < productCount; i++) {
            if (products[i].getName().equals(wantedName)) {
                return products[i];
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "products=:\n" + Arrays.toString(products);
    }
}

