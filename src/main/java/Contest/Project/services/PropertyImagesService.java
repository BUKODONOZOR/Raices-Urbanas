package Contest.Project.services;

import Contest.Project.entities.Property;
import Contest.Project.entities.PropertyImages;
import Contest.Project.repositories.PropertyImagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PropertyImagesService {

    @Autowired
    private PropertyImagesRepository propertyImagesRepository;

    public PropertyImages save(String url, Property property) {
        PropertyImages propertyImages = PropertyImages.builder()
                .url(url)
                .id_property(property)
                .build();

        return propertyImagesRepository.save(propertyImages);
    }

    public PropertyImages deleteById(Integer id) {
        if (propertyImagesRepository.existsById(id)) {
            PropertyImages register = propertyImagesRepository.findById(id).orElse(null);

            propertyImagesRepository.deleteById(id);

            return register;
        } else {
            throw new IllegalArgumentException("URL con ID " + id + " no existe");
        }
    }

}
