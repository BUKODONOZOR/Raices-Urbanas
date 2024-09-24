package Contest.Project.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "property_images")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PropertyImages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_property_images")
    private int id_property_images;

    @ManyToOne
    @JoinColumn(name = "id_property", nullable = false)
    private Property id_property;

    @Column(name = "url", columnDefinition = "TEXT", nullable = false   )
    private String url;

}
