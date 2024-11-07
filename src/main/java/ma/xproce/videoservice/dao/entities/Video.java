package ma.xproce.videoservice.dao.entities;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String url;
    private String description;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    @JsonBackReference
    private Creator creator;
}