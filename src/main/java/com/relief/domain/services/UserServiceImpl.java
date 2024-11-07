package com.relief.domain.services;

import com.relief.domain.models.Users;
import com.relief.domain.services.interfaces.AbstractBaseService;
import com.relief.domain.services.interfaces.UserService;
import com.relief.infrastructure.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl extends AbstractBaseService<Users, UUID> implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    protected JpaRepository<Users, UUID> getRepository() {
        return this.userRepository;
    }

    @Override
    public Users getCurrentUser() {
        return null;
    }
}
