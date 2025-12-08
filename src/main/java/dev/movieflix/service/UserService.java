package dev.movieflix.service;

import dev.movieflix.controller.request.RegisterUserRequest;
import dev.movieflix.entity.User;
import dev.movieflix.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    //TODO - cadastro de user
}
