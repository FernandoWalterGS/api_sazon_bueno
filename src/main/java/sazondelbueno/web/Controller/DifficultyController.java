package sazondelbueno.web.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sazondelbueno.web.Model.Difficulty;
import sazondelbueno.web.Responses.recipe.dofficulty.DifficultyResponse;
import sazondelbueno.web.Service.DifficultyService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/difficulty")
@CrossOrigin("*")

public class DifficultyController {
    @Autowired
    DifficultyService service;

    @PostMapping()
    public ResponseEntity<Difficulty> saveLevel(@RequestBody Difficulty d) {
        Difficulty difficulty = service.saveLevel(d);
        return new ResponseEntity<>(difficulty, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<Difficulty>> allLevels() {
        List<Difficulty> list = service.allLevels();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Difficulty>> getLevelById(@PathVariable("id") Long id) {
        Optional<Difficulty> difficulty = service.getLevelById(id);
        return new ResponseEntity<>(difficulty, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Difficulty> update(@RequestBody Difficulty difficulty, @PathVariable Long id) {
        try {
            return new ResponseEntity<>(service.update(id, difficulty), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DifficultyResponse> delete(@PathVariable Long id) {
        try {
            service.delete(id);
            return new ResponseEntity<>(new DifficultyResponse(true,"difficultad agregada con exito"), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(new DifficultyResponse(false, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
