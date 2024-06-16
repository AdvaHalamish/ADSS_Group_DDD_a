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
    private ProductStatus status;
    private double size;
    private double cost_price;
    private double selling_price;



    public Product(Item firstItem) {
        this.items = new HashMap<>();
        addItem(firstItem);
        productName=firstItem.getName();
        category=firstItem.getCategory();
        sub_category=firstItem.getSub_category();
        size=firstItem.getSize();
        status=ProductStatus.InStorage;
        if(firstItem.stored.equals(ItemPlace.Warehouse)) {
            quantityInStore = 0;
            quantityInWarehouse = 1;
        } else if (firstItem.stored.equals(ItemPlace.Store)) {
            quantityInStore = 1;
            quantityInWarehouse = 0;
        }
        else {
            quantityInStore = 0;
            quantityInWarehouse = 0;
        }
        cost_price=firstItem.getCostPrice();
        selling_price=firstItem.getSellingPrice();
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
    public void removeItem(Item item, ItemStatus Itemstatus) {
        if (item != null && items.containsKey(item.getItem_code())) {
            item.setStatus(Itemstatus); // Change status to "Removed"
            // Update quantity and amounts in other classes accordingly
            if (item.getStored() == ItemPlace.Store) {
                quantityInStore--;
            } else if (item.getStored() == ItemPlace.Warehouse) {
                quantityInWarehouse--;
            }
        }
        check_quantity();
        if(getTotalQuantity()<=0){
            status=ProductStatus.NotinStorage;
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


    public void applyDiscount(Discount new_discount) {
        if (new_discount.isDiscountActive()) {
            for (Item item : items.values()) {
                if(item.getStatus().equals(ItemStatus.Available)) {
                    double discountedPrice = item.getCostPrice() * (1 - new_discount.getDiscountRate());
                    item.setSellingPrice(discountedPrice);
                }
            }
            discount=new_discount;
            selling_price=cost_price*(1-new_discount.getDiscountRate());
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


    public Discount getDiscount() {
        return discount;
    }

    public ProductStatus getStatus() {
        return status;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Product: ").append(productName).append("\n");
        sb.append("Category: ").append(category).append("\n");
        sb.append("Sub-category: ").append(sub_category).append("\n");
        sb.append("Size: ").append(size).append("\n");
        sb.append("Quantity in Store: ").append(quantityInStore).append("\n");
        sb.append("Quantity in Warehouse: ").append(quantityInWarehouse).append("\n");
        sb.append("Minimum Quantity for Alert: ").append(minimumQuantityForAlert).append("\n");
        sb.append("Price: ").append(selling_price).append("\n");

        if (discount != null && discount.isDiscountActive()) {
            sb.append("Discount: ").append(discount.getDiscountRate()).append("\n");
        } else {
            sb.append("Discount: Not Active\n");
        }

        sb.append("Status: ").append(status).append("\n");
        return sb.toString();
    }
}
