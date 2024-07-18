


package src;

import src.components.Order;
import src.components.ShoppingCart;
import src.components.product.Product;
import src.users.Buyer;
import src.users.Seller;
import src.utils.*;

import java.util.Arrays;
import java.util.Comparator;

public class MarketSystem {
    private String systemName;
    private Buyer[] buyersList;
    private Seller[] sellersList;
    private int buyerCount = 0;
    private int sellerCount = 0;

    public MarketSystem(String systemName) {
        this.systemName = systemName;
        this.buyersList = new Buyer[2];
        this.sellersList = new Seller[2];
    }

    public Buyer[] getBuyersList() {
        return buyersList;
    }

    public Seller[] getSellersList() {
        return sellersList;
    }

    public String getSystemName() {
        return systemName;
    }

    public Status setSystemName(String systemName) {
        this.systemName = systemName;
        return Status.SUCCESS;
    }

    public Status isUsernameAvailable(String username) {
        for (int i = 0; i < buyerCount; i++) {
            if (buyersList[i].getUserName().equals(username)) {
                return Status.NAME_TAKEN;
            }
        }
        for (int i = 0; i < sellerCount; i++) {
            if (sellersList[i].getUserName().equals(username)) {
                return Status.NAME_TAKEN;
            }
        }
        return Status.SUCCESS;
    }

    public Status addBuyer(String userName, String password, String streetName, int buildingNumber, String city, String country) {
        if (!isUsernameAvailable(userName).equals(Status.SUCCESS)) {
            System.out.println("Username is already taken.");
            return Status.NAME_TAKEN;
        }

        if (buyerCount == buyersList.length) {
            expandBuyersList();
        }
        buyersList[buyerCount++] = new Buyer(userName, password, new Address(streetName, buildingNumber, city, country));
        return Status.SUCCESS;
    }

    private void expandBuyersList() {
        Buyer[] newBuyers = new Buyer[buyersList.length * 2];
        System.arraycopy(buyersList, 0, newBuyers, 0, buyersList.length);
        buyersList = newBuyers;
    }

    public Status addSeller(String sellerName, String sellerPassword) {
        if (!isUsernameAvailable(sellerName).equals(Status.SUCCESS)) {
            System.out.println("Username is already taken.");
            return Status.NAME_TAKEN;
        }

        if (sellerCount == sellersList.length) {
            expandSellersList();
        }
        sellersList[sellerCount++] = new Seller(sellerName, sellerPassword);
        return Status.SUCCESS;
    }

    private Status expandSellersList() {
        Seller[] newSellers = new Seller[sellersList.length * 2];
        System.arraycopy(sellersList, 0, newSellers, 0, sellersList.length);
        sellersList = newSellers;
        return Status.SUCCESS;
    }

    public Seller getSellerByIndex(int index) {
        if (validSellerIndex(index).equals(Status.SUCCESS)) {
            return sellersList[index];
        }
        return null;
    }

    public Buyer getBuyerByIndex(int index) {
        if (validBuyerIndex(index).equals(Status.SUCCESS)) {
            return buyersList[index];
        }
        return null;
    }

    public Seller getSellerByName(String name) {
        for (Seller seller : sellersList) {
            if (seller != null && seller.getUserName().equals(name)) {
                return seller;
            }
        }
        return null;
    }

    public Buyer getBuyerByName(String name) {
        for (Buyer buyer : buyersList) {
            if (buyer != null && buyer.getUserName().equals(name)) {
                return buyer;
            }
        }
        return null;
    }
    public Status hasBuyerItems(int buyerIndex) {
        if (validBuyerIndex(buyerIndex).equals(Status.SUCCESS) && buyersList[buyerIndex].getCurrentCartProductCount() > 0) {
            return Status.SUCCESS;
        }
        return Status.NO_PRODUCT;
    }

    public Status clearBuyerCart(int buyerIndex) {
        if (validBuyerIndex(buyerIndex).equals(Status.SUCCESS)) {
            return buyersList[buyerIndex].getCurrentCart().clearCart();
        }
        return Status.INVALID_RANGE;
    }

    public String getBuyerCartHistory(int buyerIndex) {
        if (buyerIndex < 0 || buyerIndex >= buyerCount) {
            return null;
        }
        StringBuilder buyerHistoryOrder = new StringBuilder();
        for (int j = 0; j < buyersList[buyerIndex].getOrderCount(); j++) {
            buyerHistoryOrder.append("    ").append(j + 1).append(") ").append(buyersList[buyerIndex].getOrderByIndex(j)).append("\n");
            buyerHistoryOrder.append("        The products of that order:\n");
            for (int k = 0; k < buyersList[buyerIndex].getOrderByIndex(j).getProductCount(); k++) {
                buyerHistoryOrder.append("        ").append(k + 1).append(") ").append(buyersList[buyerIndex].getOrderByIndex(j).getProductByIndex(k).getSummary()).append("\n");
            }
        }
        return buyerHistoryOrder.toString();
    }
    public Status setBuyerCurrentCartFromHistory(int buyerIndex, int cartIndex) {
        // Set the chosen cart from history as the current cart and return success status
        Status status = validBuyerIndex(buyerIndex);
        if (!status.equals(Status.SUCCESS)) {
            return status;
        }
        Buyer buyer = buyersList[buyerIndex];
        status = validOrderIndex(cartIndex,buyerIndex);
        if (!status.equals(Status.SUCCESS)) {
            return status;
        }

        Order selectedOrder = buyer.getOrderByIndex(cartIndex);
        if (selectedOrder == null) {
            return Status.EMPTY_CART;
        }
        buyer.getCurrentCart().clearCart();
        for (int i = 0; i < selectedOrder.getProductCount(); i++) {
            Product product = selectedOrder.getProductByIndex(i);
            buyer.addProductToCart(product);
        }

        return Status.SUCCESS;
    }

    public String getProductsByCategory(Category category) {
        StringBuilder sb = new StringBuilder("Products in ").append(category).append(" category:\n");
        for (Seller seller : sellersList) {
            if (seller != null) {
                for (Product product : seller.getProducts()) {
                    if (product != null && product.getCategory() == category) {
                        sb.append(product).append("\n");
                    }
                }
            }
        }
        return sb.toString();
    }
    public String getProductNameByIndex(int sellerIndex, int productIndex) {
        return sellersList[sellerIndex].getProductByIndex(productIndex).getName();
    }

    public String getCategories() {
        StringBuilder sb = new StringBuilder("Categories:\n");
        for (Category category : Category.values()) {
            sb.append(category).append("\n");
        }
        return sb.toString();
    }

    public static Seller[] sortSellersByProductCount(Seller[] sellers) {
        Arrays.sort(sellers, Comparator.comparingInt(Seller::getProductCount).reversed());
        return sellers;
    }

    public static Buyer[] sortBuyersByUsername(Buyer[] buyers) {
        Arrays.sort(buyers, Comparator.comparing(Buyer::getUserName));
        return buyers;
    }


    public Status addProductToSeller(String productName, double price, int sellerIndex, Category category, boolean isSpeciallyPacked) {
        if(isSpeciallyPacked){
            return sellersList[sellerIndex].addSpecialProduct(productName, price, category);
        }
        return sellersList[sellerIndex].addProduct(productName, price, category);
    }

    public Status addProductToBuyer(int buyerIndex, int sellerIndex, int productIndex) {
        Product product = sellersList[sellerIndex].getProductByIndex(productIndex);
        return buyersList[buyerIndex].addProductToCart(product);
    }

    public Status makePurchaseBuyerByIndex(int buyerIndex) {
        try {
            if (buyerIndex >= 0 && buyerIndex < buyerCount) {
                return buyersList[buyerIndex].purchaseCart();
            }
        }
        catch (EmptyCartException e){
            return Status.EMPTY_CART;
        }
        return Status.EMPTY_CART;
    }

    public String generateStrSellersList() {
        Arrays.sort(sellersList, 0, sellerCount, Comparator.comparingInt(Seller::getProductCount).reversed());
        StringBuilder strBuildSellers = new StringBuilder();
        for (int i = 0; i < sellerCount; i++) {
            strBuildSellers.append((i + 1) + ") " + sellersList[i].getUserName() + "\n");
        }
        return strBuildSellers.toString();
    }

    public String generateStrSellersProducts(int indexSeller) {
        StringBuilder strBuildSellersProducts = new StringBuilder();
        for (int i = 0; i < sellersList[indexSeller].getProductCount(); i++) {
            strBuildSellersProducts.append((i + 1) + ") " + sellersList[indexSeller].getProductByIndex(i) + "\n");
        }
        return strBuildSellersProducts.toString();
    }

    public String generateStrBuyersList() {
        Arrays.sort(buyersList, 0, buyerCount, Comparator.comparing(Buyer::getUserName));
        StringBuilder strBuildBuyers = new StringBuilder();
        for (int i = 0; i < buyerCount; i++) {
            strBuildBuyers.append((i + 1) + ") " + buyersList[i].getUserName() + "\n");
        }
        return strBuildBuyers.toString();
    }

    public String generateStrBuyerCartDetailsByIndex(int buyerIndex) {
        StringBuilder cartDetails = new StringBuilder();
        ShoppingCart cart = buyersList[buyerIndex].getCurrentCart();
        Product[] products = cart.getProducts();

        cartDetails.append("ShoppingCart:\n");
        cartDetails.append("Products:\n");
        for (Product product : products) {
            if (product != null) {
                cartDetails.append(product).append("\n");
            }
        }
        cartDetails.append("Total price: ").append(cart.getTotalPrice());

        return cartDetails.toString();
    }

    public String buyersData() {
        Arrays.sort(buyersList, 0, buyerCount, Comparator.comparing(Buyer::getUserName));
        StringBuilder buyerList = new StringBuilder();
        for (int i = 0; i < buyerCount; i++) {
            buyerList.append("-------------------------------------------------------\n");
            buyerList.append(i + 1 + ") " + buyersList[i].toString() + "\n");
            buyerList.append(buyersList[i].getAddress()).append("\n");
            buyerList.append("  * Current cart: \n");
            for (int j = 0; j < buyersList[i].getCurrentCart().getProductCount(); j++) {
                buyerList.append("    " + (j + 1) + ") " + buyersList[i].getCurrentCart().getProductByIndex(j).getSummary() + "\n");
            }
            buyerList.append("\n  * History Orders: \n");
            for (int j = 0; j < buyersList[i].getOrderCount(); j++) {
                buyerList.append("    ").append(j + 1).append(") ").append(buyersList[i].getOrderByIndex(j)).append("\n");
                buyerList.append("        The products of that order:\n");
                for (int k = 0; k < buyersList[i].getOrderByIndex(j).getProductCount(); k++) {
                    buyerList.append("        ").append(k + 1).append(") ").append(buyersList[i].getOrderByIndex(j).getProductByIndex(k).getSummary()).append("\n");
                }
            }
        }
        buyerList.append("-------------------------------------------------------");
        return buyerList.toString();
    }

    public String sellersData() {
        Arrays.sort(sellersList, 0, sellerCount, Comparator.comparingInt(Seller::getProductCount).reversed());
        StringBuilder sellerList = new StringBuilder();
        for (int i = 0; i < sellerCount; i++) {
            sellerList.append("-------------------------------------------------------\n");
            sellerList.append(i + 1 + ") " + sellersList[i] + "\n");
            sellerList.append("    The items of that seller:\n");
            for (int j = 0; j < sellersList[i].getProductCount(); j++) {
                sellerList.append("    " + (j + 1) + ") " + sellersList[i].getProductByIndex(j) + "\n");
            }
        }
        sellerList.append("-------------------------------------------------------");
        return sellerList.toString();
    }

    public String productsByCategory(Category category) {
        StringBuilder productsList = new StringBuilder();
        productsList.append("Products in category: ").append(category.name()).append("\n");

        boolean foundProducts = false;

        for (Seller seller : sellersList) {
            if (seller != null) {
                for (Product p : seller.getProducts()) {
                    if (p.getCategory() == category) {
                        productsList.append("Seller: ").append(seller.getUserName())
                                .append(" - Product: ").append(p.getName())
                                .append(" - Price: $").append(p.getPrice()).append("\n");
                        foundProducts = true;
                    }
                }
            }
        }

        if (!foundProducts) {
            productsList.append("No products found in this category.");
        }

        return productsList.toString();

    }
    public Status isBuyerHasOrderHistory(int buyerIndex){
        if(buyersList[buyerIndex].getOrderCount()==0){
            return Status.No_ORDER_HISTORY;
        }
        return Status.SUCCESS;
    }

    public Status isAnyBuyersExists() {
        if (buyerCount > 0){
            return Status.SUCCESS;
        }
        return Status.NO_BUYER;
    }

    public Status isAnySellersExists() {
        if (sellerCount > 0){
            return Status.SUCCESS;
        }
        return Status.NO_SELLER;
    }



    public Status isProductNameExists(int sellerIndex, String productName) {
        return sellersList[sellerIndex].isProductNameExists(productName);
    }
    public Status validSellerIndex(int index){
        if (sellerCount == 0){
            return Status.NO_SELLER;
        }
        return ManageUtils.validateRange(index,this.sellerCount);
    }
    public Status validItemIndex(int index, int sellerIndex){
        return ManageUtils.validateRange(index,sellersList[sellerIndex].getProductCount());
    }
    public Status validBuyerIndex(int index){
        if (buyerCount == 0){
            return Status.NO_BUYER;
        }
        return ManageUtils.validateRange(index,this.buyerCount);
    }
    public Status validOrderIndex(int index, int buyerIndex){
        return ManageUtils.validateRange(index,buyersList[buyerIndex].getOrderCount());
    }
    public Status validCartIndex(int index, int buyerIndex){
        return ManageUtils.validateRange(index,buyersList[buyerIndex].getCurrentCartProductCount());
    }
}
