package main;

import main.entity.ToDoEntity;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import main.repository.ToDoRepository;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.stream.Stream;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        new SpringApplication(App.class).run(args);
    }

    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/todo").allowedOrigins("http://localhost:4200");
            }
        };
    }

    @Bean
    CommandLineRunner init(ToDoRepository toDoRepository) {
        return args -> {
            Stream.of("Купить молоко", "Вынести мусор", "Купить Google", "Не забыть замяукать кота", "И так далее").forEach(name -> {
                ToDoEntity toDoEntity = new ToDoEntity();
                toDoEntity.setName(name);
                toDoEntity.setDescription(name);
                toDoRepository.save(toDoEntity);
            });
            toDoRepository.findAll().forEach(System.out::println);
        };
    }
}
