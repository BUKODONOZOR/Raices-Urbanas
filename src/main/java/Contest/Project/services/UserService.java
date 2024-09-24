package Contest.Project.services;

import Contest.Project.dtos.UserDTO;
import Contest.Project.entities.Role;
import Contest.Project.entities.User;
import Contest.Project.repositories.RoleRepository;
import Contest.Project.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public User register(UserDTO userDTO) {
        if (userRepository.findByEmail(userDTO.getEmail()) != null) {
            throw new RuntimeException("El correo ya está en uso");
        }

        Role role = roleRepository.findByName(userDTO.getRoleName());
        if (role == null) {
            if (userDTO.getRoleName() == null) {
                throw new RuntimeException("El nombre del rol no puede ser nulo");
            }
            role = new Role();
            role.setName(userDTO.getRoleName());
            role = roleRepository.save(role);
        }

        User user = new User();
        user.setUserName(userDTO.getUserName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setIdRole(role);
        return userRepository.save(user);
    }

    public User authenticate(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return user;
        }
        return null;
    }

}
