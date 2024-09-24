package Contest.Project.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.List;

@Entity
@Table(name = "properties")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_property")
    private int id_property;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private User id_user;

    @Column(name = "address", nullable = false, columnDefinition = "TEXT")
    private String address;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "number_bathrooms", nullable = false)
    private int numberOfBathrooms;

    @Column(name = "propertySize", nullable = false)
    private int propertySize;

    @Column(name = "stratum", nullable = false)
    private int stratum;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "id_property_images", cascade = CascadeType.ALL)
    private List<PropertyImages> PropertyImages;

    @ManyToOne
    @JoinColumn(name = "id_property_objective", nullable = false)
    private PropertyObjective propertyObjective;

    @ManyToOne
    @JoinColumn(name = "id_property_type", nullable = false)
    private PropertyType propertyType;

    @ManyToOne
    @JoinColumn(name = "id_zone", nullable = false)
    private Zone zone;

}
