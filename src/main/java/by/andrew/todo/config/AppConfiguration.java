package by.andrew.todo.config;

import by.andrew.todo.repository.TodoRepository;
import by.andrew.todo.repository.TodoRepositoryInMemory;
import by.andrew.todo.repository.TodoRepositoryJpaDatabase;
import by.andrew.todo.repository.TodoRepositorySQL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class AppConfiguration {
    private static final Logger logger = LoggerFactory.getLogger(TodoRepositoryJpaDatabase.class);

    @Value("${app.todo.config.repo.type}")
    private String repoType;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Bean
    public TodoRepository todoRepository() {
        logger.info("repoType: {}", repoType);
        TodoRepository todoRepository = null;
        switch (repoType) {
            case "sql":
                todoRepository = new TodoRepositorySQL(jdbcTemplate);
                break;
            case "jpa":
                todoRepository = new TodoRepositoryJpaDatabase();
                break;
            case "memory":
                todoRepository = new TodoRepositoryInMemory();
                break;
        }
        logger.info("TodoRepository type: {}", todoRepository != null ? todoRepository.getClass().getSimpleName() : null);
        return todoRepository;
    }
}
