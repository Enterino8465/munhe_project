package src.users;

import src.components.product.Product;
import src.components.product.ProductBox;
import src.components.product.SpeciallyPackedProduct;
import src.utils.Category;
import src.utils.Status;

public class Seller extends User {
    private ProductBox productBox;

    public Seller(String userName, String password) {
        super(userName, password);
        this.productBox = new ProductBox();
    }

    public Status addProduct(String name, double price, Category category) {
        return productBox.addProduct(name, price, category);
    }
    public Status addProduct(Product product) {
        return productBox.addProduct(product);
    }
    public Status addSpecialProduct(String name, double price, Category category) {
        return productBox.addSpecialProduct(name, price, category);
    }
    public Status addSpecialProduct(SpeciallyPackedProduct sp) {
        return productBox.addSpecialProduct(sp);
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

    public Status isProductNameExists(String wantedName) {
        if(productBox.getProductByName(wantedName) != null){
            return Status.NO_PRODUCT;
        }
        return Status.SUCCESS;
    }

    @Override
    public String toString() {
        return  "Name= '" + getUserName() ;
    }
}

