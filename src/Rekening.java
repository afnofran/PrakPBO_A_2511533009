import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Locale;

public class Rekening {
    String nomorRekening;
    String namaPemiliki;
    double saldo;

    //Implementasis Asosiasi (1 to many)
    ArrayList<Transaksi> riwayatTransaksi;

    public Rekening(String nomor, String nama, double saldoAwal){
        nomorRekening = nomor;
        namaPemiliki = nama;
        saldo = saldoAwal;
        //Wajib menginisialisasi ArrayList di dalam constructor agar tidak NullPointerException
        this.riwayatTransaksi = new ArrayList<>();

        System.out.println("Rekening atas nama " + namaPemiliki + " berhasil dibuat dengan saldo  " + formatNumber(saldo));
    }

    public void setorTunai(double nominal){
        if (nominal > 0){
            saldo+=nominal;
            //Mrekam riwta
            String idTrx = "TRX-S-" + System.currentTimeMillis();
            Transaksi trxBaru = new Transaksi(idTrx,"Kredit",nominal);
            riwayatTransaksi.add(trxBaru);

            System.out.println("Setor tunai Rp " + nominal +" berhasil. Saldo saat ini: " + formatNumber(saldo));
        }else {
            System.out.println("Gagal: Nominal setor harus lebih dari 0!");
        }
    }

    public void cekInformasi(){
        System.out.println("--- INFO REKENING ---");
        System.out.println("No. Rekening : " + nomorRekening);
        System.out.println("Nama pemilik : " + namaPemiliki);
        System.out.println("Saldo Akhir  : " + formatNumber(saldo));
        System.out.println("----------------------");
    }

    public void tarikTunai(double nominal){
        if (nominal > saldo){
            System.out.println("Transaksi Gagal: Saldo tidak mencukupi. Saldo Anda: Rp[" + saldo+']');
        }
        else if (nominal <10000){
            System.out.println("Transaksi Gagal: Minimal nominal penarikan 10.000");
        }else {
            saldo-=nominal;

            //Mrekam riwta
            String idTrx = "TRX-T-" + System.currentTimeMillis();
            Transaksi trxBaru = new Transaksi(idTrx,"Debit",nominal);
            riwayatTransaksi.add(trxBaru);

            System.out.println("Penarikan dengan nominal Rp "+ formatNumber(nominal) + " berhasil! Sisa Saldo Anda: " + formatNumber(saldo));
        }
    }

    //Tugas 2 Praktikum 2
    public void cetakMutasi(){
        if (riwayatTransaksi.isEmpty()){
            System.out.println("Belum ada transaksi pada rekening ini");
            return;
        }
        System.out.println("=======");
        for (Transaksi transaksi : riwayatTransaksi)
            transaksi.cetakDetail();
        System.out.println("=======");

    }

    public String formatNumber(double num){
        return "Rp"+NumberFormat.getNumberInstance(Locale.GERMANY).format(num) +",00";
    }
}
