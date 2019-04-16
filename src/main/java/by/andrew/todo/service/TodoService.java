package by.andrew.todo.service;

import by.andrew.todo.controller.TodoController;
import by.andrew.todo.model.TodoModel;
import by.andrew.todo.repository.TodoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TodoService {
    private static final Logger logger = LoggerFactory.getLogger(TodoController.class);
    private static final AtomicLong counter = new AtomicLong(1);

    @Autowired
    @Qualifier("todoRepository")
    private TodoRepository todoRepository;

    public TodoModel add(TodoModel todoModel) {
        if (todoModel.getId() != null) {
            throw new RuntimeException("Error! Not empty ID!");
        }
        todoModel.setId(counter.getAndIncrement());
        logger.info("POST: add todoModel: " + todoModel);
        return todoRepository.add(todoModel);
    }

    public TodoModel get(Long id) {
        logger.info("GET todo by ID: " + id);
        return todoRepository.get(id);
    }

    public void update(TodoModel todo) {
        todoRepository.update(todo);
    }

    public void delete(Long id) {
        int delId;
        todoRepository.delete(id);
        logger.info("DEL: delete todoModel ID: ");
    }

    public Collection<TodoModel> getAll() {//Collection for In Memory
        return todoRepository.getAll();
    }


//    public String nbrb(Long id) {
//
//
//        return todoRepository.nbrb(id);
//    }

}