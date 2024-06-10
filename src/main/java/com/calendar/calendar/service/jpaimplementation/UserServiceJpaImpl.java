package com.calendar.calendar.service.jpaimplementation;

import com.calendar.calendar.exception.EntityNotFoundException;
import com.calendar.calendar.model.User;
import com.calendar.calendar.repository.UserRepository;
import com.calendar.calendar.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceJpaImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()){
            throw new EntityNotFoundException("User non trovato con l'id: " + id);
        }
        return userOptional.get();
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User updateUserFromId(Long id, User user) {
        User userToUpdate = this.getUserById(id);
        BeanUtils.copyProperties(user,userToUpdate,"id","calendarioList");
        User userUpdated = userRepository.save(userToUpdate);
        return userUpdated;
    }

    @Override
    public void deleteUserFromId(Long id) {
        User user = this.getUserById(id);
        userRepository.delete(user);
    }
}
