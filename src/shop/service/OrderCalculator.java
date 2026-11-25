package shop.service;

import shop.discount.DiscountStrategy;
import shop.model.Order;
import shop.model.Shippable;

public class OrderCalculator {
    // int goldPercent;
    long ratePerKg;
    public OrderCalculator(long ratePerKg){
        // this.goldPercent=goldPercent;
        this.ratePerKg=ratePerKg;
    }
    public Bill calculate(Order order, DiscountStrategy discountStrategy){
        // [R1] Mendata subtotal
        long subtotal = 0;

        // [R3] Menghitung total berat
        int grams = 0;
        for (Order.Line line : order.lines()) {
            subtotal += line.total();
            if (line.product() instanceof Shippable item) {
                grams += item.getWeightGram() * line.qty();
            }
        }

        // [R2] Menghitung discount
        long discount = discountStrategy.discountFor(subtotal);

        // [R4] Menghitung harga shipping
        long shipping = grams == 0 ? 0 : ((grams + 999) / 1000) * ratePerKg;
        // [R5]  Menghitung total harga
        long total = subtotal - discount + shipping;
        return new Bill(subtotal, discount, shipping, total);
    }
}
