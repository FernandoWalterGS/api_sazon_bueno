package sazondelbueno.web.Service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import sazondelbueno.web.Model.Category;
import sazondelbueno.web.Repository.CategoryRepository;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.properties")
class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;


    // guarda la categoria

    @Test
    void saveCategory_ReturnCategory() {
        Category categoriaEsperada = new Category(5, "Carnes", new Date());
        when(categoryRepository.save(any(Category.class))).thenReturn(categoriaEsperada);
        Category categoriaActual = categoryService.saveCategory(categoriaEsperada);
        assertEquals(categoriaEsperada, categoriaActual);
    }

    // listar todas las categorias
    @Test
    void allCategories_ReturnsCategories() {

        CategoryRepository categoryRepositoryMock = mock(CategoryRepository.class);
        List<Category> categoriasEsperadas = Arrays.asList(
                new Category(1, "Postres", new Date()),
                new Category(2, "Ensaladas", new Date()),
                new Category(3, "carnes", new Date())
        );
        when(categoryRepositoryMock.findAll()).thenReturn(categoriasEsperadas);

        CategoryService categoryService = new CategoryService(categoryRepositoryMock);
        List<Category> categoriasActuales = categoryService.allCategories();
        assertEquals(categoriasEsperadas.size(), categoriasActuales.size());

    }

    //buscar por id

    @Test
    void categoryById_ReturnCategory() {

        Category categoryExpected = new Category(2, "Ensaladas", new Date());
        when(categoryRepository.findById(2L)).thenReturn(Optional.of(categoryExpected));

        categoryService = new CategoryService(categoryRepository);
        Optional<Category> categoryActual = categoryService.getCategoryById(2L);

        assertEquals(Optional.of(categoryExpected), categoryActual);
    }

    // eliminar por id

    @Test
    void deleteCategoryById() {

        Category categoryToDelete = new Category(2, "Ensaladas", new Date());
        when(categoryRepository.findById(2L)).thenReturn(Optional.of(categoryToDelete));

        categoryService = new CategoryService(categoryRepository);
        categoryService.delete(2L);
        verify(categoryRepository).deleteById(2L);
    }

    //actualizar categoria por id

    @Test
    void updateCategoryById() {

        Category updatedCategory = new Category(2, "Nueva Ensalada", new Date());
        Category existingCategory = new Category(2, "Ensaladas", new Date());

        when(categoryRepository.getReferenceById(2L)).thenReturn(existingCategory);
        // Creamos el servicio con el repositorio simulado
        categoryService = new CategoryService(categoryRepository);
        // Llamamos al método que queremos probar
        Category updated = categoryService.update(2L, updatedCategory);
        // Verificamos que se llamó al método getReferenceById y save del repositorio
        verify(categoryRepository).getReferenceById(2L);
        verify(categoryRepository).save(existingCategory);
        // Verificamos que la categoría devuelta sea la esperada
        assertEquals(updatedCategory.getName(), updated.getName());
        assertEquals(existingCategory.getId(), updated.getId());
    }








}
