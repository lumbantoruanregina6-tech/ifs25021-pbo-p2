package framework.view;

import adapter.presenter.TodoPresenter;
import framework.util.InputUtil;
import usecase.TodoUseCase;
import usecase.UpdateResult;

public class TodoView {
    private final TodoUseCase useCase;
    private final TodoPresenter presenter;

    public TodoView(TodoUseCase useCase, TodoPresenter presenter) {
        this.useCase = useCase;
        this.presenter = presenter;
    }

    public void show() {
        boolean running = true;
        while (running) {
            presenter.showTodos(useCase.getAllTodos());
            printMenu();
            String input = InputUtil.input("Pilih");
            switch (input.toLowerCase()) {
                case "1" -> addTodo();
                case "2" -> updateTitle();
                case "3" -> changeDone();
                case "4" -> searchTodo();
                case "5" -> removeTodo();
                case "x" -> running = false;
                default -> presenter.showInvalidChoice();
            }
            if (running) {
                System.out.println();
            }
        }
    }

    private void printMenu() {
        System.out.println("Menu:");
        System.out.println("1. Tambah");
        System.out.println("2. Ubah Judul");
        System.out.println("3. Tandai Selesai/Belum");
        System.out.println("4. Cari");
        System.out.println("5. Hapus");
        System.out.println("x. Keluar");
    }

    private void addTodo() {
        System.out.println("[Menambah Todo]");
        String title = InputUtil.input("Judul (x Jika Batal)");
        if ("x".equalsIgnoreCase(title)) {
            return;
        }

        try {
            presenter.showAddSuccess(useCase.addTodo(title));
        } catch (IllegalArgumentException e) {
            presenter.showInvalidInput(e.getMessage());
        }
    }

    private void updateTitle() {
        System.out.println("[Mengubah Judul Todo]");
        String strId = InputUtil.input("ID Todo yang diubah (x Jika Batal)");
        if ("x".equalsIgnoreCase(strId)) {
            return;
        }

        Integer id = parseId(strId);
        if (id == null) {
            return;
        }

        String title = InputUtil.input("Judul Baru");
        try {
            showUpdateResult(useCase.updateTitle(id, title), id);
        } catch (IllegalArgumentException e) {
            presenter.showInvalidInput(e.getMessage());
        }
    }

    private void changeDone() {
        System.out.println("[Menandai Todo]");
        String strId = InputUtil.input("ID Todo (x Jika Batal)");
        if ("x".equalsIgnoreCase(strId)) {
            return;
        }

        Integer id = parseId(strId);
        if (id == null) {
            return;
        }

        String status = InputUtil.input("Status (1 = Selesai, 2 = Belum)");
        switch (status) {
            case "1" -> showUpdateResult(useCase.setDone(id, true), id);
            case "2" -> showUpdateResult(useCase.setDone(id, false), id);
            default -> presenter.showInvalidChoice();
        }
    }

    private void searchTodo() {
        System.out.println("[Mencari Todo]");
        String keyword = InputUtil.input("Kata Kunci (x Jika Batal)");
        if (!"x".equalsIgnoreCase(keyword)) {
            presenter.showSearchResults(useCase.searchTodos(keyword), keyword);
        }
    }

    private void removeTodo() {
        System.out.println("[Menghapus Todo]");
        String strId = InputUtil.input("[ID Todo] yang dihapus (x Jika Batal)");
        if ("x".equalsIgnoreCase(strId)) {
            return;
        }

        Integer id = parseId(strId);
        if (id == null) {
            return;
        }

        if (useCase.removeTodo(id)) {
            presenter.showRemoveSuccess();
        } else {
            presenter.showRemoveFailed(id);
        }
    }

    private void showUpdateResult(UpdateResult result, int id) {
        switch (result) {
            case SUCCESS -> presenter.showUpdateSuccess();
            case NO_CHANGE -> presenter.showNoChanges();
            case NOT_FOUND -> presenter.showUpdateFailed(id);
        }
    }

    private Integer parseId(String value) {
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            presenter.showInvalidId();
            return null;
        }
    }
}
