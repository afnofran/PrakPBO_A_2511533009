package Praktikum5;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        ArrayList<Rekening> rekenings = new ArrayList<>();
        Rekening akunAktif = null;
        boolean isRunning =true;
        System.out.println("=== SISTEM PERBANKAN MINI ===");
        while (isRunning) {
            System.out.println("\nMenu Utama:");
            System.out.println("1. Buka Praktikum5.Rekening Baru");
            System.out.println("2. Setor Tunai");
            System.out.println("3. Tark Tunai");
            System.out.println("4. Cek Informasi Praktikum5.Rekening");
            System.out.println("5. Ganti akun");
            System.out.println("6. Cetak Mutasi (Riwayat)");
            System.out.println("7. Simulasi Akhir Bulan (Khusus Tabungan)");
            System.out.println("8. Cetak Terbesar dan Terkecil");
            System.out.println("9. Tmapilkan Akumulasi");
            System.out.println("10. Ganti PIN");
            System.out.println("11. Simulasi Akhir Tahun");
            System.out.println("12. Transker ke rekening lain");
            System.out.println("0. Keluar");
            System.out.print("Pilih menu: ");
            if (!input.hasNextInt()){
                System.out.println("Error Harus Angka");
                input = new Scanner(System.in);
                continue;
            }
            int pilihan = input.nextInt();
            input.nextLine();
            switch (pilihan){
                case 1:
                    System.out.print("Masukkan No Praktikum5.Rekening: ");
                    String no = input.nextLine();
                    System.out.println();
                    System.out.print("Masukkan Nama Pemilik: ");
                    String nama = input.nextLine();
                    System.out.print("Masukkan Saldo Awal: ");
                    if (!input.hasNextInt()){
                        System.out.println("Error Harus Angka atau Nominal terlalu besar");
                        input = new Scanner(System.in);
                        continue;
                    }
                    double saldo = input.nextDouble();

                    if (saldo <= 50000){
                        System.out.println("Praktikum5.Rekening baru harus di isi dengan saldo awal Rp 50.000");
                        break;
                    }
                    input.nextLine();
                    System.out.print("Masukkan Pin Untuk Praktikum5.Rekening ini (6 digit): ");
                    if (!input.hasNextInt()){
                        System.out.println("pin harus erupa angka");
                        continue;
                    }
                    int pin = input.nextInt();
                    if (!validasiPin(String.valueOf(pin))){
                        System.out.println("Pin Tidak Boleh Berulang atau berurutan");
                        continue;
                    }
                    if (String.valueOf(pin).length() != 6){
                        System.out.println("PIN HARUS 6 DIGIT\nAkun Gagal dibuat");
                        continue;
                    }


                    System.out.println("Pilih Produk: 1. Tabungan Umum | 2. Gro Bisnis | 3. Praktikum5.Rekening VIP");
                    System.out.print("Pilihan:");
                    int opsiProduk = input.nextInt();
                    if (opsiProduk == 1){
                        System.out.print("Masukkan Bunga (Persen): ");
                        double sukuBunga = input.nextDouble();

                        akunAktif = new RekeningTabungan(no,nama,saldo,String.valueOf(pin),sukuBunga);

                    }else if(opsiProduk == 2){
                        System.out.print("Masukkan Limit Pinjaman: 1");
                        double limitPinjaman = input.nextDouble();
                        akunAktif = new RekeningGiro(no,nama,saldo,String.valueOf(pin),limitPinjaman);
                    } else if (opsiProduk == 3) {
                        System.out.println("Selamat, Praktikum5.Rekening Anda VIP Nih Bonus 100.000 ");
                        akunAktif = new RekeningVIP(no,nama,saldo,String.valueOf(pin));
                        akunAktif.setorTunai(100_000);
                    } else{
                        System.out.println("Pilihan tidak valid (tidak ada di menu)");
                        continue;
                    }

                    rekenings.add(akunAktif);
                    break;
                case 2:
                    if (akunAktif == null){
                        System.out.println("Error: Mohon maaf, Anda belum memiliki nomor rekening");
                    } else {
                        if (akunAktif.isTerblokir()) continue;
                        System.out.print("Masukkan nominal setor: ");

                        if (!input.hasNextInt()){
                            System.out.println("Error Harus Angka");
                            input = new Scanner(System.in);
                            continue;
                        }
                        double setor = input.nextDouble();
                        if (setor < 10_000){
                            System.out.println("");
                            continue;
                        }
                        akunAktif.setorTunai(setor);
                    }
                    break;
                case 3:
                    //Latihan/Tugas Jawaban:
                    System.out.println("Masukkan PIN sebelum melakukan Tariktunai");
                    System.out.print("Masukkan PIN: ");
                    String inputPin = input.nextLine();
                    if (!akunAktif.ontentikasi(inputPin)) {
                        System.out.println("Akses Ditolak: PIN yang Anda masukkan salah!");
                    }else{
                        if (akunAktif == null){
                            System.out.println("Error: Mohon maaf, Anda belum memiliki nomor rekening");
                        }else {
                            if (akunAktif.isTerblokir()) continue;
                            System.out.print("Mauskkan nominal: ");
                            if (!input.hasNextInt()){
                                System.out.println("Error Harus Angka");
                                input = new Scanner(System.in);
                                continue;
                            }
                            double tarik = input.nextDouble();
                            if (tarik < 10_000){
                                System.out.println("Minimal Nominal Tarik adalah 10.000");
                                continue;
                            }
                            akunAktif.tarikTunai(tarik);
                        }
                    }
                    break;
                case 4:
                    if (akunAktif == null){
                        System.out.println("Error: Anda belum buka rekening!");
                    } else {
                        if (akunAktif.isTerblokir()) continue;
                        System.out.println(rekenings.size() );
                        akunAktif.cekInformasi();
                    }
                    break;
                case 5:
                    if (rekenings.isEmpty()){
                        System.out.println("Error: Belum ada rekening yang pernah dibuat");
                        continue;
                    }
                    System.out.print("Masukkan No Praktikum5.Rekening: ");
                    String nomor = input.nextLine();
                    System.out.print("Masukkan Nama Pemilik: ");
                    String namauser = input.nextLine();
                    boolean found = false;
                    for (Rekening loopedRek : rekenings){
                        if (nomor.equalsIgnoreCase(loopedRek.getNomorRekening()) && namauser.equalsIgnoreCase(loopedRek.getNamaPemiliki())) {
                            akunAktif=loopedRek;
                            System.out.println("Akun Ditemukan!!");
                            found = true;
                            break;
                        }
                    }
                    if (!found){
                        System.out.println("akun tidak ditmeukan");
                    }
                    break;
                case 6:
                    if (akunAktif.isTerblokir()) continue;
                    System.out.println("Masukkan PIN sebelum melakukan Tariktunai");
                    System.out.print("Masukkan PIN: ");
                    inputPin = input.nextLine();
                    //Tugas 2 Praktikum 2
                    if (!akunAktif.ontentikasi(inputPin)) {
                        System.out.println("Akses Ditolak: PIN yang Anda masukkan salah!");
                    }else{
                        if (akunAktif == null){
                            System.out.println("Error: Mohon maaf, Anda belum memiliki nomor rekening");
                        }else {
                            akunAktif.cetakMutasi();
                        }
                    }
                    break;
                case 7:
                    if (akunAktif == null) {
                        System.out.println("belum ada akun");
                        break;
                    }
                    if (!(akunAktif instanceof RekeningTabungan)){
                        System.out.println("Gagal: Fitur bunga akhir bulan hanya berlaku untuk Praktikum5.Rekening Tabungan.");
                        break;
                    }

                    ((RekeningTabungan) akunAktif).tambahBungaAkhirBulan();
                    break;
                    //Tugas 2 Praktikum 2
                case 8:
                    if (akunAktif == null){
                        System.out.println("Belum buka akun");

                    }else
                        if (akunAktif.isTerblokir()) continue;
                        akunAktif.cetakTerbesarTerkecil();
                    break;
                case 9:
                    if (akunAktif == null) {
                        System.out.println("Silahkan buka akun terlebih dahulu");
                    }else{
                        if (akunAktif.isTerblokir()) continue;
                        akunAktif.tampilkanAkumulasi();

                    }
                    break;
                case 10:
                    if (akunAktif == null) {
                        System.out.println("Silahkan buka akun terlebih dahulu");
                    }else{
                        if (akunAktif.isTerblokir()) continue;
                        System.out.print("Silahkan masukkan PIN baru:");
                        String pinBaru = input.nextLine();
                        akunAktif.gantiPin(pinBaru);
                    }

                    break;
                case 11:
                    if (akunAktif == null)
                        System.out.println("Silahkan buat ");
                    else
                        if (!(akunAktif instanceof RekeningTabungan)){
                            System.out.println("AYE YOW INI BUKAN REKENING TABUNGAN");
                            break;
                        }
                        RekeningTabungan rt = (RekeningTabungan) akunAktif;
                        if (!rt.isAkhirTahun()){
                            System.out.println("AYE YOW BELUM AKHIR TAHUN");
                            break;
                        }
                        if (!rt.isOrangKaya()){
                            System.out.println("Tabungan mu dibawah 10.000.000 Tidak bisa dapet bonus akhir tahunaowaowkawokawokw");
                        }
                        rt.tambahBungaAkhirTahun();
                        rt.resetBulan();
                        break;
                case 12:
                    if (akunAktif == null)
                        System.out.println("Silahkan buat akun");
                    else
                        // Minta Nomor Praktikum5.Rekening Tujuan
                        System.out.print("Masukkan Nomor Praktikum5.Rekening Tujuan: ");
                    String noRekTujuan = input.nextLine();

                    // Cari nomor rekening tujuan di daftarRekening
                    Rekening akunTujuan = null;
                    for (Rekening rek : rekenings) {
                        if (rek.getNomorRekening().equalsIgnoreCase(noRekTujuan)) {
                            akunTujuan = rek;
                            break;
                        }
                    }

                    // Jika rekening tujuan ditemukan
                    if (akunTujuan != null) {
                        System.out.print("Masukkan Nominal Transfer: ");
                        double nominalTransfer = input.nextDouble();
                        input.nextLine(); // Consume newline

                        System.out.print("Masukkan PIN Anda: ");
                        String pinInput = input.nextLine();

                        // Panggil method transfer
                        akunAktif.transfer(akunTujuan, nominalTransfer, pinInput);
                    } else {
                        System.out.println("Praktikum5.Transaksi Gagal: Praktikum5.Rekening tujuan tidak ditemukan!");
                    }
                    break;
                case 0:
                    isRunning = false;
                    System.out.println("Sistem ditutup. Terima kasih!");
                    break;
                default:
                    System.out.println("Pilihan tidak valid");
            }
        }
    }

    public static boolean validasiPin(String pin){
        char[] pinCharArray = pin.toCharArray();
        //GA BOLEH: XXXXXX
        //GA BOLEH: 123456 / 654321
        // 123456 = 21
        // 234567 = 27
        // 345678 = 33
        //Gaboleh 6 angka sama
        char first = pinCharArray[0];
        int firstInteger = pinCharArray[0];
        int berurutValidator = 0;
        for (int i = 1;i< pinCharArray.length;i++){
            if (firstInteger+i==pinCharArray[i] || firstInteger-i==pinCharArray[i]){
//                System.out.println("BERURUT " + i);
                berurutValidator++;
            }
        }
        if (berurutValidator == 5) return false;
        for (int i=1;i<pinCharArray.length;i++){
            if (first == pinCharArray[i]) {
//                System.out.println("BERULANG");
                return false;
            }
        }
        return true;
    }
}