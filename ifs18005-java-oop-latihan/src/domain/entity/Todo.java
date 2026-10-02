package domain.entity;

public class Todo {
    private final int id;
    private String title;
    private boolean done;

    public Todo(int id, String title, boolean done) {
        this.id = id;
        this.title = title;
        this.done = done;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public boolean isDone() { return done; }

    public void changeTitle(String title) { this.title = title; }
    public void markDone(boolean done) { this.done = done; }
}
