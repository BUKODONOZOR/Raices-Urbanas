package Contest.Project.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "property_type")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PropertyType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_property_type")
    private int id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

}
