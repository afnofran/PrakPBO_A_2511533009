    package shop.demo;

    import shop.model.Voucher;

    public class VoucherDemo {
        public static void main(String[] args) {
            Voucher v1 = new Voucher.Builder("hitherelmao", 20).description("ini voucer").build();
            System.out.println("Object v1: "+v1.toString());
            Voucher v2 = new Voucher.Builder(null, 0).build();
            System.out.println("Object v2: "+v2.toString());
        }
    }
