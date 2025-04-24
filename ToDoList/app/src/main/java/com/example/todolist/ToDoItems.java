package com.example.todolist;

public class ToDoItems {
    private String id;
    protected String todo_item;
    private boolean checked;

    public ToDoItems(String id, String todo_item) {
        this.id = id;
        this.todo_item = todo_item;
        this.checked = false;
    }

    public String getId() {
        return id;
    }
    public String getTodo_item() {
        return todo_item;
    }
    public boolean isChecked() {
        return checked;
    }
    public void setTodo_item(String todo_item) {
        this.todo_item = todo_item;
    }
    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
