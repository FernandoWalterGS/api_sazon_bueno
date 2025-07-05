package sazondelbueno.web.Service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sazondelbueno.web.Model.Category;
import sazondelbueno.web.Repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository repository;

    @Autowired
    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public Category saveCategory(Category category) {
        return repository.save(category);
    }

    public List<Category> allCategories() {
        return repository.findAll();
    }

    public Optional<Category> getCategoryById(Long id) {
        return repository.findById(id);
    }

    public Category update(Long id, Category category) {
        Category category1 = repository.getReferenceById(id);
        BeanUtils.copyProperties(category, category1, "id");
        repository.save(category1);

        return category1;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
