package adapter.presenter;

import domain.entity.Transaction;
import domain.entity.TransactionType;
import java.util.List;

public class FinancePresenter {
    private String format(Transaction transaction) {
        String type = transaction.getType() == TransactionType.INCOME ? "Pemasukan" : "Pengeluaran";
        return String.format("%d | %s | Rp %d | %s",
                transaction.getId(), transaction.getDescription(), transaction.getAmount(), type);
    }

    private void printList(List<Transaction> list, String header, String emptyMessage) {
        System.out.println(header);
        if (list == null || list.isEmpty()) {
            System.out.println(emptyMessage);
        } else {
            for (Transaction transaction : list) {
                System.out.println(format(transaction));
            }
        }
    }

    public void showTransactions(List<Transaction> list, long balance) {
        printList(list, "Daftar Transaksi:", "- Belum ada transaksi!");
        System.out.printf("Saldo: Rp %d%n", balance);
    }

    public void showSearchResults(List<Transaction> list, String keyword) {
        printList(list, "Hasil Pencarian: \"" + keyword + "\"", "- Transaksi tidak ditemukan!");
    }

    public void showSortedTransactions(List<Transaction> list) {
        printList(list, "Daftar Transaksi (Terurut):", "Tidak ada transaksi.");
    }

    public void showBalance(long balance) {
        System.out.printf("Saldo saat ini: Rp %d%n", balance);
    }

    public void showAddSuccess(Transaction transaction) {
        if (transaction == null) {
            System.out.println("Transaksi gagal ditambahkan.");
        } else {
            System.out.printf("Berhasil menambah transaksi: %s%n", format(transaction));
        }
    }

    public void showRemoveSuccess() {
        System.out.println("Berhasil menghapus transaksi.");
    }

    public void showRemoveFailed(int id) {
        System.out.printf("[!] Gagal menghapus transaksi dengan ID: %d.%n", id);
    }

    public void showInvalidChoice() {
        System.out.println("[!] Pilihan tidak dimengerti.");
    }

    public void showInvalidAmount() {
        System.out.println("[!] Jumlah tidak valid!");
    }

    public void showInvalidId() {
        System.out.println("[!] ID tidak valid!");
    }

    public void showInvalidSortOption() {
        System.out.println("[!] Pilihan tidak valid!");
    }
}
