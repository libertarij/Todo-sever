package by.andrew.todo.repository;

import by.andrew.todo.model.TodoModel;

import java.util.Collection;

public interface TodoRepository {

    TodoModel add(TodoModel todo);

    TodoModel get(Long id);

    void update(TodoModel todo);

    void delete(Long id);

    Collection<TodoModel> getAll();

//    String nbrb(Long id);
}
