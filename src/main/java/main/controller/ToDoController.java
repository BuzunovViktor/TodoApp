package main.controller;

import main.repository.ToDoRepository;
import main.entity.ToDoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ToDoController {

    @Autowired
    private final ToDoRepository toDoRepository;

    public ToDoController(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }

    @GetMapping("/todo")
    public ResponseEntity<List<ToDoEntity>> getToDos() {
        return new ResponseEntity((List<ToDoEntity>) toDoRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/todo/{id}")
    public ResponseEntity<ToDoEntity> getToDo(@PathVariable Long id) {
        Optional<ToDoEntity> toDoEntity = toDoRepository.findById(id);
        if (toDoEntity.isPresent()) {
            return new ResponseEntity(toDoEntity.get(),HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/todo")
    public ResponseEntity addUser(@RequestBody ToDoEntity toDoEntity) {
        toDoRepository.save(toDoEntity);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/todo")
    public ResponseEntity updateToDo(@RequestBody ToDoEntity toDoEntity, @RequestParam Long id) {
        Optional<ToDoEntity> updateToDoEntity = toDoRepository.findById(id);
        if (updateToDoEntity.isPresent()) {
            updateToDoEntity.get().setName(toDoEntity.getName());
            updateToDoEntity.get().setDescription(toDoEntity.getDescription());
            toDoRepository.save(updateToDoEntity.get());
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/todo/{id}")
    public ResponseEntity deleteToDo(@PathVariable Long id) {
        toDoRepository.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }

}
