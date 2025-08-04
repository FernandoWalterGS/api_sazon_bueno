package sazondelbueno.web.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sazondelbueno.web.Dto.CommentDTO;
import sazondelbueno.web.Model.Comment;
import sazondelbueno.web.Service.CommentService;

import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/comment")

public class CommentController {

    @Autowired
    CommentService service;

    @PostMapping
    public ResponseEntity<Comment> saveComment(@RequestBody Comment m) {
        Comment comment = service.saveComment(m);
        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CommentDTO>> getComments() {
        List<CommentDTO> commentList = service.listComments();
        return new ResponseEntity<>(commentList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Comment>> getById(@PathVariable("id") Long id) {
        Optional<Comment> comment = service.getById(id);
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @GetMapping("recipe/{id}")
    public ResponseEntity<List<CommentDTO>> findByRecipeId(@PathVariable("id") Long id) {
        List<CommentDTO> comments = service.listCommentByRecipe(id);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("id") Long id) {
        boolean exit = service.deleteById(id);
        return new ResponseEntity<>(exit, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable("id") Long id, @RequestBody Comment comment) {
        try {
            return new ResponseEntity<>(service.updateById(id, comment), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }


}
