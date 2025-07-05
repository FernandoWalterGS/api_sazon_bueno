package sazondelbueno.web.Config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sazondelbueno.web.Model.Category;
import sazondelbueno.web.Model.Difficulty;
import sazondelbueno.web.Repository.CategoryRepository;
import sazondelbueno.web.Repository.DifficultyRepository;


@Configuration
public class LoadDataBase {

    @Bean
    CommandLineRunner initDataBase(CategoryRepository categoryRepository, DifficultyRepository difficultyRepository) {
        return args -> {
            if (categoryRepository.count() == 0) {
                categoryRepository.save(new Category("carnes"));
                categoryRepository.save(new Category("postres"));
                categoryRepository.save(new Category("ensaladas"));
                categoryRepository.save(new Category("pastas"));
            }
            if (
                    difficultyRepository.count() == 0) {
                difficultyRepository.save(new Difficulty("facil"));
                difficultyRepository.save(new Difficulty("intermedio"));
                difficultyRepository.save(new Difficulty("dificil"));
            }
        };
    }
}
