package adapter.presenter;

import domain.entity.Guest;
import java.util.List;

public class GuestPresenter {
    private String format(Guest guest) {
        return String.format("%d | %s | %s", guest.getId(), guest.getName(), guest.getPurpose());
    }

    private void printList(List<Guest> list, String header, String emptyMessage) {
        System.out.println(header);
        if (list == null || list.isEmpty()) {
            System.out.println(emptyMessage);
        } else {
            for (Guest guest : list) {
                System.out.println(format(guest));
            }
        }
    }

    public void showGuests(List<Guest> list) {
        printList(list, "Daftar Tamu:", "- Data tamu belum tersedia!");
    }

    public void showSearchResults(List<Guest> list, String keyword) {
        printList(list, "Hasil Pencarian: \"" + keyword + "\"", "- Tamu tidak ditemukan!");
    }

    public void showAddSuccess(Guest guest) {
        System.out.printf("Berhasil mendaftarkan tamu: %s%n", format(guest));
    }

    public void showDeleteSuccess() {
        System.out.println("Berhasil menghapus tamu.");
    }

    public void showDeleteFailed(int id) {
        System.out.printf("[!] Gagal menghapus tamu dengan ID: %d.%n", id);
    }

    public void showInvalidChoice() {
        System.out.println("[!] Pilihan tidak dimengerti.");
    }

    public void showInvalidId() {
        System.out.println("[!] ID tidak valid!");
    }

    public void showMenu() {
        System.out.println("Menu:");
        System.out.println("1. Daftarkan");
        System.out.println("2. Cari");
        System.out.println("3. Hapus");
        System.out.println("x. Keluar");
    }
}
