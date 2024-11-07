package com.relief.domain.services.interfaces;


import com.relief.domain.models.Users;

import java.util.UUID;

public interface UserService extends BaseService<Users, UUID> {
    Users getCurrentUser();
}
