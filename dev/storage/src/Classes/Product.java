package Classes;

import java.util.HashMap;

public class Product {
    private HashMap<String,Item> items;
    private int quantityInStore;
    private int quantityInWarehouse;
    private int minimumQuantityForAlert;
    private Discount discount;
    private String productName;
    private String category;
    private String sub_category;
    private double size;


    public Product(Item firstItem) {
        this.items = new HashMap<>();
        addItem(firstItem);
        productName=firstItem.getName();
        category=firstItem.getCategory();
        sub_category=firstItem.getSub_category();
        size=firstItem.getSize();
    }
    public void set_minimum(int minimum){
        minimumQuantityForAlert=minimum;
    }

    public HashMap<String,Item> getItems() {
        return items;
    }

    public int getQuantityInStore() {
        return quantityInStore;
    }

    public void addItem(Item new_item) {
        if (new_item != null && !(items.containsKey(new_item.getItem_code()))) {

            items.put(new_item.getItem_code(),new_item);
            if (new_item.getStored() == ItemPlace.Store)
                quantityInStore++;
            if (new_item.getStored() == ItemPlace.Warehouse)
                quantityInWarehouse++;
        }

    }
//TODO CHANGE

    public void removeItem(Item del_item) {
        if (del_item != null && items.containsKey(del_item.getItem_code())) {
            items.remove(del_item.getItem_code());
            if (del_item.getStored() == ItemPlace.Store)
                quantityInStore--;
            if (del_item.getStored() == ItemPlace.Warehouse)
                quantityInWarehouse--;
        }

    }

    public int getQuantityInWarehouse() {
        return quantityInWarehouse;
    }


    public int getMinimumQuantityForAlert() {
        return minimumQuantityForAlert;
    }

    public int getTotalQuantity() {
        return quantityInStore + quantityInWarehouse;
    }


    //TODO CHANGE
    public void applyDiscount(Discount new_discount) {
        if (new_discount.isDiscountActive()) {
            for (Item item : items.values()) {
                double discountedPrice = item.getCostPrice() * (1 - new_discount.getDiscountRate());
                item.setSellingPrice(discountedPrice);
            }
        }
    }


    public void check_quantity(){
        if (getTotalQuantity() < minimumQuantityForAlert) {
            System.out.println("Alert: The total quantity of product '" + productName + "' is below the minimum threshold. Current total quantity: " + getTotalQuantity());
        }
    }

    public String getCategory() {
        return category;
    }

    public String getSubCategory() {
        return sub_category;
    }

    public double getSize() {
        return size;
    }

    public String getProductName() {
        return productName;
    }

    public Boolean getifDiscount(){
        if(discount==null)
            return false;
        return true;
    }

    public Discount getDiscount() {
        return discount;
    }
}
