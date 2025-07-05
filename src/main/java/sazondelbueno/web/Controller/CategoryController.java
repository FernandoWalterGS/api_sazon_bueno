package sazondelbueno.web.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sazondelbueno.web.Model.Category;
import sazondelbueno.web.Responses.recipe.category.CategoryResponse;
import sazondelbueno.web.Service.CategoryService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/category")
@CrossOrigin("*")

public class CategoryController {
    @Autowired
    CategoryService service;

    @PostMapping()
    public ResponseEntity<Category> saveCategory(@RequestBody Category c) {
        Category category = service.saveCategory(c);
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<Category>> allCategories() {
        List<Category> list = service.allCategories();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Category>> getCategoryById(@PathVariable("id") Long id) {
        Optional<Category> category = service.getCategoryById(id);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Category> update(@RequestBody Category category, @PathVariable Long id) {
        try {
            return new ResponseEntity<>(service.update(id, category), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CategoryResponse> delete(@PathVariable Long id) {
        try {
            service.delete(id);
            return new ResponseEntity<>(new CategoryResponse(true,"categoria eliminada"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new CategoryResponse(false,"No se pudo eliminar la categoria"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
