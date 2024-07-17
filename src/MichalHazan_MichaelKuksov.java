package src;

import src.utils.Category;
import src.utils.Manage;
import src.utils.Status;

import java.util.Scanner;

public class MichalHazan_MichaelKuksov {
    public static void main(String[] args) {
        MarketSystem system = new MarketSystem("M&M market");
        Scanner scanner = new Scanner(System.in);

        boolean exit = false;
        while (!exit) {
            System.out.println("Menu:");
            System.out.println("0. Exit");
            System.out.println("1. Add a Seller");
            System.out.println("2. Add a Buyer");
            System.out.println("3. Add a Product to Seller");
            System.out.println("4. Add a Product to Buyer");
            System.out.println("5. Pay for Buyer's Shopping Cart");
            System.out.println("6. Display All Buyers");
            System.out.println("7. Display All Sellers");
            System.out.println("8. Display All Products from a Category");
            System.out.println("9. Create a New Cart from Buyer's Cart History");
            System.out.print("Choose an option: ");

            int choice = getValidIntInput(scanner);

            switch (choice) {
                case 0 -> exit = true;
                case 1 -> addSeller(system, scanner);
                case 2 -> addBuyer(system, scanner);
                case 3 -> addProductToSeller(system, scanner);
                case 4 -> addProductToBuyer(system, scanner);
                case 5 -> payForBuyerCart(system, scanner);
                case 6 -> displayAllBuyers(system);
                case 7 -> displayAllSellers(system);
                case 8 -> displayProductsByCategory(system, scanner);
                case 9 -> createNewCartFromHistory(system, scanner);
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
        scanner.close(); // Close the scanner
    }

// Utility method to get valid integer input
    private static int getValidIntInput(Scanner scanner) {
        Status status;
        Manage manage = new Manage();
        do {
            System.out.println("Enter a number: ");
            String num = scanner.nextLine();
            status = manage.setInt(num);
            if (!status.equals(Status.SUCCESS)){
                System.out.println(status.getDescription());
            }
        }while (!status.equals(Status.SUCCESS));
        return manage.getNumInt();
    }
    // Utility method to get valid double input
    private static double getValidDoubleInput(Scanner scanner) {
        Status status;
        Manage manage = new Manage();
        do {
            System.out.println("Enter number: ");
            String num = scanner.nextLine();
            status = manage.setDouble(num);
            if (!status.equals(Status.SUCCESS)){
                System.out.println(status.getDescription());
            }
        }while (!status.equals(Status.SUCCESS));
        return manage.getNumDouble();
    }
    // Utility method to get valid double input
    private static String getValidStringInput(Scanner scanner){
        Status status;
        Manage manage = new Manage();
        do{
            String str = scanner.nextLine();
            status = manage.setString(str);
            if (!status.equals(Status.SUCCESS)){
                System.out.println(status.getDescription());
            }
        }while (!status.equals(Status.SUCCESS));
        return manage.getStr();
    }
    private static Category getValidCategory(Scanner scanner){
        Status status;
        Manage manage = new Manage();
        do{
            String category = scanner.nextLine();
            status = manage.setCategory(category);
            if (!status.equals(Status.SUCCESS)){
                System.out.println(status.getDescription());
            }
        }while (!status.equals(Status.SUCCESS));
        return manage.getCategory();
    }
    //--------case 1: Add Seller--------------
    private static void addSeller(MarketSystem system, Scanner scanner) {
        String sellerName;

        while (true) {
            System.out.print("Enter seller name: ");
            sellerName = getValidStringInput(scanner);
            if (!system.isUsernameTaken(sellerName)) {
                break;
            }
            System.out.println("Username already taken. Please choose a different name. ");
        }

        System.out.print("Enter seller password: ");
        String sellerPassword = getValidStringInput(scanner);

        if (system.addSeller(sellerName, sellerPassword)) {
            System.out.println("Seller added.");
        }
    }
    //--------case 2: Add Buyer--------------
    private static void addBuyer(MarketSystem system, Scanner scanner) {
        String buyerName;

        while (true) {
            System.out.print("Enter buyer name: ");
            buyerName = getValidStringInput(scanner);
            if (!system.isUsernameTaken(buyerName)) {
                break;
            }
            System.out.println("Username already taken. Please choose a different name.");
        }

        System.out.print("Enter buyer password: ");
        String buyerPassword = getValidStringInput(scanner);
        System.out.print("Enter street name: ");
        String streetName = getValidStringInput(scanner);
        System.out.print("Enter building number: ");
        int buildingNumber = getValidIntInput(scanner);
        System.out.print("Enter city: ");
        String city = getValidStringInput(scanner);
        System.out.print("Enter country: ");
        String country = getValidStringInput(scanner);

        if (system.addBuyer(buyerName, buyerPassword, streetName, buildingNumber, city, country)) {
            System.out.println("Buyer added.");
        }
    }
    //--------case 3: Add Product to Seller--------------
    private static void addProductToSeller(MarketSystem system, Scanner scanner) {
        if (!system.isAnySellersExists()) {
            System.out.println("There are no sellers yet. Please add seller first");
            return;
        }
        System.out.println("The lists of Sellers: ");
        System.out.println(system.generateStrSellersList());
        System.out.println("Enter number of seller: ");
        int indexSeller = getValidIntInput(scanner) -1;
        if (indexSeller >= 0 && !system.isSellerByIndexExists(indexSeller)) {
            System.out.println("Invalid index. Please try again.");
            return;
        }
        System.out.println("Enter Product Name: ");
        String productName = getValidStringInput(scanner);
        while (system.isProductNameExists(indexSeller, productName)) {
            System.out.println("Product name is taken, please choose another one: ");
            productName = getValidStringInput(scanner);
        }
        System.out.println("Enter product price: ");
        double productPrice = getValidDoubleInput(scanner);
        System.out.println("Select Category:");
        Category category = getCategory(scanner); //todo
        System.out.print("Is this a special package? (yes/no): ");
        String isSpecialPackageInput = scanner.nextLine();//todo
        boolean isSpecialPackage = isSpecialPackageInput.equalsIgnoreCase("yes");
        if (isSpecialPackage) {
            System.out.println("Enter special package price: ");
            double packagingPrice = getValidDoubleInput(scanner);
            system.addProductToSeller(productName, productPrice, indexSeller, category, isSpecialPackage, packagingPrice);
        } else {
            system.addProductToSeller(productName, productPrice, indexSeller, category);
        }

        System.out.println("Product added");
    }
    //--------case 4: Add Product to Buyer--------------
    private static void addProductToBuyer(MarketSystem system, Scanner scanner) {
        if (!system.isAnyBuyersExists()) {
            System.out.println("There are no buyers yet. Please add buyer first");
            return;
        }
        if (!system.isAnySellersExists()) {
            System.out.println("There are no sellers yet. Please add seller first");
            return;
        }
        System.out.println("The lists of Buyers: ");
        System.out.println(system.generateStrBuyersList());
        System.out.println("Enter number of buyer: ");
        int indexBuyer = getValidIntInput(scanner) -1;
        if (!system.isBuyerByIndexExists(indexBuyer)) {
            System.out.println("Invalid index. Please try again.");
            return;
        }
        System.out.println("The lists of Sellers: ");
        System.out.println(system.generateStrSellersList());
        System.out.println("Enter number of seller: ");
        int indexSeller = getValidIntInput(scanner) -1;
        if (!system.isSellerByIndexExists(indexSeller)) {
            System.out.println("Invalid index. Please try again.");
            return;
        }
        if (!system.isSellerHasAnyProduct(indexSeller)) {
            System.out.println("There are no items in this seller bag yet.");
            return;
        }
        System.out.println("The lists of Products: ");
        System.out.println(system.generateStrSellersProducts(indexSeller));
        System.out.println("Enter number of product: ");
        int indexItem = getValidIntInput(scanner) -1;
        if (!system.isSellerHasProductByIndex(indexSeller , indexItem)) {
            System.out.println("Invalid index. Please try again later.");
            return;
        }
        //isProductWithSpecialPackage
        boolean includePackaging = false;
        if (system.isProductWithSpecialPackage(indexSeller, indexItem)) {
            System.out.println("This product has special packaging for an additional $" + system.getPriceProductSpecialPackage(indexSeller, indexItem) + ". Do you want to include it? (yes/no): ");
            String response = scanner.nextLine().trim().toLowerCase();
            includePackaging = response.equals("yes");
        }
        system.addProductToBuyer(indexBuyer, indexSeller, indexItem, includePackaging);
        System.out.println("You Chose to add to " + system.getBuyerByIndex(indexBuyer).getUserName() + "'s cart: \n" + system.getProductNameByIndex(indexSeller, indexItem));

    }
    //--------case 5: Pay for Buyer's Shopping Cart--------------
    private static void payForBuyerCart(MarketSystem system, Scanner scanner) {
        if (!system.isAnyBuyersExists()) {
            System.out.println("There are no buyers yet. Please add buyer first");
            return;
        }
        System.out.println("The lists of Buyers: ");
        System.out.println(system.generateStrBuyersList());
        System.out.println("Enter number of buyer: ");
        int indexBuyer = getValidIntInput(scanner) -1;
        if (!system.isBuyerByIndexExists(indexBuyer)) {
            System.out.println("Invalid index. Please try again.");
            return;
        }
        if (system.isBuyerCartEmpty(indexBuyer)) {
            System.out.println("The cart is empty. No purchase can be made.");
            return;
        }
        System.out.println(system.generateStrBuyerCartDetailsByIndex(indexBuyer));
        system.makePurchaseBuyerByIndex(indexBuyer);
        System.out.println("purchase completed successfully");
    }
    //--------case 6: Display All Buyers--------------
    private static void displayAllBuyers(MarketSystem system) {
        System.out.println(system.buyersData());
    }
    //--------case 7: Display All Sellers--------------
    private static void displayAllSellers(MarketSystem system) {
        System.out.println(system.sellersData());
    }

    //--------case 8: Display All Products from a Category--------------
    private static void displayProductsByCategory(MarketSystem system, Scanner scanner) {

        if (!system.isAnySellersExists()) {
            System.out.println("There are no sellers yet. Please add a seller first.");
            return;
        }

        System.out.println("Select Category:");
        Category category = getCategory(scanner);

        System.out.println(system.productsByCategory(category));

    }

    //--------case 9: Create a New Cart from Buyer's Cart History--------------
    private static void createNewCartFromHistory(MarketSystem system, Scanner scanner) {

        if (!system.isAnyBuyersExists()) {
            System.out.println("There are no buyers yet. Please add a buyer first.");
            return;
        }

        System.out.println("List of Buyers:");
        System.out.println(system.generateStrBuyersList());


        System.out.print("Choose a buyer by number: ");
        int buyerIndex = getValidIntInput(scanner) - 1;

        if (system.getBuyerOrderCount(buyerIndex) == 0) {
            System.out.println("The selected buyer has no order history. Operation canceled.");
            return;
        }
        if (system.hasBuyerItems(buyerIndex)) {
            System.out.println("The current cart is not empty. Do you want to clear it and create a new one? (Y/N)");
            String clearCartChoice = scanner.nextLine().trim().toUpperCase();

            if (clearCartChoice.equals("Y")) {
                system.clearBuyerCart(buyerIndex);
            } else {
                System.out.println("Operation canceled.");
                return;
            }
        }

        System.out.println("List of Buyer's Cart History:");
        System.out.println(system.getBuyerCartHistory(buyerIndex));


        System.out.print("Choose a cart from history by number: ");
        int cartIndex = getValidIntInput(scanner) - 1;
        // Set the chosen cart from history as the current cart
        if (system.setBuyerCurrentCartFromHistory(buyerIndex, cartIndex)) {
            System.out.println("The cart has been successfully set as the current cart.");
        } else {
            System.out.println("Invalid cart index. Operation canceled.");
        }

    }

    ///////////////////////////////////////  GET CATEGORY  /////////////////////////////////////////

    public static Category getCategory(Scanner scanner) {
        System.out.println("Enter product category:");
        for (Category c : Category.values()) {
            System.out.println("- " + c);
        }

        Category category = null;
        while (category == null) {
            String input = getValidStringInput(scanner);
            if (!input.isEmpty()) {
                try {
                    category = Category.valueOf(input.toUpperCase());
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid category. Please enter again.");
                }
            } else {
                System.out.println("Write one of the name of the categories list above");
            }
        }
        return category;
    }



}
