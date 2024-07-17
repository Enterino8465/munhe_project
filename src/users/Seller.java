package src.users;

import src.components.product.Product;
import src.components.product.ProductBox;
import src.utils.Category;

public class Seller extends User {
    private ProductBox productBox;

    public Seller(String userName, String password) {
        super(userName, password);
        this.productBox = new ProductBox();
    }

    public boolean addProduct(String name, double price, Category category) {
        return productBox.addProduct(name, price, category);
    }
    //add product with specialPackaging
    public boolean addProduct(String name, double price, Category category, boolean specialPackaging, double packagingPrice) {
        return productBox.addProduct(name, price, category, specialPackaging, packagingPrice);
    }

    public boolean addProduct(Product product) {
        return productBox.addProduct(product);
    }

    public Product[] getProducts() {
        return productBox.getProducts();
    }

    public int getProductCount() {
        return productBox.getProductCount();
    }

    public Product getProductByIndex(int index) {
        return productBox.getProductByIndex(index);
    }

    public boolean isProductNameExists(String wantedName) {
        return productBox.getProductByName(wantedName) != null;
    }
    public boolean isProductHasSpecialPackage(int index) {
        return productBox.getProductByIndex(index).hasSpecialPackaging();
    }

    @Override
    public String toString() {
        return  "Name= '" + getUserName() ;
    }
}

