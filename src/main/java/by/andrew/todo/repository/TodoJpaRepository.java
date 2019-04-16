package by.andrew.todo.repository;

import by.andrew.todo.model.TodoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoJpaRepository extends JpaRepository<TodoModel, Long> {
}