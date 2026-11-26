package shop.service;

/** Membuat nomor invoice berurutan: INV-1001, INV-1002, ... */
public class OrderNumberGenerator {
    private int counter = 1000;

    private static OrderNumberGenerator instance;
    public static OrderNumberGenerator getInstance(){
        if (instance == null) instance = new OrderNumberGenerator();
        return instance;
    }
    private OrderNumberGenerator() { }

    public String next() {
        counter++;
        return "INV-" + counter;
    }
}
