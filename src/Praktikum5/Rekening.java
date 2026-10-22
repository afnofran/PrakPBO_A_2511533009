package Praktikum5;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class Rekening {
    private String nomorRekening;
    private String namaPemiliki;
    private String pin;

    private boolean terblokir;

    protected double saldo;
    private double saldoAwal;

    private double pengeluaranTerbesar = 0,pengeluaranTerkecil = 0,pemasukanTerbesar = 0,pemasukanTerkecil= 0;

    //Implementasis Asosiasi (1 to many)
    protected ArrayList<Transaksi> riwayatTransaksi;

    public Rekening(String nomor, String nama, double saldoAwal, String pinAwal){
        nomorRekening = nomor;
        namaPemiliki = nama;
        saldo = saldoAwal;
        this.terblokir = false;
        this.saldoAwal=saldoAwal;
        if (pinAwal.length() == 6){
            this.pin=pinAwal;
        }else{
            System.out.println("Peringatan: PIN harus 6 digit, Menggunakan PIN default 123456");
            this.pin="123456";
        }


        //Wajib menginisialisasi ArrayList di dalam constructor agar tidak NullPointerException
        this.riwayatTransaksi = new ArrayList<>();

        System.out.println("Praktikum5.Rekening atas nama " + namaPemiliki + " berhasil dibuat dengan saldo  " + formatNumber(saldo));
    }

    public String getNomorRekening(){
        return this.nomorRekening;
    }
    public String getNamaPemiliki(){
        return namaPemiliki;
    }

    int pinSalah=0;
    public boolean ontentikasi(String inputPin){
        if (!this.pin.equals(inputPin)) pinSalah+=1;
        if (this.pinSalah == 3){
            this.terblokir = true;
            System.out.println("akun Anda terblokir");
        }
        return this.pin.equals(inputPin);
    }
    public boolean isTerblokir(){
        if (terblokir) System.out.println("Akun anda terblokir");
        return terblokir;
    }
    public void gantiPin(String input){
        if (input.equals(pin)){
            System.out.println("Error: PIN TIDAK BOLEH SAMA");
            return;
        }
        this.pin=input;
    }


    public void tampilkanAkumulasi(){
        double totalSetor = 0;
        double totalTarik = 0;
        for (Transaksi transaksi : riwayatTransaksi){
            if (transaksi.getJenis() == "Kredit") {
                totalSetor+=transaksi.getNominal();
            } else if (transaksi.getJenis() == "Praktikum5.Debit") {
                totalTarik +=transaksi.getNominal();
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
        System.out.println("No. Praktikum5.Rekening : " + nomorRekening);
        System.out.println("Nama pemilik : " + namaPemiliki);
        System.out.println("Saldo Akhir  : " + formatNumber(saldo));
        System.out.println("----------------------");
    }

    double batasTarik = Integer.MAX_VALUE;
    public void setBatasTarik(double nominal){
        this.batasTarik=nominal;
    }
    public boolean diAtasBatas(double nominal){
        return nominal >= batasTarik;
    }
    public boolean tarikTunai(double nominal){
        if (nominal > saldo){
            System.out.println("Praktikum5.Transaksi Gagal: Saldo tidak mencukupi. Saldo Anda: Rp[" + saldo+']');
            return false;
        }
        if (diAtasBatas(nominal)){
            System.out.println("Tidak bisa melakukan penarikan di atas limit tarik (500.000");
            return false;
        }
        else if (nominal <10000){
            System.out.println("Praktikum5.Transaksi Gagal: Minimal nominal penarikan 10.000");
            return false;
        }else {
            saldo-=nominal;
            if (nominal > pengeluaranTerbesar){
                pengeluaranTerbesar = nominal;
            } else if (nominal < pengeluaranTerbesar) {
                pengeluaranTerkecil = nominal;
            }
            //Mrekam riwta
            String idTrx = "TRX-T-" + System.currentTimeMillis();
            Transaksi trxBaru = new Transaksi(idTrx,"Praktikum5.Debit",nominal);
            riwayatTransaksi.add(trxBaru);

            System.out.println("Penarikan dengan nominal Rp "+ formatNumber(nominal) + " berhasil! Sisa Saldo Anda: " + formatNumber(saldo));
            return true;
        }
    }

    //Tugas 2 Praktikum 2
    public void cetakMutasi(){
        if (riwayatTransaksi.isEmpty()){
            System.out.println("Belum ada transaksi pada rekening ini");
            return;
        }
        System.out.println("=======");
        for (int i = riwayatTransaksi.size() - 1;i<riwayatTransaksi.size();i--){

            riwayatTransaksi.get(i).cetakDetail();
        }
//        for (Praktikum5.Transaksi transaksi : riwayatTransaksi)
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

    public void transfer(Rekening tujuan, double nominal, String pinInput) {
        // Langkah 1: Autentikasi PIN
        if (!this.pin.equals(pinInput)) { // Asumsi atribut PIN dinamai 'pin' atau gunakan getter
            System.out.println("Praktikum5.Transaksi Gagal: PIN yang Anda masukkan salah!");
            return;
        }
        if (this.getNomorRekening().equals(tujuan.getNomorRekening())) {
            System.out.println("Praktikum5.Transaksi Gagal: Tidak dapat melakukan transfer ke rekening sendiri!");
            return;
        }

        boolean suksesTarik = this.tarikTunai(nominal);

        if (suksesTarik) {
            tujuan.setorTunai(nominal);
            System.out.println("Transfer sebesar Rp" + nominal + " ke rekening " + tujuan.getNomorRekening() + " BERHASIL!");
        } else {
            System.out.println("Transfer GAGAL dialokasikan.");
        }
    }
}
