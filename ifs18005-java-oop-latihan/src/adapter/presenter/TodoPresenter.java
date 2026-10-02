package adapter.presenter;

import domain.entity.Todo;
import java.util.List;

public class TodoPresenter {
    private String format(Todo todo) {
        return String.format("%d | [%s] %s", todo.getId(), todo.isDone() ? "x" : " ", todo.getTitle());
    }

    private void printList(List<Todo> list, String header, String emptyMessage) {
        System.out.println(header);
        if (list == null || list.isEmpty()) {
            System.out.println(emptyMessage);
        } else {
            for (Todo todo : list) {
                System.out.println(format(todo));
            }
        }
    }

    public void showTodos(List<Todo> list) {
        printList(list, "Daftar Todo:", "- Data todo belum tersedia!");
    }

    public void showSearchResults(List<Todo> list, String keyword) {
        printList(list, "Hasil Pencarian: \"" + keyword + "\"", "- Todo tidak ditemukan!");
    }

    public void showAddSuccess(Todo todo) {
        System.out.printf("Berhasil menambah todo: %s%n", format(todo));
    }

    public void showRemoveSuccess() {
        System.out.println("Berhasil menghapus todo.");
    }

    public void showRemoveFailed(int id) {
        System.out.printf("[!] Gagal menghapus todo dengan ID: %d.%n", id);
    }

    public void showUpdateSuccess() {
        System.out.println("Berhasil mengubah todo.");
    }

    public void showUpdateFailed(int id) {
        System.out.printf("[!] Gagal mengubah todo dengan ID: %d.%n", id);
    }

    public void showNoChanges() {
        System.out.println("[!] Tidak ada perubahan data.");
    }

    public void showInvalidInput(String message) {
        System.out.printf("[!] %s%n", message);
    }

    public void showInvalidChoice() {
        System.out.println("[!] Pilihan tidak dimengerti.");
    }

    public void showInvalidId() {
        System.out.println("[!] ID tidak valid!");
    }
}
