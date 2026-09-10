import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

public class Rekening {
    String nomorRekening;
    String namaPemiliki;
    double saldo;

    public Rekening(String nomor, String nama, double saldoAwal){
        nomorRekening = nomor;
        namaPemiliki = nama;
        saldo = saldoAwal;
        System.out.println("Rekening atas nama " + namaPemiliki + " berhasil dibuat dengan saldo  " + formatNumber(saldo));
    }

    public void setorTunai(double nominal){
        if (nominal > 0){
            saldo+=nominal;
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
            System.out.println("Penarikan dengan nominal Rp "+ formatNumber(nominal) + " berhasil! Sisa Saldo Anda: " + formatNumber(saldo));
        }
    }

    public String formatNumber(double num){
        return NumberFormat.getCurrencyInstance().format(num);
    }
}
