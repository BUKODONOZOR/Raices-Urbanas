package Contest.Project.controllers;

import Contest.Project.entities.Property;
import Contest.Project.entities.PropertyImages;
import Contest.Project.services.PropertyImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/RaicesUrbanas/pictures")
public class PropertyImagesController {

    @Autowired
    PropertyImagesService propertyImagesService;


    @PostMapping("/upload")
    public ResponseEntity<?> SavePropertyImage(@RequestBody Property property, @RequestBody String url) {
        try {

            PropertyImages newPropertyImages = propertyImagesService.save(url, property);

            return new ResponseEntity<>( newPropertyImages , HttpStatus.OK);

        } catch (Exception e) {

            System.err.println("Error al procesar la URL: " + e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    public ResponseEntity<Object> deleteById(@PathVariable Integer id) {
        try {

            PropertyImages deletedImage = propertyImagesService.deleteById(id);
            return ResponseEntity.ok(deletedImage);
        } catch (IllegalArgumentException e) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("URL con ID " + id + " no existe.");
        }
    }


}
