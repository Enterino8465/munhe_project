package src.components;

import src.components.product.Product;
import src.components.product.ProductBox;

public class ShoppingCart {
    private double totalPrice;
    private ProductBox pBox;

    public ShoppingCart() {
        this.pBox = new ProductBox();
    }


    public boolean addProduct(Product product) {
        totalPrice += product.getPrice();
        return pBox.addProduct(product);
    }
    public boolean addProduct(Product product, boolean includePackaging) {
        double finalPrice = product.getPrice();
        if (includePackaging && product.hasSpecialPackaging()) {
            finalPrice += product.getPackagingPrice();
        }
        totalPrice += finalPrice;
        return pBox.addProduct(product);
    }
    public Product[] getProducts() {
        return pBox.getProducts();
    }

    public Product getProductByIndex(int i){
        return pBox.getProductByIndex(i);
    }

    public int getProductCount() {
        return pBox.getProductCount();
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void clearCart() {
        this.pBox = new ProductBox();
        this.totalPrice = 0.0;
    }

    @Override
    public String toString() {
        return "ShoppingCart:\n" +
                pBox.toString() +
                "\ntotal price= " + this.totalPrice;
    }
}


