package com.dipl.neuralphotos.service.transactional;

import com.dipl.neuralphotos.model.User;
import com.dipl.neuralphotos.repository.UserRepository;
import com.dipl.neuralphotos.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TransactionalUserService implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public TransactionalUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public List<com.dipl.neuralphotos.model.User> findAll(){
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public User findUserById(Long userId){
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isPresent()){
            return optionalUser.get();
        }else {
            throw new EntityNotFoundException("User with id "+userId+" not found");
        }
    }

    @Override
    @Transactional
    public User findUserByEmail(String email){
        return userRepository.findByEmail(email);
    }
}
