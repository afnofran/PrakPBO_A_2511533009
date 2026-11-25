package shop.testdouble;

import shop.exception.PaymentFailedException;
import shop.payment.PaymentMethod;

public class FakePaymentMethod implements PaymentMethod{

    long lastAmount;
    int callCount=0;
    @Override
    public String name() {
        // TODO Auto-generated method stub
        return "Fake";
    }

    @Override
    public void pay(long amount) throws PaymentFailedException {
        lastAmount=amount;
        callCount++;
    }

    public long getLastAmount(){
        return this.lastAmount;
    }

    public int getCallCount(){
        return callCount;
    }

}
