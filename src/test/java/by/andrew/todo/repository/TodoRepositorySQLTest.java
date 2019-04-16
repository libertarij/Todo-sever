package by.andrew.todo.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TodoRepositorySQLTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void greetingShouldReturnDefaultMessage() throws Exception {
        List<Object> lT = new ArrayList<>();
        assertThat(restTemplate.getForObject("http://localhost:"+port+"/todos/", String.class).contains("[]"));
        System.out.println();
    }
}
//
//
//    @Autowired
////    private TestRestTemplate restTemplate;
//
////    @Autowired
////    private MockMvc mockMvc;
//
////    @MockBean
////    private TodoService service;
//
////    @Test
////    public void testInsertObject() throws Exception {
////        String url = BASE_URL + "/object";
////        TodoModel todoModel = new TodoModel();
//////        todoModel.setId(1L);
////        todoModel.setName("Andrew");
////        todoModel.setSuccess(false);
////
////
////        ObjectMapper mapper = new ObjectMapper();
////        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
////        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
////        String requestJson=ow.writeValueAsString(todoModel);
////
////        mockMvc.perform(post(url)
////                .contentType(MediaType.APPLICATION_JSON_UTF8)
////                .content(requestJson))
////                .andExpect(status().isOk());
////    }