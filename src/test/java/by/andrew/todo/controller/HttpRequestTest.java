package by.andrew.todo.controller;


import by.andrew.todo.model.TodoModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HttpRequestTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void postTodoModelSql() throws Exception {
        TodoModel todoModel = new TodoModel();
        todoModel.setName("Andrey");
        todoModel.setSuccess(false);
        System.out.println(todoModel);

        //1
        TodoModel expectedTodoModel = new TodoModel();
        expectedTodoModel.setId(1L);
        expectedTodoModel.setName("Andrey");
        expectedTodoModel.setSuccess(false);
        TodoModel result = restTemplate
                .postForObject("http://localhost:" + port + "/todos/", todoModel, TodoModel.class);
        assertEquals(expectedTodoModel, result);

        //2
        Collection result2 = restTemplate
                .getForObject("http://localhost:" + port + "/todos/", ArrayList.class);
        assertEquals(1, result2.size());

        //3
        TodoModel updateTodoModel = new TodoModel();
        updateTodoModel.setId(result.getId());
        updateTodoModel.setName("Andrey2");
        updateTodoModel.setSuccess(false);
        restTemplate
                .put("http://localhost:" + port + "/todos/", updateTodoModel);

        //4
        restTemplate
                .delete("http://localhost:" + port + "/todos/"+result.getId());


    }




}