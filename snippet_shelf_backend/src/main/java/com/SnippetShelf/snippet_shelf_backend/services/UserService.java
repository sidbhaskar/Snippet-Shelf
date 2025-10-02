package com.SnippetShelf.snippet_shelf_backend.services;

import com.SnippetShelf.snippet_shelf_backend.models.User;
import com.SnippetShelf.snippet_shelf_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public User findUser(String username) {
        return userRepository.findByUsername(username).orElseThrow();
    }
}
