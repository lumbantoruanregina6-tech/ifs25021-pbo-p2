package usecase;

import domain.entity.Todo;
import domain.repository.ITodoRepository;
import java.util.List;
import java.util.Optional;

public class TodoUseCase {
    private final ITodoRepository repository;

    public TodoUseCase(ITodoRepository repository) {
        this.repository = repository;
    }

    public List<Todo> getAllTodos() {
        return repository.findAll();
    }

    /** @throws IllegalArgumentException jika judul kosong. */
    public Todo addTodo(String title) {
        requireNotBlank(title);
        return repository.save(title.trim());
    }

    public boolean removeTodo(int id) {
        return repository.deleteById(id);
    }

    /** @throws IllegalArgumentException jika judul kosong. */
    public UpdateResult updateTitle(int id, String title) {
        requireNotBlank(title);

        Optional<Todo> found = repository.findById(id);
        if (found.isEmpty()) {
            return UpdateResult.NOT_FOUND;
        }

        Todo todo = found.get();
        if (title.trim().equals(todo.getTitle())) {
            return UpdateResult.NO_CHANGE;
        }

        todo.changeTitle(title.trim());
        return repository.update(todo) ? UpdateResult.SUCCESS : UpdateResult.NOT_FOUND;
    }

    /** Menandai todo selesai / belum selesai. */
    public UpdateResult setDone(int id, boolean done) {
        Optional<Todo> found = repository.findById(id);
        if (found.isEmpty()) {
            return UpdateResult.NOT_FOUND;
        }

        Todo todo = found.get();
        if (todo.isDone() == done) {
            return UpdateResult.NO_CHANGE;
        }

        todo.markDone(done);
        return repository.update(todo) ? UpdateResult.SUCCESS : UpdateResult.NOT_FOUND;
    }

    public List<Todo> searchTodos(String keyword) {
        String lowerKeyword = keyword.toLowerCase();
        return repository.findAll().stream()
                .filter(t -> t.getTitle().toLowerCase().contains(lowerKeyword))
                .toList();
    }

    private void requireNotBlank(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Judul tidak boleh kosong!");
        }
    }
}
