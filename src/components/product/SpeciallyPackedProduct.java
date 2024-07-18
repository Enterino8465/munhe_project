package src.components.product;

import src.utils.Category;

public class SpeciallyPackedProduct extends Product{
    boolean specialPacked;
    public SpeciallyPackedProduct(String name, double price, Category category) {
        super(name, price, category);
        this.PackInSpecialPackage();
    }
    public SpeciallyPackedProduct(SpeciallyPackedProduct sp) {
        super(sp.getName(), sp.getPrice(), sp.getCategory());
        this.PackInSpecialPackage();
    }
    private void PackInSpecialPackage(){
        this.specialPacked = true;
    }
    @Override
    public String toString() {
        return super.toString() + "this item is specially packed";
    }
}
