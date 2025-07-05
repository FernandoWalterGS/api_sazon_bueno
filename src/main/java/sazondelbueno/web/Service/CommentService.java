package sazondelbueno.web.Service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sazondelbueno.web.Dto.CommentDTO;
import sazondelbueno.web.Model.Comment;
import sazondelbueno.web.Repository.CommentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    @Autowired
    private CommentRepository repository;

    public Comment saveComment(Comment comment) {
        return repository.save(comment);
    }

    public List<CommentDTO> listComments() {
       List<Comment> comments= repository.findAll();
       List<CommentDTO> commentDTOList= new ArrayList<>();

       comments.forEach(comment -> {

           commentDTOList.add(new CommentDTO(
                   comment.getId(),
                   comment.getRecipe().getId(),
                   comment.getComment(),
                   comment.getCreatedAt(),
                   comment.getAccount().getName()));
       });

       return commentDTOList;
    }

    public List<CommentDTO>listCommentByRecipe(Long id){
        Optional<List<Comment>> list = repository.findAllByRecipeIdOrderByCreatedAtDesc(id);
        List<CommentDTO> commentDTOList= new ArrayList<>();

      list.ifPresent(comments -> {

          comments.forEach(comment -> {
              commentDTOList.add(new CommentDTO(
                      comment.getId(),
                      comment.getRecipe().getId(),
                      comment.getComment(),
                      comment.getCreatedAt(),
                      comment.getAccount().getName()));
          });
        });



      return commentDTOList;

    }

    public Optional<Comment> getById(Long id) {
        return repository.findById(id);
    }

    public boolean deleteById(Long id) {
        try {
            repository.deleteById(id);
            return true;

        } catch (Exception e) {
            return false;
        }
    }


    public Comment updateById(Long id, Comment comment) {
        Comment commentReference = repository.getReferenceById(id);
        BeanUtils.copyProperties(comment, commentReference, "id");
        return repository.save(commentReference);

    }


}
