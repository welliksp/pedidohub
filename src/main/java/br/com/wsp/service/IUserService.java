package br.com.wsp.service;

import br.com.wsp.dto.UserRequest;
import br.com.wsp.dto.UserResponse;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    Optional<UserResponse> save(UserRequest userRequest) throws Exception;

    List<UserResponse> findAllUsers();

}
