package shop.payment;

import shop.exception.PaymentFailedException;

public class QrisPayment implements PaymentMethod{
    // long balance;
    public QrisPayment(){
    }

    @Override
    public String name() {
        // TODO Auto-generated method stub
        return "QRIS";
    }

    @Override
    public void pay(long amount) throws PaymentFailedException {
        if (amount > 10_000_000) throw new PaymentFailedException("transaksi QRIS maksimal Rp10.000.000");
        // balance-=amount;
    }

}
