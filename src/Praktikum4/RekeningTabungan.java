package Praktikum4;

public class RekeningTabungan extends Rekening{
    private double sukuBunga;


    public RekeningTabungan(String nomor, String nama, double saldoAwal, String pinAwal, double sukuBunga) {
        super(nomor, nama, saldoAwal, pinAwal);
        this.sukuBunga=sukuBunga;
    }

    int totalBulan = 0;
    public void tambahBungaAkhirBulan(){
        double nominalBunga = saldo + (sukuBunga / 100);
        saldo += nominalBunga;

        String idTrx = "TRX-B-" + System.currentTimeMillis();
        riwayatTransaksi.add(new Transaksi(idTrx,"Bunga", nominalBunga));

        System.out.println("Bunga " + sukuBunga + "% berasil ditambahkan: Rp" + nominalBunga);
        totalBulan++;
        System.out.println("Pada bulan ke-" + totalBulan);
    }

    public boolean isAkhirTahun(){
        return totalBulan >= 12;
    }
    public void resetBulan(){
        totalBulan = 0;
    }
    public boolean isOrangKaya(){
        return saldo > 10_000_000;
    }

    public void tambahBungaAkhirTahun(){
        double nominalBunga = saldo + (sukuBunga / 100);
        saldo += nominalBunga;

        String idTrx = "TRX-B-" + System.currentTimeMillis();
        riwayatTransaksi.add(new Transaksi(idTrx,"Bunga", nominalBunga));

        System.out.println("Bunga " + sukuBunga + "% berasil ditambahkan: Rp" + nominalBunga);
    }
}
