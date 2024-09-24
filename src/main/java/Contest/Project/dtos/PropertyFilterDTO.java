package Contest.Project.dtos;

import lombok.Data;

@Data
public class PropertyFilterDTO {
    private Double minPrice;
    private Double maxPrice;
    private Integer minNumberOfBathrooms;
    private Integer maxNumberOfBathrooms;
    private Integer minPropertySize;
    private Integer maxPropertySize;
    private Integer minStratum;
    private Integer maxStratum;
    private Integer zoneId;
}
