package Contest.Project.controllers;

import Contest.Project.dtos.LoginDTO;
import Contest.Project.dtos.UserDTO;
import Contest.Project.entities.User;
import Contest.Project.repositories.UserRepository;
import Contest.Project.security.JwtUtil;
import Contest.Project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/RaicesUrbanas")
@CrossOrigin(origins = "https://raices-urbanas-deploy-9w99.vercel.app" )
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @CrossOrigin(origins = "https://raices-urbanas-deploy-9w99.vercel.app" )
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDTO userDTO) {
        try {
            User newUser = userService.register(userDTO);
            return new ResponseEntity<>(newUser, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @CrossOrigin(origins = "https://raices-urbanas-deploy-9w99.vercel.app" )
    @PostMapping("/login")
    public String login(@RequestBody UserDTO userDTO) {
        User existingUser = userService.authenticate(userDTO.getEmail(), userDTO.getPassword());

        if (existingUser == null) {
            throw new RuntimeException("Invalid credentials");
        }

        return jwtUtil.generateToken(existingUser.getEmail());
    }

    /*
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        User user = userService.authenticate(loginDTO.getEmail(), loginDTO.getPassword());
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Contraseña incorrecta o usuario no encontrado", HttpStatus.UNAUTHORIZED);
        }
    }*/

}
