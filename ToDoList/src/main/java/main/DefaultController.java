package main;

import main.entity.ToDoEntity;
import main.repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
public class DefaultController {

    @Autowired
    private ToDoRepository toDoRepository;

    @RequestMapping("/")
    public String index(Model model) {
        Iterable<ToDoEntity> iterable = toDoRepository.findAll();
        ArrayList<ToDoEntity> todoList = new ArrayList<>();
        iterable.forEach(toDoEntity -> {
            todoList.add(toDoEntity);
        });
        model.addAttribute("todoList",todoList);
        return "index";
    }

}
