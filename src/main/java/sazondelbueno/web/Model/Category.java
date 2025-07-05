package sazondelbueno.web.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false, length = 20)
    private String name;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    public Category(String name) {
        this.name = name;
    }

    @PrePersist
    protected void onCreate() {
        TimeZone timeZone = TimeZone.getTimeZone("America/Bogota");
        Calendar calendar = Calendar.getInstance(timeZone);
        this.createdAt = calendar.getTime();
    }
}
