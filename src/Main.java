import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);

        Rekening akunAktif = null;
        boolean isRunning =true;
        System.out.println("=== SISTEM PERBANKAN MINI ===");
        while (isRunning) {
            System.out.println("\nMenu Utama:");
            System.out.println("1. Buka Rekening Baru");
            System.out.println("2. Setor Tunai");
            System.out.println("3. Tark Tunai");
            System.out.println("4. Cek Informasi Rekening");
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
                    System.out.print("Masukkan No Rekening: ");
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
                        System.out.println("Rekening baru harus di isi dengan saldo awal Rp 50.000");
                        break;
                    }
                    akunAktif = new Rekening(no,nama,saldo);
                    break;
                case 2:
                    if (akunAktif == null){
                        System.out.println("Error: Mohon maaf, Anda belum memiliki nomor rekening");
                    } else {
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
                    if (akunAktif == null){
                        System.out.println("Error: Mohon maaf, Anda belum memiliki nomor rekening");
                    }else {
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
                    break;
                case 4:
                    if (akunAktif == null){
                        System.out.println("Error: Anda belum buka rekening!");
                    } else {
                        akunAktif.cekInformasi();
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
}