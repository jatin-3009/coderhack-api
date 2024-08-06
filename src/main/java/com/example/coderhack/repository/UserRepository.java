package com.example.coderhack.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.coderhack.entity.UserEntity;

@Repository
public interface UserRepository extends MongoRepository<UserEntity, String> {
    
}
