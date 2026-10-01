package adapter.presenter;

import java.util.List;

import domain.entity.Transaction;

public class FinancePresenter {
    public void showTransactions(List<Transaction> transactions, long balance) {
        System.out.println("Daftar Transaksi:");
        if (transactions.isEmpty()) {
            System.out.println("- Belum ada transaksi!");
        } else {
            for (Transaction transaction : transactions) {
                System.out.println(format(transaction));
            }
        }
        System.out.printf("Saldo: Rp %d%n", balance);
    }

    public void showBalance(long balance) {
        System.out.printf("Saldo saat ini: Rp %d%n", balance);
    }

    public void showAddSuccess(Transaction transaction) {
        if (transaction == null) {
            System.out.println("Transaksi gagal ditambahkan.");
        } else {
            System.out.println("Berhasil menambah transaksi: " + format(transaction));
        }
    }

    private String format(Transaction transaction) {
        String type = transaction.getType() == domain.entity.TransactionType.INCOME ? "Pemasukan" : "Pengeluaran";
        return transaction.getId() + " | " + transaction.getDescription() + " | Rp " + transaction.getAmount() + " | " + type;
    }

    public void showSearchResults(List<Transaction> transactions, String keyword) {
        System.out.println("Hasil Pencarian: \"" + keyword + "\"");
        if (transactions.isEmpty()) {
            System.out.println("- Transaksi tidak ditemukan!");
        } else {
            transactions.forEach(transaction -> System.out.println(format(transaction)));
        }
    }

    public void showSortedTransactions(List<Transaction> transactions, long balance) {
        System.out.println("Daftar Transaksi (Terurut):");
        if (transactions.isEmpty()) {
            System.out.println("Tidak ada transaksi.");
        } else {
            transactions.forEach(transaction -> System.out.println(format(transaction)));
        }
    }

    public void showInvalidChoice() {
        System.out.println("[!] Pilihan tidak dimengerti.");
    }

    public void showInvalidAmount() {
        System.out.println("[!] Jumlah tidak valid!");
    }

    public void showInvalidSortOption() {
        System.out.println("[!] Pilihan tidak valid!");
    }

    public void showInvalidId() {
        System.out.println("[!] ID tidak valid!");
    }

    public void showRemoveSuccess() {
        System.out.println("Berhasil menghapus transaksi.");
    }

    public void showRemoveFailed(int id) {
        System.out.println("[!] Gagal menghapus transaksi dengan ID: " + id + ".");
    }
}
