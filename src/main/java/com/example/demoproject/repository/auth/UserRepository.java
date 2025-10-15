package com.example.demoproject.repository.auth;

import com.example.demoproject.criteria.auth.UserCriteria;
import com.example.demoproject.criteria.main.CategoryCriteria;
import com.example.demoproject.entity.auth.User;
import com.example.demoproject.repository.GenericCrudRepository;

public interface UserRepository extends GenericCrudRepository<User, Long, UserCriteria> {
}
