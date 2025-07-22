package br.com.wsp.service.impl;

import br.com.wsp.dto.UserRequest;
import br.com.wsp.dto.UserResponse;
import br.com.wsp.entity.Role;
import br.com.wsp.entity.User;
import br.com.wsp.repository.RoleRepository;
import br.com.wsp.repository.UserRepository;
import br.com.wsp.service.IUserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.*;

@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public Optional<UserResponse> save(UserRequest userRequest) throws Exception {

        validateUserExist(userRequest.email());

        Role userRole = roleRepository.findByName(Role.Values.USER.name()).get();

        String username = Normalizer.normalize(userRequest.firstName().toLowerCase(Locale.ROOT), Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "") + "_" + UUID.randomUUID();

        User user = new User();
        user.setFirstName(userRequest.firstName());
        user.setLastName(userRequest.lastName());
        user.setUsername(username);
        user.setEmail(userRequest.email());
        user.setBirthdate(userRequest.birthdate());
        user.setPassword(bCryptPasswordEncoder.encode(userRequest.password()));
        user.setRoles(Set.of(userRole));

        User userSaved = userRepository.save(user);

        return Optional.of(new UserResponse(userSaved.getId(), userSaved.getFirstName(), userSaved.getLastName(), userSaved.getUsername(), userSaved.getEmail(), userSaved.getBirthdate()));

    }

    @Override
    public List<UserResponse> findAllUsers() {

        List<UserResponse> userResponseList = new ArrayList<>();

        userRepository.findAll()
                .forEach(user -> {
                    UserResponse response = new UserResponse(user.getId(), user.getFirstName(), user.getLastName(), user.getUsername(), user.getEmail(), user.getBirthdate());
                    userResponseList.add(response);
                });


        return userResponseList;
    }

    private void validateUserExist(String email) throws Exception {

        List<User> byEmail = userRepository.findByEmail(email);
        if (!byEmail.isEmpty()) {
            throw new Exception("Usuario possui cadatro");
        }


    }
}
