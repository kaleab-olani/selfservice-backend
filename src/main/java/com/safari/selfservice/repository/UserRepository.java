package com.safari.selfservice.repository;

import com.safari.selfservice.UserState;
import com.safari.selfservice.models.SystemUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.stream.Collectors;

public interface UserRepository extends JpaRepository<SystemUser, Integer> {
    SystemUser findByPhoneNumber(String phoneNumber);
    List<SystemUser> findByUserState(String userState);
    default List<SystemUser> findActiveUsers(){
        return findAll().
                stream().
                filter(systemUser ->
                        ! (systemUser.getUserState().equals(UserState.UN_REGISTERED.name())
                                || systemUser.getUserState().equals(UserState.NEW.name())) ).
                collect(Collectors.toList());
    };

}
