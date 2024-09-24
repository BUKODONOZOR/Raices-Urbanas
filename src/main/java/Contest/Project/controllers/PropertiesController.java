package Contest.Project.controllers;

import Contest.Project.dtos.PropertyDTO;
import Contest.Project.dtos.PropertyFilterDTO;
import Contest.Project.dtos.PropertyResponseDTO;
import Contest.Project.entities.Property;
import Contest.Project.services.PropertiesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/RaicesUrbanas/properties")
public class PropertiesController {

    @Autowired
    private PropertiesService propertiesService;


    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody PropertyDTO requestDTO) {
        try {
            Property createdProperty = propertiesService.create(requestDTO);
            return new ResponseEntity<>(propertiesService.createResponse(createdProperty), HttpStatus.CREATED);
        }
        catch (RuntimeException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Integer id) {
        propertiesService.delete(id);
    }

    @GetMapping("/readAll")
    public List<PropertyDTO> readAll() {
        List<PropertyDTO> propertyResponse = propertiesService.readAll();
        return ResponseEntity.ok(propertyResponse).getBody();
    }

    @GetMapping("/{id}")
    public PropertyDTO readById(@PathVariable Integer id) {
        return propertiesService.readById(id);
    }


    @PutMapping("/update/{id}")
    public Property update(@RequestBody PropertyDTO requestDTO, @PathVariable Integer id) {
        Property updateProperty = propertiesService.update(new PropertyDTO(),id);
        return ResponseEntity.ok(updateProperty).getBody();
    }

    @GetMapping("/filter")
    public List<PropertyResponseDTO> filterProperties(PropertyFilterDTO filter) {
        return propertiesService.filterProperties(filter);
    }

}
