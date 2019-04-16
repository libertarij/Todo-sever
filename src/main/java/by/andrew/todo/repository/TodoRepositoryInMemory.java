package by.andrew.todo.repository;

import by.andrew.todo.model.TodoModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
@Lazy
public class TodoRepositoryInMemory implements TodoRepository {
    private static final Logger logger = LoggerFactory.getLogger(TodoRepositoryInMemory.class);
    private static final Map<Long, TodoModel> todoRepository = new ConcurrentHashMap<>();

    @Override
    public TodoModel add(TodoModel todo) {
        Long id = todo.getId();
        todoRepository.put(id, todo);
        return get(id);
    }

    @Override
    public TodoModel get(Long id) {
        return todoRepository.get(id);
    }

    @Override
    public void update(TodoModel todo) {
        Long id = todo.getId();
        if (!id.equals(todo.getId())) {
            throw new RuntimeException("Error!!!!!!!");
        }
        todoRepository.replace(id, todo);
    }

    @Override
    public void delete(Long id) {
        logger.info("DEL: delete todoModel ID: " + id);
        todoRepository.remove(id);
    }

    public Collection<TodoModel> getAll() {
        logger.info("getAll from TodoRepositoryInMemory ");
        return todoRepository.values();
    }

//    @Override
//    public String nbrb(Long id) {
//       RestTemplate restTemplate;
//        logger.info("http://www.nbrb.by/API/ExRates/Currencies/" + id);
//        URL url = null;
//        String line ="";
//        try {
//            url = new URL("http://www.nbrb.by/API/ExRates/Currencies/" + id);
//            HttpURLConnection con = (HttpURLConnection) url.openConnection();
//            con.setRequestMethod("GET");
//            con.connect();
//
//            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
//            StringBuilder sb = new StringBuilder();
//            while ((line = br.readLine()) != null) {
//                sb.append(line);
//                sb.append("\n");
//            }
//            br.close();
//            return sb.toString();
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return line;
//    }
}