package sazondelbueno.web.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class CommentDTO {
    private Long id;
    private Long id_recipe;
    private String comment;
    private Date createdAt;
    private String author;



}
