package Praktikum4;

public class Debit extends RekeningTabungan{
    public Debit(String nomor, String nama, double saldoAwal, String pinAwal, double sukuBunga) {
        super(nomor, nama, saldoAwal, pinAwal, sukuBunga);
        setBatasTarik(500_000);
    }
}
