import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Locale;

public class Rekening {
    String nomorRekening;
    String namaPemiliki;

    double saldo;
    double saldoAwal;

    double pengeluaranTerbesar = 0,pengeluaranTerkecil = 0,pemasukanTerbesar = 0,pemasukanTerkecil= 0;

    //Implementasis Asosiasi (1 to many)
    ArrayList<Transaksi> riwayatTransaksi;

    public Rekening(String nomor, String nama, double saldoAwal){
        nomorRekening = nomor;
        namaPemiliki = nama;
        saldo = saldoAwal;
        this.saldoAwal=saldoAwal;
        //Wajib menginisialisasi ArrayList di dalam constructor agar tidak NullPointerException
        this.riwayatTransaksi = new ArrayList<>();

        System.out.println("Rekening atas nama " + namaPemiliki + " berhasil dibuat dengan saldo  " + formatNumber(saldo));
    }

    public void tampilkanAkumulasi(){
        double totalSetor = 0;
        double totalTarik = 0;
        for (Transaksi transaksi : riwayatTransaksi){
            if (transaksi.jenis == "Kredit") {
                totalSetor+=transaksi.nominal;
            } else if (transaksi.jenis == "Debit") {
                totalTarik +=transaksi.nominal;
            }
        }

        System.out.println("Totall Setor: " + totalSetor);
        System.out.println("Total TAIRK: " + totalTarik);
        System.out.println("Akumulasi: " + (totalSetor - totalTarik));
        System.out.println("Saldo Sebelumnya: " + formatNumber(saldoAwal));
        System.out.println("Saldo saat ini: " + formatNumber(saldo));
    }

    public void setorTunai(double nominal){
        if (nominal > 0){
            if (nominal > pemasukanTerbesar){
                pemasukanTerbesar = nominal;
            } else if (nominal < pemasukanTerbesar) {
                pemasukanTerkecil = nominal;
            }
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
            if (nominal > pengeluaranTerbesar){
                pengeluaranTerbesar = nominal;
            } else if (nominal < pengeluaranTerbesar) {
                pengeluaranTerkecil = nominal;
            }
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
        for (int i = riwayatTransaksi.size() -1;i>riwayatTransaksi.size() - 3;i--){
            riwayatTransaksi.get(i).cetakDetail();
        }
//        for (Transaksi transaksi : riwayatTransaksi)
        System.out.println("=======");

    }

    public void cetakTerbesarTerkecil(){
        System.out.println("Pemasukan Terbesar: " + pemasukanTerbesar);
        System.out.println("Pemasukan Terkecil: " + pemasukanTerkecil);
        System.out.println("pengeluaran Terbesar: " + pengeluaranTerbesar);
        System.out.println("pengeluaran Terkecil: " + pengeluaranTerkecil);
    }

    public String formatNumber(double num){
        return "Rp"+NumberFormat.getNumberInstance(Locale.GERMANY).format(num) +",00";
    }
}
