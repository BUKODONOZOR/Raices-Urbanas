package Contest.Project.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Entity
@Table(name = "property_objective")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PropertyObjective {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_property_objective")
    private int id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

}
