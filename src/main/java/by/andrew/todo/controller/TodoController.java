package by.andrew.todo.controller;

import by.andrew.todo.model.Rates;
import by.andrew.todo.model.TodoModel;
import by.andrew.todo.service.TodoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Collection;

@RestController
@RequestMapping("/todos")
@CrossOrigin //Для разрешения доступа к сайтам
public class TodoController {
    private static final Logger logger = LoggerFactory.getLogger(TodoController.class);

    @Autowired
    private TodoService todoService;

    @PostMapping
    public TodoModel add(@RequestBody TodoModel todo) {
        return todoService.add(todo);
    }

    @GetMapping(path = "/nbrb/{currency}")
    public double nbrb(@PathVariable String currency) throws IOException {
        logger.info("http://www.nbrb.by/API/ExRates/Rates/" + currency + "?ParamMode=2");
        RestTemplate restTemplate = new RestTemplate();
        Rates rates = restTemplate.getForObject("http://www.nbrb.by/API/ExRates/Rates/{currency}?ParamMode=2", Rates.class, currency);
        return rates.getCur_OfficialRate();
    }

    @GetMapping(path = "/{id}")
    public TodoModel get(@PathVariable Long id) {
        return todoService.get(id);
    }

    @PutMapping
    public void update(@RequestBody TodoModel todo) {
        todoService.update(todo);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Long id) {
        todoService.delete(id);
    }

    @GetMapping
    public Collection<TodoModel> getAll() {
        return todoService.getAll();
    }
}


//    @GetMapping(path = "/test")
//    public String test(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        response.sendError(403, "my_error");
//        return "ffffff";
//    }
//
//    @GetMapping(path = "/nbrb/{id}")
//    public String nbrb(@PathVariable Long id) throws IOException {
//        RestTemplate restTemplate;
//        logger.info("http://www.nbrb.by/API/ExRates/Rates/" + id);
//        URL url = null;
//        String line = "";
//        try {
//            url = new URL("http://www.nbrb.by/API/ExRates/Rates/" + id);
//            HttpURLConnection con = (HttpURLConnection) url.openConnection();
//            con.setRequestMethod("GET");
//            con.connect();
//            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
//            StringBuilder sb = new StringBuilder();
//            while ((line = br.readLine()) != null) {
//                sb.append(line);
//                sb.append("\n");
//            }
//
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