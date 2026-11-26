package shop.model;

public class Voucher {
    private final String code,description;;
    private final int percent;
    private final int minimumPurchase;
    private Voucher(Builder b){
        this.code=b.code;
        this.percent=b.percent;
        this.minimumPurchase=b.minimumPurchase;
        this.description=b.description;
    }

    public static class Builder{
        String code, description = "";
        int percent,minimumPurchase = 0;
        public Builder(String code, int percent){
            this.code=code;
            this.percent=percent;
        }

        public Builder minimumPurchase(int num){
            this.minimumPurchase=num;
            return this;
        }

        public Builder description(String desc){
            this.description=desc;
            return this;
        }

        public Voucher build(){
            if (percent < 1 || percent >50) throw new IllegalStateException("percent harus di atas 1 atau di bawah 50");
            return new Voucher(this);
        }
    }
}
