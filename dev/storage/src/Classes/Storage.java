package Classes;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Storage {
    private List<Product> allProducts;
    private static Storage instance;

    public Storage() {
        allProducts = new ArrayList<>();
    }
    public static Storage getInstance() {
        if (instance == null) {
            instance = new Storage();
        }
        return instance;
    }
    public Product getProductByName(String name_product) {
        Product getproduct = null;
        for (Product product : allProducts) {
            if (product.getProductName().equals(name_product)) {
                getproduct = product;
            }
        }
        return getproduct;
    }

    public void insertItem(Item newItem) {
        Product existingProduct = null;
        for (Product product : allProducts) {
            if (product.getCategory().equals(newItem.getCategory()) &&
                    product.getSubCategory().equals(newItem.getSub_category()) &&
                    product.getSize() == newItem.getSize()) {
                existingProduct = product;
                break;
            }
        }

        if (existingProduct != null) {
            existingProduct.addItem(newItem);
        } else {

            Product newProduct = new Product(newItem);
            newProduct.addItem(newItem);
            allProducts.add(newProduct);
        }
    }
     public boolean removeItem(String name_code, ItemStatus status) {
        for (Product product : allProducts) {
            Item item = product.getItems().get(name_code);
            if (item != null) {
                product.removeItem(item,status);
                return true;
            }
        }
         return false;
     }

    public List<Product> getProductsBySubCategory(String subCategory) {
        return allProducts.stream()
                .filter(product -> product.getSubCategory().equalsIgnoreCase(subCategory))
                .collect(Collectors.toList());
    }

    public List<Product> generateCategoryReport(String category) {
        return allProducts.stream()
                .filter(product -> product.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    public List<Item> generateExpiredProductsReport() {
        return allProducts.stream()
                .flatMap(product -> product.getItems().values().stream())  // שינוי כאן
                .filter(Item::isExpired)
                .collect(Collectors.toList());
    }

    public List<Item> generateDefectiveProductsReport() {
        return allProducts.stream()
                .flatMap(product -> product.getItems().values().stream())  // שינוי כאן
                .filter(item -> item.getStatus() == ItemStatus.Defective)
                .collect(Collectors.toList());
    }

    // Generate report for products below the minimum quantity
    public List<Product> generateBelowMinimumReport() {
        return allProducts.stream()
                .filter(product -> product.getTotalQuantity() < product.getMinimumQuantityForAlert())
                .collect(Collectors.toList());
    }

    // Apply discount to specific category or products
    public void applyDiscountToCategory(String category, Discount discount) {
        for (Product product : allProducts) {
            if (product.getCategory().equalsIgnoreCase(category)) {
                product.applyDiscount(discount);
            }
        }
    }

    public void applyDiscountToProduct(String productName, Discount discount) {
        for (Product product : allProducts) {
            if (product.getProductName().equalsIgnoreCase(productName)) {
                product.applyDiscount(discount);
            }
        }
    }

    public List<Product> getAllProducts() {
        return allProducts;
    }

    public List<Product> getProductsByCategory(String category) {
        return allProducts.stream()
                .filter(product -> product.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    public List<Item> getItemsByStatus(ItemStatus status) {
        List<Item> itemsByStatus = new ArrayList<>();
        for (Product product : allProducts) {
            for (Item item : product.getItems().values()) {
                if (item.getStatus() == status) {
                    itemsByStatus.add(item);
                }
            }
        }
        return itemsByStatus;
    }

    public List<Item> getItemsByPlace(ItemPlace place) {
        List<Item> itemsByPlace = new ArrayList<>();
        for (Product product : allProducts) {
            for (Item item : product.getItems().values()) {
                if (item.getStored() == place) {
                    itemsByPlace.add(item);
                }
            }
        }
        return itemsByPlace;
    }

    public Item getItemByCode(String itemCode) {
        for (Product product : allProducts) {
            for (Item item : product.getItems().values()) {
                if (item.getItem_code().equals(itemCode)) {
                    return item;
                }
            }
        }
        return null; // Item not found
    }

    public int TotalQuantity() {
        int sum = 0;
        for (Product product : allProducts) {
            sum+=product.getTotalQuantity();
        }
        return sum;
    }
    public int TotalQuantityInStore() {
        int sum = 0;
        for (Product product : allProducts) {
            sum+=product.getQuantityInStore();
        }
        return sum;
    }
    public int TotalQuantityInWareHouse() {
        int sum = 0;
        for (Product product : allProducts) {
            sum+=product.getQuantityInWarehouse();
        }
        return sum;
    }
    public void setMinimumQuantityForProduct(String productName, int minimumQuantity) {
        Product product = getProductByName(productName);
        if (product != null) {
            product.set_minimum(minimumQuantity);
        } else {
            System.out.println("Product not found.");
        }
    }

}