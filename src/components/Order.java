package src.components;

import src.components.product.Product;

import java.util.Date;

public class Order {
    private ShoppingCart shoppingCart;
    private Date orderDate;

    public Order(ShoppingCart sc){
        this.shoppingCart = sc;
        this.orderDate = new Date();
    }

    public int getProductCount(){
        return shoppingCart.getProductCount();
    }

    public Product getProductByIndex(int index){
        if (index>=0 && index<shoppingCart.getProductCount()){
            return shoppingCart.getProductByIndex(index);
        }
        return null;
    }

    public Product[] getProducts() {
        return shoppingCart.getProducts();
    }

    public double getTotalPrice() {
        return shoppingCart.getTotalPrice();
    }

    public Date getOrderDate() {
        return orderDate;
    }

    @Override
    public String toString() {
        return "Date of the order is: " + orderDate;
    }
}

