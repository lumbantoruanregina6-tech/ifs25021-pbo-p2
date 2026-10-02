package adapter.repository;

import domain.entity.Todo;
import domain.repository.ITodoRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TodoRepository implements ITodoRepository {
    private final List<Todo> data = new ArrayList<>();
    private int idCounter = 0;

    @Override
    public List<Todo> findAll() {
        return new ArrayList<>(data);
    }

    @Override
    public Optional<Todo> findById(int id) {
        return data.stream().filter(t -> t.getId() == id).findFirst();
    }

    @Override
    public Todo save(String title) {
        Todo todo = new Todo(nextId(), title, false);
        data.add(todo);
        return todo;
    }

    @Override
    public boolean deleteById(int id) {
        return data.removeIf(t -> t.getId() == id);
    }

    @Override
    public boolean update(Todo todo) {
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getId() == todo.getId()) {
                data.set(i, todo);
                return true;
            }
        }
        return false;
    }

    private int nextId() {
        return ++idCounter;
    }
}
