package src.components;

import src.components.product.Product;
import src.components.product.ProductBox;
import src.components.product.SpeciallyPackedProduct;
import src.utils.Status;

public class ShoppingCart {
    private double totalPrice;
    private ProductBox pBox;

    public ShoppingCart() {
        this.pBox = new ProductBox();
    }


    public Status addProduct(Product product) {
        totalPrice += product.getPrice();
        return pBox.addProduct(product);
    }
    public Status addProduct(SpeciallyPackedProduct product) {
        double finalPrice = product.getPrice();
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


