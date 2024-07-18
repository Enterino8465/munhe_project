package src.users;

import src.components.Order;
import src.components.ShoppingCart;
import src.components.product.Product;
import src.components.product.SpeciallyPackedProduct;
import src.utils.Address;
import src.utils.EmptyCartException;
import src.utils.Status;

public class Buyer extends User {
    private ShoppingCart currentCart;
    private Order[] ordersHistory;
    private int orderCount;
    private Address address;

    public Buyer(String userName, String password, Address address) {
        super(userName, password);
        this.currentCart = new ShoppingCart();
        this.ordersHistory = new Order[2]; // Initial size of order history
        this.orderCount = 0; // Initialize order count
        this.address = address;
    }

    public int getOrderCount() {
        return orderCount;
    }


    public Order getOrderByIndex(int index) {
        if (index >= 0 && index < orderCount) {
            return ordersHistory[index];
        }
        return null;
    }

    public Address getAddress() {
        return address;
    }

    public Status setAddress(Address address) {
        this.address = address;
        return Status.SUCCESS;
    }

    public int getCurrentCartProductCount() {
        return currentCart.getProductCount();
    }

    public Order[] getOrdersHistory() {
        return ordersHistory;
    }

    public ShoppingCart getCurrentCart() {
        return currentCart;
    }

    public Status addProductToCart(Product p) {
        return this.currentCart.addProduct(p);
    }
    public Status addProductToCart(SpeciallyPackedProduct p) {
        return this.currentCart.addProduct(p);
    }

    public Status purchaseCart() throws EmptyCartException {
        if (this.currentCart.getProductCount() == 0){
            throw new EmptyCartException();
        }
        if (orderCount == ordersHistory.length) { // Double the size of the array
            Order[] newOrdersHistory = new Order[ordersHistory.length * 2];
            for (int i = 0; i < orderCount; i++) {
                newOrdersHistory[i] = ordersHistory[i];
            }
            this.ordersHistory = newOrdersHistory;
        }
        this.ordersHistory[orderCount++] = new Order(currentCart);
        this.currentCart = new ShoppingCart();
        return Status.SUCCESS;
    }
    public Status isNotCartEmpty() {
        if (currentCart.getProductCount() == 0){
            return Status.EMPTY_CART;
        }
        return Status.SUCCESS;
    }
    @Override
    public String toString() {
        return "Buyer userName: " + getUserName();
    }
}

