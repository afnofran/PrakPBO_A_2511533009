package shop.model;

/** Pelanggan. Versi awal: dibuat dengan constructor lima parameter. */
public class Customer {
    private final String name;
    private final String phone;
    private final String email;
    private final String address;
    private final String memberLevel;

    private Customer(Builder b) {
        this.name = b.name;
        this.phone = b.phone;
        this.email = b.email;
        this.address = b.address;
        this.memberLevel = b.memberLevel;
    }

    public String getName() { return name; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }
    public String getAddress() { return address; }
    public String getMemberLevel() { return memberLevel; }

    public static class Builder{
        
        private final String name;
        private final String phone;
        private String email = "";
        private String address = "";
        private String memberLevel = "REGULAR";
        public Builder(String name, String phone){
            if (name == null || name.isEmpty()) throw new IllegalStateException("Nama pelanggan wajib diisi");
            if (phone == null || phone.isEmpty()) throw new IllegalStateException("Nomor handphone pelanggan wajib diisi");
            if (!phone.startsWith("08")) throw new IllegalStateException("Nomor HP harus diawali 08");
            this.name=name;
            this.phone=phone;
        }

        public Builder email(String email){
            this.email = email;
            return this;
        }

        public Builder address(String address){
            this.address = address;
            return this;
        }

        public Builder memberLevel(String level){
            this.memberLevel=level;
            return this;
        }

        public Customer build(){
            if (name == null || name.isEmpty()) throw new IllegalStateException("Nama pelanggan wajib diisi");
            if (phone == null || phone.isEmpty()) throw new IllegalStateException("Nomor handphone pelanggan wajib diisi");
            if (!phone.startsWith("08")) throw new IllegalStateException("Nomor HP harus diawali 08");
            return new Customer(this);
        }
    }
}
