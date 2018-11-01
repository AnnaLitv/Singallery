package com.dipl.neuralphotos.service;

import com.dipl.neuralphotos.model.User;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public interface UserService {
    @Transactional
    List<User> findAll();

    @Transactional
    User findUserById(Long userId);

    @Transactional
    User findUserByEmail(String email);
}
