


package src;

import src.components.Order;
import src.components.ShoppingCart;
import src.components.product.Product;
import src.users.Buyer;
import src.users.Seller;
import src.utils.Address;
import src.utils.Category;

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

    public boolean setSystemName(String systemName) {
        this.systemName = systemName;
        return true;
    }

    public boolean isUsernameTaken(String username) {
        for (int i = 0; i < buyerCount; i++) {
            if (buyersList[i].getUserName().equals(username)) {
                return true;
            }
        }
        for (int i = 0; i < sellerCount; i++) {
            if (sellersList[i].getUserName().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public boolean addBuyer(String userName, String password, String streetName, int buildingNumber, String city, String country) {
        if (isUsernameTaken(userName)) {
            System.out.println("Username is already taken.");
            return false;
        }

        if (buyerCount == buyersList.length) {
            expandBuyersList();
        }
        buyersList[buyerCount++] = new Buyer(userName, password, new Address(streetName, buildingNumber, city, country));
        return true;
    }

    private void expandBuyersList() {
        Buyer[] newBuyers = new Buyer[buyersList.length * 2];
        System.arraycopy(buyersList, 0, newBuyers, 0, buyersList.length);
        buyersList = newBuyers;
    }

    public boolean addSeller(String sellerName, String sellerPassword) {
        if (isUsernameTaken(sellerName)) {
            System.out.println("Username is already taken.");
            return false;
        }

        if (sellerCount == sellersList.length) {
            expandSellersList();
        }
        sellersList[sellerCount++] = new Seller(sellerName, sellerPassword);
        return true;
    }

    private void expandSellersList() {
        Seller[] newSellers = new Seller[sellersList.length * 2];
        System.arraycopy(sellersList, 0, newSellers, 0, sellersList.length);
        sellersList = newSellers;
    }

    public Seller getSellerByIndex(int index) {
        if (index >= 0 && index < sellerCount) {
            return sellersList[index];
        }
        return null;
    }

    public Buyer getBuyerByIndex(int index) {
        if (index >= 0 && index < buyerCount) {
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
    public boolean hasBuyerItems(int buyerIndex) {
        if (buyerIndex < 0 || buyerIndex >= buyerCount) {
            return false;
        }
        return buyersList[buyerIndex].getCurrentCartProductCount() > 0;
    }

    public boolean clearBuyerCart(int buyerIndex) {
        if (buyerIndex < 0 || buyerIndex >= buyerCount) {
            return false;
        }
        buyersList[buyerIndex].getCurrentCart().clearCart();
        return true;
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
    public boolean setBuyerCurrentCartFromHistory(int buyerIndex, int cartIndex) {
        // Set the chosen cart from history as the current cart and return success status
        if (buyerIndex < 0 || buyerIndex >= buyerCount) {
            return false;
        }

        Buyer buyer = buyersList[buyerIndex];
        if (cartIndex < 0 || cartIndex >= buyer.getOrderCount()) {
            return false;
        }

        Order selectedOrder = buyer.getOrderByIndex(cartIndex);
        if (selectedOrder == null) {
            return false;
        }
        for (int i = 0; i < selectedOrder.getProductCount(); i++) {
            Product product = selectedOrder.getProductByIndex(i);
            buyer.addProductToCart(product, false);
        }

        return true;
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
    public double getPriceProductSpecialPackage(int sellerIndex, int productIndex) {
        return sellersList[sellerIndex].getProductByIndex(productIndex).getPackagingPrice();
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

    public boolean addProductToSeller(String productName, double price, int sellerIndex, Category category) {
        return sellersList[sellerIndex].addProduct(productName, price, category);
    }

    public boolean addProductToSeller(String productName, double price, int sellerIndex, Category category, boolean specialPackaging, double packagingPrice) {
        return sellersList[sellerIndex].addProduct(productName, price, category, specialPackaging, packagingPrice);
    }

    public void addProductToBuyer(int buyerIndex, int sellerIndex, int productIndex, boolean includePackaging) {
        Product product = sellersList[sellerIndex].getProductByIndex(productIndex);
        buyersList[buyerIndex].addProductToCart(product, includePackaging);
    }

    public boolean isBuyerCartEmpty(int buyerIndex) {
        if (buyerIndex >= 0 && buyerIndex < buyerCount) {
            return buyersList[buyerIndex].isCartEmpty();
        }
        return true; // Return true if the buyer index is invalid
    }
    public boolean makePurchaseBuyerByIndex(int buyerIndex) {
        if (buyerIndex >= 0 && buyerIndex < buyerCount) {
            return buyersList[buyerIndex].purchaseCart();
        }
        return false;
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

    public StringBuffer sellersData() {
        Arrays.sort(sellersList, 0, sellerCount, Comparator.comparingInt(Seller::getProductCount).reversed());
        StringBuffer sellerList = new StringBuffer();
        for (int i = 0; i < sellerCount; i++) {
            sellerList.append("-------------------------------------------------------\n");
            sellerList.append(i + 1 + ") " + sellersList[i] + "\n");
            sellerList.append("    The items of that seller:\n");
            for (int j = 0; j < sellersList[i].getProductCount(); j++) {
                sellerList.append("    " + (j + 1) + ") " + sellersList[i].getProductByIndex(j) + "\n");
            }
        }
        sellerList.append("-------------------------------------------------------");
        return sellerList;
    }

    public StringBuffer productsByCategory(Category category) {
        StringBuffer productsList = new StringBuffer();
        productsList.append("Products in category: ").append(category.name()).append("\n");

        boolean foundProducts = false;

        for (Seller seller : sellersList) {
            if (seller != null) {
                Product[] products = seller.getProducts();
                for (int i = 0; i < seller.getProductCount(); i++) {
                    Product product = products[i];
                    if (product.getCategory() == category) {
                        productsList.append("Seller: ").append(seller.getUserName())
                                .append(" - Product: ").append(product.getName())
                                .append(" - Price: $").append(product.getPrice()).append("\n");
                        foundProducts = true;
                    }
                }
            }
        }

        if (!foundProducts) {
            productsList.append("No products found in this category.");
        }

        return productsList;

    }

    public boolean isAnyBuyersExists() {
        return buyerCount > 0;
    }

    public boolean isBuyerByIndexExists(int index) {
        return index >= 0 && index < buyerCount;
    }
    public int getBuyerOrderCount(int buyerIndex) {
        if (isBuyerByIndexExists(buyerIndex)) {
            return buyersList[buyerIndex].getOrderCount();
        }
        return 0;
    }
    public boolean isAnySellersExists() {
        return sellerCount > 0;
    }

    public boolean isSellerByIndexExists(int index) {
        return index >= 0 && index < sellerCount;
    }

    public boolean isSellerHasAnyProduct(int sellerIndex) {
        return sellersList[sellerIndex].getProductCount() > 0;
    }

    public boolean isSellerHasProductByIndex(int sellerIndex, int productIndex) {
        return sellersList[sellerIndex].getProductCount() > productIndex;
    }
    public boolean isProductNameExists(int sellerIndex, String productName) {
        return sellersList[sellerIndex].isProductNameExists(productName);
    }
    public boolean isProductWithSpecialPackage(int sellerIndex, int productIndex) {
        return sellersList[sellerIndex].isProductHasSpecialPackage(productIndex);
    }


}
