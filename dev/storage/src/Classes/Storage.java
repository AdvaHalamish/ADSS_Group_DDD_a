package Classes;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Storage {
    private List<Product> allProducts;
    public int amount;
    public Storage(){
        allProducts=new ArrayList<>();
        amount=0;
    }

    public void deleteProduct (String name_product) {
        for (Product product : allProducts) {
            if (product.getProductName().equals(name_product)) {
                allProducts.remove(product);
                break;
            }
        }
    }
    public Product getProductByName(String name_product){
        Product getproduct=null;
        for (Product product : allProducts) {
            if (product.getProductName().equals(name_product)) {
                getproduct=product;
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
        amount++;
    }
     public void deleteItem(String name_code) {
        for (Product product : allProducts) {
            Item item = product.getItems().get(name_code);
            if (item != null) {
                product.removeItem(item);
                if (product.getItems().isEmpty()) {
                    allProducts.remove(product);
                }
                break;
            }
        }
        amount--;
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

    public List<Product> getAllProducts() {
        return allProducts;
    }

    public int getAmount() {
        return amount;
    }
}