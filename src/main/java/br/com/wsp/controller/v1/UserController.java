package br.com.wsp.controller.v1;

import br.com.wsp.dto.UserRequest;
import br.com.wsp.dto.UserResponse;
import br.com.wsp.service.IUserService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/user")
public class UserController {


    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Optional<UserResponse>> save(@RequestBody @Valid UserRequest userRequest) throws Exception {

        Optional<UserResponse> userSaved = userService.save(userRequest);

        return ResponseEntity.ok().body(userSaved);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<List<UserResponse>> findAllUsers() {

        return ResponseEntity.ok(userService.findAllUsers());

    }

}
