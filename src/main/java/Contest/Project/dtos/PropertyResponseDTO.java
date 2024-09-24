package Contest.Project.dtos;

import Contest.Project.entities.PropertyImages;
import lombok.Data;

import java.util.List;

@Data
public class PropertyResponseDTO {

    private String address;

    private double price;

    private int numberOfBathrooms;

    private int propertySize;

    private int stratum;

    private String description;

    private String zone;

    private List<Contest.Project.entities.PropertyImages> PropertyImages;

}
