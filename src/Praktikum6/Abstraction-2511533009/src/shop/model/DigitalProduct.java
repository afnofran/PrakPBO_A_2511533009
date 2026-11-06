package shop.model;

import shop.discount.Discountable;

public class DigitalProduct extends Product implements Discountable{
    String downloadUrl;
    private static final int DIGITAL_DISCOUNT_PERCENT = 10;
    public DigitalProduct(String sku, String name, long price, String downloadUrl){
        super(sku, name, price);
        if (downloadUrl == null || downloadUrl == "") throw new NullPointerException(); 
        this.downloadUrl = downloadUrl;
    }

    @Override 
    public String category(){
         return "Digital";
    }

    public String getDownloadUrl(){
        return "Digital";
    }

    @Override
    public int discountPercent() {
        return DIGITAL_DISCOUNT_PERCENT;
    }

    @Override 
    public long getFinalPrice(){
        return applyDiscount(getPrice());
    }
}
