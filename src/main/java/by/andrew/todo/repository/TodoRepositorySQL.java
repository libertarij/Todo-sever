package by.andrew.todo.repository;

import by.andrew.todo.model.TodoModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collection;

@Repository
@Lazy
public class TodoRepositorySQL implements TodoRepository {
    private static final Logger logger = LoggerFactory.getLogger(TodoRepositorySQL.class);

    private static final String addSql = "INSERT INTO todoTable (id, name, success) VALUES (?, ?, ?)";
    private static final String getSql = "SELECT * FROM todoTable WHERE id=";
    private static final String updSql = "UPDATE todoTable SET name = ?, success = ? WHERE id = ?";
    private static final String delSql = "DELETE FROM todoTable WHERE id = %d";
    private static final String getAllSql = "SELECT * FROM todoTable";
    private static final int[] types = {Types.VARCHAR, Types.BIT, Types.INTEGER};

    final JdbcTemplate jdbcTemplate;

    public TodoRepositorySQL(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        baseInit();
    }

    private void baseInit() {
        logger.info("Creating tables");
        jdbcTemplate.execute("DROP TABLE todoTable IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE todoTable(" +
                "id SERIAL, name VARCHAR(256), success BIT )");
    }

    RowMapper<TodoModel> ROW_MAPPER = (ResultSet rs, int rowNum) -> {
        TodoModel todoModel = new TodoModel();
        todoModel.setId(rs.getLong("id"));
        todoModel.setName(rs.getString("name"));
        todoModel.setSuccess(rs.getBoolean("success"));
        return todoModel;
    };

    @Override
    public TodoModel add(TodoModel todo) {
        long newId = todo.getId();
        int updatedLines = jdbcTemplate.update(addSql, newId, todo.getName(), todo.isSuccess());
        logger.debug("SQL add TodoModel result added rows {}", updatedLines);
        return get(newId);
    }

    @Override
    public TodoModel get(Long id) {
        return jdbcTemplate.query(getSql + id, ROW_MAPPER).get(0);
    }

    @Override
    public void update(TodoModel todo) {
        logger.info("SQL update TodoModel todo = " + todo);
        Object[] params = {todo.getName(), todo.isSuccess(), todo.getId()};
        jdbcTemplate.update(updSql, params, types);

    }

    @Override
    public void delete(Long id) {
        int delrows = jdbcTemplate.update(String.format(delSql, id));
        logger.info("SQL delete TodoModel id {}, changed rows {}", id, delrows);
//        return id;
    }

    @Override
    public Collection<TodoModel> getAll() {
        ArrayList<TodoModel> listTodo = new ArrayList<>();
        listTodo.addAll(jdbcTemplate.query(getAllSql, ROW_MAPPER));
        logger.info("SQL getAll return all TodoModel " + listTodo.toString());
        return listTodo;
    }

//    @Override
//    public String nbrb(Long id) {
//        RestTemplate restTemplate;
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