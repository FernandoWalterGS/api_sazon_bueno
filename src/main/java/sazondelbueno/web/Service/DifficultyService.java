package sazondelbueno.web.Service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sazondelbueno.web.Model.Difficulty;
import sazondelbueno.web.Repository.DifficultyRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DifficultyService {
    private final DifficultyRepository repository;

    @Autowired
    public DifficultyService(DifficultyRepository repository) {
        this.repository = repository;
    }

    public List<Difficulty> allLevels() {
        return repository.findAll();
    }

    public Optional<Difficulty> getLevelById(Long id) {
        return repository.findById(id);
    }

    public Difficulty saveLevel(Difficulty difficulty) {
        return repository.save(difficulty);
    }

    public Difficulty update(Long id, Difficulty difficulty) {
        Difficulty difficulty1 = repository.getReferenceById(id);
        BeanUtils.copyProperties(difficulty, difficulty1, "id");
        return repository.save(difficulty1);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}

