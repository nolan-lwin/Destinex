package com.team08.csci205_final_project.repository;

import com.team08.csci205_final_project.model.Provider.Provider;
import com.team08.csci205_final_project.model.User.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface ProviderRepository extends MongoRepository<Provider, String> {
    @Query("{ 'email' : ?0 }")
    Optional<Provider> findByEmail(String email);
}
