package com.safari.selfservice.service;

import com.safari.selfservice.UserState;
import com.safari.selfservice.models.SystemUser;
import com.safari.selfservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<SystemUser> getAllUsers() {
        return userRepository.findActiveUsers();
    }

    public SystemUser createNewUser(SystemUser user) {
        if (user.getPhoneNumber() == null || user.getPhoneNumber().isBlank()){
            return null;
        }
        SystemUser existingUser = userRepository.findByPhoneNumber(user.getPhoneNumber());
        if (existingUser == null){
            user.setUserState(UserState.NEW.name());
            user.setNumOfMessagesLeft(10);
            return userRepository.save(user);
        } else {
            return existingUser;
        }
    }

    public SystemUser authenticate(String phoneNumber, String password) {
        SystemUser user = userRepository.findByPhoneNumber(phoneNumber);
        boolean authenticated = user != null && Objects.equals(user.getPassword(), password);
        if (authenticated){
            user.setPassword("");
            return user;
        }
        return null;
    }

    public SystemUser activate(Integer id) {
        Optional<SystemUser> user = userRepository.findById(id);
        if (user.isPresent()){
            SystemUser _user = user.get();
            if ( _user.getUserState().equals(UserState.NEW.name()) ||
                 _user.getUserState().equals(UserState.UN_REGISTERED.name())){

                _user.setUserState(UserState.ACTIVE.name());
                return userRepository.save(_user);
            } else if (_user.getUserState().equals(UserState.ACTIVE.name())){
                return userRepository.save(_user);
            }
        }
        return null;
    }

    public boolean canActivateUserWithId(Integer id) {
        return userRepository.findById(id).isPresent();
    }

    public SystemUser updateUser(SystemUser user) {
        Optional<SystemUser> _user = userRepository.findById(user.getId());
        if (_user.isPresent()){
            SystemUser existingUser = _user.get();

        }
        return null;
    }

    public Boolean deRegister(Integer user) {
        Optional<SystemUser> systemUser = userRepository.findById(user);
        if (systemUser.isPresent()){
            SystemUser user1 = systemUser.get();
            user1.setUserState(UserState.UN_REGISTERED.name());
            userRepository.save(user1);
            return true;
        }
        return false;
    }
}
