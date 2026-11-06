package shop.model;

import shop.shipping.Shippable;

public class PhysicalProduct extends Product implements Shippable{
    int weightGrams;
    public PhysicalProduct(String sku, String name, long price, int weightGrams) {
        super(sku, name, price);
        if (weightGrams <= 0 ) throw new IllegalArgumentException(new Throwable("Berat tidak boleh negatif"));
        this.weightGrams=weightGrams;
    }

    @Override 
    public int getWeightGrams(){
        return this.weightGrams;
    }
    
    @Override 
    public String category(){
        return "Fisik";
    }

    
}
