package by.andrew.todo.repository;

import by.andrew.todo.model.TodoModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
@Lazy
public class TodoRepositoryJpaDatabase implements TodoRepository {
    private static final Logger logger = LoggerFactory.getLogger(TodoRepositoryJpaDatabase.class);

    @Autowired
    private TodoJpaRepository todoJpaRepository;

    @Override
    public TodoModel add(TodoModel todo) {
        Long id = todo.getId();
        logger.info(todo.toString());
        todoJpaRepository.save(todo);
        return get(id);
    }

    @Override
    public TodoModel get(Long id) {
        return todoJpaRepository.getOne(id);
    }

    @Override
    public void update(TodoModel todo) {
        Long id = todo.getId();
        todoJpaRepository.save(todo);
        todo.setId(id);
        logger.info("Объект= " + todo);
    }

    @Override
    public void delete(Long id) {
        todoJpaRepository.deleteById(id);
        logger.info("DEL: delete todoModel ID: " + id);
//        return id;
    }

    @Override
    public Collection<TodoModel> getAll() {
        logger.info("getAll from TodoRepositoryDatabase " + todoJpaRepository.findAll());
        return todoJpaRepository.findAll();
    }

//    @Override
//    public String nbrb(Long id) {
//        return null;
//    }
}
