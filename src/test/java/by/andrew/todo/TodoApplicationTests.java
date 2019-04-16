package by.andrew.todo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TodoApplicationTests {

    @Test
    public void contextLoads() throws Exception {

    }

}


//	@Autowired
//	private TestRestTemplate restTemplate;
//
//	@Test
//	public void contextLoads() throws Exception{
//		assertThat(this.restTemplate.getForObject("http://127.0.0.1:8080/todos/",
//				String.class)).contains("[]");
//	}