package sazondelbueno.web.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "recipe")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 40, nullable = false)
    private String name;

    @Column(name = "description", nullable = false, length = 100)
    private String description;

    @Column(name = "image")
    private String image;

    @Column(name = "time_min", nullable = false)
    private int time_min;

    @Column(name = "ingredients", length = 2000, nullable = false)
    private String ingredients;

    @Column(name = "preparation", length = 4000, nullable = false)
    private String preparation;

    @ManyToOne
    @JoinColumn(name = "id_account", nullable = false, updatable = false)
    private Account account;

    @ManyToOne
    @JoinColumn(name = "id_difficulty", nullable = false)
    private Difficulty difficulty;

    @ManyToOne
    @JoinColumn(name = "id_category", nullable = false)
    private Category category;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false)
    private Date createdAt;


    @PrePersist
    protected void onCreate() {
        TimeZone timeZone = TimeZone.getTimeZone("America/Bogota");
        Calendar calendar = Calendar.getInstance(timeZone);
        this.createdAt = calendar.getTime();
    }
}
