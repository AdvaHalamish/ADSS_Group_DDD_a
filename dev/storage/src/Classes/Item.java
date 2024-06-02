package Classes;

import java.time.LocalDate;

public class Item {

    private String Item_name;
    private double costPrice;
    private double sellingPrice;
    private String manufacturer;
    private String Item_code;

    private String category;
    private String sub_category;
    private double size;

    public ItemPlace stored;
    private LocalDate expirationDate;

    public ItemStatus status;


    /**
     * Constructs a new Classes.Item object with the given parameters.
     *
     * @param Item_name: the name of the Classes.Item
     * @param othercostPrice: the costPrice of the Classes.Item
     * sellingPrice: the default is costPrice of the Classes.Item
     * @param stored: the place stored of the Classes.Item
     * @param expirationDate: the expirationDate of the Classes.Item
     * @param manufacturer: the manufacturer of the Classes.Item
     * @param Item_code: the code of the Classes.Item
     * @param category: the category of the Classes.Item
     * @param sub_category: the sub-category of the Classes.Item
     * @param size: the size of the Classes.Item
     */
    public Item(String Item_name, double othercostPrice, String manufacturer, String Item_code, String category, String sub_category, double size, ItemPlace stored, LocalDate expirationDate, ItemStatus status) {
        this.Item_name = Item_name;
        this.costPrice = othercostPrice;
        this.sellingPrice = othercostPrice;
        this.manufacturer = manufacturer;
        this.Item_code = Item_code;
        this.category = category;
        this.sub_category = sub_category;
        this.size = size;
        this.stored = stored;
        this.expirationDate = expirationDate;
        this.status = status;
    }

    /**
     * Gets the name of the Classes.Item.
     *
     * Return the name of the Classes.Item
     */
    public String getName() {

        return Item_name;
    }
    /**
     * Gets the manufacturer of the Classes.Item.
     *
     * Return the manufacturer of the Classes.Item
     */
    public String getManufacturer() {

        return manufacturer;
    }

    /**
     * Returns a string representation of the Classes.Item object.
     *
     * Return a string representation of the Classes.Item object
     */
    @Override
    public String toString() {
        return  "Item_name='" + Item_name + '\'' +
                ", size='" + size + '\'' +
                ", sub category='" + sub_category + '\'' +
                ", category='" + category + '\'' +
                ", Item_code='" + Item_code + '\'' +
                ", expiration date='" + expirationDate ;
    }


    /**
     * Gets the code of the Classes.Item.
     *
     * Return the code of the Classes.Item
     */
    public String getItem_code() {

        return Item_code;
    }

    public ItemPlace getStored() {
        return stored;
    }

    /**
     * Gets the category of the Classes.Item.
     *
     * Return the category of the Classes.Item
     */
    public String getCategory() {

        return category;
    }

    /**
     * Gets the sub-category of the Classes.Item.
     *
     * Return: the sub-category of the Classes.Item
     */
    public String getSub_category() {

        return sub_category;
    }

    /**
     * Gets the size of the Classes.Item.
     *
     * Return: the size of the Classes.Item
     */
    public double getSize() {

        return size;
    }

    /**
     * update the place of the Classes.Item stored.
     *
     */
    public void set_place(ItemPlace new_place){
        stored=new_place;
    }
    /**
     * Method to check if the Classes.Item is expired
     *
     */
    public boolean isExpired() {
        if(LocalDate.now().isAfter(expirationDate)) {
            setStatus(ItemStatus.Expired);
            return true;
        }
        return false;
    }

    public void setStatus(ItemStatus status) {
        this.status = status;
    }

    public String getItem_name() {
        return Item_name;
    }

    public double getCostPrice() {
        return costPrice;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public ItemStatus getStatus() {
        return status;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

}
